package nhaile_G20_A02_Phase2;

/**Title: SudokuFrame class
 * Description: This program is basis for a Sudoku Game. It displays a frame where a player can choose between different levels and 
 * challenge themselves. 
 * Copyright: Copyright (c) 2020
 * Company: Heritage College
 * @author Nahom Haile
 * @version 1.0
 ***/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class SudokuFrame extends JFrame implements ActionListener {
	
	// creating instance variables

	private JPanel boardPanel;
	private JPanel bottomPanel;
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenuItem mntmNormalMode;
	private JMenuItem mntmEasyMode;
	private JMenuItem mntmMediumMode;
	private JMenuItem mntmHardMode;
	private JMenuItem mntmUndo;
	private JMenuItem mntmSave;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmRules;
	private JMenuItem mntmQuit;
	private JButton board[][] = new JButton[9][9];
	private JLabel lblRow;
	private JTextField fldRow;
	private JLabel lblCol;
	private JTextField fldCol;
	private JLabel lblValue;
	private JTextField fldValue;
	private JPanel topPanel;
	private JLabel lblTitle;
	private JButton markSquare;
	SudokuGame game = new SudokuGame();
	String filename;
	String guess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuFrame frame = new SudokuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SudokuFrame() {
		getContentPane().setBackground(Color.MAGENTA);
		setBackground(Color.MAGENTA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 352);
		getContentPane().setLayout(new BorderLayout(0, 0));

		topPanel = new JPanel();
		topPanel.setBackground(Color.BLUE);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		lblTitle = DefaultComponentFactory.getInstance()
				.createTitle("Welcome to My Sudoku Game! Please choose a level in the game menu");
		lblTitle.setForeground(Color.WHITE);
		topPanel.add(lblTitle);

		boardPanel = new JPanel();
		getContentPane().add(boardPanel, BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLUE);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblRow = new JLabel("row:");
		lblRow.setForeground(Color.WHITE);
		lblRow.setBackground(Color.WHITE);
		bottomPanel.add(lblRow);

		fldRow = new JTextField();
		bottomPanel.add(fldRow);
		fldRow.setColumns(3);
		fldRow.setEnabled(false);

		lblCol = new JLabel("col:");
		lblCol.setForeground(Color.WHITE);
		bottomPanel.add(lblCol);

		fldCol = new JTextField();
		bottomPanel.add(fldCol);
		fldCol.setColumns(3);
		fldCol.setEnabled(false);

		lblValue = new JLabel("value:");
		lblValue.setForeground(Color.WHITE);
		bottomPanel.add(lblValue);

		fldValue = new JTextField();
		bottomPanel.add(fldValue);
		fldValue.setColumns(3);
		fldValue.setEnabled(false);

		markSquare = new JButton("Mark Sqaure");
		markSquare.setBackground(Color.BLACK);
		markSquare.setForeground(Color.WHITE);
		markSquare.setEnabled(false);
		bottomPanel.add(markSquare);
		markSquare.addActionListener(this);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		mntmNormalMode = new JMenuItem("Normal Mode");
		mnGame.add(mntmNormalMode);
		mntmNormalMode.addActionListener(this);

		mntmEasyMode = new JMenuItem("Easy Mode");
		mnGame.add(mntmEasyMode);
		mntmEasyMode.addActionListener(this);

		mntmMediumMode = new JMenuItem("Medium Mode");
		mnGame.add(mntmMediumMode);
		mntmMediumMode.addActionListener(this);

		mntmHardMode = new JMenuItem("Hard Mode");
		mnGame.add(mntmHardMode);
		mntmHardMode.addActionListener(this);

		mntmUndo = new JMenuItem("Undo");
		mnGame.add(mntmUndo);
		mntmUndo.addActionListener(this);

		mntmSave = new JMenuItem("Save");
		mnGame.add(mntmSave);
		mntmSave.addActionListener(this);

		mntmUndo.setEnabled(false);
		mntmSave.setEnabled(false);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		mnHelp.addActionListener(this);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(this);

		mntmRules = new JMenuItem("Rules");
		mnHelp.add(mntmRules);
		mntmRules.addActionListener(this);

		mntmQuit = new JMenuItem("Quit");
		mnHelp.add(mntmQuit);
		mntmQuit.addActionListener(this);

		initializeBoard();
	} // SudokuFrame()

	public void enable() {
		fldRow.setEnabled(true);
		fldCol.setEnabled(true);
		fldValue.setEnabled(true);
		markSquare.setEnabled(true);
		mntmUndo.setEnabled(true);
		mntmSave.setEnabled(true);
	} // enable()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNormalMode) {
			filename = "sudoku.txt";
			game.validateFile(filename);
			boardPanel.setVisible(true);
			enable();
			setUpGame();
		}
		if (e.getSource() == mntmEasyMode) {
			filename = "easySudoku.txt";
			game.validateFile(filename);
			boardPanel.setVisible(true);
			enable();
			setUpGame();
		}
		if (e.getSource() == mntmMediumMode) {
			filename = "mediumSudoku.txt";
			game.validateFile(filename);
			boardPanel.setVisible(true);
			enable();
			setUpGame();
		}
		if (e.getSource() == mntmHardMode) {
			filename = "hardSudoku.txt";
			game.validateFile(filename);
			boardPanel.setVisible(true);
			enable();
			setUpGame();
		}

		if (e.getSource() == mntmAbout) {
			JOptionPane.showMessageDialog(this, new About_Panel(), "About", JOptionPane.PLAIN_MESSAGE);
		}

		if (e.getSource() == mntmRules) {
			JOptionPane.showMessageDialog(this, new Rules_Panel(), "Rules", JOptionPane.PLAIN_MESSAGE);
		}

		if (e.getSource() == mntmQuit) {
			JOptionPane.showMessageDialog(this, "Thank you for playing my sudoku game!", "Thank you!",
					JOptionPane.PLAIN_MESSAGE);
			game.saveGame();
			System.exit(-1);
		}

		if (e.getSource() == mntmSave) {
			JOptionPane.showMessageDialog(this, "Your game is saved in " + filename, "Game saved!",
					JOptionPane.PLAIN_MESSAGE);
			game.saveGame();
		}

		if (e.getSource() == mntmUndo) {
			if (game.canUndo()) {
				JOptionPane.showMessageDialog(this, "Undo last move only...", "Undo", JOptionPane.PLAIN_MESSAGE);
				game.undo();
				board[game.row][game.col].setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Can't undo on the first move", "Sorry", JOptionPane.PLAIN_MESSAGE);
			}
		}

		if (e.getSource() == markSquare) {
			if (fldRow.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "you must fill in a row number", "Error",
						JOptionPane.PLAIN_MESSAGE);
			}
			if (fldCol.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "you must fill in a column number", "Error",
						JOptionPane.PLAIN_MESSAGE);
			}
			if (fldValue.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "you must fill in a value number", "Error",
						JOptionPane.PLAIN_MESSAGE);
			}
			guess = fldRow.getText() + "," + fldCol.getText();
			checkGamePlay();
		}

	} // actionPerformed(e)

	public void checkGamePlay() {
		if (game.checkRow(guess)) {
			if (game.checkCol(guess)) {
				if (game.checkGuess(guess)) {
					if (game.checkValue(fldValue.getText())) {
						if (game.checkMoveInRow(guess, fldValue.getText())
								&& game.checkMoveInCol(guess, fldValue.getText())
								&& game.checkMoveInBox(guess, fldValue.getText())) {
							game.markBox(guess, fldValue.getText());
							board[Integer.parseInt(fldRow.getText()) - 1][Integer.parseInt(fldCol.getText()) - 1]
									.setText(fldValue.getText());
							if (game.isComplete()) {
								JOptionPane.showMessageDialog(this,
										"Congratulations you have completed the board! You did it! Want to play again? Pick another level.",
										"Congratulations", JOptionPane.PLAIN_MESSAGE);
								game.saveGame();
							}
						} else {
							JOptionPane.showMessageDialog(this,
									"Invalid move There is already a " + fldValue.getText()
											+ " in that row/column/square. Please try again.",
									"Error", JOptionPane.PLAIN_MESSAGE);
							fldValue.setText("");
						}
					} else {

						JOptionPane.showMessageDialog(this, "Invalid value number, Please try again.", "Error",
								JOptionPane.PLAIN_MESSAGE);
						fldValue.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Invalid move. That location has a value. Please try again.",
							"Error", JOptionPane.PLAIN_MESSAGE);
					fldRow.setText("");
					fldCol.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Invalid column number. Please try again.", "Error",
						JOptionPane.PLAIN_MESSAGE);
				fldCol.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Invalid row number. Please try again.", "Error",
					JOptionPane.PLAIN_MESSAGE);
			fldRow.setText("");
		}
	} // checkGamePlay()

	public void initializeBoard() {
		boardPanel.setLayout(new GridLayout(9, 9));
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[row].length; ++col) {
				board[row][col] = new JButton();
				boardPanel.add(board[row][col]);
				board[row][col].addActionListener(this);
				board[row][col].setEnabled(false);
			}
		}
	} // initializeBoard()

	public void setUpGame() {
		resetBoard();
		game.readFromFile();
		for (int row = 0; row < game.board.length; ++row) {
			for (int col = 0; col < game.board[row].length; ++col) {
				board[row][col].setEnabled(true);
				if (!game.board[row][col].equals("*"))
					board[row][col].setText("" + game.board[row][col]);
				if (((row == 0 || row == 1 || row == 2) || (row == 6 || row == 7 || row == 8))
						&& (col == 0 || col == 1 || col == 2)
						|| (((row == 0 || row == 1 || row == 2 || (row == 6 || row == 7 || row == 8)))
								&& (col == 6 || col == 7 || col == 8))
						|| ((row == 3 || row == 4 || row == 5) && (col == 3 || col == 4 || col == 5)))
					board[row][col].setBackground(Color.CYAN);
				else {
					board[row][col].setBackground(Color.DARK_GRAY);
					board[row][col].setForeground(Color.white);
				}

			}
		}
	} // setUpGame()

	public void resetBoard() {
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[row].length; ++col) {
				board[row][col].setText("");
			}
		}
	} // resetBoard()
} // SudokuFrame class