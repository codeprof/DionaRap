package controller;

import java.awt.event.*;
import javax.swing.JDialog;

/**
 * Klasse um auf den Schliessen-Button im Fenster zu reagieren.
 * Die Kalsse Implementiert das Interface <code>ActionListener</code>
 *  
 * @see java.awt.event.ActionListener
 */
public class ListenerPawnInfo implements ActionListener {
	/** Instanz des Vaterfensters */
	private JDialog parent;
	
	/**
	 * erstellt eine neue Instanz des Listeners.
	 * 
	 * @param parent Instanz des Vaterfensters
	 */
	public ListenerPawnInfo(JDialog parent) {
		this.parent = parent;
	}
	/**
	 * Event-Handler fuer den Schliessen-Button. Beim Klick auf dem Schiessen-Button soll das Fenster versteckt werden.
	 * @param event Event vom Type <code>ActionEvent</code>, auf dan reagiert werden soll
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.setVisible(false);
	}

}
