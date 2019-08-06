import java.awt.Point;
import java.lang.reflect.Type;

import portOFmarrow.guiclass.BoardChoice;

//stores and works with game data 
//a 2D Player array from the game grid
//1D Point array for moves.
public class TicTacToeGame implements TicTacToe {
	public static int counter = 0;
	

	/**
	 * This is the class you must write. It must correctly implement the TicTacToe
	 * interface to enforce all rules of the game to pass all tests in
	 * TicTacToeTester and work with TicTacToeGUI. You must use arrays (not
	 * ArrayLists) for storing and working with game data - a 2D Player array for
	 * the game grid, and a 1D Point array for moves.
	 * 
	 * Important: The correctness of your TicTacToeGame implementation will be
	 * determined by passing TicTacToeTester - not by whether a game can be played
	 * in the GUI.
	 * 
	 * 
	 */
	BoardChoice[][] grid = { { BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN },
			{ BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN },
			{ BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN } };
	Point[] moveHistory = new Point[600];
	@Override
	public void newGame() {
		// OPEN
		counter = counter - counter;
		// set game in progress
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; i < grid.length; i++) {

				grid[i][j] = BoardChoice.OPEN;
			}
		}

	}

	/**
	 * If the choice is invalid for any reason, return false. A choice is invalid if
	 * the game is over, the position is already claimed, or the player made the
	 * previous choice (no player can make two moves in a row). If the chosen row,
	 * column position is not already claimed and the game is not already over,
	 * claim it for the player. A winning move or choosing the last open position
	 * ends the game.
	 * 
	 * @param player
	 *            expecting either BoardChoice.X or BoardChoice.O
	 * @param row
	 *            row to claim - value from 0 to 2
	 * @param col
	 *            column to claim - value from 0 to 2
	 * @return true if the choice was a valid move, else false
	 */

	@Override
	public boolean choose(BoardChoice player, int row, int col) {

		if ((player == BoardChoice.X)) {
			if (grid[row][col] == BoardChoice.OPEN) {
				grid[row][col] = BoardChoice.X;
				Point point = new Point(row, col);
				moveHistory[counter] = point;
				counter += 1;
				return true;
			} else {
				return false;
			}
		}
		if ((player == BoardChoice.O)) {
			if (grid[row][col] == BoardChoice.OPEN) {
				grid[row][col] = BoardChoice.O;
				Point point = new Point(row, col);
				moveHistory[counter] = point;
				counter += 1;
				return true;
			} else {
				return false;
			}
		}
		// tells which player to select
		// check if valid
		// cliams speicific row index.
		// adds claimed data to the array

		return false;
	}

	/**
	 * Return true if either player X or O has achieved 3-in-a-row, whether
	 * vertically, horizontally, or diagonally, or if all positions have been
	 * claimed without a winner.
	 * 
	 * @return true if the game is over, else false
	 */
	@Override
	public boolean gameOver() {

		// horizontal
		if ((grid[0][0] == grid[0][1]) && (grid[0][1] == grid[0][2]) && grid[0][0] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("first row win horizontal");
			return true;
		}
		if ((grid[1][0] == grid[1][1]) && (grid[1][1] == grid[1][2]) && grid[1][0] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("second row win horizontal");
			return true;
		}
		if ((grid[2][0] == grid[2][1]) && (grid[2][1] == grid[2][2]) && grid[2][0] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("third row win horizontal");
			return true;
		}
		// vertical
		if ((grid[0][0] == grid[1][0]) && (grid[1][0] == grid[2][0]) && grid[0][0] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("first column win veritcal");
			return true;
		}
		if ((grid[0][1] == grid[1][1]) && (grid[1][1] == grid[2][1]) && grid[0][1] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("second column win veritcal");
			return true;
		}
		if ((grid[0][2] == grid[1][2]) && (grid[1][2] == grid[2][2]) && grid[0][2] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("third column win veritcal");
			return true;
		}
		// diagonal
		if ((grid[2][0] == grid[1][1]) && (grid[1][1] == grid[0][2]) && grid[2][0] != BoardChoice.OPEN) {

			// return game is over
			System.out.println("diagonal win Right to left");
			return true;
		}
		if ((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]) && grid[0][0] != BoardChoice.OPEN) {
			// return game is over
			System.out.println("diagonal win left to Right");
			return true;
		}
		int count = 0;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (grid[i][j] == BoardChoice.OPEN) {
					count += 1;
				}

			}
			// check if theres any moves left if not ends the game
		}

		if (count <= 0) {

			return true;
		}
		return false;

	}

	@Override
	/**
	 * Return the winner (X, O, or TIE) if the game is over, or IN_PROGRESS if the
	 * game is not over.
	 * 
	 * @return the winner of a completed game or IN_PROGRESS
	 */
	public GameState getGameState() {
		// check if move was even or odd to determine who won. then
		// returns game state of winner
		if (gameOver() == true && (counter % 2 == 0)) {
			System.out.println("XWON");
			return GameState.X_WON;

		}
		if (gameOver() == true && (counter % 2 > 0)) {
			return GameState.O_WON;
		}

		if (gameOver() == false) {
			return GameState.IN_PROGRESS;
		} else {
			return GameState.TIE;
		}
	}

	/**
	 * Get the current game board with each position marked as belonging to X, O, or
	 * OPEN. Preserve encapsulation by returning a copy of the original data.
	 * 
	 * @return array showing the current game board
	 */
	@Override
	public BoardChoice[][] getGameGrid() {

		BoardChoice[][] copyGrid = { { BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN },
				{ BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN },
				{ BoardChoice.OPEN, BoardChoice.OPEN, BoardChoice.OPEN } };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				copyGrid[i][j] = grid[i][j];
			}
		}
		return copyGrid;

		// return grid;
	}

	/**
	 * Get the sequence of moves, where even indexes correspond to the first
	 * player's moves and odd indexes correspond to the second player's moves. NOTE:
	 * Move rows are stored in the first coordinate, "x", and move columns are
	 * stored in the second coordinate, "y". While possibly counter-intuitive, it is
	 * intentional. Preserve encapsulation by returning a copy of the original data.
	 * 
	 * @return array showing the sequence of claimed positions
	 */
	@Override
	public Point[] getMoves() {

		Point[] moves = new Point[counter + 1];

		for (int x = 0; x < counter; x++) {

			moves[x] = moveHistory[x];
		}

		return moves;
	}
}
