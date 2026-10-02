alert('Boas vindas ao jogo do número secreto');
let NumeroMaximo = 5000
//adicionando o console.log para verificar "chute" após a entrada do usuário
let numeroSecreto = parseInt(Math.random()*NumeroMaximo +1);
console.log(numeroSecreto);
let chute;
let tentativas = 1;

//enquanto o chute não acertar o número
while (chute != numeroSecreto){
    chute = prompt(`Escolha um número entre 1 a ${NumeroMaximo}`);
//se o chute for == ao numero secreto
    if (chute == numeroSecreto){
        break;    
    }
    else{
        if(numeroSecreto > chute) {
            alert(`O número secreto é maior do que ${chute}`);
        }
        else{
            alert(`O número secreto é menor do que ${chute}`);
        }
        //incrementa tentativas depois do chute
        tentativas++;
    }
}
let palavraTentativa = tentativas > 1? 'tentativas' : 'tentativa';
alert(`Isso aí! \nvocê descobriu o número ${numeroSecreto}!\nCom ${tentativas} ${palavraTentativa} :)`);