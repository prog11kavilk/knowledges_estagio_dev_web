import getDados from "./getDados.js";

// Mapeia os elementos DOM que você deseja atualizar
const elementos = {
    top5: document.querySelector('[data-name="top5"]'),
    lancamentos: document.querySelector('[data-name="lancamentos"]'),
    series: document.querySelector('[data-name="series"]'),
    filmes: document.querySelector('[data-name="filmes"]'),
    pesquisa: document.querySelector('[data-name="categoria"]')
};

const sectionsParaOcultar = document.querySelectorAll('.section');
const categoriaSelect = document.querySelector('[data-categorias]');
const tituloPesquisa = document.querySelector('[data-titulo-pesquisa]');
const pesquisaVazia = document.querySelector('[data-pesquisa-vazio]');
const busca = document.querySelector('[data-busca]');
const campoBusca = document.querySelector('[data-busca-campo]');

// Função genérica para tratamento de erros
function lidarComErro(mensagemErro) {
    console.error(mensagemErro);
}

function escaparHtml(texto) {
    return String(texto ?? '').replace(/[&<>"']/g, c => ({
        '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    })[c]);
}

// A API devolve um objeto de erro quando algo falha; aqui vira lista vazia
function comoLista(dados) {
    return Array.isArray(dados) ? dados : [];
}

// Marca se o título é série ou filme, para o link abrir a página de detalhes certa
function marcar(lista, tipo) {
    return comoLista(lista).map(item => ({ ...item, tipo }));
}

function criarCartaz(titulo, copia = false) {
    const pagina = titulo.tipo === 'filme' ? 'filme.html' : 'detalhes.html';
    const temPoster = titulo.poster && titulo.poster !== 'N/A';
    const nota = titulo.avaliacao ? `<span class="cartaz__nota">★ ${Number(titulo.avaliacao).toFixed(1)}</span>` : '';
    const imagem = temPoster
        ? `<img src="${escaparHtml(titulo.poster)}" alt="${copia ? '' : escaparHtml(titulo.titulo)}" loading="lazy">`
        : `<span class="cartaz__sem-poster">${escaparHtml(titulo.titulo)}</span>`;

    return `
        <li class="cartaz" ${copia ? 'aria-hidden="true"' : ''}>
            <a href="/${pagina}?id=${titulo.id}" ${copia ? 'tabindex="-1"' : ''} title="${escaparHtml(titulo.titulo)}">
                ${imagem}
                ${nota}
            </a>
            <p class="cartaz__titulo">${escaparHtml(titulo.titulo)}</p>
        </li>
    `;
}

// Esteira: a lista aparece duas vezes seguidas e a animação desliza metade do trilho,
// assim o fim de uma cópia emenda no começo da outra sem "pulo"
function preencherEsteira(secao, titulos) {
    const trilho = secao.querySelector('.esteira__trilho');
    secao.querySelectorAll('.esteira__seta').forEach(seta => seta.hidden = titulos.length === 0);
    if (titulos.length === 0) {
        trilho.innerHTML = '<li class="esteira__vazia">Nenhum título por aqui ainda.</li>';
        trilho.style.removeProperty('--itens');
        return;
    }
    const cartazes = titulos.map(t => criarCartaz(t)).join('');
    const copias = titulos.map(t => criarCartaz(t, true)).join('');
    trilho.innerHTML = cartazes + copias;
    trilho.style.setProperty('--itens', titulos.length);
}

// ---------- Setas das esteiras: cada clique anda 4 cartazes para o lado da seta ----------

const CARTAZES_POR_CLIQUE = 4;
const DURACAO_DESLIZE = 450; // ms

function criarSeta(esteira, direcao) {
    const botao = document.createElement('button');
    botao.type = 'button';
    botao.className = `esteira__seta esteira__seta--${direcao < 0 ? 'voltar' : 'avancar'}`;
    botao.setAttribute('aria-label', direcao < 0 ? 'Voltar 4 títulos' : 'Avançar 4 títulos');
    botao.innerHTML = `<span class="material-symbols-outlined" aria-hidden="true">${direcao < 0 ? 'chevron_left' : 'chevron_right'}</span>`;
    botao.addEventListener('click', () => moverEsteira(esteira, direcao));
    return botao;
}

function adicionarSetas(esteira) {
    const area = document.createElement('div');
    area.className = 'esteira-area';
    esteira.replaceWith(area);
    area.append(criarSeta(esteira, -1), esteira, criarSeta(esteira, 1));
}

function moverEsteira(esteira, direcao) {
    const trilho = esteira.querySelector('.esteira__trilho');
    const cartaz = trilho.querySelector('.cartaz');
    if (!cartaz) {
        return;
    }
    const larguraCartaz = cartaz.offsetWidth + parseFloat(getComputedStyle(cartaz).marginRight);
    const distancia = larguraCartaz * CARTAZES_POR_CLIQUE * direcao;

    // Sem animação (preferência ou sistema): a esteira é uma rolagem comum
    const animacao = trilho.getAnimations()[0];
    if (!animacao) {
        esteira.scrollBy({ left: distancia, behavior: 'smooth' });
        return;
    }

    // Esteira animada: a animação percorre metade do trilho (uma cópia da lista) em "duracao" ms,
    // então adiantar/atrasar o relógio dela equivale a andar a distância de 4 cartazes
    const duracao = animacao.effect.getComputedTiming().duration;
    deslizar(animacao, duracao * distancia / (trilho.offsetWidth / 2), duracao);
}

// Aplica o deslocamento aos poucos (com desaceleração no fim), somando ao movimento normal da esteira
function deslizar(animacao, deslocamento, duracao) {
    const inicio = performance.now();
    let aplicado = 0;

    function quadro(agora) {
        const t = Math.min((agora - inicio) / DURACAO_DESLIZE, 1);
        const alvo = deslocamento * (1 - Math.pow(1 - t, 3));
        const tempo = animacao.currentTime + alvo - aplicado;
        // Mantém o relógio dentro de uma volta: como as duas cópias se emendam, não há "pulo"
        animacao.currentTime = ((tempo % duracao) + duracao) % duracao;
        aplicado = alvo;
        if (t < 1) {
            requestAnimationFrame(quadro);
        }
    }
    requestAnimationFrame(quadro);
}

document.querySelectorAll('.section .esteira').forEach(adicionarSetas);

// Grade usada na busca e no filtro de categoria
function preencherGrade(titulos) {
    const ulExistente = elementos.pesquisa.querySelector('ul');
    if (ulExistente) {
        ulExistente.remove();
    }
    pesquisaVazia.hidden = titulos.length > 0;

    const ul = document.createElement('ul');
    ul.className = 'lista';
    ul.innerHTML = titulos.map(t => criarCartaz(t)).join('');
    elementos.pesquisa.appendChild(ul);
}

function mostrarPesquisa(titulo) {
    sectionsParaOcultar.forEach(section => section.classList.add('hidden'));
    elementos.pesquisa.classList.remove('hidden');
    tituloPesquisa.textContent = titulo;
}

function mostrarInicio() {
    sectionsParaOcultar.forEach(section => section.classList.remove('hidden'));
    elementos.pesquisa.classList.add('hidden');
}

// Junta séries e filmes alternando um de cada (usado nos lançamentos)
function intercalar(a, b) {
    const resultado = [];
    for (let i = 0; i < Math.max(a.length, b.length); i++) {
        if (a[i]) resultado.push(a[i]);
        if (b[i]) resultado.push(b[i]);
    }
    return resultado;
}

function porAvaliacao(a, b) {
    return (b.avaliacao ?? 0) - (a.avaliacao ?? 0);
}

// ---------- Home: top 10 de cada seção ----------

geraSecoes();
async function geraSecoes() {
    try {
        const [seriesTop, filmesTop, seriesLancamentos, filmesLancamentos] = await Promise.all([
            getDados('/series/top10'),
            getDados('/filmes/top10'),
            getDados('/series/lancamentos'),
            getDados('/filmes/lancamentos')
        ]);

        const series = marcar(seriesTop, 'serie');
        const filmes = marcar(filmesTop, 'filme');

        preencherEsteira(elementos.lancamentos,
            intercalar(marcar(seriesLancamentos, 'serie'), marcar(filmesLancamentos, 'filme')).slice(0, 10));
        preencherEsteira(elementos.top5, [...series, ...filmes].sort(porAvaliacao).slice(0, 10));
        preencherEsteira(elementos.series, series);
        preencherEsteira(elementos.filmes, filmes);
    } catch (error) {
        lidarComErro("Ocorreu um erro ao carregar os dados.");
    }
}

// ---------- Filtro por categoria ----------

categoriaSelect.addEventListener('change', async function () {
    const categoriaSelecionada = categoriaSelect.value;
    fecharBusca();

    if (categoriaSelecionada === 'todos') {
        mostrarInicio();
        return;
    }

    const nome = categoriaSelect.options[categoriaSelect.selectedIndex].text;
    mostrarPesquisa(`Categoria: ${nome}`);
    try {
        const categoria = encodeURIComponent(categoriaSelecionada);
        const [series, filmes] = await Promise.all([
            getDados(`/series/categoria/${categoria}`),
            getDados(`/filmes/categoria/${categoria}`)
        ]);
        preencherGrade([...marcar(series, 'serie'), ...marcar(filmes, 'filme')].sort(porAvaliacao));
    } catch (error) {
        lidarComErro("Ocorreu um erro ao carregar os dados da categoria.");
    }
});

// ---------- Busca pelo nome (teclado) ----------

let esperaBusca;
let ultimaBusca = '';

function abrirBusca() {
    busca.classList.add('busca--aberta');
    campoBusca.focus();
}

function fecharBusca() {
    clearTimeout(esperaBusca);
    campoBusca.value = '';
    ultimaBusca = '';
    busca.classList.remove('busca--aberta');
}

async function buscar(texto) {
    texto = texto.trim();
    if (texto === ultimaBusca) {
        return;
    }
    ultimaBusca = texto;

    if (texto.length < 2) {
        if (categoriaSelect.value === 'todos') {
            mostrarInicio();
        }
        return;
    }

    categoriaSelect.value = 'todos';
    mostrarPesquisa(`Resultados para "${texto}"`);
    try {
        const termo = encodeURIComponent(texto);
        const [series, filmes] = await Promise.all([
            getDados(`/series/buscar?titulo=${termo}`),
            getDados(`/filmes/buscar?titulo=${termo}`)
        ]);
        // Ignora respostas atrasadas de uma busca que já foi trocada
        if (texto === ultimaBusca) {
            preencherGrade([...marcar(series, 'serie'), ...marcar(filmes, 'filme')].sort(porAvaliacao));
        }
    } catch (error) {
        lidarComErro("Ocorreu um erro ao buscar.");
    }
}

document.querySelector('[data-busca-abrir]').addEventListener('click', () => {
    if (busca.classList.contains('busca--aberta') && campoBusca.value.trim()) {
        buscar(campoBusca.value);
    } else {
        abrirBusca();
    }
});

// Digitando: busca depois de uma pausa curta; Enter busca na hora; Esc limpa e fecha
campoBusca.addEventListener('input', () => {
    clearTimeout(esperaBusca);
    esperaBusca = setTimeout(() => buscar(campoBusca.value), 300);
});

campoBusca.addEventListener('keydown', evento => {
    if (evento.key === 'Enter') {
        clearTimeout(esperaBusca);
        buscar(campoBusca.value);
    } else if (evento.key === 'Escape') {
        fecharBusca();
        mostrarInicio();
        campoBusca.blur();
    }
});

campoBusca.addEventListener('blur', () => {
    if (!campoBusca.value.trim()) {
        busca.classList.remove('busca--aberta');
    }
});

// Atalho "/" abre a busca de qualquer lugar da página (menos quando já está digitando em um campo)
document.addEventListener('keydown', evento => {
    const digitando = evento.target.closest?.('input, textarea, select, [contenteditable="true"]');
    if (evento.key === '/' && !digitando && !document.querySelector('dialog[open]')) {
        evento.preventDefault();
        abrirBusca();
    }
});

// Pôster que não carrega vira um cartão com o nome
document.addEventListener('error', evento => {
    const imagem = evento.target;
    if (imagem.tagName === 'IMG' && imagem.closest('.cartaz')) {
        const substituto = document.createElement('span');
        substituto.className = 'cartaz__sem-poster';
        substituto.textContent = imagem.closest('a').title;
        imagem.replaceWith(substituto);
    }
}, true);
