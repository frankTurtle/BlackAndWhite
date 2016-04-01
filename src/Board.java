/**
 * Created by Barret J. Nobel on 3/31/2016.
 * Class to create a board to hold the Tile objects
 */

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int BOARD_SIZE = 7; //................................................ the board size
    private Tile[] board = new Tile[ BOARD_SIZE ]; //................................... private instance variable to hold the board of Tile objects
    private boolean solved; //.......................................................... private instance variable to determine when board is solved
    private Map<Integer, Integer> availableMoves = new HashMap<Integer, Integer>(); //.. private instance variable to store the available moves with their cost

    // Default constructor
    // builds the board as follows:
    // [ W, W, W, Empty , B, B, B ]
    // Also sets the board solved to false
    public Board(){
        this.setSolved( false ); //................................................... sets solved state to false
        for( int i = 0; i < BOARD_SIZE; i++ ){ //..................................... loop through the board and create new tile objects
            if( i == 3 )board[i] = new Tile( 'E', i ); //............................. set the empty location to the middle
            else board[i] = ( i < 3 ) ? new Tile( 'B', i ) : new Tile( 'W', i ); //... create the W or B tiles
        }
    }

    // Setter method to set if the board is solved
    public void setSolved( boolean answer ){
        this.solved = answer;
    }

//    public Map[] getAvailableMoves(){
//
//    }

    // Overridden toString
    // prints out the current board
    public String toString(){
        String returnString = "";
        for( Tile tile : board ){ returnString += tile; }

        return returnString;
    }

//    map.put("dog", "type of animal");
//    System.out.println(map.get("dog"));
}
