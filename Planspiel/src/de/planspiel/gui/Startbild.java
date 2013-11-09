package de.planspiel.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * 
 * @author Ann-Kathrin Gessat
 *
 */
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

		JPanel contentPanel = new JPanel();
		FlowLayout fl_contentPanel = (FlowLayout) contentPanel.getLayout();
		fl_contentPanel.setAlignment(FlowLayout.LEADING);
		fl_contentPanel.setVgap(40);
		fl_contentPanel.setHgap(40);
		frmPlanspiel.getContentPane().add(contentPanel);

		JLabel lblText = new JLabel("Herzlich Willkommen!\r\n...");
		contentPanel.add(lblText);

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.TRAILING);
		frmPlanspiel.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton SpielStartenButton = new JButton("Spiel starten");
		buttonPanel.add(SpielStartenButton);
	}

}
