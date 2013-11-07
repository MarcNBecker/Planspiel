package de.planspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;

import javax.swing.ListSelectionModel;

public class Einstellungsbild {

	private JFrame frmEntscheidungen;
	private JTextField textFieldKredit;
	private JTable tableVerkaufspreis;
	private JTable tableRohstoffe;
	private JTable tableFilialen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Einstellungsbild window = new Einstellungsbild();
					window.frmEntscheidungen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Einstellungsbild() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEntscheidungen = new JFrame();
		frmEntscheidungen.setTitle("Entscheidungen");
		frmEntscheidungen.setBounds(100, 100, 1280, 720);
		frmEntscheidungen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel uebersichtPanel = new JPanel();
		uebersichtPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		uebersichtPanel.setToolTipText("");

		JPanel entscheidungPanel = new JPanel();
		uebersichtPanel.setLayout(new BorderLayout(0, 0));

		JPanel kettenPanel = new JPanel();
		uebersichtPanel.add(kettenPanel, BorderLayout.WEST);
		kettenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Unternehmenskette");
		kettenPanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel Ukettenname = new JLabel(".....");
		kettenPanel.add(Ukettenname);
		Ukettenname.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel rundenPanel = new JPanel();
		uebersichtPanel.add(rundenPanel, BorderLayout.EAST);
		rundenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblRundeT = new JLabel("Runde");
		lblRundeT.setHorizontalAlignment(SwingConstants.RIGHT);
		rundenPanel.add(lblRundeT);
		lblRundeT.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblRunde = new JLabel("....");
		lblRunde.setHorizontalAlignment(SwingConstants.RIGHT);
		rundenPanel.add(lblRunde);

		JLabel lblVonT = new JLabel("von");
		lblVonT.setHorizontalAlignment(SwingConstants.RIGHT);
		rundenPanel.add(lblVonT);
		lblVonT.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblGesamtRunde = new JLabel("....");
		rundenPanel.add(lblGesamtRunde);
		lblGesamtRunde.setHorizontalAlignment(SwingConstants.RIGHT);
		frmEntscheidungen.getContentPane().setLayout(new BorderLayout(0, 0));
		frmEntscheidungen.getContentPane().add(uebersichtPanel, BorderLayout.NORTH);
		frmEntscheidungen.getContentPane().add(entscheidungPanel, BorderLayout.CENTER);
		entscheidungPanel.setLayout(new BorderLayout(0, 0));

		JPanel ausgabenPanel = new JPanel();
		entscheidungPanel.add(ausgabenPanel, BorderLayout.NORTH);
		ausgabenPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		ausgabenPanel.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel kreditPanel = new JPanel();
		panel.add(kreditPanel);
		kreditPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblKredit = new JLabel("Kredith\u00F6he:         ");
		kreditPanel.add(lblKredit);

		textFieldKredit = new JTextField();
		kreditPanel.add(textFieldKredit);
		textFieldKredit.setColumns(10);

		tableVerkaufspreis = new JTable();
		tableVerkaufspreis.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, }, new String[] { "Produkt", "Verkaufspreis" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVerkaufspreis.getColumnModel().getColumn(0).setResizable(false);
		tableVerkaufspreis.getColumnModel().getColumn(0).setPreferredWidth(107);
		ausgabenPanel.add(tableVerkaufspreis, BorderLayout.SOUTH);

		JScrollPane scrollPaneVerkaufspreis = new JScrollPane(tableVerkaufspreis);
		scrollPaneVerkaufspreis.setPreferredSize(new Dimension(1280, 100));
		ausgabenPanel.add(scrollPaneVerkaufspreis, BorderLayout.CENTER);

		JPanel rohstoffPanel = new JPanel();
		entscheidungPanel.add(rohstoffPanel, BorderLayout.CENTER);
		rohstoffPanel.setLayout(new BorderLayout(0, 0));

		JPanel tabellePanel1 = new JPanel();
		rohstoffPanel.add(tabellePanel1, BorderLayout.NORTH);
		tabellePanel1.setLayout(new BorderLayout(0, 0));

		JLabel lblRohstoffeinkauf = new JLabel("Rohstoffeinkauf");
		tabellePanel1.add(lblRohstoffeinkauf, BorderLayout.NORTH);

		tableRohstoffe = new JTable();
		tabellePanel1.add(tableRohstoffe);
		tableRohstoffe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRohstoffe.setCellSelectionEnabled(true);
		tableRohstoffe.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null }, }, new String[] { "ausgew\u00E4hlt", "H\u00E4ndler", "A - Preis", "A - Qualit\u00E4t", "B - Preis",
				"B - Qualit\u00E4t", "C - Preis", "C - Qualit\u00E4t" }) {
					private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Object.class, String.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRohstoffe.getColumnModel().getColumn(0).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(1).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(2).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(3).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(4).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(5).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(6).setResizable(false);
		tableRohstoffe.getColumnModel().getColumn(7).setResizable(false);

		JScrollPane scrollPaneRohstoffe = new JScrollPane(tableRohstoffe);
		tabellePanel1.add(scrollPaneRohstoffe);
		scrollPaneRohstoffe.setPreferredSize(new Dimension(1280, 260));

		JButton buttonRohstoffekaufen = new JButton("Rohstoffe kaufen");
		rohstoffPanel.add(buttonRohstoffekaufen, BorderLayout.WEST);

		JPanel filialenPanel = new JPanel();
		entscheidungPanel.add(filialenPanel, BorderLayout.SOUTH);
		filialenPanel.setLayout(new BorderLayout(0, 0));

		JPanel tabellePanel2 = new JPanel();
		filialenPanel.add(tabellePanel2, BorderLayout.NORTH);
		tabellePanel2.setLayout(new BorderLayout(0, 0));

		JLabel lblFilialenUndMitarbeiter = new JLabel("Filialen und Mitarbeiter");
		tabellePanel2.add(lblFilialenUndMitarbeiter, BorderLayout.NORTH);

		tableFilialen = new JTable();
		tabellePanel2.add(tableFilialen);
		tableFilialen.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, }, new String[] { "Standort", "Anzahl Mitarbeiter", "Marketingbudget",
				"einstellen/entlassen", "verkaufen" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Integer.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableFilialen.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableFilialen.getColumnModel().getColumn(1).setResizable(false);
		tableFilialen.getColumnModel().getColumn(1).setPreferredWidth(104);
		tableFilialen.getColumnModel().getColumn(2).setResizable(false);
		tableFilialen.getColumnModel().getColumn(2).setPreferredWidth(95);
		tableFilialen.getColumnModel().getColumn(3).setPreferredWidth(113);

		JScrollPane scrollPaneFilialen = new JScrollPane(tableFilialen);
		tabellePanel2.add(scrollPaneFilialen);
		scrollPaneFilialen.setPreferredSize(new Dimension(1260, 200));

		JButton buttonFilialeKaufen = new JButton("Filiale kaufen");
		filialenPanel.add(buttonFilialeKaufen, BorderLayout.WEST);

		JPanel buttonPanel = new JPanel();
		frmEntscheidungen.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JButton zurueckButton = new JButton("Zur\u00FCck zum Ergebnis");
		buttonPanel.add(zurueckButton, BorderLayout.WEST);
		zurueckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		zurueckButton.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton absendenButton = new JButton("absenden");
		buttonPanel.add(absendenButton, BorderLayout.EAST);

		JPanel summePanel = new JPanel();
		buttonPanel.add(summePanel, BorderLayout.CENTER);

		JLabel lblSummeDerKostenT = new JLabel("Summe der Kosten:");
		summePanel.add(lblSummeDerKostenT);

		JLabel lblSummeDerKosten = new JLabel("...");
		summePanel.add(lblSummeDerKosten);
	}
}
