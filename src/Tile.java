/**
 * Created by Barret J. Nobel on 3/31/2016.
 */

public class Tile {
    private char color;
    private int currentLocation;

    public Tile( char color, int currentLocation ){
        this.setColor( color );
        this.setCurrentLocation( currentLocation );
    }

    public void setColor( char color ){
        this.color = color;
    }

    public void setCurrentLocation( int currentLocation ){
        this.currentLocation = currentLocation;
    }

    public char getColor(){
        return this.color;
    }

    public int getCurrentLocation(){
        return this.currentLocation;
    }
}
