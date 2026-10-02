package br.com.alura.screenmatch.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

/**
 * Converte as exceções do Spring, do Hibernate/JPA e da aplicação em respostas JSON
 * no padrão ProblemDetail (RFC 7807), em vez de páginas de erro ou stack traces.
 */
@RestControllerAdvice
public class TratadorDeErros {

    private static final Logger log = LoggerFactory.getLogger(TratadorDeErros.class);

    // ---------- 4xx: erros de quem fez a requisição ----------

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ProblemDetail tratarNaoEncontrado(RuntimeException ex) {
        return erro(HttpStatus.NOT_FOUND, "Recurso não encontrado",
                ex.getMessage() != null ? ex.getMessage() : "Registro não encontrado");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail tratarRotaInexistente(NoHandlerFoundException ex) {
        return erro(HttpStatus.NOT_FOUND, "Endpoint não encontrado",
                "Não existe endpoint para " + ex.getHttpMethod() + " " + ex.getRequestURL());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail tratarTipoInvalido(MethodArgumentTypeMismatchException ex) {
        return erro(HttpStatus.BAD_REQUEST, "Parâmetro inválido",
                "O valor '" + ex.getValue() + "' não é válido para o parâmetro '" + ex.getName() + "'");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail tratarParametroAusente(MissingServletRequestParameterException ex) {
        return erro(HttpStatus.BAD_REQUEST, "Parâmetro obrigatório ausente",
                "Informe o parâmetro '" + ex.getParameterName() + "'");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail tratarArgumentoInvalido(IllegalArgumentException ex) {
        return erro(HttpStatus.BAD_REQUEST, "Requisição inválida", ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail tratarMetodoNaoSuportado(HttpRequestMethodNotSupportedException ex) {
        return erro(HttpStatus.METHOD_NOT_ALLOWED, "Método não permitido",
                "O método " + ex.getMethod() + " não é suportado neste endpoint");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail tratarViolacaoIntegridade(DataIntegrityViolationException ex) {
        log.warn("Violação de integridade no banco: {}", ex.getMostSpecificCause().getMessage());
        return erro(HttpStatus.CONFLICT, "Conflito de dados",
                "A operação viola uma restrição do banco de dados");
    }

    // ---------- 5xx: erros do servidor / banco ----------

    @ExceptionHandler({CannotCreateTransactionException.class, DataAccessResourceFailureException.class})
    public ProblemDetail tratarBancoIndisponivel(RuntimeException ex) {
        log.error("Banco de dados indisponível", ex);
        return erro(HttpStatus.SERVICE_UNAVAILABLE, "Banco de dados indisponível",
                "Não foi possível conectar ao banco de dados. Tente novamente mais tarde");
    }

    @ExceptionHandler({DataAccessException.class, PersistenceException.class, HibernateException.class})
    public ProblemDetail tratarErroDeBanco(RuntimeException ex) {
        log.error("Erro de acesso ao banco de dados", ex);
        return erro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no banco de dados",
                "Ocorreu um erro ao acessar os dados");
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail tratarErroInesperado(Exception ex) {
        log.error("Erro inesperado", ex);
        return erro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno",
                "Ocorreu um erro inesperado no servidor");
    }

    private ProblemDetail erro(HttpStatus status, String titulo, String detalhe) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(status, detalhe);
        problema.setTitle(titulo);
        return problema;
    }
}
