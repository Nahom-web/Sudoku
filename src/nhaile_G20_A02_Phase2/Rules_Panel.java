package nhaile_G20_A02_Phase2;

/**Title: Rules_Panel class
 * Description: This panel displays the rules for sudoku.
 * Copyright: Copyright (c) 2020
 * Company: Heritage College
 * @author Nahom Haile
 * @version 1.0
 ***/

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Rules_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	// creating instance variable
	private GridBagLayout gridBagLayout;

	public Rules_Panel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblSudokuIsA = new JLabel(
				"Sudoku is a logic based, number placement puzzle. The board is a 9 x 9 grid with 81 squares");
		GridBagConstraints gbc_lblSudokuIsA = new GridBagConstraints();
		gbc_lblSudokuIsA.insets = new Insets(0, 0, 5, 0);
		gbc_lblSudokuIsA.gridx = 0;
		gbc_lblSudokuIsA.gridy = 0;
		add(lblSudokuIsA, gbc_lblSudokuIsA);

		JLabel lblNewLabel = new JLabel("Some of the squares are already filled in at the start of the puzzle");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblThePlayerMust = new JLabel(
				"The player must fill in the rest of the squares by observing these 3 rules.");
		GridBagConstraints gbc_lblThePlayerMust = new GridBagConstraints();
		gbc_lblThePlayerMust.insets = new Insets(0, 0, 5, 0);
		gbc_lblThePlayerMust.gridx = 0;
		gbc_lblThePlayerMust.gridy = 2;
		add(lblThePlayerMust, gbc_lblThePlayerMust);

		JLabel lblYouMust = new JLabel(
				"1.\tYou must place the numbers 1 \u2013 9 in each row without repeating a number.");
		GridBagConstraints gbc_lblYouMust = new GridBagConstraints();
		gbc_lblYouMust.insets = new Insets(0, 0, 5, 0);
		gbc_lblYouMust.gridx = 0;
		gbc_lblYouMust.gridy = 3;
		add(lblYouMust, gbc_lblYouMust);

		JLabel lblYouMust_1 = new JLabel(
				"2.\tYou must place the numbers 1 \u2013 9 in each column without repeating a number.");
		GridBagConstraints gbc_lblYouMust_1 = new GridBagConstraints();
		gbc_lblYouMust_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblYouMust_1.gridx = 0;
		gbc_lblYouMust_1.gridy = 4;
		add(lblYouMust_1, gbc_lblYouMust_1);

		JLabel lblYouMust_2 = new JLabel(
				"3.\tYou must place the numbers 1 \u2013 9 in each of the marked 3 x 3 boxes without repeating a number.");
		GridBagConstraints gbc_lblYouMust_2 = new GridBagConstraints();
		gbc_lblYouMust_2.gridx = 0;
		gbc_lblYouMust_2.gridy = 5;
		add(lblYouMust_2, gbc_lblYouMust_2);
	} // Rules_Panel()

} // Rules_Panel class
