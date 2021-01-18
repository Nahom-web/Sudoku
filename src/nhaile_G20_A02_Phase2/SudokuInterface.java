package nhaile_G20_A02_Phase2;

/**Title: SudokuInterface class
 * Description: This is an interface for the game Sudoku. To play you either type a file/board you want to play on or 
 * you can press enter an the default board displays. You can save the game at any time and quit at any time! Have fun
 * Copyright: Copyright (c) 2020
 * Company: Heritage College
 * @author Nahom Haile
 * @version 1.0
 ***/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuInterface {

	// Instance variables

	private String guess;
	private String value;
	private String filename;
	SudokuGame game = new SudokuGame();
	Scanner input = new Scanner(System.in);

	public void printBoard() {
		game.getBoard();
		for (int row = 0; row < game.board.length; row++) {
			if (row % 3 == 0 && row != 0)
				System.out.println("---------------------");
			for (int col = 0; col < game.board[row].length; col++) {
				if (col == 3 || col == 6)
					System.out.print("| ");
				System.out.print(game.board[row][col] + " ");
			}
			System.out.println();
		}
	} // printBoard()

	public void enterFilename() {
		filename = input.nextLine();
		if (game.validateFile(filename)) {
			if (game.validateFileContent()) {
				System.out.println(
						"Type Q at any time to exit the game, S to save the game or U to undo your last move\n");
				game.readFromFile();
				printBoard();
				checkGuess();
			} else
				System.out.println(filename + " doesn't have the right file format");
		}
		if (filename.length() == 0) {
			filename = "Sudoku.txt";
			if (game.validateFileContent()) {
				System.out.println(
						"Type Q at any time to exit the game, S to save the game or U to undo your last move\n");
				game.readFromFile();
				printBoard();
				checkGuess();
				if (game.isComplete()) {
					printBoard();
					System.out.println("Congratulations you completed the board!");
					game.saveGame();
				} else {
					printBoard();
					System.out.println("Congratulations you finished the puzzle");
					game.saveGame();
				}
			} else
				System.out.println(filename + " doesn't have the right file format");
		} else {
			System.out.println(filename + " does not exist");
			System.out.println("Please enter the filename for your puzzle: ");
			enterFilename();
		}
	} // enterFilename()

	public void checkGuess() {
		System.out.print("Enter square number (row, column) ->");
		guess = input.nextLine();
		guess.toUpperCase();

		if (guess.length() == 1) {
			if (guess.equalsIgnoreCase("s"))
				saveGame();
			if (guess.equalsIgnoreCase("u")) {
				if (game.canUndo()) {
					System.out.println("Undo last move only...");
					game.undo();
					printBoard();
					checkGuess();
				} else {
					System.out.println("Can't undo on the first move\n");
					checkGuess();
				}
			}
			if (guess.equalsIgnoreCase("q"))
				quit();

			else {
				System.out.println("You can only s-save, u-undo, q-quit or enter a valid guess, Please try again");
				checkGuess();
			}
		} else {
			if (game.checkRow(guess)) {

				if (game.checkCol(guess)) {

					if (game.checkGuess(guess)) {
						checkValue();
					} else {
						System.out.println("Invalid move. That location has a value. Please try again.\n");
						checkGuess();
					}
				} else {
					System.out.println("Invalid column number. Please try again.\n");
					checkGuess();
				}
			} else {
				System.out.println("Invalid row number. Please try again.\n");
				checkGuess();
			}
		}

	} // checkInput()

	public void checkValue() {
		System.out.print("Enter value: ");
		value = input.nextLine();

		if (value.equalsIgnoreCase("s"))
			saveGame();
		if (value.equalsIgnoreCase("u")) {
			if (game.canUndo()) {
				System.out.println("Undo last move only...");
				game.undo();
				printBoard();
				checkValue();
			} else {
				System.out.println("Can't undo on the first move\n");
				checkValue();
			}
		}
		if (value.equalsIgnoreCase("q"))
			quit();

		if (game.checkValue(value)) {
			if (game.checkMoveInRow(guess, value) && game.checkMoveInCol(guess, value)
					&& game.checkMoveInBox(guess, value)) {
				game.markBox(guess, value);
				if (game.isComplete()) {
					printBoard();
					System.out.println("Congratulations you completed the board!");
					game.saveGame();
				} else {
					printBoard();
					checkGuess();
				}
			} else {
				System.out.println(
						"Invalid move There is already a " + value + " in that row/column/square. Please try again.\n");
				checkValue();
			}
		} else {
			System.out.println("Invalid value number, Please try again.\n");
			checkValue();
		}

	} // checkValue();

	public void saveGame() {
		System.out.println("Game saved in " + filename + " Thanks for playing Heritage Sudoku");
		game.saveGame();
		System.exit(-1);
	} // saveGame()

	public void quit() {
		System.out.println("Sorry to see you leave. See you next time");
		game.quitGame();
	} // quit()

	public static void main(String[] args) {
		SudokuInterface cli = new SudokuInterface();
		System.out.println("Welcome to Heritage Sudoku");
		System.out.println("Please enter the filename for your puzzle: ");
		cli.enterFilename();
	} // main(String[])

} // SudokuInterface class