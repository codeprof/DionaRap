package controller;

import java.awt.event.*;
import javax.swing.*;

import view.MainWindow;

/**
 * Diese Klasse beinhaltet das Handling für alle Menuepunkte im Hauptfenster.
 * Die Klasse implementiert das Interface <code>ActionListener</code>
 */ 
public class ListenerMainMenuBar implements ActionListener{
	/** Instanz des Hauptfensters */
	private MainWindow parent;
	
	/**
	 * Konstruktor fuer die Klasse <code>ListenerMainMenuBar</code>.
	 * @param parent Instanz des Hauptfensters vom Typ <code>MainWindow</code>
	 */
	public ListenerMainMenuBar(MainWindow parent){
		this.parent = parent;
	}
	
	/**
	 * Event-Handler fuer das Hauptmenue. Es wird auf die folgenden Aktionen reagiert;<br>
	 * - Aenderung des Darstellungsstiels (Look and Feel)<br> 
	 * - Navigationsfenster ein- bzw. ausblenden<br>
	 * - Aendern des Themas (Aussehen der Icons)<br>
	 * - Anzeige der Spielbeschriebung<br>
	 * - Anzeige der Spielfiguren<br>
	 * - neues Level erstellen (Leveleditor oeffnen)<br>
	 * - Level aus einer Datei laden
	 * 
	 * @param e Ereignis vom Typ ActionEvent
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
 
		if (e.getActionCommand().equals("changeLookAndFeel")) {
			parent.setLookAndFeel( (String)((JComponent)e.getSource()).getClientProperty("class") ); //Klassenname aus Userdaten holen
		}
		else if (e.getActionCommand().equals("toggleNavigator")) {
			boolean show = !parent.getNavigation().isVisible();
			parent.getNavigation().setVisible(show);
			((JMenuItem)e.getSource()).setText("Navigator " + (show ? "aus": "ein")  + "blenden");
		}
		else if (e.getActionCommand().equals("changeTheme")) {		
			((JRadioButtonMenuItem)e.getSource()).setSelected(true);
			parent.getTheme().loadTheme(((JRadioButtonMenuItem)e.getSource()).getText()); //neues Thema laden
			parent.getGameField().updatePawns(parent.getModel().getAllPawns()); // neu darstellen
		}
		else if (e.getActionCommand().equals("showGameDescription") ) {
			parent.showGameDescription();
		}
		else if (e.getActionCommand().equals("showPawnInfo") ) {
			parent.showPawnInfo();
		}		
		else if (e.getActionCommand().equals("createLevel") ) {
			parent.showLevelEditorDialog();
		}
		else if (e.getActionCommand().equals("loadLevel") ) {
			parent.loadLevel();
		}				
	}
}
