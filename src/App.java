import java.util.Arrays;

/**
 * Created by Barret J. Nobel on 3/31/2016.
 */
public class App {
    public static void main( String[] args ){
        Board newBoard = new Board();

        System.out.println(Arrays.toString(newBoard.getBoard()) );
//        System.out.println( newBoard.getAvailableMoves().keySet() );
    }

}
