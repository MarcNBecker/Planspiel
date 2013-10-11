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
import javax.swing.JTextField;

import java.awt.Window.Type;

public class Kaufen_Rohstoffe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

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
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(100, 103));
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblHaendlerT = new JLabel("H\u00E4ndler");
					panel_1.add(lblHaendlerT, BorderLayout.WEST);
				}
				{
					JLabel lblHaendler = new JLabel("... :");
					panel_1.add(lblHaendler);
				}
			}
			{
				table = new JTable();
				panel.add(table, BorderLayout.SOUTH);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
					},
					new String[] {
						"Rohstoff", "noch im Lager", "Kaufmenge"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, true
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(88);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(76);
				table.getColumnModel().getColumn(2).setResizable(false);
			}
			{
				JScrollPane scrollPane = new JScrollPane(table);
				panel.add(scrollPane, BorderLayout.CENTER);
			}
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.SOUTH);
				panel_2.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblSummeT = new JLabel("Gesamtsumme: ");
					panel_2.add(lblSummeT, BorderLayout.WEST);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("...");
					panel_2.add(lblNewLabel_1, BorderLayout.CENTER);
				}
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
