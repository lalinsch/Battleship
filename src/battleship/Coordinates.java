package battleship;
/*
This class represents coordinates, which are 2 integers parsed from a String input
 */
public class Coordinates {
    private String input;
    private int x;
    private int y;
    private boolean isValid;

    public Coordinates(String input) {
        if (checkIfInputIsValid(input)) {
            this.input = input;
            translateCoordinates();
        } else {
            isValid = false;
        }
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.isValid = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isValid() {
        return isValid;
    }

    //Uses regex to verify the input is of a valid format e.f. "A8"
    private boolean checkIfInputIsValid(String input) {
        String regex = "^[A-J](?:[1-9]|10)$";
        return input.toUpperCase().matches(regex);
    }

    //Converts the sections of the input into integer values
    private void translateCoordinates() {
        String[] parts = input.split("(?<=\\D)(?=\\d)"); //e.g. "A8"
        x = parts[0].toUpperCase().charAt(0) - 65; //for the character side (A = 0)
        y = Integer.parseInt(parts[1]) - 1; //for the integere (1 = 0;
        isValid = true;
    }

    //the equales method is overriden to be able to compare two instances of the coordinates class
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) obj;
        return getX() == coordinates.getX() && getY() == coordinates.getY();
    }
}
