package view;

import javax.swing.*;
import java.awt.*;
import de.fhwgt.dionarap.model.objects.*;

/**
 *	Ein eigenes Steuerelement abgeleitet von der JFrame-Klassen, welche das Speilfeld repräsentiert
 */
public class GameField extends JPanel
{
	/** Interne Identifikationsnummer für Serialisierung */	
	private static final long serialVersionUID = 7063508065602719680L;
	/** zweidimensionales Array, dass die einzelnen Felder des Spielfeldes beinhaltet */		
	private JLabel fields[][];
	/** horizontale und vertikale Spielfeldbreite */
	private int horz, vert;
	/** Instanz des Hauptfensters */
	private MainWindow main; 	
	/**
	 * Erstellt eine neue Instanz des Spielfeldes mit einem Schachbrettmuster.
	 * Das Schachbrettmuster wird aus Labels mit 2 verschiedenen Farben aufgebaut.
	 * 
	 * @param main Instanz des Hauptfensters
	 * @param horz Anzahl horizontaler Bloecke
	 * @param vert Anzahl vertikaler Bloecke 
	 */		
	public GameField(MainWindow main, int horz, int vert) {
		this.main = main;		
		resize(horz, vert);
	}
	/**
	 * Aendert die Groesse des Spielfeldes.
	 * 
	 * @param horz Anzahl horizontaler Bloecke
	 * @param vert Anzahl vertikaler Bloecke 
	 */		
	public void resize(int horz, int vert) {
		removeAll();
		this.horz = horz;
		this.vert = vert;		
    	fields = new JLabel[horz][vert];
    	setLayout( new GridLayout(vert, horz) );
		for (int y = 0; y < vert; y++) { // Schachbrettmuster erstellen
			for (int x = 0; x < horz; x++) {
				JLabel lbl = fields[x][y] = new JLabel();
				lbl.setBackground( new Color( (x + y) % 2 == 0 ? 0xFFFF0000 : 0xFFFFFFFF) ); // sicherstellen, dass sich die Farben abwechseln (rot und weiss)
				lbl.setOpaque(true);
				add(lbl);
			}
		}		
	}
	/**
	 * Setzt die angegebenen Spielfiguren
	 * @param pawns Array mit zu setzenden Spielfiguren
	 */	    
    public void updatePawns(AbstractPawn[] pawns) {
    	for (int y = 0; y < vert; y++) { // Entfernt alle Elemente vom Spielfeld
			for (int x = 0; x < horz; x++) {
				fields[x][y].setIcon(null); 
			}
		}	
    	for(AbstractPawn p : pawns) {
    		ImageIcon symbol = null;
    		if (p instanceof Ammo)
    			symbol = main.getTheme().getIcon("ammo.png");
    		else if (p instanceof Destruction)
    			symbol = main.getTheme().getIcon("destruction.gif");
        	else if (p instanceof Obstacle)
        		symbol = main.getTheme().getIcon("obstacle.gif");
            else if (p instanceof Opponent)
            	symbol = main.getTheme().getIcon("opponent.gif");
            else if (p instanceof Player)
            {
            	if (main.getModel().isGameOver())
            		symbol = main.getTheme().getIcon("loss.gif");
            	else
            		symbol = main.getTheme().getIcon("player" + main.getModel().getPlayer().getViewDirection() +".gif");     		
            }
            else if (p instanceof Vortex)
            	symbol = main.getTheme().getIcon("vortex.gif");
    		fields[p.getX()][p.getY()].setIcon(symbol); 
    	}
    }
}