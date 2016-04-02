/**
 * Created by Barret J. Nobel on 3/31/2016.
 * Class to create a board to hold the Tile objects
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int BOARD_SIZE = 7; //............... the board size
    private final String LEFT = "LEFT"; //............. used for the hash table left side costs
    private final String RIGHT = "RIGHT"; //........... used for the hast table right side costs

    private Tile[] board = new Tile[ BOARD_SIZE ]; //.. private instance variable to hold the board of Tile objects
    private boolean solved; //......................... private instance variable to determine when board is solved
    private int emptyLocation; //...................... private instance variable to hold the empty location
    private int gValue;
    private int hValue;

    // Default constructor
    // builds the board as follows:
    // [ B, B, B, Empty , W, W, W ]
    // Also sets the board solved to false
    public Board(){
        this.setSolved( false ); //................................................... sets solved state to false
        for( int i = 0; i < BOARD_SIZE; i++ ){ //..................................... loop through the board and create new tile objects
            if( i == 3 )board[i] = new Tile( 'E', i ); //............................. set the empty location to the middle
            else board[i] = ( i < 3 ) ? new Tile( 'B', i ) : new Tile( 'W', i ); //... create the W or B tiles
        }
        this.setEmptyLocation( 3 ); //................................................ sets the empty location
    }

    // Setter method to set if the board is solved
    public void setSolved( boolean answer ){
        this.solved = answer;
    }

    public void setEmptyLocation( int location ){
        this.emptyLocation = location;
    }

    // Method to calculate the gValue
    public void setgValue(){

    }

    // Method to return all available moves
    // in the hashing format of < direction, cost >
    public Map getAvailableMoves() {
        Map<String, ArrayList<Integer>> availableMoves = new HashMap<String, ArrayList<Integer>>(); //.. variable to store the available moves with their cost
        ArrayList<Integer> costsRight = new ArrayList<>(); //........................................... arrayList for the right side of costs
        ArrayList<Integer> costsLeft = new ArrayList<>(); //............................................ arrayList for the left side of costs

        if (emptyLocation < 3) { //..................................................................... where is the empty value in the board?
            costsLeft.add(3);
            costsLeft.add(3);
            costsLeft.add(3);
            costsRight.add(2);
            costsRight.add(1);
            costsRight.add(0);
        } else if (emptyLocation == 3) { //............................................................. goal location
            costsLeft.add(1);
            costsLeft.add(1);
            costsLeft.add(2);
            costsRight.add(1);
            costsRight.add(1);
            costsRight.add(2);
        } else { //..................................................................................... empty square is in the right half
            costsLeft.add(2);
            costsLeft.add(1);
            costsLeft.add(0);
            costsLeft.add(3);
            costsLeft.add(3);
            costsLeft.add(3);
        }

        availableMoves.put( LEFT, costsLeft ); //...................................................... put all the counts into the hash with the names
        availableMoves.put( RIGHT, costsRight );

        return availableMoves;
    }

    // Method to get the board
    public Tile[] getBoard(){
        Tile[] returnBoard = new Tile[ this.board.length ]; //.................... create a new board to return
        System.arraycopy( this.board, 0, returnBoard, 0, this.board.length ); //.. copy from the instance variable

        return returnBoard;
    }


    // Method to return the state of the board ( solved or not )
    public boolean getSolved(){
        return solved;
    }

    // Overridden toString
    // prints out the current board
//    public String toString(){
//        String returnString = ""; //......................... create an empty string
//        for( Tile tile : board ){ returnString += tile; } //. loop through every tile in the board and concatenate tile toString
//
//        return returnString;
//    }

    // Overridden toString alternative
    // prints out the board in a more user friendly format
    public String toString(){
        String returnString = "[ "; //.......................................... create the beginning of the string
        for( Tile tile : board ){ returnString += tile.getColor() + ", "; } //.. loop through each tile in the board and get the color
        returnString = returnString.substring( 0, returnString.length()-2 ); //. cut the ending space and comma off
        returnString += " ]"; //................................................ add closing brace
        return returnString;
    }
}
