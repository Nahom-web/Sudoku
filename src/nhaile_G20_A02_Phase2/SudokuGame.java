package nhaile_G20_A02_Phase2;

/**Title: SudokuGame class
 * Description: This program is the logic behind the game Sudoku. It read in the board you want to play on, it validates each move you make and
 * determines if you win or completed the game. 
 * Copyright: Copyright (c) 2020
 * Company: Heritage College
 * @author Nahom Haile
 * @version 1.0
 ***/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SudokuGame {

	// instance variables

	public String[][] board;
	private int lastMove;
	public int row;
	public int col;
	public String guess;
	public String filename;

	public SudokuGame() {
		board = new String[9][9];
		lastMove = 0;
		filename = "sudoku.txt";
	} // SudokuGame()

	public boolean validateFile(String file) {
		if (file.equalsIgnoreCase("easySudoku.txt") || file.equalsIgnoreCase("mediumSudoku.txt")
				|| file.equalsIgnoreCase("hardSudoku.txt") || file.equalsIgnoreCase("sudoku.txt")) {
			filename = file;
			readFromFile();
			return true;
		} else
			return false;
	} // validateFile(String)

	public void readFromFile() {
		try {
			Scanner input = new Scanner(new File(filename));
			int row = 0;
			while (input.hasNextLine()) {
				String inLine = input.nextLine();
				StringTokenizer tokens = new StringTokenizer(inLine, "~|\r\n");
				for (int col = 0; col < board[row].length; col++) {
					String nextNum = tokens.nextToken();
					board[row][col] = nextNum;
				}
				row++;
			}
		} catch (IOException e) {
			e.getMessage();
		}
	} // readFromFile()

	public boolean validateFileContent() {
		readFromFile();

		if (board == null || board.length != 9 || board[0].length != 9)
			return false;

		// checking row
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (!board[row][col].equals("*")) {
					if (Integer.parseInt(board[row][col]) >= 1 && Integer.parseInt(board[row][col]) <= 9) {
						return true;
					}
				}
			}
		}

		// checking column
		int col = 0;
		for (int row = 0; row < board[col].length; row++) {
			if (!board[col][row].equals("*")) {
				if (Integer.parseInt(board[col][row]) >= 1 && Integer.parseInt(board[col][row]) <= 9) {
					return true;
				}
			}
		}
		col++;

		// checking 3x3 box
		for (int square = 0; square < board.length; square++) {
			for (int i = square / 3 * 3; i < square / 3 * 3 + 3; i++) {
				for (int j = square % 3 * 3; j < square % 3 * 3 + 3; j++) {
					if (!board[i][j].equals("*")) {
						if (Integer.parseInt(board[i][j]) >= 1 && Integer.parseInt(board[i][j]) <= 9) {
							return true;
						}
					}
				}
			}
		}

		return false;
	} // validateFileContent()

	public boolean checkRow(String row) {
		if ((Character.getNumericValue(row.charAt(0)) >= 1 && Character.getNumericValue(row.charAt(0)) <= 9)
				&& row.charAt(1) == ',')
			return true;
		else
			return false;
	} // checkRow(String)

	public boolean checkCol(String col) {
		if (col.length() == 3)
			if (Character.getNumericValue(col.charAt(2)) >= 1 && Character.getNumericValue(col.charAt(2)) <= 9)
				return true;
			else
				return false;
		else
			return false;

	} // checkCol(String)

	public boolean checkGuess(String g) {
		guess = g;
		row = Integer.parseInt(guess.substring(0, 1)) - 1; // removing 1 to make the row accordingly to the board
															// size
		col = Character.getNumericValue(guess.charAt(2)) - 1; // removing 1 to make the column accordingly to the
																// board size

		
		if (board[row][col].equals("*"))
			return true;
		else
			return false;
	} // checkGuess(String)

	public boolean checkValue(String value) {
		if (value.length() == 1) {
			if (Character.isDigit(value.charAt(0))) {
				if (Integer.parseInt(value) >= 1 && Integer.parseInt(value) <= 9) {
					return true;
				} else
					return false;
			} else
				return false;
		} else
			return false;
	} // checkValue(String)

	public boolean checkMoveInRow(String g, String value) {
		guess = g;
		row = Integer.parseInt(guess.substring(0, 1)) - 1;
		col = Character.getNumericValue(guess.charAt(2)) - 1;

		// checking if the value is in the same row
		if (!board[row][0].equals(value) && !board[row][1].equals(value) && !board[row][2].equals(value)
				&& !board[row][3].equals(value) && !board[row][4].equals(value) && !board[row][5].equals(value)
				&& !board[row][6].equals(value) && !board[row][7].equals(value) && !board[row][8].equals(value))
			return true;
		else
			return false;

//		for (int c = 0; c < board[row].length; c++) {
//			if (!board[row][c].equals(value))
//				return true;
//		}
//		return false;

	} // checkMoveInRow(String, String)

	public boolean checkMoveInCol(String g, String value) {
		guess = g;
		row = Integer.parseInt(guess.substring(0, 1)) - 1;
		col = Character.getNumericValue(guess.charAt(2)) - 1;

		// checking if the value is in the same column
		if (!board[0][col].equals(value) && !board[1][col].equals(value) && !board[2][col].equals(value)
				&& !board[3][col].equals(value) && !board[4][col].equals(value) && !board[5][col].equals(value)
				&& !board[6][col].equals(value) && !board[7][col].equals(value) && !board[8][col].equals(value))
			return true;
		else
			return false;
		
//		for(int r = 0; r < board.length; r++) {
//			if(!board[r][col].equals(value));
//				return true;
//		}
//		return false;
		
	} // checkMoveInCol(String, String)

	public boolean checkMoveInBox(String g, String value) {
		guess = g;
		row = Integer.parseInt(guess.substring(0, 1)) - 1;
		col = Character.getNumericValue(guess.charAt(2)) - 1;

		int r = row - row % 3; // getting starting index of the 3x3 box for the row
		int c = col - col % 3; // getting starting index of the 3x3 box for the column

		if (!board[r][c].equals(value) && !board[r][c + 1].equals(value) && !board[r][c + 2].equals(value)
				&& !board[r + 1][c].equals(value) && !board[r + 1][c + 1].equals(value)
				&& !board[r + 1][c + 2].equals(value) && !board[r + 2][c].equals(value)
				&& !board[r][c + 1].equals(value) && !board[r + 2][c + 2].equals(value))
			return true;

		return false;
	} // checkMoveInBox(String, String)

	public String markBox(String g, String value) {
		guess = g;
		row = Integer.parseInt(guess.substring(0, 1)) - 1;
		col = Character.getNumericValue(guess.charAt(2)) - 1;
		lastMove++;
		getBoard();
		return board[row][col] = value;
	} // markBox();

	public String[][] getBoard() {
		return board;
	} // getBoard()

	public boolean canUndo() {
		if (lastMove != 0) {
			return true;
		} else
			return false;
	}// canUndo()

	public String[][] undo() {
		row = Integer.parseInt(guess.substring(0, 1)) - 1;
		col = Character.getNumericValue(guess.charAt(2)) - 1;
		getBoard();
		board[row][col] = "*";
		return board;
	} // undo()

	public void quitGame() {
		saveGame();
		System.exit(-1);
	} // quitGame()

	public void saveGame() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[x].length; y++) {
					out.write(board[x][y] + "~");
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	} // saveGame()

	public boolean isComplete() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].equals("*")) { // checking if there are any stars in the board
					return false;
				}
			}
		}
		return true;
	} // isComplete()

} // SudokuGame class