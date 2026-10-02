// Preferências das Configurações (salvas na conta) e o efeito de cada uma na página
export const PREFERENCIAS_PADRAO = {
    velocidade: 'media',
    pausarNoHover: true,
    mostrarNota: true,
    reduzirAnimacoes: false,
    notifNovidades: true,
    notifEpisodios: true,
    notifRecomendacoes: false
};

export function comPadrao(preferencias) {
    return { ...PREFERENCIAS_PADRAO, ...(preferencias || {}) };
}

export function aplicarPreferencias(preferencias) {
    const p = comPadrao(preferencias);
    const corpo = document.body;
    corpo.dataset.velocidade = p.velocidade;
    corpo.classList.toggle('pausar-no-hover', Boolean(p.pausarNoHover));
    corpo.classList.toggle('ocultar-notas', !p.mostrarNota);
    corpo.classList.toggle('sem-animacao', Boolean(p.reduzirAnimacoes));
    return p;
}
