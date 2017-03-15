package controller;

import javax.swing.*;
import java.awt.event.*;
import view.*;


/**
 * Listenerklassen, die auf Klickereignisse im Ziffernblock des Navigationsfensters reagiert.
 * Dazu wird die Schnittstelle <code>ActionListener</code> implementiert.
 * 
 * @see java.awt.event.ActionListener 
 */
public class ListenerShoot implements ActionListener
{	
	/**
	 * Ereignis-Handler fuer den Schiess-Button mit der Bezeichnung "5" des Ziffernblocks(<code>NumBlock</code>)
	 * im Navigationsfenster(<code>Navigation</code>).
	 * Im Controller wird die Methode shoot() aufgerufen. Dazu wird das Hauptfenster ueber getTopLevelAncestor()
	 * ermittelt und anschließen shoot() aus der Controller-Klasse ausgeführt. 
	 * @param e Ereignisobjekt vom Typ <code>ActionEvent</code>, durch die
	 *          der gedrueckten Button identiefiziert werden kann.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */	
	@Override	
	public void actionPerformed(ActionEvent e) {
		MainWindow main = ((Navigation) ((JComponent)e.getSource()).getTopLevelAncestor() ).getMainWindow();
		main.getContoller().shoot();
		main.requestFocus();
	}
}

