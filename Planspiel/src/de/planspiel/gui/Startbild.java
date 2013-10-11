package de.planspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Startbild {

	private JFrame frmPlanspiel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startbild window = new Startbild();
					window.frmPlanspiel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Startbild() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlanspiel = new JFrame();
		frmPlanspiel.setTitle("Planspiel");
		frmPlanspiel.setBounds(100, 100, 800, 600);
		frmPlanspiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlanspiel.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(40);
		flowLayout.setHgap(40);
		frmPlanspiel.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Herzlich Willkommen!\r\n...");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		frmPlanspiel.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnSpielStarten = new JButton("Spiel starten");
		panel_1.add(btnSpielStarten);
	}

}
