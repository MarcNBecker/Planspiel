package de.planspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import de.planspiel.cafe.Standorttyp;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

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
			JPanel panel_1 = new JPanel();
			contentPanel.add(panel_1);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblStandort = new JLabel("Standort:           ");
				lblStandort.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panel_1.add(lblStandort);
			}
			{
				JComboBox comboBox = new JComboBox();
				panel_1.add(comboBox);
				comboBox.setModel(new DefaultComboBoxModel(Standorttyp.values()));
			}
		}
		{
			JPanel panel_2 = new JPanel();
			contentPanel.add(panel_2);
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblAnzahlMitarbeiter = new JLabel("Anzahl Mitarbeiter: ");
				panel_2.add(lblAnzahlMitarbeiter);
			}
			{
				textFieldMitarbeiter = new JTextField();
				panel_2.add(textFieldMitarbeiter);
				textFieldMitarbeiter.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton kaufenButton = new JButton("kaufen");
				kaufenButton.setActionCommand("OK");
				buttonPane.add(kaufenButton);
				getRootPane().setDefaultButton(kaufenButton);
			}
			{
				JButton cancelButton = new JButton("abbrechen");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
