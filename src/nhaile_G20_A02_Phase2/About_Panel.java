package nhaile_G20_A02_Phase2;

/**Title: About_Panel class
 * Description: This panel displays an about panel that contains my information.
 * Copyright: Copyright (c) 2020
 * Company: Heritage College
 * @author Nahom Haile
 * @version 1.0
 ***/

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class About_Panel extends JPanel {

	/**
	 * Create the panel.
	 */

	// creating my instance variables
	
	private GridBagLayout gridBagLayout;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblYear;
	private JLabel lblSchool;

	public About_Panel() {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblTitle = new JLabel("Sudoku Game");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		lblAuthor = new JLabel("Nahom Haile");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 1;
		add(lblAuthor, gbc_lblAuthor);

		lblYear = new JLabel("2020");
		GridBagConstraints gbc_Copyright = new GridBagConstraints();
		gbc_Copyright.insets = new Insets(0, 0, 5, 0);
		gbc_Copyright.gridx = 0;
		gbc_Copyright.gridy = 2;
		add(lblYear, gbc_Copyright);

		lblSchool = new JLabel("Heritage College");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.gridx = 0;
		gbc_lblCompany.gridy = 3;
		add(lblSchool, gbc_lblCompany);
	} // About_Panel()

} // About_Panel class