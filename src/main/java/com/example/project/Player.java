package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}
    
    public void setLives(int lives) {
        numLives = lives;
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { //checks if direction is "w"
            setY(getY()+1); //if direction is "w", increases y value by 1
        } else if (direction.equals("a")) { //checks if direction is "a"
            setX(getX()-1); //if direction is "a", decreases x value by 1
        } else if (direction.equals("s")) { //checks if direction is "s"
            setY(getY()-1); //if direction is "s", decreases y value by 1
        } else if (direction.equals("d")) { //checks if direction is "d"
            setX(getX()+1); //if direction is "d", increases x value by 1
        }
    }

    //interacts with object
    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        //numTreasures is the total treasures at the beginning of the game
        if (isValid(size, direction)) { //checks if the move is valid
            if (obj instanceof Trophy) { //checks if obj is a Trophy
                if (treasureCount == numTreasures) { //if obj is a Trophy, checks if treasureCount is equal to numTreasure
                    win = true; //if treasureCount is equal to numtreasure, sets win to true
                }
            } else if (obj instanceof Enemy) { //checks if obj is an Enemy
                numLives--; //if obj is an Enemy, decrements numLives
            } else if (obj instanceof Treasure) { //checks if obj is a Treasure
                treasureCount++; //if obj is a Treasure, increments treasureCount
            }
        }
    }

    //checks if a move is valid
    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) { //checks if direction is "w"
            if (getY()+1 > size-1) { //if direction is "w", check is the move is outside grid boundaries. uses size-1 instead of size because the grid starts at 0
                return false; //if move is outside grid boundaries, returns false
            }
        } else if (direction.equals("a")) { //checks if direction is "a"
            if (getX()-1 < 0) { //if direction is "a", check is the move is outside grid boundaries
                return false; //if move is outside grid boundaries, returns false
            }
        } else if (direction.equals("s")) { //checks if direction is "s"
            if (getY()-1 < 0) { //if direction is "s", check is the move is outside grid boundaries
                return false; //if move is outside grid boundaries, returns false
            }
        } else if (direction.equals("d")) { //checks if direction is "d"
            if (getX()+1 > size-1) { //if direction is "d", check is the move is outside grid boundaries
                return false; //if move is outside grid boundaries, returns false
            }
        }
        return true; //returns true
    }

    //checks if player can get Trophy
    public boolean canGetTrophy(String direction, int numTreasures, Trophy trophy) {
        if (numTreasures == treasureCount) { //checks if numTreasures is equal to treasureCount
            return true; //if numTreasures is equal to treasureCount, returns true
        }
        if (direction.equals("w")) { //checks if direction is "w"
            if (getX() == trophy.getX() && getY()+1 == trophy.getY()) { //checks if the player is moving into a spot with Trophy in it
                return false; //returns false if player is moving into Trophy since they do not have all the treasure yet
            }
        } else if (direction.equals("a")) { //checks if direction is "a"
            if (getX()-1 == trophy.getX() && getY() == trophy.getY()) { //checks if the player is moving into a spot with Trophy in it
                return false; //returns false if player is moving into Trophy since they do not have all the treasure yet
            }
        } else if (direction.equals("s")) { //checks if direction is "s"
            if (getX() == trophy.getX() && getY()-1 == trophy.getY()) { //checks if the player is moving into a spot with Trophy in it
                return false; //returns false if player is moving into Trophy since they do not have all the treasure yet
            }
        } else if (direction.equals("d")) { //checks if direction is "d"
            if (getX()+1 == trophy.getX() && getY() == trophy.getY()) { //checks if the player is moving into a spot with Trophy in it
                return false; //returns false if player is moving into Trophy since they do not have all the treasure yet
            }
        }
        return true; //returns true
    }

    //returns string with player's coordinates on cartesian plane
    @Override
    public String getCoords(){
        return "Player:(" + getX() + "," + getY() + ")";
    }

    //returns a string with player's row and column in a 2D array
    @Override
    public String getRowCol(int size){
        return "Player:[" + Math.abs(getY()-size+1) + "][" + getX() + "]";
    }
}



