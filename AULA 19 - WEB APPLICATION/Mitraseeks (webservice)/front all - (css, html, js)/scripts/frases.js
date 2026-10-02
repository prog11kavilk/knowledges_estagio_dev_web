import getDados from "./getDados.js";

const params = new URLSearchParams(window.location.search);
const fichaDescricao = document.getElementById('ficha-descricao');
const botaoSortear = document.querySelector('[data-sortear]');
let fraseAtual = null;

function escaparHtml(texto) {
    return String(texto ?? '').replace(/[&<>"']/g, c => ({
        '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    })[c]);
}

// Fundo em gradiente com as cores predominantes do cartaz (calculadas no back);
// sem cores, fica o gradiente padrão do detalhes.css
function aplicarCores(cores) {
    const corpo = document.body;
    ['--cor-1', '--cor-2', '--cor-3'].forEach(v => corpo.style.removeProperty(v));
    if (!cores || cores.length === 0) {
        corpo.classList.remove('frase-colorida');
        return;
    }
    corpo.style.setProperty('--cor-1', cores[0]);
    corpo.style.setProperty('--cor-2', cores[1] ?? cores[0]);
    corpo.style.setProperty('--cor-3', cores[2] ?? cores[1] ?? cores[0]);
    corpo.classList.add('frase-colorida');
}

function mostrarFrase(data) {
    if (!data || !data.frase) {
        fichaDescricao.innerHTML = `<p>Nenhuma frase encontrada por aqui ainda.</p>`;
        aplicarCores(null);
        return;
    }
    fraseAtual = data;
    const pagina = data.tipo === 'filme' ? 'filme.html' : 'detalhes.html';
    const cartaz = data.poster
        ? `<img src="${escaparHtml(data.poster)}" alt="${escaparHtml(data.titulo)}" />`
        : `<span class="frase-sem-poster">${escaparHtml(data.titulo)}</span>`;
    const link = data.tituloId
        ? `<a class="frase-link" href="${pagina}?id=${data.tituloId}">Ver ${data.tipo === 'filme' ? 'filme' : 'série'}</a>`
        : '';

    fichaDescricao.innerHTML = `
        ${cartaz}
        <div>
            <h2>${escaparHtml(data.titulo)}</h2>
            <div class="descricao-texto">
                <p class="frase-texto"><i>"${escaparHtml(data.frase)}"</i></p>
                <p><b>Citado por:</b> ${escaparHtml(data.personagem)}</p>
                ${link}
            </div>
        </div>
    `;
    aplicarCores(data.cores);
    // Guarda a frase na URL: recarregar ou compartilhar mostra a mesma
    history.replaceState(null, '', `?id=${data.id}`);
}

function carregarFrase(endpoint) {
    botaoSortear.disabled = true;
    getDados(endpoint)
        .then(mostrarFrase)
        .catch(error => {
            console.error('Erro ao obter a frase:', error);
        })
        .finally(() => {
            botaoSortear.disabled = false;
        });
}

botaoSortear.addEventListener('click', () => {
    carregarFrase(`/frases/aleatoria?exceto=${fraseAtual?.id ?? 0}`);
});

const fraseId = params.get('id');
carregarFrase(fraseId ? `/frases/${encodeURIComponent(fraseId)}` : '/frases/aleatoria');
