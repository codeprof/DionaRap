package view;

import javax.swing.*;

import controller.ListenerNewGame;

import java.awt.*;

/**
 *	Eine eigene Fensterklasse abgeleitet von JWindow, welche das Navigationsfenster repräsentiert
 */
public class Navigation extends JWindow
{
	/** Interne Identifikationsnummer für Serialisierung */	
	private static final long serialVersionUID = 8787680146056306529L;	
	/** Instanz des Hauptfensters */
	private MainWindow main; 
	/** Standardweite und Standardhöhe der einzelnen Steuerelemente  in Pixel */	
	private static final int CONTROL_WIDTH = 200, CONTROL_HEIGHT = 30;
	/** Nicht editierbares Punktefeld */	
	private JTextField scoreField;
	/** "Neues Spiel" Button */	
	private JButton newGame;	
	/**
	 * Erstellt eine neue Instanz des Navigationsfensters
	 * 
	 * @param parent Instanz des Hauptfensters 
	 */		
	public Navigation(MainWindow parent) {
		super(parent);
		main = parent;
		initComponents();	
		updateLocation();
		setVisible(true);
	}
	/**
	 * Gibt die Instanz des Hauptfensters zurück
	 */		
	public MainWindow getMainWindow() {
		return main;
	}
	/**
	 * Aktualisiert die Position des Navigationsfensters, damit dieses recht neben dem Hauptfenster liegt
	 */	
	public void updateLocation() {
		Rectangle rect = getParent().getBounds();
		setLocation( (int)(rect.getX() + rect.getWidth() + 10), (int)rect.getY());  //Navigaitionsfenster neben dem Hauptfenster platzieren
	}
	/**
	 * Setzt den aktuellen Punktestand
	 * 
	 * @param points Punktestand
	 */	
	public void setScore(int points) {
		scoreField.setText(Integer.toString(points));
	}
	/**
	 * Interne Funktion um die Steuerelemente zu laden
	 */		
    private void initComponents() {
    	JPanel border = new JPanel();
    	border.setBorder(BorderFactory.createLineBorder(Color.red, 3)); //Unserem Navigationsfenster einen roten Rand geben
    	border.setLayout(new BoxLayout(border, BoxLayout.Y_AXIS));
    	add(border);

    	scoreField = new JTextField();
    	scoreField.setEditable(false);
    	scoreField.setAlignmentX(Component.LEFT_ALIGNMENT);

    	JLabel pointsLbl = new JLabel("Punktestand:");
    	pointsLbl.setPreferredSize (new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
    	pointsLbl.setVerticalAlignment(SwingConstants.TOP);
    	pointsLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

    	NumBlock numBlock = new NumBlock();
    	numBlock.setPreferredSize (new Dimension(CONTROL_WIDTH, 3 * CONTROL_HEIGHT));
    	numBlock.setAlignmentX(Component.LEFT_ALIGNMENT);

    	newGame = new JButton("neues Spiel");
    	newGame.addActionListener(new ListenerNewGame());
    	newGame.setEnabled(false);
    	JPanel btnPanel = new JPanel(); //Den "neues Spiel" Button in ein JPanel legen, um sicherzustellen, das er die korrekte größe bekommt
    	btnPanel.setLayout(new GridLayout(1, 1));
    	btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	btnPanel.add(newGame);

    	border.add(numBlock); //Alle Steuerlemente in das Panel mit dem roten Rand legen
    	border.add(pointsLbl);
    	border.add(scoreField);
    	border.add(Box.createVerticalStrut(10));
    	border.add(btnPanel);
    	pack();
    }
    
    public void enableNewGame(boolean enable) {
    	newGame.setEnabled(enable);
    	if (enable)
    		scoreField.setText("0"); //Punktestand zurücksetzen
    }
}

