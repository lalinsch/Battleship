package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
This class represents a player, its instance variables, and its methods
 */
public class Player {
    private final Board board;
    private final String name;
    private final List<Ship> shipList;
    private boolean isWinner;
    private final Scanner scan;

    public Player(String name, Scanner scan) {
        this.board = new Board();
        this.name = name;
        this.shipList = new ArrayList<>();
        this.isWinner = false;
        this.scan = scan;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean isWinner() {
        return isWinner;
    }

    //This method allows the player to input the positions of the ships on their board
    public void placeShips() {
        System.out.println(name + ", place your ships on the game field");
        board.printBoard(true);
        for (ShipValues shipValues : ShipValues.values()) {
            Ship ship = new Ship(shipValues);
            while (true) {
                System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells)");
                ShipPosition position = new ShipPosition(new Coordinates(scan.next()), new Coordinates(scan.next()));
                if (!position.isValid()) {
                    System.out.println("Error: Input is invalid");
                } else if (position.getLength() != ship.getLength()) {
                    System.out.println("Error: incorrect length for " + ship.getName());
                } else if (board.positionIsTaken(position)) {
                    System.out.println("Error: One or more of the positions is taken!");
                } else if (board.shipIsAdjacent(position)) {
                    System.out.println("Error, too close to another ship");
                } else {
                    ship.setPosition(position);
                    takePositions(ship);
                    break;
                }
            }
            board.printBoard(true);
            System.out.println();
        }
    }

    //If the input is valid, this method uses the board class to specify the values on the input
    public void takePositions(Ship ship) {
        int start;
        int end;
        ShipPosition position = ship.getPosition();
        Coordinates coordinates;

        if (position.isHorizontal()) {
            int row = position.getaX();
            start = position.getaY();
            end = position.getbY();
            for (int j = start; j <= end; j++) {
                coordinates = new Coordinates(row, j);
                board.takeSpace(coordinates);
                ship.addCoordinates(coordinates);
            }
            shipList.add(ship);
        } else if (position.isVertical()) {
            int col = position.getaY();
            start = position.getaX();
            end = position.getbX();
            for (int i = start; i <= end; i++) {
                coordinates = new Coordinates(i, col);
                board.takeSpace(coordinates);
                ship.addCoordinates(coordinates);
            }
            shipList.add(ship);
        }
    }

    //This method is used at every turn to attack the opponent's board
    public void attack(Player opponentPlayer) {
        Board opponentBoard = opponentPlayer.getBoard();
        while (true) {
            opponentBoard.printBoard(false);
            System.out.println("---------------------");
            board.printBoard(true);
            System.out.println(name + ", it's your turn:");
            Coordinates coordinates = new Coordinates(scan.next());
            if (!coordinates.isValid()) {
                System.out.println("Error: Incorrect input");
            } else if (opponentBoard.coordinateIsTaken(coordinates) || opponentBoard.coordinateIsHit(coordinates)) {
                opponentBoard.takeShot(coordinates);
                Ship ship = opponentPlayer.getShipFromCoordinates(coordinates);
                if (ship != null) {
                    if (opponentPlayer.checkIfShipIsSank(ship)) {
                        if (opponentPlayer.areAllShipsSunk()) {
                            isWinner = true;
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else {
                            System.out.println("You sank a ship!");
                        }
                        break;
                    } else {
                        System.out.println("You hit a ship!");
                    }
                }
                break;
            } else if (!opponentBoard.coordinateIsTaken(coordinates)) {
                opponentBoard.markMissed(coordinates);
                System.out.println("You missed!");
                break;
            }
        }
    }

    public boolean areAllShipsSunk() {
        for (Ship ship : shipList) {
            if (!ship.isSank()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfShipIsSank(Ship ship) {
        for (Coordinates coordinates : ship.getCoordinatesList()) {
            if (!board.coordinateIsHit(coordinates)) {
                return false;
            }
        }
        ship.setIsSank(true);
        return true;
    }

    private Ship getShipFromCoordinates(Coordinates coordinates) {
        for (Ship ship : shipList) {
            for (Coordinates coordinate : ship.getCoordinatesList()) {
                if (coordinates.equals(coordinate)) {
                    return ship;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
