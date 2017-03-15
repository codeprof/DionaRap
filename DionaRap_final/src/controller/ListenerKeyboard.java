package controller;

import java.awt.event.*;
import view.*;

/**
 * Listenerklasse, die auf Tastaturereigniss im Hauptfenster reagiert.
 * Dabei soll der Spieler, wie beim druck der entsprechenden Schaltflaechen im Navigationsfester,
 * sich bewegen oder schiessen können.
 *
 * @see java.awt.event.ActionListener
 */
public class ListenerKeyboard implements KeyListener
{
	/** Instanz des Hautpfensters */
	private MainWindow main;
	/**
	 * Erstellt eine Instanz des Keyboard-Listeners
	 * 
	 * @param main zu übergebende Instanz des Hauptfensters 
	 */		
	public ListenerKeyboard(MainWindow main) {
		this.main = main;
	}
	/**
	 * Ereignis-Handler, der ausgelöst wird, eine Taste auf der Tastatur gedrückt wird.
	 * Dadurch soll, wie beim Navigationsfester der Spieler bewegt oder geschossen werden können.
	 * @param e Ereignisobjekt vom Typ <code>KeyEvent</code>, 
	 * über das der Scancode für die gedrueckte Taste ermittelt werden kann.
	 */		
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code > KeyEvent.VK_NUMPAD0 && code <= KeyEvent.VK_NUMPAD9) {
			if (code == KeyEvent.VK_NUMPAD5) {
				main.getContoller().shoot();
			}
			else {
				main.getContoller().movePlayer(e.getKeyCode() - KeyEvent.VK_NUMPAD0);
			}
		}	
	}
	@Override
	public void keyReleased(KeyEvent e) { } //Hier nicht benoetigt		
	@Override
	public void keyTyped(KeyEvent e) { } //Hier nicht benoetigt		
}

