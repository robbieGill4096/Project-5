import java.awt.Point;
import java.lang.reflect.Type;

//stores and works with game data 
//a 2D Player array from the game board
//1D Point array for moves.
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

	@Override
	public GameState getGameState() {
		if (gameOver() == true) {
			return state;
		} else {
			return state.IN_PROGRESS;
		}

	}

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

	@Override
	public Point[] getMoves() {
		Point[] MovesToWin = new Point[numOfMoves];
		for (int i = 0; i < numOfMoves; i++) {
			MovesToWin[i] = moveHistory[i];
		}
		return MovesToWin;

	}

}