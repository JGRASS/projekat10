package shoppinglist.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
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
import shoppinglist.SHOPen;
import shoppinglist.SHOPenInterfejs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents a public class that makes a shopping list and leads you to other options of editing shopping list .
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainWindowGUI extends JFrame {

	private static JPanel contentPane;
	private JTextField textFieldNewItem;
	private JTextField textFieldQuantity;
	private SHOPenInterfejs system;

	/**
	 * List of items in food category.
	 */
	private static LinkedList<Item> food = new LinkedList<Item>();
	
	/**
	 * List of items in drinks category.
	 */
	private static LinkedList<Item> drinks = new LinkedList<Item>();
	
	/**
	 * List of items in hygiene category.
	 */
	private static LinkedList<Item> hygiene = new LinkedList<Item>();
	
	/**
	 * List of items in other category.
	 */
	//	private static LinkedList<Item> other = new LinkedList<Item>();

	/**
	 * Additional String made of food category items. 
	 */
	static String foodS = "";
	
	/**
	 * Additional String made of drinks category items. 
	 */
	static String drinksS = "";
	
	/**
	 * Additional String made of hygiene category items. 
	 */
	static String hygieneS = "";
	
	/**
	 * Additional String made of other category items. 
	 */
	//	static String otherS = "";
	
	/**
	 * Text area of current shopping list.
	 */
	final static JTextArea textAreaShoppingList = new JTextArea();

	/**
	 * Method sets area of current shopping list to String from textArea.
	 * @param textArea
	 */
	public static void setTextAreaShoppingList(String textArea) {
		textAreaShoppingList.setText(textArea);
	}

	/**
	 * Method sets category food inside shopping list to food list
	 * @param food list as a Linked list
	 */
	public static void setFood(LinkedList<Item> food) {
		MainWindowGUI.food = food;
	}

	/**
	 * Method sets category drinks inside shopping list to food list
	 * @param drinks list as a Linked list
	 */
	public static void setDrinks(LinkedList<Item> drinks) {
		MainWindowGUI.drinks = drinks;
	}

	/**
	 * Method sets category hygiene inside shopping list to food list
	 * @param hygiene list as a Linked list
	 */
	public static void setHygiene(LinkedList<Item> hygiene) {
		MainWindowGUI.hygiene = hygiene;
	}
	
	/**
	 * Method sets category other inside shopping list to food list
	 * @param other list as a Linked list
	 */
	//	public static void setOther(LinkedList<Item> other) {
	//		MainWindowGUI.other = other;
	//	}

	/**
	 * Method that returns list of items in food category as a String.
	 * @return food items as a String
	 */
	public static String getFoodS() {
		return foodS;
	}
	
	
	/**
	 * Method sets String foodS to entered list of items
	 * @param list of food items as String
	 */
	public static void setFoodS(String f) {
		foodS = f;
	}

	/**
	 * Method that returns list of items in drinks category as a String.
	 * @return drinks items as a String
	 */
	public static String getDrinksS() {
		return drinksS;
	}

	/**
	 * Method sets String drinksS to entered list of items
	 * @param list of drinks items as String
	 */
	public static void setDrinksS(String d) {
		drinksS = d;
	}

	/**
	 * Method that returns list of items in hygiene category as a String.
	 * @return hygiene items as a String
	 */
	public static String getHygieneS() {
		return hygieneS;
	}

	/**
	 * Method sets String hygieneS to entered list of items
	 * @param list of hygiene items as String
	 */
	public static void setHygieneS(String h) {
		hygieneS = h;
	}

	/**
	 * Method that returns list of items in other category as a String.
	 * @return other items as a String
	 */
	//	public static String getOtherS() {
	//		return otherS;
	//	}
	
	/**
	 * Method sets String otherS to entered list of items
	 * @param list of other items as String
	 */
	//	public static void setOtherS(String o) {
	//		otherS = o;
	//	}

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWindowGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				closeApp();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/icons/shopping-bag-definition.jpg")));
		setTitle("SHOPen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 386);

		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				/*
				 * kada kliknemo na new pita nas da li da sacuvamo listu koju smo do sad pravili, ako izaberemo yes onda pokrece
				 * metodu save, ako izaberemo no otvara se nova lista
				 */
				int opt = JOptionPane.showConfirmDialog(contentPane, "Do you want to save your list before you open new?",
						"Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
				if(opt == JOptionPane.YES_OPTION) {
					save();
				}
				/*
				 * "otvara" se nova lista, odnosno brise se sve sto smo upisivali u liste ukoliko vec nisu bile prazne
				 */
				if(!food.isEmpty()){
					food.remove();
					foodS = "";
				}
				if(!drinks.isEmpty()) {
					drinks.remove();
					drinksS = "";
				}
				//				if(!other.isEmpty()) {
				//					other.remove();
				//					otherS = "";
				//				}
				if(!hygiene.isEmpty()) {
					hygiene.remove();
					hygieneS = "";
				}

				textAreaShoppingList.setText("");
				if (!SHOPen.getShoppingList().isEmpty())
					SHOPen.setShoppingList(null);
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {

				JFileChooser jf = new JFileChooser();
				int opcija = jf.showOpenDialog(contentPane);

				if (opcija == JFileChooser.APPROVE_OPTION) {
					File f = jf.getSelectedFile();
					textAreaShoppingList.append("\n" + f.getAbsolutePath());
					system.readFile(f.getAbsolutePath());
				}
				// dodati deserijalizaciju
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				closeApp();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnOptions.add(mntmEdit);

		JMenuItem mntmCombine = new JMenuItem("Combine");
		mnOptions.add(mntmCombine);

		JMenuItem mntmTranferCategory = new JMenuItem("Tranfer category");
		mnOptions.add(mntmTranferCategory);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				JOptionPane.showMessageDialog(contentPane,
						"Version 1.0 \nAuthors: \n  Sanja Zelenovic, \n  Milena Djurdjic, \n  Nevena Djuricic", 
						"About", JOptionPane.INFORMATION_MESSAGE);
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

		final JComboBox comboBoxItemCategory = new JComboBox<>();

		comboBoxItemCategory.setModel(new DefaultComboBoxModel(new String[] {"- select category -", "Food", "Drinks", "Hygiene", "Other"}));
		panelNewItems.add(comboBoxItemCategory, "cell 0 5 2 1,growx");

		JLabel lblQuantity = new JLabel("Quantity");
		panelNewItems.add(lblQuantity, "cell 0 6,alignx left,aligny center");

		JScrollPane scrollPaneShoppingList = new JScrollPane();
		contentPane.add(scrollPaneShoppingList, "cell 1 1 3 1,grow");

		textAreaShoppingList.setEditable(false);
		scrollPaneShoppingList.setViewportView(textAreaShoppingList);

		JPanel panelButtons = new JPanel();
		panelButtons.setMaximumSize(new Dimension(32767, 50));
		contentPane.add(panelButtons, "cell 0 2 4 1,grow");
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				save();
			}
		});
		panelButtons.add(btnSave);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if( !(textAreaShoppingList.getText().isEmpty()) ) {
					new EditGUI().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Error: You have to add an item in order to change it!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panelButtons.add(btnEdit);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				closeApp();
			}
		});
		panelButtons.add(btnExit);

		JButton btnAddToList = new JButton("Add to list");
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(comboBoxItemCategory.getSelectedItem().equals("- select category -")) {
						JOptionPane.showMessageDialog(contentPane, "You have to select a category!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}

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
					Item newItem = new Item();
					newItem.setCategory(category);
					newItem.setItemName(item);
					newItem.setQuantity(quantity);

					// unos u Shopping listu

					String newLine = ""; 

					/*
					 * ako je jedini objekat u listi ovaj koji smo sad dodali onda to znaci da je lista pre bila prazna
					 * pa nam treba da se ta nova lista pojavi u sledecem redu
					 */
					if(comboBoxItemCategory.getSelectedItem().equals("Drinks")) {
//						if(!drinks.contains(newItem)) {
							drinks.add(newItem);

							if (drinks.size() == 1) {
								if (!textAreaShoppingList.getText().equals("")) {
									newLine = "\n";
								}

								drinksS =  newLine + "Drinks" + newItem.toString();
							}
							else
								drinksS += newItem.toString();
						}
//					} else {
//						JOptionPane.showMessageDialog(contentPane, "Error! The item is already in the list!", 
//								"Error", JOptionPane.ERROR_MESSAGE);
//					}

					if(comboBoxItemCategory.getSelectedItem().equals("Food")) {
//						if(!food.contains(newItem)) {
							food.add(newItem);

							if (food.size() == 1) {
								if (!textAreaShoppingList.getText().equals("")){
									newLine = "\n";
								}

								foodS = newLine + "Food" + newItem.toString();
							} else
								foodS += newItem.toString();
						}
//					} else {
//						JOptionPane.showMessageDialog(contentPane, "Error! The item is already in the list!", 
//								"Error", JOptionPane.ERROR_MESSAGE);
//					}


					if(comboBoxItemCategory.getSelectedItem().equals("Hygiene")) {
//						if(!hygiene.contains(newItem)) {
							hygiene.add(newItem);

							if (hygiene.size() == 1) {
								if (!textAreaShoppingList.getText().equals("")) {
									newLine = "\n";
								}

								hygieneS = newLine + "Hygiene" + newItem.toString();
							}
							else
								hygieneS += newItem.toString();
						}
//					} else {
//						JOptionPane.showMessageDialog(contentPane, "Error! The item is already in the list!", 
//								"Error", JOptionPane.ERROR_MESSAGE);
//					}

					//					if(comboBoxItemCategory.getSelectedItem().equals("Other")) {
					//						other.addLast(newItem);
					//
					//						if (other.size() == 1)
					//							if (!textAreaShoppingList.getText().equals("")) {
					//								newLine = "\n";
					//
					//								otherS = newLine + "Other" + newItem.toString();
					//							}
					//							else 
					//								otherS += newItem.toString();
					//					}

					textAreaShoppingList.setText(foodS + drinksS + hygieneS); //+ otherS

					textFieldNewItem.setText("");
					textFieldQuantity.setText("");
					
					system.addToList(newItem); // metoda iz SHOPen-a , update shopping liste
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

	public void save() {
		/*
		 * ako nije prazan text area pita nas da li zelimo da sacuvamo listu, ako izaberemo yes serijalizuju se uneti objekti
		 * u fajl shoppinglist.out i prikazuje se gde je sacuvano 
		 */
		if(!textAreaShoppingList.getText().isEmpty()) {
			int option = JOptionPane.showConfirmDialog(contentPane, "Do you want to save your shopping list?",
					"Save", JOptionPane.YES_NO_CANCEL_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new BufferedOutputStream(new FileOutputStream("files/shoppinglist.out")));

					JFileChooser fc = new JFileChooser();
					int opcija = fc.showSaveDialog(contentPane);

					if (opcija == JFileChooser.APPROVE_OPTION) {
						File f = fc.getSelectedFile();
						textAreaShoppingList.append("\n" + f.getAbsolutePath());
						system.saveFile(f.getAbsolutePath());
					}					

					out.writeObject(system.returnShoppingList());

					out.close();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Error: " + e.getMessage(), 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			/*
			 * u suprotnom nas obavestava da moramo nesto da unesemo kako bismo sacuvali listu
			 */
		} else {
			JOptionPane.showMessageDialog(contentPane, "Error: You have to add an item in order to save the list!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void closeApp() {

		int option = JOptionPane.showConfirmDialog(contentPane, "Do you really want to exit? REALLY?!",
				"Exit", JOptionPane.YES_NO_CANCEL_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static LinkedList<Item> returnFood() {
		return food;
	}

	public static LinkedList<Item> returnDrinks() {
		return drinks;
	}

	public static LinkedList<Item> returnHygiene() {
		return hygiene;
	}

	//	public static LinkedList<Item> showOther() {
	//		return other;
	//	}


}