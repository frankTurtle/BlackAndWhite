/**
 * Created by Barret J. Nobel on 3/31/2016.
 * Class to create a board to hold the Tile objects
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Board implements Cloneable{
    private final int BOARD_SIZE = 7; //............... the board size
    private final String LEFT = "LEFT"; //............. used for the hash table left side costs
    private final String RIGHT = "RIGHT"; //........... used for the hast table right side costs

    private Tile[] board = new Tile[ BOARD_SIZE ]; //.. private instance variable to hold the board of Tile objects
    private boolean solved; //......................... private instance variable to determine when board is solved
    private int emptyLocation; //...................... private instance variable to hold the empty location
    private int gValue; //............................. private instance variable to hold the g value of the board
    private int hValue; //............................. private instance variable to hold the h value of the board

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
        this.setgValue(); //.......................................................... sets the gValue
    }

    // Setter method to set if the board is solved
    public void setSolved( boolean answer ){
        this.solved = answer;
    }

    // Method to set the hValue
    public void sethValue( int hValue ){
        this.hValue = hValue;
    }

    // Method to set the board of tiles
    public void setBoard( Tile[] boardIn ) {
        this.board = boardIn.clone();
    }

    // Setter method for empty location
    public void setEmptyLocation( int location ){
        this.emptyLocation = location;
    }

    // Method to calculate the gValue
    // loops through the entire board and compares the index to the known index's of the goal state
    // adds up the number of spaces out of position
    public void setgValue(){
        char color; //.......................................................................................................... variable to hold the current tile's color
        int totalgValue = 0; //................................................................................................. temp variable to add up all out of place tiles
        LinkedList<Integer> indexW = new LinkedList<>(); //..................................................................... list of known indexes for W tiles
        LinkedList<Integer> indexB = new LinkedList<>(); //..................................................................... list of known indexes for B tiles

        indexW.add(0); //....................................................................................................... add known indexes to all lists
        indexW.add(1);
        indexW.add(2);

        indexB.add(6);
        indexB.add(5);
        indexB.add(4);

        for( int i = 0; i < this.board.length; i++ ){ //........................................................................ loop through each Tile in the board
            color = this.board[ i ].getColor(); //.............................................................................. gets the color

            if( i < 3 && color == 'B' ){ //..................................................................................... this is where the W's go, check if its a B
                totalgValue += this.gValueHelper( indexB, 'B', i );
            }
            else if( i == 3 && color != 'E' ){ //............................................................................... if its not blank spot where it supposed to be
                totalgValue += ( color == 'B' ) ? this.gValueHelper( indexB, 'B', i ) : this.gValueHelper( indexW, 'W', i ); //. assigns value appropriately based on W or B color
            }
            else if( i > 3 && color == 'W' ){ //................................................................................ this is where the B's go, check if its a W
                totalgValue += this.gValueHelper( indexW, 'W', i );
            }
        }

        this.gValue = totalgValue; //.......................................................................................... set the gValue instance variable for this board
    }

    // Method to loop through the list and get the value to add for the g value
    private int gValueHelper( LinkedList<Integer> list, char compareTo, int currentIndex ){
        int returnInt = 0; //................................................................. int to return

        for( Integer index : list ){ //....................................................... loop through each index in the list passed in
            if( this.board[ index ].getColor() !=  compareTo ) { //........................... if colors are not the same
                returnInt += Math.abs( index - currentIndex ); //............................. copy index value to return, and remove from the list
                list.remove( index ); //...................................................... remove the item from the list to cut down on list looping
                break;
            }
        }
        return returnInt;
    }

    // Method to return all available moves
    // in the hashing format of < direction, cost >
    public Map getAvailableMoves() {
        Map<String, ArrayList<Integer>> availableMoves = new HashMap<String, ArrayList<Integer>>(); //.. variable to store the available moves with their cost
        ArrayList<Integer> costsRight = new ArrayList<Integer>(); //.................................... arrayList for the right side of costs
        ArrayList<Integer> costsLeft = new ArrayList<Integer>(); //..................................... arrayList for the left side of costs

        if (emptyLocation < 3) { //..................................................................... where is the empty value in the board?
            costsLeft.add(1);
            costsLeft.add(1);
            costsLeft.add(2);
            for( int i = 0; i < this.emptyLocation; i++ ){
                costsRight.add( (i == 0 || i == 1) ? 1 : 2 );
            }
        } else if (emptyLocation == 3) { //............................................................. goal location
            costsLeft.add(1);
            costsLeft.add(1);
            costsLeft.add(2);
            costsRight.add(2);
            costsRight.add(1);
            costsRight.add(1);
        } else { //..................................................................................... empty square is in the right half
            for( int i = BOARD_SIZE - 1 ; i > this.emptyLocation; i-- ){
                costsLeft.add( (i == BOARD_SIZE - 1 || i == BOARD_SIZE - 2) ? 1 : 2 );
            }
            costsRight.add(1);
            costsRight.add(1);
            costsRight.add(2);
        }

        availableMoves.put( LEFT, costsLeft ); //....................................................... put all the counts into the hash with the names
        availableMoves.put( RIGHT, costsRight );

        return availableMoves;
    }

    // Method to return the empty tile
    public int getEmptyLocation(){
        return this.emptyLocation;
    }

    // Method to make a move if its valid
    // takes the index where it's attempting to make the move from
    // updates empty location value once completed
    // returns true or false if it's not a valid move
    public boolean makeAMove( int fromIndex ) {
        int[] cost = { 1, 1, 2 }; //.................................. value of all costs
        int difference = Math.abs(this.emptyLocation - fromIndex); //. get the absolute value between the empty one and the one wanting to move
        boolean returnIfValid = !( difference > 3 ); //............... if its over 3 then it's not a valid move

        if( returnIfValid ){ //....................................... if its valid, swap them
            this.sethValue( cost[difference - 1] ); //................ sets the H Value
            Tile temp = this.board[ fromIndex ];
            this.board[ fromIndex ] = this.board[ emptyLocation ];
            this.board[ emptyLocation ] = temp;
            this.setEmptyLocation( temp.getCurrentLocation() ); //.... and update the location of the empty tile
            this.setgValue();
        }

        return returnIfValid;
    }

    // Method to get the gValue
    public int getgValue(){
        return this.gValue;
    }

    // Method to return the hValue
    public int gethValue(){
        return this.hValue;
    }

    // Method to get the board
    // UPDATE: THIS IS A SHALLOW COPY
    public Tile[] getBoard(){
        Tile[] returnBoard = new Tile[ this.board.length ]; //.................... create a new board to return
        System.arraycopy( this.board, 0, returnBoard, 0, this.board.length ); //.. copy from the instance variable

        return returnBoard;
    }

    // Overridden method to clone the current board object
    protected Object clone() {
        Board clone = null; //............................. create the new board object to return

        try {
            clone = (Board)super.clone(); //............... create a new object from super class
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
        }

        clone.setEmptyLocation( this.emptyLocation ); //... deep copy instance variables
        clone.setBoard( this.board );
        clone.setSolved( this.getSolved() );

        return clone;
    }


    // Method to return the state of the board ( solved or not )
    public boolean getSolved(){
        return solved;
    }

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
