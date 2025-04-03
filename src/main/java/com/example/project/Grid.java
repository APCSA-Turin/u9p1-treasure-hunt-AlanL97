package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Dot(j, Math.abs(i-size+1));
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}


    //places a sprite in a spot in grid
    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[Math.abs(s.getY()-size+1)][s.getX()] = s; //sets a element in grid to s based on s' x and y coordinates
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        grid[Math.abs(s.getY()-size+1)][s.getX()] = s; //sets a element in grid to s based on s' x and y coordinates
        if (direction.equals("w")) { //checks if direction is equal to "w"
            grid[Math.abs(s.getY()-size)][s.getX()] = new Dot(s.getX(), s.getY()-1); //sets the spot in grid the s used to be to a new Dot with the proper coordinates
        } else if (direction.equals("a")) { //checks if direction is equal to "a"
            grid[Math.abs(s.getY()-size+1)][s.getX()+1] = new Dot(s.getX()+1, s.getY()); //sets the spot in grid the s used to be to a new Dot with the proper coordinates
        } else if (direction.equals("s")) { //checks if direction is equal to "s"
            grid[Math.abs(s.getY()-size+2)][s.getX()] = new Dot(s.getX(), s.getY()+1); //sets the spot in grid the s used to be to a new Dot with the proper coordinates
        } else if (direction.equals("d")) { //checks if direction is equal to "d"
            grid[Math.abs(s.getY()-size+1)][s.getX()-1] = new Dot(s.getX()-1, s.getY()); //sets the spot in grid the s used to be to a new Dot with the proper coordinates
        }
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) { //iterates through every array in grid
            for (int j = 0; j < grid[0].length; j++) { //iterates through every element in every array in grid
                if (grid[i][j] instanceof Dot) { //checks if the element in grid is a Dot
                    System.out.print("⬜"); //prints an image corresponding to the sprite
                } else if (grid[i][j] instanceof Enemy) { //checks if the element in grid is an Enemy
                    System.out.print("🦂"); //prints an image corresponding to the sprite
                } else if (grid[i][j] instanceof Player) { //checks if the element in grid is a Player
                    System.out.print("🦄"); //prints an image corresponding to the sprite
                } else if (grid[i][j] instanceof Treasure && !(grid[i][j] instanceof Trophy)) { //checks if the element in grid is a Treasure
                    System.out.print("🌈"); //prints an image corresponding to the sprite
                } else if (grid[i][j] instanceof Trophy) { //checks if the element in grid is a Trophy
                    System.out.print("🏆"); //prints an image corresponding to the sprite
                }
            }
            System.out.println(); //prints line break after iterating through an array in grid
        }
    }
    
    public void gameover() { //use this method to display a loss
        for (int i = 0; i < grid.length; i++) { //iterates through every array in grid
            for (int j = 0; j < grid[0].length; j++) { //iterates through every element in every array in grid
                if (grid[i][j] instanceof Player) { //checks if an element in grid is a Player
                    System.out.print("🦄"); //if element is a Player, prints image corresponding with Player
                    continue;
                }
                System.out.print("💀"); //prints skull image
            }
            System.out.println(); //prints line break
        }
        System.out.println("You lose!");
    }

    public void win() { //use this method to display a win 
        for (int i = 0; i < grid.length; i++) { //iterates through every array in grid
            for (int j = 0; j < grid[0].length; j++) { //iterates through every element in every array in grid
                if (grid[i][j] instanceof Player) { //checks if an element in grid is a Player
                    System.out.print("🦄"); //if element is a Player, prints image corresponding with Player
                    continue;
                }
                System.out.print("🌈"); //prints rainbow image
            }
            System.out.println(); //prints line break
        }
        System.out.println("You win!");
    }


}