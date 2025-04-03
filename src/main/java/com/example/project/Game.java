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
                    break;
                }
                if (player.getLives() == 0) { //checks if player has lost
                    grid.gameover(); //if player has lost, calls gameover()
                    break;
                }    
            }
        }
            
     
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid = new Grid(size);
        player = new Player(0, 0);
        trophy = new Trophy(8, 7);
        enemies = new Enemy[3];
        treasures = new Treasure[2];
        enemies[0] = new Enemy(3, 4);
        enemies[1] = new Enemy(6, 5);
        enemies[2] = new Enemy(5, 2);
        treasures[0] = new Treasure(2, 1);
        treasures[1] = new Treasure(6, 9);
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        grid.placeSprite(enemies[0]);
        grid.placeSprite(enemies[1]);
        grid.placeSprite(enemies[2]);
        grid.placeSprite(treasures[0]);
        grid.placeSprite(treasures[1]);
    }

    public static void main(String[] args) {
        Game g = new Game(10);
    }
}