package battleship;
//Using enum for the constant ship values, we iterate through this to place the ships on the board for each player.
public enum ShipValues {
    Carrier(5, "Aircraft Carrier"),
    Battleship(4, "Battleship"),
    Submarine(3, "Submarine"),
    Cruiser(3, "Cruiser"),
    Destroyer(2, "Destroyer");

    private final int length;
    private final String name;


    ShipValues(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}
