package battleship;
/*
This class represents the values on the board, it consists of a 2D char array
It takes objects from the coordinates class to place, change and check the status of a cell.
It can be modified to make boards of larger sizes
* */
public class Board {
    private final char[][] board;
    private final int size;

    public Board() {
        this.size = 10;
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '~';
            }
        }
    }

    public void takeSpace(Coordinates coordinates) {
        int col = coordinates.getX(); int row = coordinates.getY();
        board[col][row] = 'O';
    }

    public void takeShot(Coordinates coordinates) {
        board[coordinates.getX()][coordinates.getY()] = 'X';
    }

    public void markMissed(Coordinates coordinates) {
        board[coordinates.getX()][coordinates.getY()] = 'M';
    }

    public boolean coordinateIsTaken(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return board[x][y] == 'O';
    }

    public boolean coordinateIsHit(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return board[x][y] == 'X';
    }

    //checks if any of the required positions are already taken by a different ship
    public boolean positionIsTaken(ShipPosition position) {
        int start;
        int end;
        int col;
        int row;
        if (position.isHorizontal()) {
            col = position.getaX();
            start = position.getaY();
            end = position.getbY();
            for (int i = start; i < end; i++) {
                if (board[col][i] != '~') {
                    return true;
                }
            }
        } else if (position.isVertical()) {
            row = position.getaY();
            start = position.getaX();
            end = position.getbX();
            for (int i = start; i < end; i++) {
                if (board[i][row] != '~') {
                    return true;
                }
            }
        }
        return false;
    }

    //Checks if there's a ship in one of the adjacent cells
    public boolean shipIsAdjacent(ShipPosition position) {
        int start;
        int end;
        int col;
        int row;
        if (position.isHorizontal()) {
            col = position.getaX();
            start = position.getaY();
            end = position.getbY();
            for (int i = start; i <= end; i++) {
                if (col == size - 1) {
                    if (board[col - 1][i] != '~') {
                        return true;
                    }
                } else if (col == 0) {
                    if (board[col + 1][i] != '~') {
                        return true;
                    }
                } else {
                    if (board[col - 1][i] != '~' || board[col + 1][i] != '~') {
                        return true;
                    }
                }
                if (i == start && start != 0) {
                    if (board[col][i - 1] != '~') {
                        return true;
                    }
                }
                if (i == end && end != size - 1) {
                    if (board[col][i + 1] != '~') {
                        return true;
                    }
                }
            }
        }
        if (position.isVertical()) {
            row = position.getaY();
            start = position.getaX();
            end = position.getbX();
            for (int i = start; i <= end; i++) {
                if (row == size - 1) {
                    if (board[i][row - 1] != '~') {
                        return true;
                    }
                } else if (row == 0) {
                    if (board[i][row + 1] != '~') {
                        return true;
                    }
                } else {
                    if (board[i][row - 1] != '~' || board[i][row + 1] != '~') {
                        return true;
                    }
                }
                if (i == start && start != 0) {
                    if (board[i - 1][row] != '~') {
                        return true;
                    }
                }
                if (i == end && end != size - 1) {
                    if (board[i + 1][row] != '~') {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //prints the board with the row and column names, can be customised to either show or hide the ships on the board
    public void printBoard(boolean isVisible) {
        char row = 'A';
        int col = 1;
        for (int i = 0; i <= size; i++) {
            if (i == 0) {
                System.out.print("" + " ");
            } else {
                System.out.print(row + " ");
                row++;
            }
            for (int j = 0; j <= size; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("" + " ");
                    } else {
                        System.out.print(col + " ");
                        col++;
                    }
                } else if (j < size) {
                    if (isVisible) {
                        System.out.print(board[i - 1][j] + " ");
                    } else {
                        char hidden = board[i - 1][j];
                        if (hidden != 'O') {
                            System.out.print(hidden + " ");
                        } else {
                            System.out.print('~' + " ");
                        }

                    }
                }
            }
            System.out.println();
        }
    }

}
