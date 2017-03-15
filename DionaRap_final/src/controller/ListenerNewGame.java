package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import view.MainWindow;
import view.Navigation;

/**
 * Klasse um auf den "Neues Spiel"-Buttons der Klasse <code>Navigation</code> zu reagieren.
 * Die Klasse implementiert das Interface <code>ActionListener</code>. 
 * @see java.awt.event.ActionListener
 */
public class ListenerNewGame implements ActionListener {

	/**
	 * Event-Handler fuer den "Neues Spiel"-Button im <code>Navigation</code>-Fenster.
	 * Das Hauptfenster wird hierbei ueber die Fenster-Hierarchie ermittelt.
	 * Beim klick auf den Button wird ein neues Spiel gestartet.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainWindow main = ((Navigation) ((JComponent)e.getSource()).getTopLevelAncestor() ).getMainWindow();
		main.prepareNewGame();
		main.startNewGame();
	}

}
