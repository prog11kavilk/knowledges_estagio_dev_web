import getDados from "./getDados.js";

const params = new URLSearchParams(window.location.search);
const filmeId = params.get('id');
const fichaDescricao = document.getElementById('ficha-descricao');

// Função para carregar informações do filme
function carregarInfoFilme() {
    getDados(`/filmes/${filmeId}`)
        .then(data => {
            fichaDescricao.innerHTML = `
                <img src="${data.poster}" alt="${data.titulo}" />
                <div>
                    <h2>${data.titulo}</h2>
                    <div class="descricao-texto">
                        <p><b>Ano de lançamento:</b> ${data.anoLancamento ?? '-'}</p>
                        <p><b>Duração:</b> ${data.duracao ? data.duracao + ' min' : '-'}</p>
                        <p>${data.sinopse}</p>
                        <p><b>Estrelando:</b> ${data.atores}</p>
                    </div>
                </div>
            `;
        })
        .catch(error => {
            console.error('Erro ao obter informações do filme:', error);
        });
}

carregarInfoFilme();
