/**
 * Created by Barret J. Nobel on 3/31/2016.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class App {
    private static final String LEFT = "LEFT"; //............. used for the hash table left side costs
    private static final String RIGHT = "RIGHT"; //........... used for the hast table right side costs

    public static void main( String[] args ){
        Board newBoard = new Board();
        PriorityQueue<Board> open = new PriorityQueue<>();

//        System.out.println(Arrays.toString(newBoard.getBoard()) );
//        System.out.println( newBoard.getAvailableMoves().values() );
//        System.out.println( newBoard.getgValue() );

//        boolean answer = newBoard.makeAMove( 0 );
//        answer = newBoard.makeAMove( 1 );
//        answer = newBoard.makeAMove( 0 );
//        System.out.println( answer );
//        System.out.println( newBoard );

        for( Board potentialMove : generateBoards( newBoard ) ){
            System.out.println( potentialMove + " " + potentialMove.getfValue());
            open.add( potentialMove );
        }

//        System.out.println( open );
        Board tmp = open.remove();
        System.out.println( "\n" + tmp + " " + tmp.getfValue());
        tmp = open.remove();
        System.out.println( "\n" + tmp + " " + tmp.getfValue());
//        System.out.println( open );
//        System.out.println( open.poll() );
//        System.out.println( open );
    }

    // Method to generate all the boards from the moves in the hash
    // returns an array of boards!
    private static ArrayList<Board> generateBoards( Board currentBoard ){

        System.out.println( currentBoard + " " + currentBoard.gethValue());

        ArrayList<Board> returnArray = new ArrayList<>(); //....................................... array of Board objects to return
        ArrayList<Integer> left = (ArrayList)currentBoard.getAvailableMoves().get( LEFT ); //...... get all moves to the left
        ArrayList<Integer> right = (ArrayList)currentBoard.getAvailableMoves().get( RIGHT ); //.... get all moves to the left

        for( int i = 0; i < left.size(); i++ ){ //................................................. loop through all moves left
            Board newBoard = (Board)currentBoard.clone();
            if( newBoard.makeAMove(i + 1 + currentBoard.getEmptyLocation()) ) {
                returnArray.add( newBoard );
            }
        }

        for( int i = currentBoard.getEmptyLocation() - right.size(); i < right.size(); i++ ){ //... loop through all moves right
            Board newBoard = (Board)currentBoard.clone();
            if( newBoard.makeAMove(i) ){
                returnArray.add( newBoard );
            }
        }

        return returnArray;
    }

}
