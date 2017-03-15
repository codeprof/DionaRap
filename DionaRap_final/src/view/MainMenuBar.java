package view;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.ListenerMainMenuBar;

/**
 * Klasse abgeleitet von JMenuBar, die das Menue des Hauptfensters repraesentiert
 */
public class MainMenuBar extends JMenuBar{	
	/** Interne Identifikationsnummer für Serialisierung */
	private static final long serialVersionUID = -4820217657086450588L;
	/** Instanz der Menueitems für "Level erstellen" und "Level laden..." */
	private JMenuItem loadLevel, createLevel;	
	/**
	 * Erstellt eine neue Instanz des Hauptmenues
	 * 
	 * @param parent Instanz des Hauptfensters
	 */
	public MainMenuBar(MainWindow parent)
	{
		JMenuItem item;	
		ListenerMainMenuBar listener = new ListenerMainMenuBar(parent);
		
		JMenu config = new JMenu("Konfigurieren");
		
		JMenu lnFSubMenu = (JMenu)config.add(new JMenu("Look and Feel"));		
		for(LookAndFeelInfo o : UIManager.getInstalledLookAndFeels()) {
			item = lnFSubMenu.add(o.getName());
			item.setActionCommand("changeLookAndFeel");
			item.putClientProperty("class", o.getClassName()); //Klassenname in Userdaten speichern 
			item.addActionListener(listener);
		}
				
		ButtonGroup group = new ButtonGroup();
		JMenu themaSubMenu = (JMenu)config.add(new JMenu("Thema"));
		for(int i = 0; i < parent.getTheme().getThemes().size(); i++) {
			String theme = parent.getTheme().getThemes().get(i);
			JRadioButtonMenuItem radioItem = new JRadioButtonMenuItem(theme);
			group.add(radioItem); //Zur Radio-Button Gruppe hinzufügen
			themaSubMenu.add(radioItem);
			radioItem.setActionCommand("changeTheme"); 
			radioItem.addActionListener(listener);
			if (i == 0){ radioItem.setSelected(true);} // erstes Thema auswählen
		} 
	    
		item = config.add(new JMenuItem("Navigator ausblenden")); //Standardmäßig eingeblendet	
		item.setActionCommand("toggleNavigator"); 	
		item.addActionListener(listener);
		
		JMenu help = new JMenu("Hilfe");
		item = help.add("Spielfiguren anzeigen");
		item.setActionCommand("showPawnInfo");
		item.addActionListener(listener);
		item = help.add("Spielbeschreibung");		
		item.setActionCommand("showGameDescription");
		item.addActionListener(listener);	
		
		JMenu level = new JMenu("Level");
		createLevel = level.add("Level erstellen");
		createLevel.setActionCommand("createLevel");
		createLevel.addActionListener(listener);
		loadLevel = level.add("Level laden...");		
		loadLevel.setActionCommand("loadLevel");
		loadLevel.addActionListener(listener);	
				
		add(config);
		add(help);
		add(level);
		parent.setJMenuBar(this);
	}
	/**
	 * Aktiviert bzw. deaktiviert die Menueitems für "Level erstellen" und "Level laden...", da
	 * diese waehrend eines Spiels nicht gedrueckt werden koennen sollen.
	 * 
	 * @param enable aktiviert(true) oder deaktiviert(false) die Menueeintraege
	 */
	public void enableLevelCommands(boolean enable) {
		createLevel.setEnabled(enable);
		loadLevel.setEnabled(enable);
	}

}
