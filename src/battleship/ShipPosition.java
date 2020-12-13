package battleship;

//The ship position class allows us to check if the ship is able to be place on the board, and also lets us identify its orientation
public class ShipPosition {
    private Coordinates a;
    private Coordinates b;
    private int aX;
    private int aY;
    private int bX;
    private int bY;
    private boolean isValid;
    private int length;

    public ShipPosition(Coordinates a, Coordinates b) {
        if (checkIfCoordinatesAreValid(a, b)) {
            this.a = a;
            this.b = b;
            parseCoordinates();
        }
    }

    public int getaX() {
        return aX;
    }

    public int getaY() {
        return aY;
    }

    public int getbY() {
        return bY;
    }

    public int getbX() {
        return bX;
    }

    public int getLength() {
        return length + 1;
    }

    public boolean isValid() {
        return isValid;
    }

    public void parseCoordinates() {
        isValid = true;
        aX = a.getX();
        aY = a.getY();
        bX = b.getX();
        bY = b.getY();
        if (isHorizontal()) {
            //if input was entered in reverse, it swaps the start and end variables
            if (aY > bY) {
                int temp = aY;
                aY = bY;
                bY = temp;
            }
            //and then calculates the length
            length = bY - aY;
        } else if (isVertical()) {
            if (aX > bX) {
                int temp = aX;
                aX = bX;
                bX = temp;
            }
            length = bX - aX;
        }
    }

    private boolean checkIfCoordinatesAreValid(Coordinates a, Coordinates b) {
        return a.isValid() && b.isValid();
    }

    public boolean isHorizontal() {
        return aX == bX;
    }

    public boolean isVertical() {
        return aY == bY;
    }


}
