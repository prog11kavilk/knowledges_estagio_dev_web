import { api, contaEmCache, exigirLogin, irParaLogin, salvarSessao } from "./sessao.js";
import { aplicarPreferencias, comPadrao } from "./preferencias.js";

let conta = contaEmCache();
let preferencias = aplicarPreferencias(conta?.preferencias);

// ---------- Dados da conta na tela ----------

function preencherAvatar(elemento, dadosConta) {
    elemento.replaceChildren();
    if (dadosConta?.foto) {
        const img = document.createElement('img');
        img.src = dadosConta.foto;
        img.alt = '';
        elemento.appendChild(img);
    } else {
        elemento.textContent = (dadosConta?.nomeUsuario || '?').charAt(0).toUpperCase();
    }
}

function mostrarConta(dadosConta) {
    if (!dadosConta) {
        return;
    }
    document.querySelectorAll('[data-nome-usuario]').forEach(el => el.textContent = dadosConta.nomeUsuario);
    document.querySelectorAll('[data-email-usuario]').forEach(el => el.textContent = dadosConta.email);
    document.querySelectorAll('[data-avatar]').forEach(el => preencherAvatar(el, dadosConta));
    if (dadosConta.criadoEm) {
        const desde = new Date(dadosConta.criadoEm).toLocaleDateString('pt-BR', { month: 'long', year: 'numeric' });
        document.querySelector('[data-membro-desde]').textContent = `Membro desde ${desde}`;
    }
}

function atualizarConta(novaConta) {
    conta = novaConta;
    salvarSessao(null, conta);
    mostrarConta(conta);
}

mostrarConta(conta);
exigirLogin().then(contaAtual => {
    if (contaAtual) {
        atualizarConta(contaAtual);
        preferencias = aplicarPreferencias(contaAtual.preferencias);
        carregarPreferenciasNoFormulario();
    }
});

// ---------- Menu da conta ----------

const botaoConta = document.querySelector('[data-conta-botao]');
const menuConta = document.querySelector('[data-conta-menu]');
const itensMenu = () => [...menuConta.querySelectorAll('[role="menuitem"]')];

function abrirMenu() {
    menuConta.hidden = false;
    botaoConta.setAttribute('aria-expanded', 'true');
    itensMenu()[0].focus();
}

function fecharMenu(devolverFoco = false) {
    menuConta.hidden = true;
    botaoConta.setAttribute('aria-expanded', 'false');
    if (devolverFoco) {
        botaoConta.focus();
    }
}

botaoConta.addEventListener('click', () => menuConta.hidden ? abrirMenu() : fecharMenu());

document.addEventListener('click', evento => {
    if (!menuConta.hidden && !evento.target.closest('[data-conta]')) {
        fecharMenu();
    }
});

// Setas navegam entre as opções, Esc fecha
menuConta.addEventListener('keydown', evento => {
    const itens = itensMenu();
    const atual = itens.indexOf(document.activeElement);
    if (evento.key === 'ArrowDown') {
        evento.preventDefault();
        itens[(atual + 1) % itens.length].focus();
    } else if (evento.key === 'ArrowUp') {
        evento.preventDefault();
        itens[(atual - 1 + itens.length) % itens.length].focus();
    } else if (evento.key === 'Escape') {
        fecharMenu(true);
    }
});

// ---------- Janelas (dialog) ----------

function avisar(dialogo, mensagem, tipo = 'erro') {
    const aviso = dialogo.querySelector('[data-aviso]');
    aviso.textContent = mensagem;
    aviso.classList.toggle('aviso--ok', tipo === 'ok');
    aviso.hidden = !mensagem;
}

document.querySelectorAll('[data-abrir]').forEach(botao => {
    botao.addEventListener('click', () => {
        fecharMenu();
        const dialogo = document.getElementById(botao.dataset.abrir);
        if (!dialogo.open) {
            dialogo.showModal();
        }
    });
});

document.querySelectorAll('[data-fechar]').forEach(botao => {
    botao.addEventListener('click', () => botao.closest('dialog').close());
});

// Clique no fundo escuro fecha a janela
document.querySelectorAll('dialog').forEach(dialogo => {
    dialogo.addEventListener('click', evento => {
        if (evento.target === dialogo) {
            dialogo.close();
        }
    });
    dialogo.addEventListener('close', () => {
        dialogo.querySelector('form')?.reset();
        if (dialogo.querySelector('[data-aviso]')) {
            avisar(dialogo, '');
        }
    });
});

async function comBotaoOcupado(formulario, acao) {
    const botao = formulario.querySelector('button[type="submit"]');
    botao.disabled = true;
    try {
        await acao();
    } catch (erro) {
        if (erro.status === 401) {
            irParaLogin();
            return;
        }
        avisar(formulario.closest('dialog'), erro.message);
    } finally {
        botao.disabled = false;
    }
}

function fecharDepois(dialogo) {
    setTimeout(() => dialogo.close(), 1200);
}

// ---------- Editar senha ----------

const formSenha = document.querySelector('[data-form-senha]');
formSenha.addEventListener('submit', evento => {
    evento.preventDefault();
    const { senhaAtual, novaSenha, confirmacao } = Object.fromEntries(new FormData(formSenha));
    comBotaoOcupado(formSenha, async () => {
        if (novaSenha !== confirmacao) {
            throw new Error('As senhas novas não conferem.');
        }
        await api('/conta/senha', { metodo: 'PUT', corpo: { senhaAtual, novaSenha } });
        avisar(formSenha.closest('dialog'), 'Senha alterada com sucesso.', 'ok');
        fecharDepois(formSenha.closest('dialog'));
    });
});

// ---------- Escolher foto ----------

const formFoto = document.querySelector('[data-form-foto]');
const dialogoFoto = document.getElementById('dialogo-foto');
const previaFoto = document.querySelector('[data-foto-previa]');
let fotoEscolhida;

dialogoFoto.addEventListener('close', () => fotoEscolhida = undefined);
document.querySelectorAll('[data-abrir="dialogo-foto"]').forEach(botao =>
    botao.addEventListener('click', () => preencherAvatar(previaFoto, conta)));

// Recorta no centro e reduz para 256x256 antes de enviar (a foto fica leve no banco)
function reduzirImagem(arquivo) {
    return new Promise((resolve, reject) => {
        const img = new Image();
        img.onload = () => {
            const lado = Math.min(img.width, img.height);
            const canvas = document.createElement('canvas');
            canvas.width = canvas.height = 256;
            canvas.getContext('2d').drawImage(img,
                (img.width - lado) / 2, (img.height - lado) / 2, lado, lado, 0, 0, 256, 256);
            URL.revokeObjectURL(img.src);
            resolve(canvas.toDataURL('image/jpeg', 0.85));
        };
        img.onerror = () => reject(new Error('Não foi possível ler essa imagem.'));
        img.src = URL.createObjectURL(arquivo);
    });
}

document.querySelector('[data-foto-arquivo]').addEventListener('change', async evento => {
    const arquivo = evento.target.files[0];
    if (!arquivo) {
        return;
    }
    try {
        fotoEscolhida = await reduzirImagem(arquivo);
        preencherAvatar(previaFoto, { ...conta, foto: fotoEscolhida });
        avisar(dialogoFoto, '');
    } catch (erro) {
        avisar(dialogoFoto, erro.message);
    }
});

document.querySelector('[data-foto-remover]').addEventListener('click', () => {
    fotoEscolhida = '';
    preencherAvatar(previaFoto, { ...conta, foto: null });
});

formFoto.addEventListener('submit', evento => {
    evento.preventDefault();
    comBotaoOcupado(formFoto, async () => {
        if (fotoEscolhida === undefined) {
            throw new Error('Escolha uma imagem primeiro.');
        }
        atualizarConta(await api('/conta/foto', { metodo: 'PUT', corpo: { foto: fotoEscolhida } }));
        avisar(dialogoFoto, fotoEscolhida ? 'Foto atualizada.' : 'Foto removida.', 'ok');
        fecharDepois(dialogoFoto);
    });
});

// ---------- Redefinir senha (código no email) ----------

const formRedefinir = document.querySelector('[data-form-redefinir]');
const dialogoRedefinir = document.getElementById('dialogo-redefinir');
const botaoRedefinir = document.querySelector('[data-redefinir-botao]');
const passo = numero => formRedefinir.querySelector(`[data-redefinir-passo="${numero}"]`);

function irParaPasso(numero) {
    passo(1).hidden = numero !== 1;
    passo(2).hidden = numero !== 2;
    botaoRedefinir.textContent = numero === 1 ? 'Enviar código' : 'Salvar nova senha';
    if (numero === 2) {
        passo(2).querySelector('input').focus();
    }
}

dialogoRedefinir.addEventListener('close', () => irParaPasso(1));

formRedefinir.querySelector('.campo__codigo').addEventListener('input', evento => {
    evento.target.value = evento.target.value.replace(/\D/g, '').slice(0, 6);
});

formRedefinir.addEventListener('submit', evento => {
    evento.preventDefault();
    comBotaoOcupado(formRedefinir, async () => {
        if (!passo(1).hidden) {
            await api('/auth/esqueci-senha', { metodo: 'POST', corpo: { email: conta.email } });
            irParaPasso(2);
            avisar(dialogoRedefinir, `Código enviado para ${conta.email}.`, 'ok');
            return;
        }
        const { codigo, novaSenha, confirmacao } = Object.fromEntries(new FormData(formRedefinir));
        if (novaSenha !== confirmacao) {
            throw new Error('As senhas não conferem.');
        }
        const sessao = await api('/auth/redefinir-senha', {
            metodo: 'POST',
            corpo: { email: conta.email, codigo, novaSenha }
        });
        salvarSessao(sessao.token, sessao.conta);
        avisar(dialogoRedefinir, 'Senha redefinida. As outras sessões foram encerradas.', 'ok');
        fecharDepois(dialogoRedefinir);
    });
});

// ---------- Configurações ----------

const status = document.querySelector('[data-config-status]');
let esperaSalvar;

document.querySelectorAll('[data-config-aba]').forEach(aba => {
    aba.addEventListener('click', () => {
        document.querySelectorAll('[data-config-aba]').forEach(outra =>
            outra.setAttribute('aria-selected', String(outra === aba)));
        document.querySelectorAll('[data-config-painel]').forEach(painel =>
            painel.hidden = painel.dataset.configPainel !== aba.dataset.configAba);
    });
});

function carregarPreferenciasNoFormulario() {
    document.querySelectorAll('[data-pref]').forEach(campo => {
        const valor = preferencias[campo.dataset.pref];
        if (campo.type === 'checkbox') {
            campo.checked = Boolean(valor);
        } else {
            campo.value = valor;
        }
    });
}

carregarPreferenciasNoFormulario();

// Cada mudança já vale na tela; o salvamento na conta espera meio segundo para agrupar cliques
document.querySelectorAll('[data-pref]').forEach(campo => {
    campo.addEventListener('change', () => {
        preferencias = comPadrao({
            ...preferencias,
            [campo.dataset.pref]: campo.type === 'checkbox' ? campo.checked : campo.value
        });
        aplicarPreferencias(preferencias);
        status.textContent = 'Salvando…';
        clearTimeout(esperaSalvar);
        esperaSalvar = setTimeout(async () => {
            try {
                atualizarConta(await api('/conta/preferencias', { metodo: 'PUT', corpo: preferencias }));
                status.textContent = 'Salvo na sua conta ✓';
            } catch (erro) {
                status.textContent = `Não foi possível salvar: ${erro.message}`;
            }
        }, 500);
    });
});

// ---------- Sair ----------

document.querySelector('[data-sair]').addEventListener('click', async () => {
    try {
        await api('/auth/logout', { metodo: 'POST' });
    } catch (erro) {
        // mesmo se o servidor falhar, a sessão local é apagada
    }
    irParaLogin();
});

// Pede um segundo clique para confirmar, sem usar o confirm() do navegador
const botaoSairTodos = document.querySelector('[data-sair-todos]');
let confirmarSaida;
botaoSairTodos.addEventListener('click', async () => {
    if (!botaoSairTodos.dataset.confirmando) {
        botaoSairTodos.dataset.confirmando = 'sim';
        botaoSairTodos.textContent = 'Clique de novo para confirmar';
        confirmarSaida = setTimeout(() => {
            delete botaoSairTodos.dataset.confirmando;
            botaoSairTodos.textContent = 'Sair de todos';
        }, 4000);
        return;
    }
    clearTimeout(confirmarSaida);
    try {
        await api('/conta/sessoes', { metodo: 'DELETE' });
    } catch (erro) {
        // a sessão local é apagada de qualquer forma
    }
    irParaLogin();
});
