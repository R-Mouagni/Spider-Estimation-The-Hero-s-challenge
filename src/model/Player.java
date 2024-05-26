package model;

/**
 * Represents a player in the game
 */
public class Player {
    private int row;
    private int col; 
    private int parcoursIndex; 
    private static int lastId = 0;
    private int id;

    /**
     * Constructs a new Player object with the specified row and column positions
     *
     * @param row The row position of the player
     * @param col The column position of the player
     */
    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        parcoursIndex = 0;
        this.id = ++lastId;
    }

    /**
     * Retrieves the row position of the player
     *
     * @return The row position of the player
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column position of the player
     *
     * @return The column position of the player
     */
    public int getCol() {
        return col;
    }

    /**
     * Retrieves the course index of the player
     *
     * @return The course index of the player
     */
    public int getParcoursIndex() {
        return parcoursIndex;
    }

    /**
     * Sets the course index of the player
     *
     * @param parcoursIndex The course index to be set
     */
    public void setParcoursIndex(int parcoursIndex) {
        if (parcoursIndex >= 0)
            this.parcoursIndex = parcoursIndex;
    }

    /**
     * Moves the player to the specified row and column positions
     *
     * @param newRow The new row position for the player
     * @param newCol The new column position for the player
     */
    public void moveTo(int newRow, int newCol) {
        if (newRow > 0 && newCol > 0) {
            this.row = newRow;
            this.col = newCol;
        }
    }

    /**
     * Retrieves the coordinates of the player
     *
     * @return The coordinates of the player in the format (row;col)
     */
    public String getCoordinates() {
        return "(" + getRow() + ";" + getCol() + ")";
    }

    /**
     * Retrieves a string representation of the player
     *
     * @return A string representation of the player including their ID and coordinates
     */
    @Override
    public String toString() {
        return "Player " + id + " at coordinates " + getCoordinates();
    }

    /**
     * Retrieves a string representation of the player for a duel game
     *
     * @return A string representation of the player including their ID and character name
     */
    public String toStringDuel() {
        switch (id) {
            case 1:
                return "Player 1 (Red Spider-Man)";
            case 2:
                return "Player 2 (Black Spider-Man)";
            case 3:
                return "Player 3 (Yellow Spider-Man)";
            case 4:
                return "Player 4 (Blue Spider-Man)";
            default:
                return "Unknown Player";
        }
    }

    /**
     * Sets the column position of the player
     *
     * @param col The column position to be set
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Sets the row position of the player
     *
     * @param row The row position to be set
     */
    public void setRow(int row) {
        this.row = row;
    }
}
