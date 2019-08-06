import java.awt.Point;
import java.lang.reflect.Type;

import portOFmarrow.guiclass.BoardChoice;

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
	public GameState state;
	public int numOfMoves = 0;
	Point[] moveHistory = new Point[DIM * DIM];

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
		if (gameOver() == true) {
			return false;
		}
		// if position is claimed != BoardChoice.OPEN or (player made previous choice)
		// return false <-----you could use getMOves movehistory[lastindex] %/2
		if ((board[row][col] != BoardChoice.OPEN)) {
			return false;
		}
		if ((board[row][col] == BoardChoice.OPEN) && (gameOver() != true)) {
			if (player == BoardChoice.X) {
				board[row][col] = BoardChoice.X;

				Point plot = new Point(row, col);
				moveHistory[numOfMoves] = plot;
				numOfMoves += 1;
				return true;
			} else {
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

		// horizontal
		if ((board[0][0] == board[0][1]) && (board[0][1] == board[0][2]) && board[0][0] != BoardChoice.OPEN) {
			// return game is over
			return true;
		}
		if ((board[1][0] == board[1][1]) && (board[1][1] == board[1][2]) && board[1][0] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		if ((board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) && board[2][0] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		// vertical
		if ((board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && board[0][0] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		if ((board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && board[0][1] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		if ((board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && board[0][2] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		// diagonal
		if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && board[2][0] != BoardChoice.OPEN) {

			// return game is over

			return true;
		}
		if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && board[0][0] != BoardChoice.OPEN) {
			// return game is over

			return true;
		}
		int count = 0;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (board[i][j] == BoardChoice.OPEN) {
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
	public GameState getGameState() {
		// GameState
		if (numOfMoves == 9 && gameOver() == true) {
			
			return state.TIE;}
		
		if (numOfMoves % 2 == 0) {
			return state.O_WON;
		}

			return state.X_WON;
	}
		
	
			
			

	@Override
	public BoardChoice[][] getGameGrid() {
		BoardChoice[][] copyB = board;
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
