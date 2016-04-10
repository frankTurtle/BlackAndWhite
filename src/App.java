/**
 * Created by Barret J. Nobel on 3/31/2016.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class App {
    private static final String LEFT = "LEFT"; //............. used for the hash table left side costs
    private static final String RIGHT = "RIGHT"; //........... used for the hast table right side costs

    public static void main( String[] args ){
        final Tile[] GOAL = generateGoal();
        Board newBoard = new Board();
        PriorityQueue<Board> open = new PriorityQueue<>();
        ArrayList<Board> closed = new ArrayList<>();
        int totalCost = 0;

        // Step 1
        open.add( newBoard );

        // Step 2
        while( !open.isEmpty() ){
            // Step 3
            Board n = open.remove();
            closed.add( n );
            if( n.equals(GOAL) ) break;

            // Step 4
            for( Board potentialMove : generateBoards( n ) ){
                // Step 4a
                if( !(open.contains(potentialMove) || closed.contains(potentialMove)) ){
                    potentialMove.setgValue( n.getgValue() + totalCost );
                    open.add( potentialMove );
                }

                // Step 4b
                if( open.contains(potentialMove) ){
                    if( potentialMove.getgValue() > (n.getgValue() +  totalCost) ){
                        potentialMove.setgValue( n.getgValue() +  totalCost );
                    }
                }

                // Step 4c
                if( closed.contains(potentialMove) ){
                    if( potentialMove.getgValue() > (n.getgValue() +  totalCost) ){
                        potentialMove.setgValue( n.getgValue() +  totalCost );
                        open.add( closed.get(closed.indexOf(potentialMove)) );
                    }
                }
            }
        }
    }

    // Method to generate all the boards from the moves in the hash
    // returns an array of boards!
    private static ArrayList<Board> generateBoards( Board currentBoard ){

//        System.out.println( currentBoard + " " + currentBoard.gethValue());

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

    private static Tile[] generateGoal(){
        char[] goalTiles = { 'W','W','W', 'E', 'B','B','B', };
        return new Board( goalTiles ).getBoard();
    }

}
