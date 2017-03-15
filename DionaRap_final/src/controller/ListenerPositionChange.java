package controller;

import java.awt.event.*;
import view.*;

/**
 * Klasse um auf das componentMoved-Ereignis des Hauptfensters zu reagieren.
 * Dazu wird die Schnittstelle <code>ComponentListener</code> implementiert.
 *  
 * @see java.awt.event.ComponentListener
 */ 
public class ListenerPositionChange implements ComponentListener{
	private Navigation nav;
	
	/**
	 *  Erzeugt eine Instanz der ListenerPositionChange-Klasse.
	 * 
	 * @param nav Instanz des Navigationsfensters
	 */
	public ListenerPositionChange(Navigation nav) {
		this.nav = nav;
	}	
	/**
	 * Ereignis-Handler, der aufgerufen wird, sobald das Hauptfenster verschoben wird.
	 * In diesem Fall muss auch das Navigationsfenster verschoben werden, damit
	 * dieses immer rechts neben dem Hauptfenster angezeigt wird.
	 * @param e Ereignisobjekt vom Typ <code>ComponentEvent</code> mit weiteren
	 *          Informationen
	 * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentListener)
	 */	
	@Override
	public void componentMoved(ComponentEvent e) {
		nav.updateLocation();
	}	
	@Override
	public void componentHidden(ComponentEvent e) { }  //Hier nicht benoetigt
	@Override
	public void componentResized(ComponentEvent e) { } //Hier nicht benoetigt
	@Override
	public void componentShown(ComponentEvent e) { } //Hier nicht benoetigt
}
