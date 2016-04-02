/**
 * Created by Barret J. Nobel on 3/31/2016.
 */
public class App {
    public static void main( String[] args ){
        Board newBoard = new Board();

//        System.out.println(Arrays.toString(newBoard.getBoard()) );
//        System.out.println( newBoard.getAvailableMoves().values() );
//        System.out.println( newBoard.getgValue() );

        boolean answer = newBoard.makeAMove( 0 );
        answer = newBoard.makeAMove( 1 );
        answer = newBoard.makeAMove( 0 );
//        System.out.println( answer );
        System.out.println( newBoard );
    }

}
