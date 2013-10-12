package de.planspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Ergebnisbild {

	private JFrame frmErgebnisDerLetzten;
	private JTable Lagerbestand;
	private JTable table_Filialen;
	private JTable GuV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ergebnisbild window = new Ergebnisbild();
					window.frmErgebnisDerLetzten.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ergebnisbild() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmErgebnisDerLetzten = new JFrame();
		frmErgebnisDerLetzten.setTitle("Ergebnis der letzten Runde");
		frmErgebnisDerLetzten.setBounds(100, 100, 1280, 720);
		frmErgebnisDerLetzten.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Uebersicht = new JPanel();
		Uebersicht.setBorder(new LineBorder(new Color(0, 0, 0)));
		Uebersicht.setToolTipText("");
		
		JPanel Ausgabe = new JPanel();
		
		JPanel Navigation = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmErgebnisDerLetzten.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(Navigation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(Uebersicht, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
				.addComponent(Ausgabe, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Uebersicht, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Ausgabe, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Navigation, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
		);
		Ausgabe.setLayout(new BorderLayout(0, 0));
		
		JPanel Ereignis = new JPanel();
		Ereignis.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ausgabe.add(Ereignis, BorderLayout.SOUTH);
		Ereignis.setPreferredSize(new Dimension(1280, 50));
		Ereignis.setLayout(new BorderLayout(0, 0));
		
		JPanel Ereignis_2 = new JPanel();
		Ereignis.add(Ereignis_2, BorderLayout.CENTER);
		Ereignis_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Hier k\u00F6nnte ihr Ereignis stehen!");
		Ereignis_2.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel Ereignis_1 = new JPanel();
		Ereignis_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ereignis.add(Ereignis_1, BorderLayout.NORTH);
		Ereignis_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Ereignis der aktuellen Runde");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		Ereignis_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel Ergebnisse = new JPanel();
		Ergebnisse.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ausgabe.add(Ergebnisse, BorderLayout.CENTER);
		Ergebnisse.setPreferredSize(new Dimension(1280, 150));
		Ergebnisse.setLayout(new BorderLayout(0, 0));
		
		JPanel Ergebnisse_Werte = new JPanel();
		Ergebnisse.add(Ergebnisse_Werte, BorderLayout.SOUTH);
		Ergebnisse_Werte.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Erfolg:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		Ergebnisse_Werte.add(lblNewLabel_8, BorderLayout.WEST);
		
		JLabel lblNewLabel_9 = new JLabel("Anzahl der Kunden in der letzten Periode:");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		Ergebnisse_Werte.add(lblNewLabel_9, BorderLayout.CENTER);
		
		JPanel Ergebnisse_GuV = new JPanel();
		Ergebnisse.add(Ergebnisse_GuV, BorderLayout.WEST);
		Ergebnisse_GuV.setPreferredSize(new Dimension(750, 100));
		Ergebnisse_GuV.setLayout(new BorderLayout(0, 0));
		
		GuV = new JTable();
		GuV.setModel(new DefaultTableModel(
			new Object[][] {
				{"Anschaffungskosten", null, "Umsatzerlöse", null},
				{"Unterhaltungskosten", null, "Sonstige Erlöse", null},
				{"Personalkosten", null, null, null},
				{"Kreditkosten", null, null, null},
				{"Marketingkosten", null, null, null},
				{"Rohstoffkosten", null, null, null},
				{"Summe", null, "Summe", null},
			},
			new String[] {
				"Kosten", "", "Erl\u00F6se", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		GuV.getColumnModel().getColumn(0).setResizable(false);
		GuV.getColumnModel().getColumn(0).setPreferredWidth(100);
		
		JScrollPane scrollPane_Marktanteil = new JScrollPane();
		Ergebnisse_GuV.add(scrollPane_Marktanteil, BorderLayout.EAST);
		Ergebnisse_GuV.add(GuV, BorderLayout.SOUTH);
		
		JScrollPane scrollPane_Ergebnisse_GuV = new JScrollPane(GuV);
		Ergebnisse_GuV.add(scrollPane_Ergebnisse_GuV, BorderLayout.WEST);

		
		JPanel Ergebnisse_Ueberschrift = new JPanel();
		Ergebnisse.add(Ergebnisse_Ueberschrift, BorderLayout.NORTH);
		Ergebnisse_Ueberschrift.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Ergebnisse der letzten Runde");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		Ergebnisse_Ueberschrift.add(lblNewLabel_7, BorderLayout.WEST);
		
		JPanel Kennzahlen = new JPanel();
		Kennzahlen.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ausgabe.add(Kennzahlen, BorderLayout.NORTH);
		Kennzahlen.setLayout(new BorderLayout(0, 0));
		Kennzahlen.setPreferredSize(new Dimension(1280, 300));
		
		JPanel Kennzahlen_1 = new JPanel();
		Kennzahlen.add(Kennzahlen_1, BorderLayout.NORTH);
		Kennzahlen_1.setLayout(new BorderLayout(0, 0));
		
		JPanel Ueberschrift = new JPanel();
		Kennzahlen_1.add(Ueberschrift, BorderLayout.NORTH);
		Ueberschrift.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ueberschrift.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Kennzahlen");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ueberschrift.add(lblNewLabel_4);
		
		JPanel Kapital = new JPanel();
		Kennzahlen_1.add(Kapital, BorderLayout.WEST);
		Kapital.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Kapital:");
		Kapital.add(lblNewLabel_2);
		
		JLabel UketteK = new JLabel(".....");
		Kapital.add(UketteK);
		
		JPanel Fremdkapital = new JPanel();
		Kennzahlen_1.add(Fremdkapital, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("Fremdkapital:");
		Fremdkapital.add(lblNewLabel_3);
		
		JLabel UketteFK = new JLabel("...");
		Fremdkapital.add(UketteFK);
		
		JPanel Filialen = new JPanel();
		Kennzahlen.add(Filialen, BorderLayout.WEST);
		
		table_Filialen = new JTable();
		table_Filialen.setModel(new DefaultTableModel(
			new Object[][] {
				{Anschaffungskosten, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Standort", "Anzahl Mitarbeiter", "Auslastung (%)", "Anzahl Konkurrenzfilialen"
			}
		));
		table_Filialen.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_Filialen.getColumnModel().getColumn(1).setPreferredWidth(106);
		table_Filialen.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_Filialen.getColumnModel().getColumn(3).setPreferredWidth(134);
		Filialen.setLayout(new BorderLayout(0, 0));
		Filialen.add(table_Filialen);
		
		JScrollPane scrollPane_Filiale = new JScrollPane(table_Filialen);
		Filialen.add(scrollPane_Filiale);
		
		
		JPanel Lager = new JPanel();
		Kennzahlen.add(Lager, BorderLayout.SOUTH);
		Lager.setPreferredSize(new Dimension(1280, 80));
		Lager.setLayout(new BorderLayout(0, 0));
		

		Lagerbestand = new JTable();
		Lagerbestand.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null},
				{"", null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Rohstoff", "Menge", "Qualit\u00E4t", "Einkaufspreis", "Verkaufspreis"
			}
		));
		Lager.add(Lagerbestand, BorderLayout.WEST);
		
		JScrollPane scrollPane_Lager = new JScrollPane(Lagerbestand);
		Lager.add(scrollPane_Lager, BorderLayout.WEST);
		scrollPane_Lager.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		Uebersicht.setLayout(new BorderLayout(0, 0));
		
		JPanel Uebersicht_Kette = new JPanel();
		Uebersicht.add(Uebersicht_Kette, BorderLayout.WEST);
		Uebersicht_Kette.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Unternehmenskette:");
		Uebersicht_Kette.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel Ukettenname = new JLabel(".....");
		Uebersicht_Kette.add(Ukettenname);
		Ukettenname.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel Uebersicht_Runde = new JPanel();
		Uebersicht.add(Uebersicht_Runde, BorderLayout.EAST);
		Uebersicht_Runde.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Runde");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		Uebersicht_Runde.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel Rundennr = new JLabel("....");
		Rundennr.setHorizontalAlignment(SwingConstants.RIGHT);
		Uebersicht_Runde.add(Rundennr);
		
		JLabel lblVon = new JLabel("von");
		lblVon.setHorizontalAlignment(SwingConstants.RIGHT);
		Uebersicht_Runde.add(lblVon);
		lblVon.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel Gesamtrundennr = new JLabel("....");
		Uebersicht_Runde.add(Gesamtrundennr);
		Gesamtrundennr.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnNewButton = new JButton("Weiter zur Entscheidung");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Navigation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		Navigation.add(btnNewButton);
		frmErgebnisDerLetzten.getContentPane().setLayout(groupLayout);
	}
}
