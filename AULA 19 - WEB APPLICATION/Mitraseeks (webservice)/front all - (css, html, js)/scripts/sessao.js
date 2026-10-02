import { buscar } from "./getDados.js";

// Sessão do usuário guardada no navegador (token recebido no login + dados da conta)
const CHAVE_TOKEN = 'mitraseeks.token';
const CHAVE_CONTA = 'mitraseeks.conta';

function ler(chave) {
    try {
        return localStorage.getItem(chave);
    } catch (e) {
        return null;
    }
}

function gravar(chave, valor) {
    try {
        if (valor === null) {
            localStorage.removeItem(chave);
        } else {
            localStorage.setItem(chave, valor);
        }
    } catch (e) {
        // navegador sem localStorage: a sessão dura só enquanto a página estiver aberta
    }
}

export function obterToken() {
    return ler(CHAVE_TOKEN);
}

export function contaEmCache() {
    try {
        return JSON.parse(ler(CHAVE_CONTA));
    } catch (e) {
        return null;
    }
}

export function salvarSessao(token, conta) {
    if (token) {
        gravar(CHAVE_TOKEN, token);
    }
    if (conta) {
        gravar(CHAVE_CONTA, JSON.stringify(conta));
    }
}

export function limparSessao() {
    gravar(CHAVE_TOKEN, null);
    gravar(CHAVE_CONTA, null);
}

export function irParaLogin() {
    limparSessao();
    window.location.replace('login.html');
}

// Chamada à API com JSON; manda o token quando existe e lança um Error com a mensagem do back
export async function api(endpoint, { metodo = 'GET', corpo } = {}) {
    const cabecalhos = { 'Content-Type': 'application/json' };
    const token = obterToken();
    if (token) {
        cabecalhos.Authorization = `Bearer ${token}`;
    }

    let resposta;
    try {
        resposta = await buscar(endpoint, {
            method: metodo,
            headers: cabecalhos,
            body: corpo === undefined ? undefined : JSON.stringify(corpo)
        });
    } catch (e) {
        throw new Error('Não foi possível falar com o servidor. Verifique se o back está rodando.');
    }

    const texto = await resposta.text();
    const dados = texto ? JSON.parse(texto) : null;

    if (!resposta.ok) {
        const erro = new Error(dados?.detail || 'Algo deu errado. Tente novamente.');
        erro.status = resposta.status;
        erro.dados = dados;
        throw erro;
    }
    return dados;
}

// Páginas internas: sem sessão válida, volta para a tela de login
export async function exigirLogin() {
    if (!obterToken()) {
        irParaLogin();
        return null;
    }
    try {
        const conta = await api('/conta');
        salvarSessao(null, conta);
        return conta;
    } catch (erro) {
        if (erro.status === 401) {
            irParaLogin();
            return null;
        }
        // servidor fora do ar: segue com a conta em cache
        return contaEmCache();
    }
}
