package controller;

import javax.swing.JOptionPane;
import de.fhwgt.dionarap.model.events.*;
import de.fhwgt.dionarap.model.listener.*;
import view.*;


/**
 * Listenerklasse, die auf Spielereignisse reagiert.
 * Damit kann das Spielfeld die Darstellung aktualisiert, sobald sich etwas im Spiel geändert hat.
 */
public class ListenerModel implements DionaRapListener
{	
	/** Instanz des Hautpfensters */	
	private MainWindow main;	
	/**
	 * Erstellt eine Instanz des Model-Listeners
	 * 
	 * @param main zu übergebende Instanz des Hauptfensters 
	 */		
	public ListenerModel (MainWindow main) {
		this.main = main;
	}	
	/**
	 * Ereignis-Handler, der ausgelöst wird, sobald sich im Spiel etwas geändert hat.
	 * Das Ereignis veranlasst das Spielfeld die Darstellung zu aktualisieren.
	 * @param e Ereignisobjekt vom Typ <code>DionaRapChangedEvent</code>, 
	 * mit weiteren Informationen über das Ereignis.
	 */		
	public void modelChanged(DionaRapChangedEvent e) {
		main.getNavigation().setScore(main.getModel().getScore());
		main.getGameField().updatePawns(main.getModel().getAllPawns());
	}
	/**
	 * Ereignis-Handler, der ausgelöst wird, wenn das Spiel beendet, also gewonnen oder verloren, ist.
	 * 
	 * @param e Spielstatus vom Typ <code>GameStatusEvent</code>
	 */
	public void statusChanged(GameStatusEvent e) {
		String message = "Sie haben gewonnen!";
		String icon = "player.gif";
		if (e.isGameOver()) {
			icon = "gameover.gif";
			message = "Sie haben verloren!";
		}
		main.prepareEndOfGame();
		JOptionPane.showMessageDialog(main, message, "Spielende", JOptionPane.PLAIN_MESSAGE, main.getTheme().getIcon(icon));			
	}


}