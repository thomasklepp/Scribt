package ashGui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import mainScribt.WorkerBeeForASH;

public class ASH_JFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ASH_JFrame instance = null;
	
	public static final int parser_typ_BGLD = 1;
	public static final int parser_typ_KTN = 2;
	public static final int parser_typ_NOE = 3;
	public static final int parser_typ_OOE = 4;
	public static final int parser_typ_SBG = 5;
	public static final int parser_typ_STMK = 6;
	public static final int parser_typ_T = 7;
	public static final int parser_typ_VBG = 8;
	public static final int parser_typ_W = 9;
		
	private int selected_parser_typ = 0;
	
	private File sorce = null;
	private File destination = null;
	
	private ASH_Panel1 contentPane = null;
	private JMenuBar menuBar = null;
	private JMenu dateiMenu = null;
	private JMenu helpMenu = null;
	private JMenu editMenu = null;
	private JMenu chooseParserMenu = null;
	
	private JMenuItem parser_menuitem_BGLD = null;
	private JMenuItem parser_menuitem_KTN = null;
	private JMenuItem parser_menuitem_NOE = null;
	private JMenuItem parser_menuitem_OOE = null;
	private JMenuItem parser_menuitem_SBD = null;
	private JMenuItem parser_menuitem_STMK = null;
	private JMenuItem parser_menuitem_T = null;
	private JMenuItem parser_menuitem_VBG = null;
	private JMenuItem parser_menuitem_W = null;
	
	private JMenuItem exitItem = null;
	private JMenuItem helpItem = null;
	private JMenuItem preferencesItem = null;
	
	private JMenuItem selectSorceItem = null;
	private JMenuItem selectDestItem = null;
	
	private final JFileChooser fileChooser = new JFileChooser();
	private WorkerBeeForASH workerBee = null;
	
	// Preferences
	private PreferencesFrame prefFrame = new PreferencesFrame();
	
	public ASH_JFrame() {
		
		createAndShowGui();
	}
	
	/**
	 * initializing UI
	 */
	private void createAndShowGui() {
			
		// initialize fileChooser
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		contentPane = new ASH_Panel1(this);
		this.setContentPane(contentPane);
		
		//Create the menu bar.
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(400, 20));
		
		//Build the datei- menu.
		dateiMenu = new JMenu("Datei");
		dateiMenu.setMnemonic(KeyEvent.VK_A);
		dateiMenu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(dateiMenu);
		
		// build the edit menu
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_A);
		editMenu.getAccessibleContext().setAccessibleDescription(
		        "Edut Menu");
		menuBar.add(editMenu);
		
		// build the help menu
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_A);
		helpMenu.getAccessibleContext().setAccessibleDescription(
		        "Help Menu");
		menuBar.add(helpMenu);
		
		// choose parser type menu
		chooseParserMenu = new JMenu("Choose Parser");
		chooseParserMenu.setMnemonic(KeyEvent.VK_A);
		chooseParserMenu.getAccessibleContext().setAccessibleDescription(
		        "Choose Parser");
		menuBar.add(chooseParserMenu); 
		
		// datei menu items
		exitItem = new JMenuItem("Exit", KeyEvent.VK_T);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		exitItem.getAccessibleContext().setAccessibleDescription("Exits the Programm");
		exitItem.addActionListener(this);
		exitItem.setActionCommand("exit");
		
		selectSorceItem = new JMenuItem("Select Sorce Folder", KeyEvent.VK_T);
		selectSorceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		selectSorceItem.getAccessibleContext().setAccessibleDescription("Opens Filechooser");
		selectSorceItem.addActionListener(this);
		selectSorceItem.setActionCommand("sorce");
		
		selectDestItem = new JMenuItem("Select Destination Folder", KeyEvent.VK_T);
		selectDestItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		selectDestItem.getAccessibleContext().setAccessibleDescription("Opens Filechooser");
		selectDestItem.addActionListener(this);
		selectDestItem.setActionCommand("destination");
		if (!contentPane.isRename()) {
			selectDestItem.setEnabled(false);
		}
		
		// help Item
		preferencesItem = new JMenuItem("Preferences", KeyEvent.VK_T);
		preferencesItem.getAccessibleContext().setAccessibleDescription("Opens Preferences");
		preferencesItem.addActionListener(this);
		preferencesItem.setActionCommand("pref");
		
		// help Item
		helpItem = new JMenuItem("Help", KeyEvent.VK_T);		
		helpItem.getAccessibleContext().setAccessibleDescription("Opens Help");
		helpItem.addActionListener(this);
		helpItem.setActionCommand("help");
		
		parser_menuitem_BGLD = new JMenuItem("Burgenland", KeyEvent.VK_T);
		parser_menuitem_BGLD.getAccessibleContext().setAccessibleDescription("Select parser for Burgenland");
		parser_menuitem_BGLD.addActionListener(this);
		parser_menuitem_BGLD.setActionCommand("burgenland");
		
		parser_menuitem_KTN = new JMenuItem("Kaernten", KeyEvent.VK_T);
		parser_menuitem_KTN.getAccessibleContext().setAccessibleDescription("Select parser for Kaernten");
		parser_menuitem_KTN.addActionListener(this);
		parser_menuitem_KTN.setActionCommand("kaernten");
		
		parser_menuitem_NOE = new JMenuItem("Niederoesterreich", KeyEvent.VK_T);
		parser_menuitem_NOE.getAccessibleContext().setAccessibleDescription("Select parser for Niederoesterreich");
		parser_menuitem_NOE.addActionListener(this);
		parser_menuitem_NOE.setActionCommand("niederoesterreich");
		
		parser_menuitem_OOE = new JMenuItem("Oberoesterreich", KeyEvent.VK_T);
		parser_menuitem_OOE.getAccessibleContext().setAccessibleDescription("Select parser for Oberoesterreich");
		parser_menuitem_OOE.addActionListener(this);
		parser_menuitem_OOE.setActionCommand("oberoesterreich");
		
		parser_menuitem_SBD = new JMenuItem("Salzburg", KeyEvent.VK_T);
		parser_menuitem_SBD.getAccessibleContext().setAccessibleDescription("Select parser for Salzburg");
		parser_menuitem_SBD.addActionListener(this);
		parser_menuitem_SBD.setActionCommand("salzburg");
		
		parser_menuitem_STMK = new JMenuItem("Steiermark", KeyEvent.VK_T);
		parser_menuitem_STMK.getAccessibleContext().setAccessibleDescription("Select parser for Steiermark");
		parser_menuitem_STMK.addActionListener(this);
		parser_menuitem_STMK.setActionCommand("steiermark");
		
		parser_menuitem_T = new JMenuItem("Tirol", KeyEvent.VK_T);
		parser_menuitem_T.getAccessibleContext().setAccessibleDescription("Select parser for Tirol");
		parser_menuitem_T.addActionListener(this);
		parser_menuitem_T.setActionCommand("tirol");
		
		parser_menuitem_VBG = new JMenuItem("Vorarlberg", KeyEvent.VK_T);
		parser_menuitem_VBG.getAccessibleContext().setAccessibleDescription("Select parser for Vorarlberg");
		parser_menuitem_VBG.addActionListener(this);
		parser_menuitem_VBG.setActionCommand("vorarlberg");
		
		parser_menuitem_W = new JMenuItem("Wien", KeyEvent.VK_T);
		parser_menuitem_W.getAccessibleContext().setAccessibleDescription("Select parser for Wien");
		parser_menuitem_W.addActionListener(this);
		parser_menuitem_W.setActionCommand("wien");
				
		// menue tree
		dateiMenu.add(selectSorceItem);
		dateiMenu.add(selectDestItem);
		dateiMenu.add(exitItem);
		editMenu.add(preferencesItem);
		helpMenu.add(helpItem);
				
		chooseParserMenu.add(parser_menuitem_BGLD);	
		chooseParserMenu.add(parser_menuitem_KTN);
		chooseParserMenu.add(parser_menuitem_NOE);
		chooseParserMenu.add(parser_menuitem_OOE);
		chooseParserMenu.add(parser_menuitem_SBD);
		chooseParserMenu.add(parser_menuitem_STMK);
		chooseParserMenu.add(parser_menuitem_T);
		chooseParserMenu.add(parser_menuitem_VBG);
		chooseParserMenu.add(parser_menuitem_W);
				
		this.setJMenuBar(menuBar);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(500, 500));
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	// Singelton Pattern
	public static ASH_JFrame instance() {
		if (instance == null) {
			instance = new ASH_JFrame();
		}
		return instance;
	}

	// Observer Pattern
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String e = arg0.getActionCommand();
		
		// exit
		if (e.equals("exit")) {
			this.dispose();
		}
		else if (e.equals("help")) {
			contentPane.setOutput("Ask STLEN or ALTAT! :-P");
			JOptionPane.showMessageDialog(this,
				    "Ask STLEN or ALTAT! :-P.");
		}
		else if (e.equals("sorce")) {
			fileChooser.showOpenDialog(this);
			sorce = fileChooser.getSelectedFile();
			if (sorce != null) {
				contentPane.setOutput("Selected Sorce: " + sorce.getAbsolutePath());
				workerBee.setPathToFiles(sorce.getAbsolutePath());
				contentPane.setSorceSelected(true);
			}
		}
		else if (e.equals("destination")) {
			fileChooser.showOpenDialog(this);
			destination = fileChooser.getSelectedFile();
			if (destination != null) {
				contentPane.setOutput("Selected Destination: " + destination.getAbsolutePath());
				workerBee.setPathToDestinationFiles(destination.getAbsolutePath());
				contentPane.setDestinationSelected(true);
			}
		}
		else if (e.equals("pref")) {
			prefFrame.setVisible(true);
		}
		else if (e.equals("burgenland")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_BGLD;
			parser_menuitem_BGLD.setSelected(true);
		}
		else if (e.equals("kaernten")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_KTN;
			parser_menuitem_KTN.setSelected(true);
		}
		else if (e.equals("niederoesterreich")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_NOE;
			parser_menuitem_NOE.setSelected(true);
		}
		else if (e.equals("oberoesterreich")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_OOE;
			parser_menuitem_OOE.setSelected(true);
		}
		else if (e.equals("salzburg")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_SBG;
			parser_menuitem_SBD.setSelected(true);
		}
		else if (e.equals("steiermark")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_STMK;
			parser_menuitem_STMK.setSelected(true);
		}
		else if (e.equals("tirol")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_T;
			parser_menuitem_T.setSelected(true);
		}
		else if (e.equals("vorarlberg")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_VBG;
			parser_menuitem_VBG.setSelected(true);
		}
		else if (e.equals("wien")) {
			setParserSelectionToNull();
			selected_parser_typ = parser_typ_W;
			parser_menuitem_W.setSelected(true);
		}
	}
	
	private void setParserSelectionToNull(){
		parser_menuitem_BGLD.setSelected(false);
		parser_menuitem_KTN.setSelected(false);
		parser_menuitem_NOE.setSelected(false);
		parser_menuitem_OOE.setSelected(false);
		parser_menuitem_SBD.setSelected(false);
		parser_menuitem_STMK.setSelected(false);
		parser_menuitem_T.setSelected(false);
		parser_menuitem_VBG.setSelected(false);
		parser_menuitem_W.setSelected(false);
		selected_parser_typ = 0;
	}
	
	public boolean getUmbenennen() {
		return prefFrame.isChangeNames();
	}

	public ASH_Panel1 getContentPane() {
		return contentPane;
	}

	public WorkerBeeForASH getWorkerBee() {
		return workerBee;
	}
	
	public void setMenuEnable(boolean enable) {
		selectDestItem.setEnabled(enable);
	}

	public int getSelected_parser_typ() {
		return selected_parser_typ;
	}

	public void setSelected_parser_typ(int selected_parser_typ) {
		this.selected_parser_typ = selected_parser_typ;
	}

	public void setWorkerBee(WorkerBeeForASH workerBee) {
		this.workerBee = workerBee;
	}
	
	
		
}
