package shoppinglist.gui;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a public class that makes a shopping list and leads you to other options of editing shopping list .
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainWindowGUI extends JFrame {

	/** The content pane. */
	static JPanel contentPane;
	
	/** The text field new item. */
	static JTextField textFieldNewItem;
	
	/** The text field quantity. */
	static JTextField textFieldQuantity;
	
	/** The combo box item category. */
	static JComboBox<String> comboBoxItemCategory = new JComboBox<String>();

	/**List of items in food category.*/
	private static LinkedList<Item> food = new LinkedList<Item>();

	/**List of items in drinks category.*/
	private static LinkedList<Item> drinks = new LinkedList<Item>();

	/**List of items in hygiene category.*/
	private static LinkedList<Item> hygiene = new LinkedList<Item>();

	/**Additional String made of food category items. */
	static String foodS = "";

	/**Additional String made of drinks category items.*/
	static String drinksS = "";

	/**Additional String made of hygiene category items.*/
	static String hygieneS = "";

	public static Object comboBoxCategory;

	/**Text area of current shopping list.*/
	final static JTextArea textAreaShoppingList = new JTextArea();

	/**
	 * Method sets area of current shopping list to String from textArea.
	 * @param textArea the new text area shopping list
	 */
	public static void setTextAreaShoppingList(String textArea) {
		textAreaShoppingList.setText(textArea);
	}

	/**
	 * Method sets category food inside shopping list to food list.
	 * @param food list as a Linked list
	 */
	public static void setFood(LinkedList<Item> food) {
		MainWindowGUI.food = food;
	}

	/**
	 * Method sets category drinks inside shopping list to food list.
	 * @param drinks list as a Linked list
	 */
	public static void setDrinks(LinkedList<Item> drinks) {
		MainWindowGUI.drinks = drinks;
	}

	/**
	 * Method sets category hygiene inside shopping list to food list.
	 * @param hygiene list as a Linked list
	 */
	public static void setHygiene(LinkedList<Item> hygiene) {
		MainWindowGUI.hygiene = hygiene;
	}

	/**
	 * Method that returns list of items in food category as a String.
	 * @return food items as a String
	 */
	public static String getFoodS() {
		return foodS;
	}

	/**
	 * Method sets String foodS to entered list of items.
	 * @param f the new food s
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
	 * Method sets String drinksS to entered list of items.
	 * @param d the new drinks s
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
	 * Method sets String hygieneS to entered list of items.
	 * @param h the new hygiene s
	 */
	public static void setHygieneS(String h) {
		hygieneS = h;
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWindowGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				GUIControler.closeApp();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/icons/shopping-bag-definition.jpg")));
		setTitle("SHOPen");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 386);

		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewList = new JMenuItem("New list");
		mntmNewList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewList.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
		mntmNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.newList();
			}
		});
		mnFile.add(mntmNewList);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openShowListWindow();
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openSaveWindow();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmExit.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.closeApp();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mntmEdit.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		mnOptions.add(mntmEdit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAbout.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/javafx/scene/web/skin/FontColor_16x16_JFX.png")));
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
		panelNewItems.setLayout(new MigLayout("", "[86px,grow][]", "[14px][20px][14px][20px][14px][][][20px][][][][][]"));

		JLabel lblNewItem = new JLabel("New item");
		panelNewItems.add(lblNewItem, "cell 0 2 2 1,alignx left,aligny center");

		textFieldNewItem = new JTextField();
		textFieldNewItem.setPreferredSize(new Dimension(6, 40));
		textFieldNewItem.setColumns(10);
		panelNewItems.add(textFieldNewItem, "cell 0 3 2 1,growx,aligny center");

		JLabel lblItemCategory = new JLabel("Item category");
		panelNewItems.add(lblItemCategory, "cell 0 4 2 1,alignx left,aligny center");		

		comboBoxItemCategory.setModel(new DefaultComboBoxModel(new String[] {"- select category -", "Food", "Drinks", "Hygiene"}));
		panelNewItems.add(comboBoxItemCategory, "cell 0 5 2 1,growx");

		JLabel lblQuantity = new JLabel("Quantity");
		panelNewItems.add(lblQuantity, "cell 0 6,alignx left,aligny center");

		JScrollPane scrollPaneShoppingList = new JScrollPane();
		contentPane.add(scrollPaneShoppingList, "cell 1 1 3 1,grow");

		textAreaShoppingList.setEditable(false);
		scrollPaneShoppingList.setViewportView(textAreaShoppingList);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textAreaShoppingList, popupMenu);

		JMenuItem mntmNewListPop = new JMenuItem("New list");
		mntmNewListPop.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		mntmNewListPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.newList();
			}
		});
		popupMenu.add(mntmNewListPop);

		JMenuItem mntmOpenPop = new JMenuItem("Open");
		mntmOpenPop.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mntmOpenPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openShowListWindow();
			}
		});
		popupMenu.add(mntmOpenPop);

		JMenuItem mntmSavePop = new JMenuItem("Save");
		mntmSavePop.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmSavePop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openSaveWindow();
			}
		});
		popupMenu.add(mntmSavePop);

		JMenuItem mntmEditPop = new JMenuItem("Edit");
		mntmEditPop.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		mntmEditPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openEditWindow();
			}
		});
		popupMenu.add(mntmEditPop);

		JMenuItem mntmExitPop = new JMenuItem("Exit");
		mntmExitPop.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmExitPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.closeApp();
			}
		});
		popupMenu.add(mntmExitPop);

		JPanel panelButtons = new JPanel();
		panelButtons.setMaximumSize(new Dimension(32767, 50));
		contentPane.add(panelButtons, "cell 0 2 4 1,grow");
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openSaveWindow();
			}
		});
		panelButtons.add(btnSave);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.openEditWindow();
			}
		});
		panelButtons.add(btnEdit);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.closeApp();
			}
		});
		panelButtons.add(btnExit);

		JButton btnAddToList = new JButton("Add to list");
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIControler.addToList();
			}
		});

		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		panelNewItems.add(textFieldQuantity, "cell 0 7 2 1,growx,aligny center");
		panelNewItems.add(btnAddToList, "cell 0 9 2 1,growx,aligny bottom");
	}

	/**
	 * Return food.
	 *
	 * @return list of added items, category food
	 */
	public static LinkedList<Item> returnFood() {
		return food;
	}

	/**
	 * Return drinks.
	 *
	 * @return list of added items, category drinks
	 */
	public static LinkedList<Item> returnDrinks() {
		return drinks;
	}

	/**
	 * Return hygiene.
	 *
	 * @return list of added items, category hygiene
	 */
	public static LinkedList<Item> returnHygiene() {
		return hygiene;
	}
	
	/**
	 * Adds the popup to textAreaShoppingList.
	 *
	 * @param component the component
	 * @param popup the popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
}