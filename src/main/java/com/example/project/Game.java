package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);


        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display(); //calls display()
            System.out.println(player.getCoords()); //prints information about player
            System.out.println(player.getRowCol(size));
            System.out.println("Treasures collected: " + player.getTreasureCount());
            System.out.println("Lives remaining: " + player.getLives());
            System.out.print("Enter a direction (w, a, s, d) or q to exit: ");
            String direction = scanner.nextLine();
            if (direction.equals("q")) { //checks if direction is equal to "q"
                break; //if direction is equal to "q", break to stop while loop
            }
            if (player.isValid(size, direction) && player.canGetTrophy(direction, treasures.length, trophy)) { //checks if the player's move is valid
                player.move(direction); //calls move() to change player's coordinates
                // System.out.println(player.getY());
                Sprite[][] s = grid.getGrid(); //sets a sprite array to grid
                player.interact(size, direction, treasures.length, s[Math.abs(player.getY()-size+1)][player.getX()]); //calls interact()
                grid.placeSprite(player, direction); //calls placeSprite()
                clearScreen();
                if (player.getWin()) { //checks if player has won
                    grid.win(); //if player has won, calls win()
                    System.out.print("Play again? (y/n) "); //takes input from user for if they want to play again
                    String play = scanner.nextLine();
                    if (play.equals("n")) { //checks if play is n
                        break; //if play is n, break
                    } else {
                        initialize(); //if play is y, calls initialize() to start the game again
                    }
                }
                if (player.getLives() == 0) { //checks if player has lost
                    grid.gameover(); //if player has lost, calls gameover()
                    System.out.print("Play again? (y/n) ");
                    String play = scanner.nextLine();
                    if (play.equals("n")) { //checks if play is n
                        break; //if play is n, break
                    } else {
                        initialize(); //if play is y, calls initialize() to start the game again
                    }
                }
            }
        }
            
     
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        System.out.print("Please enter a difficulty: ");
        Scanner scan = new Scanner(System.in);
        String difficulty = scan.nextLine(); //takes input from user to select a difficulty
        treasures = new Treasure[2];
        player = new Player(0, 0);
        if (difficulty.equals("easy")) { //checks if difficulty equals "easy"
            size = 15; //sets the size of the grid to 15
            enemies = new Enemy[2]; //sets enemies to an Enemy array with length 2
            enemies[0] = new Enemy(7, 6);
            enemies[1] = new Enemy(12, 4);
            treasures[0] = new Treasure(3, 2);
            treasures[1] = new Treasure(9, 14);
            trophy = new Trophy(12, 10);
        } else if (difficulty.equals("hard")) { //checks if difficulty equals "hard"
            size = 5; //sets the size of the grid to 5
            player.setLives(1); //sets numLives in player to 1
            enemies = new Enemy[4]; //sets enemies to an Enemy array with length 4
            enemies[0] = new Enemy(1, 1);
            enemies[1] = new Enemy(3, 2);
            enemies[2] = new Enemy(2, 3);
            enemies[3] = new Enemy(4, 0);
            treasures[0] = new Treasure(1, 3);
            treasures[1] = new Treasure(4, 2);
            trophy = new Trophy(4, 1);
        } else if (difficulty.equals("medium")) { //checks if difficulty equals "medium"
            size = 10; //sets the size of the grid to 10
            enemies = new Enemy[3]; //sets enemies to an Enemy array with length 2
            treasures = new Treasure[2];
            enemies[0] = new Enemy(3, 4);
            enemies[1] = new Enemy(6, 5);
            enemies[2] = new Enemy(5, 2);
            treasures[0] = new Treasure(2, 1);
            treasures[1] = new Treasure(7, 9);
            trophy = new Trophy(8, 7);
        }
        grid = new Grid(size);
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        grid.placeSprite(enemies[0]);
        grid.placeSprite(enemies[1]);
        grid.placeSprite(treasures[0]);
        grid.placeSprite(treasures[1]);
        if (difficulty.equals("medium") || difficulty.equals("hard")) { //checks if diffulty is equal to "hard" or "medium"
            grid.placeSprite(enemies[2]); //adds the additional third Enemy in the hard and medium difficulty to grid
        }
        if (difficulty.equals("hard")) { //checks if difficulty is equal to "hard"
            grid.placeSprite(enemies[3]); //adds the additional fourth Enemy in the hard difficulty to grid
        }
    }

    public static void main(String[] args) {
        Game g = new Game(10);
    }
}