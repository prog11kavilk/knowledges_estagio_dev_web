//variáveis da Bolinha
let xBolinha = 300;
let yBolinha = 200;
let diametro = 15;
let raio = diametro / 2;

//velocidade da Bolinha
let velocidadeXBolinha = 5;
let velocidadeYBolinha = 5;

//variáveis da raquete
let xRaquete = 5;
let yRaquete = 100;
let raqueteComprimento = 10;
let raqueteAltura = 90;

//variáveis do oponente
let xRaqueteOponente = 585;
let yRaqueteOponente = 150;
let velocidadeYOponente;

//placar do jogo
let meusPontos = 0;
let pontosOponente = 0;
let pontosParaVencer = 10;


let colidiu = false;


function setup() {
  createCanvas(600, 400);
}

function draw() {
  background(0);
  mostraBolinha();
  movimentaBolinha();
  verificaColisaoBorda();

  mostraRaquete(xRaquete, yRaquete);
  movimentaMinhaRaquete();
  verificaColisaoRaquete(xRaquete, yRaquete);

  mostraRaquete(xRaqueteOponente, yRaqueteOponente);
  verificaColisaoRaquete(xRaqueteOponente, yRaqueteOponente);
  movimentaRaqueteOponente();

  incluiPlacar();
  marcaPonto();
}

function mostraBolinha() {
  fill(255);
  noStroke();
  circle(xBolinha, yBolinha, diametro);
}

function movimentaBolinha() {
  xBolinha += velocidadeXBolinha;
  yBolinha += velocidadeYBolinha;
}

function verificaColisaoBorda() {
  if (xBolinha + raio > width || xBolinha - raio < 0) {
    velocidadeXBolinha *= -1;
  }
  if (yBolinha + raio > height || yBolinha - raio < 0) {
    velocidadeYBolinha *= -1;
  }
}

function mostraRaquete(x, y) {
  fill(255);
  rect(x, y, raqueteComprimento, raqueteAltura);
}

function movimentaMinhaRaquete() {
  if (keyIsDown('w')) {
    yRaquete -= 10;
  }
  if (keyIsDown('s')) {
    yRaquete += 10;
  }
  // impede a raquete de sair da tela
  yRaquete = constrain(yRaquete, 0, height - raqueteAltura);
}

function verificaColisaoRaquete(x, y) {
  colidiu = collideRectCircle(x, y, raqueteComprimento, raqueteAltura, xBolinha, yBolinha, raio);
  if (colidiu) {
    velocidadeXBolinha *= -1;
  }
}

function movimentaRaqueteOponente() {
  if (keyIsDown(UP_ARROW)) {
    yRaqueteOponente -= 10;
  }
  if (keyIsDown(DOWN_ARROW)) {
    yRaqueteOponente += 10;
  }
  // impede a raquete de sair da tela
  yRaquete = constrain(yRaquete, 0, height - raqueteAltura);
}

function incluiPlacar() {
  stroke(255);
  textAlign(CENTER);
  textSize(14);
  fill(color(10 , 10, 220));
  rect(235, 10, 30, 20);
  fill(255);
  text(meusPontos, 250, 26);

  fill(color(220, 10, 10));
  rect(335, 10, 30, 20);
  fill(255);
  text(pontosOponente, 350, 26);
}

function marcaPonto() {
  if (xBolinha > 590) {
    meusPontos += 1;
    resetBolinha();
  }
  if (xBolinha < 10) {
    pontosOponente += 1;
    resetBolinha();
  }
}

function resetBolinha() {
  xBolinha = width / 2;
  yBolinha = height / 2;
  velocidadeXBolinha *= -1;
}