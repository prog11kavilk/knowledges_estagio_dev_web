package br.com.alura.mitraSeeks.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    // Para que serve o código enviado por email no momento
    public enum FinalidadeCodigo { CONFIRMACAO, REDEFINICAO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Coluna mantida como nome_usuario para não perder os usuários já cadastrados
    @Column(name = "nome_usuario", unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String senhaHash;
    private boolean emailConfirmado;
    // Conta de administrador: criada pelo AdminInicializador a partir do .env, sem cadastro
    @Column(nullable = false, columnDefinition = "boolean not null default false")
    private boolean admin;

    private String codigo;
    @Enumerated(EnumType.STRING)
    private FinalidadeCodigo finalidadeCodigo;
    private LocalDateTime codigoExpiraEm;
    private int tentativasCodigo;

    @Column(columnDefinition = "TEXT")
    private String foto;
    @Column(columnDefinition = "TEXT")
    private String preferencias;
    private LocalDateTime criadoEm;

    public Usuario() {}

    public Usuario(String nomeUsuario, String email, String senhaHash) {
        this.userName = nomeUsuario;
        this.email = email;
        this.senhaHash = senhaHash;
        this.criadoEm = LocalDateTime.now();
    }

    public void definirCodigo(String codigo, FinalidadeCodigo finalidade, LocalDateTime expiraEm) {
        this.codigo = codigo;
        this.finalidadeCodigo = finalidade;
        this.codigoExpiraEm = expiraEm;
        this.tentativasCodigo = 0;
    }

    public void limparCodigo() {
        this.codigo = null;
        this.finalidadeCodigo = null;
        this.codigoExpiraEm = null;
        this.tentativasCodigo = 0;
    }

    public void registrarTentativaCodigo() {
        this.tentativasCodigo++;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String nomeUsuario) {
        this.userName = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public boolean isEmailConfirmado() {
        return emailConfirmado;
    }

    public void setEmailConfirmado(boolean emailConfirmado) {
        this.emailConfirmado = emailConfirmado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCodigo() {
        return codigo;
    }

    public FinalidadeCodigo getFinalidadeCodigo() {
        return finalidadeCodigo;
    }

    public LocalDateTime getCodigoExpiraEm() {
        return codigoExpiraEm;
    }

    public int getTentativasCodigo() {
        return tentativasCodigo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
