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

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindowGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNewItem;
	private JTextField textFieldQuantity;
	private JTextField textFieldItemCategory;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 386);
		
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
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(130, 10));
		contentPane.add(panel, "cell 0 1,alignx left,aligny center");
		panel.setLayout(new MigLayout("", "[86px][]", "[14px][20px][14px][20px][14px][][20px][][][]"));
		
		JLabel lblNewItem = new JLabel("New item");
		panel.add(lblNewItem, "cell 0 2 2 1,alignx left,aligny center");
		
		textFieldNewItem = new JTextField();
		textFieldNewItem.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		textFieldNewItem.setPreferredSize(new Dimension(6, 40));
		textFieldNewItem.setColumns(10);
		panel.add(textFieldNewItem, "cell 0 3 2 1,alignx left,aligny center");
		
		JLabel lblItemCategory = new JLabel("Item category");
		panel.add(lblItemCategory, "cell 0 4 2 1,alignx left,aligny center");
		
		textFieldItemCategory = new JTextField();
		textFieldItemCategory.setEditable(false);
		textFieldItemCategory.setColumns(10);
		panel.add(textFieldItemCategory, "cell 0 5 2 1,alignx left,aligny center");
		
		JLabel lblQuantity = new JLabel("Quantity");
		panel.add(lblQuantity, "cell 0 6 2 1,alignx left,aligny center");
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		panel.add(textFieldQuantity, "cell 0 7 2 1,alignx left,aligny center");
		
		JButton btnAddToList = new JButton("Add to list");
		panel.add(btnAddToList, "cell 0 8 2 1,alignx left");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 1 3 1,grow");
		
		JTextArea textAreaShoppingList = new JTextArea();
		scrollPane.setViewportView(textAreaShoppingList);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 2 4 1,growx,aligny bottom");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSave = new JButton("Save");
		panel_1.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		panel_1.add(btnEdit);
	}

}
