import { api, obterToken, salvarSessao } from "./sessao.js";

// Já tem sessão: vai direto para o app
if (obterToken()) {
    window.location.replace('index.html');
}

const etapas = document.querySelectorAll('[data-etapa]');
const abas = document.querySelectorAll('.abas__aba');
const aviso = document.querySelector('[data-aviso]');
let emailPendente = '';

function mostrarEtapa(nome) {
    etapas.forEach(etapa => etapa.hidden = etapa.dataset.etapa !== nome);
    abas.forEach(aba => aba.setAttribute('aria-selected', String(aba.dataset.ir === nome)));
    document.querySelector('[data-abas]').hidden = !['entrar', 'cadastro'].includes(nome);
    document.querySelectorAll('[data-email-destino]').forEach(el => el.textContent = emailPendente);
    esconderAviso();
    const primeiroCampo = document.querySelector(`[data-etapa="${nome}"] input`);
    if (primeiroCampo) {
        primeiroCampo.focus();
    }
}

function mostrarAviso(mensagem, tipo = 'erro') {
    aviso.textContent = mensagem;
    aviso.classList.toggle('aviso--ok', tipo === 'ok');
    aviso.hidden = false;
}

function esconderAviso() {
    aviso.hidden = true;
}

function entrarNoApp(sessao) {
    salvarSessao(sessao.token, sessao.conta);
    window.location.replace('index.html');
}

// Desabilita o botão enquanto a requisição roda e mostra o erro do back, se houver
async function enviar(formulario, acao) {
    const botao = formulario.querySelector('button[type="submit"]');
    botao.disabled = true;
    esconderAviso();
    try {
        await acao(Object.fromEntries(new FormData(formulario)));
    } catch (erro) {
        mostrarAviso(erro.message);
    } finally {
        botao.disabled = false;
    }
}

function validarSenhas(senha, confirmacao) {
    if (senha !== confirmacao) {
        throw new Error('As senhas não conferem.');
    }
}

// Navegação entre as etapas
document.querySelectorAll('[data-ir]').forEach(botao => {
    botao.addEventListener('click', () => mostrarEtapa(botao.dataset.ir));
});

// Mostrar/ocultar senha
document.querySelectorAll('[data-mostrar-senha]').forEach(botao => {
    botao.addEventListener('click', () => {
        const campo = botao.parentElement.querySelector('input');
        const visivel = campo.type === 'text';
        campo.type = visivel ? 'password' : 'text';
        botao.querySelector('span').textContent = visivel ? 'visibility' : 'visibility_off';
        botao.setAttribute('aria-label', visivel ? 'Mostrar senha' : 'Ocultar senha');
    });
});

// Código: aceita só números
document.querySelectorAll('.campo__codigo').forEach(campo => {
    campo.addEventListener('input', () => campo.value = campo.value.replace(/\D/g, '').slice(0, 6));
});

document.querySelector('[data-etapa="entrar"]').addEventListener('submit', evento => {
    evento.preventDefault();
    enviar(evento.target, async ({ login, senha }) => {
        try {
            entrarNoApp(await api('/auth/login', { metodo: 'POST', corpo: { login, senha } }));
        } catch (erro) {
            // Senha certa mas email ainda não confirmado: o back mandou um código novo
            if (erro.status === 403 && erro.dados?.email) {
                emailPendente = erro.dados.email;
                mostrarEtapa('codigo');
                mostrarAviso('Seu email ainda não foi confirmado. Enviamos um novo código.', 'ok');
                return;
            }
            throw erro;
        }
    });
});

document.querySelector('[data-etapa="cadastro"]').addEventListener('submit', evento => {
    evento.preventDefault();
    enviar(evento.target, async ({ nomeUsuario, email, senha, confirmacao }) => {
        validarSenhas(senha, confirmacao);
        const resposta = await api('/auth/cadastro', { metodo: 'POST', corpo: { nomeUsuario, email, senha } });
        emailPendente = email.trim().toLowerCase();
        mostrarEtapa('codigo');
        mostrarAviso(resposta.mensagem, 'ok');
    });
});

document.querySelector('[data-etapa="codigo"]').addEventListener('submit', evento => {
    evento.preventDefault();
    enviar(evento.target, async ({ codigo }) => {
        entrarNoApp(await api('/auth/confirmar', { metodo: 'POST', corpo: { email: emailPendente, codigo } }));
    });
});

document.querySelector('[data-reenviar]').addEventListener('click', async () => {
    try {
        await api('/auth/reenviar-codigo', { metodo: 'POST', corpo: { email: emailPendente } });
        mostrarAviso('Código reenviado. Confira sua caixa de entrada e o spam.', 'ok');
    } catch (erro) {
        mostrarAviso(erro.message);
    }
});

document.querySelector('[data-etapa="esqueci"]').addEventListener('submit', evento => {
    evento.preventDefault();
    enviar(evento.target, async ({ email }) => {
        const resposta = await api('/auth/esqueci-senha', { metodo: 'POST', corpo: { email } });
        emailPendente = email.trim().toLowerCase();
        mostrarEtapa('nova-senha');
        mostrarAviso(resposta.mensagem, 'ok');
    });
});

document.querySelector('[data-etapa="nova-senha"]').addEventListener('submit', evento => {
    evento.preventDefault();
    enviar(evento.target, async ({ codigo, novaSenha, confirmacao }) => {
        validarSenhas(novaSenha, confirmacao);
        entrarNoApp(await api('/auth/redefinir-senha', {
            metodo: 'POST',
            corpo: { email: emailPendente, codigo, novaSenha }
        }));
    });
});

mostrarEtapa('entrar');
