package battleship;

import java.util.ArrayList;
import java.util.List;
//This class represents ships on the board, consists of coordinates and a ShipPosition
public class Ship {

    private final int length;
    private final String name;
    private boolean isSank;
    private ShipPosition position;
    private final List<Coordinates> coordinatesList;

    Ship(ShipValues shipValues) {
        this.length = shipValues.getLength();
        this.name = shipValues.getName();
        this.isSank = false;
        this.coordinatesList = new ArrayList<>();
    }

    public void addCoordinates(Coordinates coordinates) {
        coordinatesList.add(coordinates);
    }

    public List<Coordinates> getCoordinatesList() {
        return coordinatesList;
    }

    public void setPosition(ShipPosition position) {
        this.position = position;
    }

    public ShipPosition getPosition() {
        return this.position;
    }

    public String getName() {
        return name;
    }

    public boolean isSank() {
        return isSank;
    }

    public void setIsSank(boolean isSank) {
        this.isSank = isSank;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getLength() {
        return this.length;
    }
}
