package de.planspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import java.awt.GridLayout;

/**
 * 
 * @author Ann-Kathrin Gessat
 *
 */
public class Anlegen_Unternehmenskette extends JDialog {

	private static final long serialVersionUID = 1L;
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
			JLabel lblPlatzhalter = new JLabel("");
			contentPanel.add(lblPlatzhalter);
		}
		{
			JPanel panelName = new JPanel();
			contentPanel.add(panelName);
			{
				JLabel lblName = new JLabel("Name: ");
				panelName.add(lblName);
				lblName.setVerticalAlignment(SwingConstants.BOTTOM);
			}
			{
				tfName = new JTextField();
				panelName.add(tfName);
				tfName.setColumns(30);
			}
			{
				JLabel lblOK = new JLabel("");
				lblOK.setIcon(new ImageIcon(Anlegen_Unternehmenskette.class.getResource("/de/planspiel/gui/gut.png")));
				panelName.add(lblOK);
			}
		}
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				JButton anlegenButton = new JButton("weitere Unternehmenskette anlegen");
				anlegenButton.setActionCommand("OK");
				buttonPanel.add(anlegenButton);
				getRootPane().setDefaultButton(anlegenButton);
			}
			{
				JButton startButton = new JButton("Spiel beginnen");
				startButton.setActionCommand("Cancel");
				buttonPanel.add(startButton);
			}
		}
	}

}
