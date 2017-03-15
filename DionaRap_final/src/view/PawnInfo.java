package view;

import java.awt.*;
import javax.swing.*;

import controller.ListenerPawnInfo;

/**
 *	Eine eigene Steuerelementklasse abgeleitet von JDialog, welche das Spielfigure-Info Fenster repräsentiert
 */
public class PawnInfo extends JDialog {
	/** Instanz des Hauptfensters */
	private MainWindow parent;
	/** Interne Identifikationsnummer für Serialisierung */
	private static final long serialVersionUID = -3506187825273769869L;
	/**
	 * Erstellt eine neue Instanz des Spielfiguren-Info Fensters
	 * @param parent Instanz des Hauptfensters
	 */
	public PawnInfo(MainWindow parent) {
		super(parent);
		JButton btnClose = new JButton("Schließen");
		this.parent = parent;
		setTitle("Spielfiguren anzeigen");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); // Nicht DISPOSE!		
		
		setLayout(new BorderLayout()); //FlowLayout()
		add(createJPanel("player.gif", "Spieler"), BorderLayout.WEST);
		add(createJPanel("opponent.gif", "Gegner"), BorderLayout.CENTER);
		add(createJPanel("obstacle.gif", "Hindernis"), BorderLayout.EAST);	
		add(btnClose, BorderLayout.SOUTH);
		setResizable(false);
		pack();
		btnClose.addActionListener( new ListenerPawnInfo(this) );
	}
	/**
	 * Erstellt ein von JPanel abgeletete Instanz, die mithilfe der
	 * Graphics2D Zeichenbefehle Icons und einen dazugehoerigen Beschriebungstext darstellt.
	 * @param imgName Dateiname des Icons
	 * @param text Beschreibungstext
	 * @return instanz eines JPanels
	 */
	private JPanel createJPanel(String imgName, String text) {
		JPanel panel = new JPanel() {	
			/** Interne Identifikationsnummer für Serialisierung */
			private static final long serialVersionUID = -2510447725112422239L;

			/**
			 * Ueberschiebenen Paint-Routine, die zuesetzlich ein Icon und einen Beschreibungstext zeichnet (Icon und Text werden aus Userdaten ermittelt)
			 * @param gr Graphics-Objekt mit dessen hilfe gezeichnet wird
			 */
			public void paint(Graphics gr) {
				super.paint(gr); //Sicherstellen, dass der Hintergrund korrekt gezeichnet wird
				Graphics2D gr2d = (Graphics2D)gr; 
				Theme theme = (Theme)this.getClientProperty("theme");
				String imgName = (String)this.getClientProperty("imgName");
				String text = (String)this.getClientProperty("text");
				if (theme != null) // Sicherheitsabfrage falls noch nicht gesetzt
				{
					gr2d.drawImage(theme.getIcon(imgName).getImage(), 15, 0, Color.BLACK, this);
					//gr2d.setFont(new Font("LucidaSans", Font.PLAIN, 40));
					gr2d.drawString(text, 15, 65);
				}
			}
		}; // Ende der anonymen Klasse, Semikolon!
		panel.putClientProperty("imgName", imgName);
		panel.putClientProperty("text", text);	
		panel.putClientProperty("theme", parent.getTheme());
		panel.setPreferredSize(new Dimension(80, 80));
		return panel;
	}
}
