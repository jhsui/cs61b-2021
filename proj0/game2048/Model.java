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

        /*
         * goal: to make it work for the side North
         */


        // !!! I IGNORE THE SEQUENCE OF THE FOR LOOP!!! WHAT THE HELL
        for (int col = 0; col < size(); col += 1) {

            /* we use this array to track if the tiles on this col are merged or not
             * merged[0] indicates that if row 2 is merged or not
             *  so, if row 2 is merged, then row 3 is smt and row 2 is empty. it's also applied to other rows
             * if row 1 is merged then row 1 is empty
             */

            boolean[] merged = {false, false, false};

            for (int row = size() - 2; row >= 0; row--) {
                Tile t = board.tile(col, row);


                if (t != null) {
                    // row 3 does not move, we start from row 2
                    if (row == 2) {
                        /*
                         * if the values are the same or row 3 is empty, we move; else we dont move
                         *
                         * leave score for later
                         *
                         * !!! we also need to know if the above is changed or not
                         */

                        if (board.tile(col, 3) == null || t.value() == board.tile(col, 3).value()) {
                            board.move(col, 3, t);
                            merged[0] = true;

                            changed = true;

                        }
                    }


                    if (row == 1) {
                        if (isTheAboveEmpty(t)) {
                            board.move(col, 3, t);
                            changed = true;
                        } else {
                            /*
                             * Now this tile's top is not empty,
                             * we first need to know which row is not empty,
                             * then we need to know if the tile has been merged or not
                             *
                             * There are several cases:
                             * 1. Row 3 is filled, row 2 is empty, merged happened
                             * 2. Row 3 is filled, row 2 is empty, merged didn't happen, row 2 was just moved
                             * 3. Row 3 is empty, row 2 is filled, impossible ❌
                             * 4. Row 3 is filled, row 2 is filled, nothing happened before
                             */


                            if (merged[0]) { // merged happened before, then row 2 must be empty
                                /*
                                 * if row 2 was merged, then row 2 is empty
                                 * now we can't merge even if the value is the same as row 3, so we just move up 1 row
                                 */
                                board.move(col, 2, t);
                                changed = true;
                            } else {
                                /*
                                 * merge did not happen
                                 *
                                 * now we first need to know if row 2 is empty
                                 *
                                 * 1. row 2 is filled, we check if the value is the same with row 2 then tell if merge
                                 *
                                 * 2. row 2 is empty, we check if the value is the same with row 3 then tell if merge
                                 */


                                if (board.tile(col, 2) != null) { // row 2 is not empty

                                    if (t.value() == board.tile(col, 2).value()) { // if row 1 is the same as row 2
                                        board.move(col, 2, t);
                                        merged[1] = true;
                                        changed = true;
                                    }
                                    // row 2 is not empty and not the same as row 1, we do nothing

                                } else { // row 2 is empty, so row 3 can't be empty
                                    if (t.value() == board.tile(col, 3).value()) { // row 1 the same as row 3
                                        board.move(col, 3, t);
                                        merged[1] = true;
                                        changed = true;
                                    } else { // row1 different from row3, so we just move it to row2
                                        board.move(col, 2, t);
                                        changed = true;
                                    }

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
                             *
                             *
                             * !!!I IGNORE THE TRUTH THAT IF THE ABOVE IS A MERGED TILE!!!
                             *
                             * ROW 1 CAN'T BE A MERGED ONE.
                             * JUST ROW 2 AND ROW 3
                             *
                             *
                             * now the above is not empty.
                             *
                             * What happened before this???
                             * Each tile above row 0 was moved up until it can't be moved. So the following 2.1 and 2.3 is impossible.
                             * All the tile above row 0 are different.
                             *
//                             * cases:
//                             * 1. one tile exists: row 3/row 2/row 1
//                             *
//                             * 2. two tiles exist:
//                             *      2.1: row 3 is empty.
//                             *           We need to know if row 2 and row 1 are the same.
//                             *           If they are, then we merge them and move the new tile to row 3 and then move row 1 to row 2.
//                             *           If they aren't, then we need to tell if row 1 and row 0 are the same, if they are, we move row 2 to row 3,
//                             *           and then move the new tile of row 1 and row 0 to row 2
//                             *      2.2: row 2 is empty. We do the same as 2.1
//                             *      2.3: row 1 is empty. We do the same as 2.1
//                             *
//                             * 3. three tiles: we need to know if the above is the same or not




                             *
                             * New cases:
                             * 1. Only one tile exists: Then it is row 3
                             * 2. Two tiles exist: They are row 2 and row 1.
                             * 3. Three tiles exist:
                             *
                             *
                             * !!! MY PROBLEM HERE IS THAT I JUST CONSIDER THE SITUATION THAT THERE IS PROBLEM BUT I IGNORE THE FACT THAT I STILL NEED TO MOVE IT TO THE TOPEST EMPTY SPOT
                             *
                             */
                            if (board.tile(col, 1) != null) { // Row 1 isn't empty.
                                if (t.value() == board.tile(col, 1).value()) { // If row 0 and row 1 are the same, we move. If not, we do nothing.
                                    board.move(col, 1, t);
                                    changed = true;
                                }

                            } else if (board.tile(col, 2) != null) { // Row 2 isn't empty
                                if (t.value() == board.tile(col, 2).value()) {
                                    board.move(col, 1, t);
                                    changed = true;
                                }

                            } else if (board.tile(col, 3) != null) {
                                if (t.value() == board.tile(col, 3).value()) {
                                    board.move(col, 3, t);
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
