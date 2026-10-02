// Endereço da API quando o front estiver publicado em outro domínio (ex.: Vercel).
// Preencha com o domínio onde o back estiver hospedado, ex.: 'https://api.meudominio.com'
const API_PRODUCAO = '';

// URL base da API:
//  - aberto pelo próprio back (http://localhost:8080): mesma origem, caminho relativo
//  - Live Server ou arquivo local (localhost:5500...): back local na 8080
//  - publicado (Vercel): API_PRODUCAO
function descobrirBaseURL() {
    const { hostname, port, protocol } = window.location;
    const local = protocol === 'file:' || ['localhost', '127.0.0.1'].includes(hostname);
    if (local) {
        return port === '8080' ? '' : 'http://localhost:8080';
    }
    return API_PRODUCAO;
}

export const baseURL = descobrirBaseURL();

// Enquanto o back estiver desligado ou subindo (o primeiro start leva uns 20 s),
// as chamadas esperam e tentam de novo sozinhas, e a página carrega assim que ele responde
const INTERVALO_RECONEXAO = 2000; // ms
const TEMPO_MAXIMO_RECONEXAO = 120000; // ms

let aviso = null;
let esperando = 0;

function mostrarAviso() {
    esperando++;
    if (aviso || !document.body) {
        return;
    }
    aviso = document.createElement('div');
    aviso.setAttribute('role', 'status');
    aviso.textContent = 'Conectando ao servidor do MitraSeeks...';
    aviso.style.cssText = 'position:fixed;left:50%;bottom:24px;transform:translateX(-50%);z-index:9999;'
        + 'padding:10px 18px;border-radius:8px;background:#0b3d91;color:#fff;'
        + 'font:14px sans-serif;box-shadow:0 4px 16px rgba(0,0,0,.35)';
    document.body.appendChild(aviso);
}

function esconderAviso() {
    esperando = Math.max(0, esperando - 1);
    if (esperando === 0 && aviso) {
        aviso.remove();
        aviso = null;
    }
}

// fetch que só lança erro de rede depois de esgotar o tempo de reconexão
export async function buscar(endpoint, opcoes) {
    const inicio = Date.now();
    let avisou = false;
    try {
        while (true) {
            try {
                return await fetch(`${baseURL}${endpoint}`, opcoes);
            } catch (erro) {
                if (Date.now() - inicio >= TEMPO_MAXIMO_RECONEXAO) {
                    throw erro;
                }
                if (!avisou) {
                    avisou = true;
                    mostrarAviso();
                }
                await new Promise(resolve => setTimeout(resolve, INTERVALO_RECONEXAO));
            }
        }
    } finally {
        if (avisou) {
            esconderAviso();
        }
    }
}

export default function getDados(endpoint) {
    return buscar(endpoint)
        .then(response => response.json())
        .catch(error => {
            console.error(`Erro ao acessar o endpoint ${endpoint}:`, error);
        });
}
