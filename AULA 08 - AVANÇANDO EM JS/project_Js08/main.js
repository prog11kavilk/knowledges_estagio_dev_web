// executa o som correspondente da tecla desejada
function tocaSom(seletorAudio){
    const elemento = document.querySelector(seletorAudio);
  
    //se não for nulo e pertencer à tag 'audio'
    if (elemento != null && elemento.localName === 'audio'){
        elemento.play();  
    }
    else{
        console.log('elemento não encontrado ou seletor indevido');
    }
}

// constante para nossas teclas
const listaDeTeclas = document.querySelectorAll('.tecla');
''
// posição na lista de teclas

// loop que repete até passar por toda a lista
for (let contador  = 0; contador < listaDeTeclas.length; contador++){
    
    const tecla = listaDeTeclas[contador];
    const instrumento = tecla.classList[1];
    const idAudio = `#som_${instrumento}`;
    
    // console.log(idAudio);
    
    tecla.onclick= function(){
        tocaSom(idAudio);
    }
    
    // função para quando a tecla for pressionada, add a cor vermelha
    tecla.onkeydown = function(evento){
        
        
        if(evento.code === 'Space' || event.code === 'Enter'){
            tecla.classList.add('ativa');
        }
    }
    
    tecla.onkeyup = function(){
        tecla.classList.remove('ativa');
    }
    // console.log(contador);
}