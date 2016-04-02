/**
 * Created by Barret J. Nobel on 3/31/2016.
 */

import java.util.ArrayList;

public class App {
    private static final String LEFT = "LEFT"; //............. used for the hash table left side costs
    private static final String RIGHT = "RIGHT"; //........... used for the hast table right side costs

    public static void main( String[] args ){
        Board newBoard = new Board();

//        System.out.println(Arrays.toString(newBoard.getBoard()) );
//        System.out.println( newBoard.getAvailableMoves().values() );
//        System.out.println( newBoard.getgValue() );

//        boolean answer = newBoard.makeAMove( 0 );
//        answer = newBoard.makeAMove( 1 );
//        answer = newBoard.makeAMove( 0 );
//        System.out.println( answer );
//        System.out.println( newBoard );

        generateBoards( newBoard );
    }

    // Method to generate all the boards from the moves in the hash
    // returns an array of boards!
    private static Board[] generateBoards( Board currentBoard ){
        Board[] returnArray = new Board[6]; //................................................... array of Board objects to return
        Board clone = (Board)currentBoard.clone(); //............................................ clone of the board to generate from
        ArrayList<Integer> left = (ArrayList)currentBoard.getAvailableMoves().get( LEFT ); //.... get all moves to the left

        for( int i = 0; i < left.size() - 1; i++ ){ //........................................... loop through all moves left
//            System.out.println( "Before: " + currentBoard );

            currentBoard.makeAMove( i + 1 + currentBoard.getEmptyLocation() );

//            System.out.println( "After: " + currentBoard );
        }

        return returnArray;
    }

}
