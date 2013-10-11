package de.planspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

public class Anlegen_Unternehmenskette extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Anlegen_Unternehmenskette dialog = new Anlegen_Unternehmenskette();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Anlegen_Unternehmenskette() {
		setType(Type.POPUP);
		setTitle("Neue Unternehmenskette anlegen");
		setBounds(100, 100, 450, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		contentPanel.setLayout(new GridLayout(3, 1, 0, 0));
		{
			JLabel label = new JLabel("");
			contentPanel.add(label);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblName = new JLabel("Name:        ");
				panel.add(lblName);
				lblName.setVerticalAlignment(SwingConstants.BOTTOM);
			}
			{
				tfName = new JTextField();
				panel.add(tfName);
				tfName.setColumns(40);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton anlegenButton = new JButton("weitere Unternehmenskette anlegen");
				anlegenButton.setActionCommand("OK");
				buttonPane.add(anlegenButton);
				getRootPane().setDefaultButton(anlegenButton);
			}
			{
				JButton startButton = new JButton("Spiel beginnen");
				startButton.setActionCommand("Cancel");
				buttonPane.add(startButton);
			}
		}
	}

}
