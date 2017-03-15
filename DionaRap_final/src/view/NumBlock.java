package view;

import javax.swing.*;
import java.awt.*;
import controller.*;


/**
 *	Eine eigene Steuerelementklasse abgeleitet von JPanel, welche den Ziffernblock repräsentiert
 */
public class NumBlock extends JPanel
{
	/** Interne Identifikationsnummer für Serialisierung */	
	private static final long serialVersionUID = 1911538531253199005L;
	/** Instanz des "Bewegungs"-Listener */	
	private ListenerMovement eventMove;
	/** Instanz des "Schiess"-Listener */	
	private ListenerShoot eventShoot;
	/**
	 * Erstellt eine Instanz für eines 3x3 Ziffernblocks
	 */
	public NumBlock() {
		eventMove = new ListenerMovement();
		eventShoot = new ListenerShoot();
    	setLayout(new GridLayout(3, 3)); //Create 3x3 number block
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for (int i = 9; i > 0 ; i--) {
			JButton btn = new JButton(Integer.toString(i));
			btn.setActionCommand(btn.getText());
			btn.addActionListener(i == 5 ? eventShoot : eventMove);
    		add(btn);
		}
	}
}


