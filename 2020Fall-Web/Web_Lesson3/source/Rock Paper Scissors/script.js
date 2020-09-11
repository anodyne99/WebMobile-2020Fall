function getRandomInteger(x) { // sets up the initial random pick
    return Math.floor(Math.random() * x);
}

function getName(x) { // assigns the correct name from the number for display
    let list = ["rock", "paper", "scissors"]
    return list[x];
}

function game(player) { // game runtime
    // rock = 0, paper = 1, scissors = 2
    let random = getRandomInteger(3);
    if (player ==  random) {
        draw(player);
    }
    else {
        switch (player) {
            case 0: // if player picks rock
                if (random == 1) { // Computer picks paper
                    playerLose(random);
                }
                if (random == 2) { // Computer picks scissors
                    playerWin(random);
                }
                break;
            case 1: // if player picks paper
                if (random == 0) { // Computer picks rock
                    playerWin(random);
                }
                if (random == 2) { // Computer picks scissors
                    playerLose(random);
                }
                break;
            case 2: // if player picks scissors
                if (random == 0) { // Computer picks rock
                    playerLose(random);
                }
                if (random == 1) { // Computer picks paper
                    playerWin(random);
                }
                break;
        }
    }
}

// End conditions below

function playerWin(computer){
    window.alert("Your opponent picked " + getName(computer) + ". You win!")
}

function playerLose(computer) {
    window.alert("Your opponent picked " + getName(computer) + ". Sorry, you lose.")
}

function draw(computer){
    window.alert("It's a draw! Your opponent also picked " + getName(computer) + ".")
}