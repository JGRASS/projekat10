package shoppinglist.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.FlowLayout;

import javax.swing.JButton;

import shoppinglist.Item;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("serial")
public class MainWindowGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNewItem;
	private JTextField textFieldQuantity;

	private LinkedList<Item> food = new LinkedList<Item>();
	private LinkedList<Item> drinks = new LinkedList<Item>();
	private LinkedList<Item> hygiene = new LinkedList<Item>();
	private LinkedList<Item> other = new LinkedList<Item>();

	String foodS = "";
	String drinksS = "";
	String hygieneS = "";
	String otherS = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowGUI frame = new MainWindowGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindowGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/icons/shopping bag.jpg")));
		setTitle("SHOPen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 386);

		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnOptions.add(mntmEdit);

		JMenuItem mntmCombine = new JMenuItem("Combine");
		mnOptions.add(mntmCombine);

		JMenuItem mntmRecepies = new JMenuItem("Recepies");
		mnOptions.add(mntmRecepies);

		JMenuItem mntmTranferCategory = new JMenuItem("Tranfer category");
		mnOptions.add(mntmTranferCategory);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPane,
						"Version 1.0    Authors: Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[161.00px,grow][82.00][95.00][80.00,grow]", "[][231px,grow][grow]"));

		JLabel lblShoppingList = new JLabel("Shopping list");
		contentPane.add(lblShoppingList, "cell 1 0 3 1,alignx center");

		JPanel panelNewItems = new JPanel();
		panelNewItems.setMaximumSize(new Dimension(400, 32767));
		panelNewItems.setPreferredSize(new Dimension(130, 10));
		contentPane.add(panelNewItems, "cell 0 1,growx,aligny center");
		panelNewItems.setLayout(new MigLayout("", "[86px,grow][]", "[14px][20px][14px][20px][14px][][][20px][][][]"));

		JLabel lblNewItem = new JLabel("New item");
		panelNewItems.add(lblNewItem, "cell 0 2 2 1,alignx left,aligny center");

		textFieldNewItem = new JTextField();
		textFieldNewItem.setPreferredSize(new Dimension(6, 40));
		textFieldNewItem.setColumns(10);
		panelNewItems.add(textFieldNewItem, "cell 0 3 2 1,growx,aligny center");

		JLabel lblItemCategory = new JLabel("Item category");
		panelNewItems.add(lblItemCategory, "cell 0 4 2 1,alignx left,aligny center");

		final JComboBox<String> comboBoxItemCategory = new JComboBox<String>();

		comboBoxItemCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Food", "Drinks", "Hygiene", "Other"}));
		panelNewItems.add(comboBoxItemCategory, "cell 0 5 2 1,growx");

		JLabel lblQuantity = new JLabel("Quantity");
		panelNewItems.add(lblQuantity, "cell 0 6,alignx left,aligny center");

		JScrollPane scrollPaneShoppingList = new JScrollPane();
		contentPane.add(scrollPaneShoppingList, "cell 1 1 3 1,grow");

		final JTextArea textAreaShoppingList = new JTextArea();
		textAreaShoppingList.setEditable(false);
		scrollPaneShoppingList.setViewportView(textAreaShoppingList);

		JPanel panelButtons = new JPanel();
		panelButtons.setMaximumSize(new Dimension(32767, 50));
		contentPane.add(panelButtons, "cell 0 2 4 1,grow");
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSave = new JButton("Save");
		panelButtons.add(btnSave);

		JButton btnEdit = new JButton("Edit");
		panelButtons.add(btnEdit);

		JButton btnAddToList = new JButton("Add to list");
		btnAddToList.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new BufferedOutputStream(new FileOutputStream("shoppinglist.out")));

					// ucitavanje iz polja za unos
					String item = textFieldNewItem.getText();
					String category = comboBoxItemCategory.getSelectedItem().toString();
					String quantity = textFieldQuantity.getText(); // ne moraju da ga unesu

					// mora biti unet naziv novog itema
					if (item.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "You have to name the item!",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// pravljenje novog objekta Item
					Item newItem = new Item(item, category);
					newItem.setQuantity(quantity);

					// unos u Shopping listu

					String newLine = "";

					if(comboBoxItemCategory.getSelectedItem().equals("Food")) {
						food.add(newItem);

						if (food.size() == 1) {
							if (!textAreaShoppingList.getText().equals(""))
								newLine = "\n";

							foodS = newLine + "Food" + newItem.toString();
						} else
							foodS += newItem.toString();
					}

					if(comboBoxItemCategory.getSelectedItem().equals("Drinks")) {
						drinks.add(newItem);

						if (drinks.size() == 1) {
							if (!textAreaShoppingList.getText().equals(""))
								newLine = "\n";

							drinksS = newLine + "Drinks" + newItem.toString();
						}
						else
							drinksS += newItem.toString();
					}

					if(comboBoxItemCategory.getSelectedItem().equals("Hygiene")) {
						hygiene.add(newItem);

						if (hygiene.size() == 1) {
							if (!textAreaShoppingList.getText().equals(""))
								newLine = "\n";

							hygieneS = newLine + "Hygiene" + newItem.toString();
						}
						else
							hygieneS += newItem.toString();
					}

					if(comboBoxItemCategory.getSelectedItem().equals("Other")) {
						other.add(newItem);

						if (other.size() == 1)
							if (!textAreaShoppingList.getText().equals("")) {
								newLine = "\n";

								otherS = newLine + "Other" + newItem.toString();
							}
							else
								otherS += newItem.toString();
					}

					textAreaShoppingList.setText(foodS + drinksS + hygieneS + otherS);

					out.writeObject(newItem);
					
					textFieldNewItem.setText("");
					textFieldQuantity.setText("");
					
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		panelNewItems.add(textFieldQuantity, "cell 0 7 2 1,growx,aligny center");
		panelNewItems.add(btnAddToList, "cell 0 9 2 1,growx,aligny bottom");
	}

}
