package game2048;

import java.util.Formatter;
import java.util.Iterator;
import java.util.Observable;


/**
 * The state of a game of 2048.
 *
 * @author TODO: YOUR NAME HERE -- jiahan
 */
public class Model extends Observable {
    /**
     * Current contents of the board.
     */
    private Board board;
    /**
     * Current score.
     */
    private int score;
    /**
     * Maximum score so far.  Updated when game ends.
     */
    private int maxScore;
    /**
     * True iff game is ended.
     */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /**
     * Largest piece value.
     */
    public static final int MAX_PIECE = 2048;

    /**
     * A new 2048 game on a board of size SIZE with no pieces
     * and score 0.
     */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /**
     * A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes.
     */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /**
     * Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     * 0 <= COL < size(). Returns null if there is no tile there.
     * Used for testing. Should be deprecated and removed.
     */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /**
     * Return the number of squares on one side of the board.
     * Used for testing. Should be deprecated and removed.
     */
    public int size() {
        return board.size();
    }

    /**
     * Return true iff the game is over (there are no moves, or
     * there is a tile with value 2048 on the board).
     */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /**
     * Return the current score.
     */
    public int score() {
        return score;
    }

    /**
     * Return the current maximum game score (updated at end of game).
     */
    public int maxScore() {
        return maxScore;
    }

    /**
     * Clear the board to empty and reset the score.
     */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /**
     * Add TILE to the board. There must be no Tile currently at the
     * same position.
     */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /**
     * Tilt the board toward SIDE. Return true iff this changes the board.
     * <p>
     * 1. If two Tile objects are adjacent in the direction of motion and have
     * the same value, they are merged into one Tile of twice the original
     * value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     * tilt. So each move, every tile will only ever be part of at most one
     * merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     * value, then the leading two tiles in the direction of motion merge,
     * and the trailing tile does not.
     */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        for (int col = 0; col < board.size(); col++) {

            boolean[] merged = {false, false}; // Used to mark if row 3 & 2, 2 & 1 are merged or not.

            for (int row = board.size() - 2; row >= 0; row--) { // Start from row 2.
                Tile t = board.tile(col, row);

                if (t != null) {

                    if (row == 2) {
                        if (isTheAboveEmpty(t)) { // Just move, no merge
                            board.move(col, 3, t);
                            changed = true;
                        } else if (board.tile(col, 3).value() == t.value()) { // we are going to merge
                            score += 2 * t.value();
                            board.move(col, 3, t);
                            changed = true;
                            merged[0] = true; // row 2 was merged to row 3, so if merged[0] == false, then row 3 can not be merged anymore and row 2 is empty
                        }
                    }

                    if (row == 1) {
                        if (isTheAboveEmpty(t)) {
                            board.move(col, 3, t);
                            changed = true;
                        } else {
                            /*
                            Above is not empty.
                            Only possible cases are:
                                1. Row 3 is filled and row 2 is empty
                                2. Row 3 and Row 2 are both filled and they are not the same.
                             */

                            if (board.tile(col, 2) == null) { // Row 2 is empty


                                if (!merged[0]) { // Row 3 has not been merged.
                                    if (t.value() == board.tile(col, 3).value()) { // Row 1 is equal to Row 3.
                                        score += 2 * t.value();
                                        board.move(col, 3, t);
                                        changed = true;
                                        merged[0] = true; // How do we deal with here??? How to set the value of the array??? Not the situation is Row 3 is merged and row 2 is empty and 1 is empty

                                    }
                                } else { // Row 3 has been merged.
                                    board.move(col, 2, t);
                                    changed = true;

                                }

                            } else { // Row 2 isn't empty
                                if (t.value() == board.tile(col, 2).value()) { // Row 1 == Row 2.
                                    score += 2 * t.value();
                                    board.move(col, 2, t);
                                    changed = true;
                                    merged[1] = true;
                                }

                            }

                        }

                    }

                    if (row == 0) {
                        if (isTheAboveEmpty(t)) {
                            board.move(col, 3, t);
                            changed = true;
                        } else {
                            /*
                            Cases:
                                1. No empty.
                                2. One empty, then it must be row 1
                                3. Two empty, then Row 3 is not empty.
                             */

                            if (board.tile(col, 1) == null) {
                                if (board.tile(col, 2) == null) { // Row 1 and Row 2 is empty
                                    if (!merged[0]) { // Row 3 has not been merged
                                        if (t.value() == board.tile(col, 3).value()) {
                                            score += 2 * t.value();
                                            board.move(col, 3, t);
                                            changed = true;
                                        }
                                    } else {
                                        board.move(col, 2, t);
                                        changed = true;
                                    }

                                } else { // Row 2 is not empty. Row 1 is empty.
                                    if (!merged[1]) { // Row 2 has not been merged
                                        if (board.tile(col, 2).value() == t.value()) {
                                            score += 2 * t.value();
                                            board.move(col, 2, t);
                                            changed = true;

                                        }

                                    } else { // Row 2 has been merged.
                                        board.move(col, 1, t);
                                        changed = true;
                                    }

                                }
                            } else { // Row 1 is not empty.
                                if (board.tile(col, 1).value() == t.value()) {
                                    score += 2 * t.value();
                                    board.move(col, 1, t);
                                    changed = true;

                                }

                            }
                        }
                    }

                }


            }
        }


        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    // A method to tell if the above of a tile is empty or not
    public boolean isTheAboveEmpty(Tile tile) {
        for (int row = tile.row() + 1; row < size(); row++) {
            if (board.tile(tile.col(), row) != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the game is over and sets the gameOver variable
     * appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /**
     * Determine whether game is over.
     */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /**
     * Returns true if at least one space on the Board is empty.
     * Empty spaces are stored as null.
     */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
//        for (int i = 0; i < b.size(); i++) {
//            for (int j = 0; j < b.size(); j++) {
//                if (b.tile(i, j) == null) {
//                    return true;
//                }
//            }
//        }
//        return false;

        for (Tile tile : b) {
            if (tile == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) != null && b.tile(i, j).value() == MAX_PIECE) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b)) {
            return true;
        }

        for (int col = 0; col < b.size() - 1; col++) {
            for (int row = 0; row < b.size() - 1; row++) {
                //cols are the same, check row; then rows are the same, check col
                if (b.tile(col, row) != null && (b.tile(col, row).value() == b.tile(col, row + 1).value() || b.tile(col, row).value() == b.tile(col + 1, row).value())) {
                    return true;
                }
            }
        }

        //check the last col
        for (int row = 0; row < b.size() - 1; row++) {
            if (b.tile(b.size() - 1, row) != null && b.tile(b.size() - 1, row).value() == b.tile(b.size() - 1, row + 1).value()) {
                return true;
            }
        }

        //check the last row
        for (int col = 0; col < b.size() - 1; col++) {
            if (b.tile(col, b.size() - 1) != null && b.tile(col, b.size() - 1).value() == b.tile(col + 1, b.size() - 1).value()) {
                return true;
            }
        }


        return false;


    }


    @Override
    /** Returns the model as a string, used for debugging. */ public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */ public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */ public int hashCode() {
        return toString().hashCode();
    }
}
