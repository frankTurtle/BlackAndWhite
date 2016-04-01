/**
 * Created by Barret J. Nobel on 3/31/2016.
 */

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int BOARD_SIZE = 7;
    private Tile[] board = new Tile[ BOARD_SIZE ]; //............................... private instance variable to hold the board of chars
    private boolean solved; //................................... private instance variable to determine when board is solved
    private Map<Integer, Integer> availableMoves = new HashMap<Integer, Integer>(); //... private instance variable to store the available moves with their cost

    public Board(){
        this.setSolved( false );
        for( int i = 0; i < BOARD_SIZE; i++ ){
            if( i == 3 )board[i] = new Tile( 'E', i );
            else board[i] = ( i < 3 ) ? new Tile( 'B', i ) : new Tile( 'W', i );
        }
    }

    public void setSolved( boolean answer ){
        this.solved = answer;
    }

//    public Map[] getAvailableMoves(){
//
//    }

    public void printBoard(){
        for( Tile tile : board ){ System.out.println( tile ); }
    }

//    map.put("dog", "type of animal");
//    System.out.println(map.get("dog"));
}
