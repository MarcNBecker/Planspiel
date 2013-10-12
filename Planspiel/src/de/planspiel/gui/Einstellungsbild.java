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

public class Einstellungsbild {

	private JFrame frmEntscheidungen;

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
		
		JPanel Uebersicht = new JPanel();
		Uebersicht.setBorder(new LineBorder(new Color(0, 0, 0)));
		Uebersicht.setToolTipText("");
		
		JPanel Ausgabe = new JPanel();
		
		JPanel Navigation = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmEntscheidungen.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(Navigation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(Uebersicht, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
				.addComponent(Ausgabe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Uebersicht, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Ausgabe, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Navigation, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
		);
		Uebersicht.setLayout(new BorderLayout(0, 0));
		
		JPanel Uebersicht_Kette = new JPanel();
		Uebersicht.add(Uebersicht_Kette, BorderLayout.WEST);
		Uebersicht_Kette.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Unternehmenskette");
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
		frmEntscheidungen.getContentPane().setLayout(groupLayout);
	}
}
