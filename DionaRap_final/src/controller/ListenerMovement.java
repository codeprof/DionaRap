package controller;

import javax.swing.*;
import java.awt.event.*;
import view.*;

/**
 *	Listenerklassen, die auf Klickereignisse im Ziffernblock des Navigationsfensters reagiert
 *
 * @see java.awt.event.ActionListener
 */
public class ListenerMovement implements ActionListener
{
	/**
	 * Ereignis-Handler fuer die Buttons des Ziffernblocks(<code>NumBlock</code>)
	 * im Navigationsfenster(<code>Navigation</code>).Die Richtung wird über den in ActionCommands
	 * gespeicherten String ermittelt.
	 * Im Controller wird die movePlayer() methode aufgerufen. Dazu wird das Hauptfenster ueber getTopLevelAncestor()
	 * ermittelt und anschließen movePlayer() aus der Controller-Klasse ausgeführt. 
	 * @param e Ereignisobjekt vom Typ <code>ActionEvent</code>, durch die
	 *          der gedrueckten Button identiefiziert werden kann.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */		
	public void actionPerformed(ActionEvent e) {	
		MainWindow main = ((Navigation) ((JComponent)e.getSource()).getTopLevelAncestor() ).getMainWindow();
		main.getContoller().movePlayer(Integer.parseInt(e.getActionCommand()));
		main.requestFocus();		
	}
}

