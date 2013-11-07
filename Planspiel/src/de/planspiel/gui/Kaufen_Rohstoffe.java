package de.planspiel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Kaufen_Rohstoffe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tableRohstoffe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Kaufen_Rohstoffe dialog = new Kaufen_Rohstoffe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Kaufen_Rohstoffe() {
		setType(Type.POPUP);
		setTitle("Rohstoffe kaufen");
		setBounds(100, 100, 450, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBounds(90, 90, 400, 250);
		{
			JPanel panelRohstoffe = new JPanel();
			panelRohstoffe.setPreferredSize(new Dimension(100, 103));
			contentPanel.add(panelRohstoffe, BorderLayout.NORTH);
			panelRohstoffe.setLayout(new BorderLayout(0, 0));
			{
				JPanel panelHaendler = new JPanel();
				panelRohstoffe.add(panelHaendler, BorderLayout.NORTH);
				panelHaendler.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblHaendlerT = new JLabel("H\u00E4ndler");
					panelHaendler.add(lblHaendlerT, BorderLayout.WEST);
				}
				{
					JLabel lblHaendler = new JLabel("... :");
					panelHaendler.add(lblHaendler);
				}
			}
			{
				tableRohstoffe = new JTable();
				panelRohstoffe.add(tableRohstoffe, BorderLayout.SOUTH);
				tableRohstoffe.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, }, new String[] { "Rohstoff", "noch im Lager", "Kaufmenge" }) {
					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Integer.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				tableRohstoffe.getColumnModel().getColumn(0).setResizable(false);
				tableRohstoffe.getColumnModel().getColumn(0).setPreferredWidth(88);
				tableRohstoffe.getColumnModel().getColumn(1).setResizable(false);
				tableRohstoffe.getColumnModel().getColumn(1).setPreferredWidth(76);
				tableRohstoffe.getColumnModel().getColumn(2).setResizable(false);
			}
			{
				JScrollPane scrollPane = new JScrollPane(tableRohstoffe);
				panelRohstoffe.add(scrollPane, BorderLayout.CENTER);
			}
			{
				JPanel panelSumme = new JPanel();
				panelRohstoffe.add(panelSumme, BorderLayout.SOUTH);
				panelSumme.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblSummeT = new JLabel("Gesamtsumme: ");
					panelSumme.add(lblSummeT, BorderLayout.WEST);
				}
				{
					JLabel lblSumme = new JLabel("...");
					panelSumme.add(lblSumme, BorderLayout.CENTER);
				}
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
