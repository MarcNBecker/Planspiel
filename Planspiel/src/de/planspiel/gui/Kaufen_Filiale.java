package de.planspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import de.planspiel.cafe.Standorttyp;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Component;

public class Kaufen_Filiale extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldMitarbeiter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Kaufen_Filiale dialog = new Kaufen_Filiale();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Kaufen_Filiale() {
		setTitle("Filiale kaufen");
		setBounds(100, 100, 358, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		{
			JPanel panelStandort = new JPanel();
			contentPanel.add(panelStandort);
			panelStandort.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblStandort = new JLabel("Standort:           ");
				lblStandort.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panelStandort.add(lblStandort);
			}
			{
				JComboBox<Standorttyp> comboBox = new JComboBox<Standorttyp>();
				panelStandort.add(comboBox);
				comboBox.setModel(new DefaultComboBoxModel<Standorttyp>(Standorttyp.values()));
			}
		}
		{
			JPanel panelMitarbeiter = new JPanel();
			contentPanel.add(panelMitarbeiter);
			panelMitarbeiter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblAnzahlMitarbeiter = new JLabel("Anzahl Mitarbeiter: ");
				panelMitarbeiter.add(lblAnzahlMitarbeiter);
			}
			{
				textFieldMitarbeiter = new JTextField();
				panelMitarbeiter.add(textFieldMitarbeiter);
				textFieldMitarbeiter.setColumns(10);
			}
		}
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				JButton kaufenButton = new JButton("kaufen");
				kaufenButton.setActionCommand("OK");
				buttonPanel.add(kaufenButton);
				getRootPane().setDefaultButton(kaufenButton);
			}
			{
				JButton cancelButton = new JButton("abbrechen");
				cancelButton.setActionCommand("Cancel");
				buttonPanel.add(cancelButton);
			}
		}
	}

}
