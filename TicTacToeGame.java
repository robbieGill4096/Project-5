import java.awt.Point;
import java.lang.reflect.Type;

/**
 * 
 * 
 * @author robbiegill
 *The TicTacToeGame acts as a back end class providing functionality for the TicTacToeGUI class.
 *
 */
public class TicTacToeGame implements TicTacToe {
	public final int DIM = 3;
	// BoardChoice[][] board = new BoardChoice[DIM][DIM];
	TicTacToe.BoardChoice[][] board = {
			{ TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN },
			{ TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN },
			{ TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN, TicTacToe.BoardChoice.OPEN } };
	private GameState state;
	private int numOfMoves = 0;
	private Point[] moveHistory = new Point[DIM * DIM];
	// test
	// numOfMoves =0;
	// board already populated
	//
	/**
	 * Reset the game.
	 * All board positions are OPEN and the game is IN_PROGRESS.
	 */
	@Override
	public void newGame() {
		numOfMoves = 0;
		state = GameState.IN_PROGRESS;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				board[i][j] = BoardChoice.OPEN;
			}
		}
	}
	/**
	 * If the choice is invalid for any reason, return false.
	 * A choice is invalid if the game is over, the position is
	 * already claimed, or the player made the previous choice
	 * (no player can make two moves in a row). 
	 * If the chosen row, column position is not already claimed
	 * and the game is not already over, claim it for the player.
	 * A winning move or choosing the last open position ends
	 * the game.
	 * 
	 * @param player expecting either BoardChoice.X or BoardChoice.O
	 * @param row row to claim - value from 0 to 2
	 * @param col column to claim - value from 0 to 2
	 * @return true if the choice was a valid move, else false
	 */
	@Override
	public boolean choose(BoardChoice player, int row, int col) {
		// add move to a point object
		// if gameOver() == true return false
		if ((numOfMoves % 2 == 0) && (player == BoardChoice.O)) {
			return false;
		}
		if ((numOfMoves % 2 > 0) && (player == BoardChoice.X)) {
			return false;
		}
		
		if (gameOver() == true) {
			return false;
		}
		// if position is claimed != BoardChoice.OPEN or (player made previous choice)
		// return false <-----you could use getMOves movehistory[lastindex] %/2
		if ((board[row][col] != BoardChoice.OPEN)) {
			
			return false;
		}
		if ((board[row][col] == BoardChoice.OPEN) && (gameOver() == false)) {
			if (player == BoardChoice.X) {
				board[row][col] = BoardChoice.X;

				Point plot = new Point(row, col);
				moveHistory[numOfMoves] = plot;
				numOfMoves += 1;
				return true;
			} if (player == BoardChoice.O) {
				board[row][col] = BoardChoice.O;

				Point plot = new Point(row, col);
				moveHistory[numOfMoves] = plot;

				numOfMoves += 1;
				return true;
			}

		}

		return false;
	}
	/**
	 * Return true if either player X or O has achieved
	 * 3-in-a-row, whether vertically, horizontally, or diagonally,
	 * or if all positions have been claimed without a winner.
	 * game over also sets the state depending on which player achieved the three in a row.
	 * @return true if the game is over, else false
	 */
	@Override
	public boolean gameOver() {

		for (int x = 0; x < DIM; x++) {
			if ((board[x][0] == board[x][1]) && (board[x][1] == board[x][2]) && board[x][0] != BoardChoice.OPEN) {
				if (board[x][0] == BoardChoice.X) {

					state = GameState.X_WON;
					return true;
				} else {

					state = GameState.O_WON;
					return true;
				}
			}
			if ((board[0][x] == board[1][x]) && (board[1][x] == board[2][x]) && board[0][x] != BoardChoice.OPEN) {
				if (board[0][x] == BoardChoice.X) {

					state = GameState.X_WON;

					return true;
				} else {

					state = GameState.O_WON;
					return true;
				}
			}

		}
		if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && board[2][0] != BoardChoice.OPEN) {

			if (board[2][0] == BoardChoice.X) {
				state = GameState.X_WON;

				return true;
			} else {
				state = GameState.O_WON;
				return true;
			}
			// return game is over
		}
		if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && board[0][0] != BoardChoice.OPEN) {

			if (board[0][0] == BoardChoice.X) {
				state = GameState.X_WON;

				return true;
			} else {
				state = GameState.O_WON;
				return true;
			}
			// return game is over

		}
		
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (board[i][j] == BoardChoice.OPEN) {
					state = GameState.IN_PROGRESS;
					return false;
				}

			}
		}
		
		state = GameState.TIE;
			return true;
		/**
		state = GameState.IN_PROGRESS;
		return false;
		**/

	}
	/**
	 * Return the winner (X, O, or TIE) if the game is over, or
	 * IN_PROGRESS if the game is not over.
	 * 
	 * @return state: which represents the winner of a completed game or IN_PROGRESS
	 */
	@Override
	public GameState getGameState() {
		if (gameOver() == true) {
			return state;
		} else {
			return state.IN_PROGRESS;
		}

	}
	/**
	 * Get the current game board with each position marked as
	 * belonging to X, O, or OPEN.
	 * Preserve encapsulation by returning a copy of the original data.
	 * 
	 * @return copyB: current copy, showing the current game board.
	 */
	@Override
	public BoardChoice[][] getGameGrid() {
		BoardChoice[][] copyB = new BoardChoice[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				copyB[i][j] = board[i][j];
			}
		}

		return copyB;

	}
	/**
	 * Get the sequence of moves, where even indexes correspond to the
	 * first player's moves and odd indexes correspond to the second
	 * player's moves.
	 * NOTE: Move rows are stored in the first coordinate, "x", and move
	 * columns are stored in the second coordinate, "y". While possibly
	 * counter-intuitive, it is intentional.
	 * Preserve encapsulation by returning a copy of the original data.
	 * 
	 * @return array showing the sequence of claimed positions as Point objects.
	 */
	@Override
	public Point[] getMoves() {
		Point[] MovesToWin = new Point[numOfMoves];
		for (int i = 0; i < numOfMoves; i++) {
			MovesToWin[i] = moveHistory[i];
		}
		return MovesToWin;

	}

}