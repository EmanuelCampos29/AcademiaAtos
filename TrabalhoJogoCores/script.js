// Definindo as cores disponíveis
const colors = [
    'vermelho',
    'azul',
    'verde',
    'amarelo',
    'roxo',
    'laranja',
    'marrom',
    'rosa',
    'cinza'
  ];
  
  let level = 1;
  let time = 3;
  let score = 0;
  let targetColor = '';
  let gameInterval;
  
  // Função para iniciar o jogo
  function startGame() {
    // Reiniciar as variáveis do jogo
    level = 1;
    time = 3;
    score = 0;
  
    // Exibir informações iniciais
    updateLevel();
    updateScore();
  
    // Configurar o intervalo de jogo
    gameInterval = setInterval(nextRound, time * 1000);
  
    // Adicionar evento de clique aos botões de cor
    const buttons = document.querySelectorAll('.color-button');
    buttons.forEach(button => {
      button.addEventListener('click', handleClick);
    });
  
    // Esconder o botão de iniciar
    const startButton = document.getElementById('start-button');
    startButton.style.display = 'none';
  
    // Iniciar a primeira rodada
    nextRound();
  }
  
  // Função para avançar para a próxima rodada
  function nextRound() {

    
    // Limpar a seleção anterior
    clearSelection();
  
    // Gerar um alvo aleatório e uma palavra aleatória
    targetColor = getRandomColor();
    const targetElement = document.getElementById('target');
    targetElement.textContent = getRandomColor();
  
    // Embaralhar os botões
    shuffleButtons();
  
    // Atualizar o nível e o tempo conforme necessário
    updateLevel();
    updateTimer();
  
    // Verificar se o tempo acabou
    if (time <= 0) {
      endGame();
    }
    const buttons = document.querySelectorAll('.color-button');
    buttons.forEach((button, index) => {
      button.style.backgroundColor = colors[index];
      button.textContent = colors[index];
    });
    
    



  }
  
  // Função para lidar com o clique nos botões de cor
  function handleClick(event) {
    const clickedColor = event.target.textContent;
    if (clickedColor === targetColor) {
      score++;
      updateScore();
      nextRound();
    } else {
      endGame();
    }
  }
  
  // Função para atualizar o nível
  function updateLevel() {
    const levelElement = document.getElementById('level');
    levelElement.textContent = `Nível: ${level}`;
  }
  
  // Função para atualizar a pontuação
  function updateScore() {
    const scoreElement = document.getElementById('score');
    scoreElement.textContent = `Pontuação: ${score}`;
  }
  
  // Função para atualizar o temporizador
  function updateTimer() {
    const timeElement = document.getElementById('time');
    timeElement.textContent = `Tempo: ${time}`;
    time -= 0.1;
    time = parseFloat(time.toFixed(1));
  }
  
  // Função para embaralhar os botões
  function shuffleButtons() {
    const buttonContainer = document.getElementById('button-container');
    const buttons = Array.from(document.querySelectorAll('.color-button'));
  
    for (let i = buttons.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [buttons[i].textContent, buttons[j].textContent] = [buttons[j].textContent, buttons[i].textContent];
    }
  
    // Remover os botões existentes
    while (buttonContainer.firstChild) {
      buttonContainer.removeChild(buttonContainer.firstChild);
    }
  
    // Adicionar os botões embaralhados
    buttons.forEach(button => {
      buttonContainer.appendChild(button);
    });
  }
  
  // Função para limpar a seleção anterior
  function clearSelection() {
    const buttons = document.querySelectorAll('.color-button');
    buttons.forEach(button => {
      button.classList.remove('selected');
    });
  }
  
  // Função para encerrar o jogo
  function endGame() {
    clearInterval(gameInterval);
  
    // Exibir a pontuação final
    const scoreElement = document.getElementById('score');
    scoreElement.textContent = `Pontuação final: ${score}`;
  
    // Exibir o botão de iniciar
    const startButton = document.getElementById('start-button');
    startButton.style.display = 'block';
  }
  
  // Função para obter uma cor aleatória
  function getRandomColor() {
    const randomIndex = Math.floor(Math.random() * colors.length);
    return colors[randomIndex];
  }
  
  // Adicionar evento de clique ao botão de iniciar
  const startButton = document.getElementById('start-button');
  startButton.addEventListener('click', startGame);
  