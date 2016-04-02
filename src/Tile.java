/**
 * Created by Barret J. Nobel on 3/31/2016.
 * Class for the a Tile object
 * contains the color and current location in the board
 */

public class Tile implements Cloneable {
    private char color; //.......... private instance variable to hold the color
    private int currentLocation; //. private instance variable to hold the current location in the board

    // Constructor with parameters
    // takes in the color and current location
    public Tile( char color, int currentLocation ){
        this.setColor( color ); //..................... sets the color
        this.setCurrentLocation( currentLocation ); //. sets currentLocation
    }

    // Method to set the color instance variable
    public void setColor( char color ){
        this.color = color;
    }

    // Method to set the currentLocation instance variable
    public void setCurrentLocation( int currentLocation ){
        this.currentLocation = currentLocation;
    }

    // Method to return the color
    public char getColor(){
        return this.color;
    }

    // Method to return the currentLocation
    public int getCurrentLocation(){
        return this.currentLocation;
    }

    // Overridden toString
    // returns a formatted string with the color and location in board
    public String toString(){
        return String.format( "Color: %c%nLocation: %d%n", color, currentLocation );
    }

    // Overridden method to clone the object
    protected Object clone() {
        Tile clone = null; //...................................... cloned Tile object to return

        try {
            clone = (Tile)super.clone(); //........................ call super class
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
        }

        clone.setCurrentLocation( this.getCurrentLocation() ); //.. deep copy instance variables
        clone.setColor( this.getColor() );

        return clone;
    }
}
