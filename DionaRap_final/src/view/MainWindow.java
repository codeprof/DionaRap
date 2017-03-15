package view;

import javax.swing.*;
import java.awt.*;
import de.fhwgt.dionarap.controller.*;
import de.fhwgt.dionarap.levelreader.LevelReader;
import de.fhwgt.dionarap.model.data.*;
import controller.*;

/**
 *	Eine eigene Fensterklasse abgeleitet von JFrame, welche das Hauptfenster repräsentiert
 */
public class MainWindow extends JFrame
{
	/** Interne Identifikationsnummer für Serialisierung */	
	private static final long serialVersionUID = -8612216034719408407L;
	/** Standardweite und Standardhöhe der Felder des Schachbretts in Pixel */	
	private static final int FIELD_WIDTH = 50, FIELD_HEIGHT = 50;
	/** Anzahl der hoizontalen und vertikalen Felder für das Schachbrettmuster */		
	private static final int NUM_HORZ = 10, NUM_VERT = 10;
	/** Instanz des Steuerelements für des Schachbretts */	
	private GameField gameField;
	/** Instanz des Navigationsfensters */
	private Navigation navigation;	
	/** Instanz der Spielbeschriebung */
	private GameDescription gameDescription;
	/** Instanz des Spielfiguren-Info Dialogs */
	private PawnInfo pawnInfo;	
	/** Dialog which contains the level editor */
	private LevelEditorDialog levelEditorDialog;
	/** Instanz des DionaRap Models */	
	private DionaRapModel model;
	/** Instanz des DionaRap Controller */	
	private DionaRapController ctrl;
	/** Instanz der Model-Konfiguration */
	private MTConfiguration conf;
	/** Instanz der Themen */
	private Theme theme;
	/** Instanz des Hauptmenüs */
	private MainMenuBar mainMenuBar;	
	/**
	 * Erstellt eine Instanz des Hauptfensters
	 * 
	 * @param title Title des Fensters
	 * @param x horizontale Position auf dem Desktop (in Pixel)
	 * @param y vertikale Position auf dem Desktop (in Pixel)    
	 */	
	public MainWindow(String title, int x, int y) {
		super(title);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		theme = new Theme(); //Achtung: muss vor 'new GameField()' aufgerufen werden
		mainMenuBar = new MainMenuBar(this);
		mainMenuBar.enableLevelCommands(false);
		getContentPane().add(gameField = new GameField(this, NUM_HORZ, NUM_VERT) );
		setLocation(x, y);
		setResizable(false);
		setVisible(true);
		gameDescription = new GameDescription(this);	
		pawnInfo = new PawnInfo(this);
		levelEditorDialog = new LevelEditorDialog(this);
		this.addComponentListener(new ListenerPositionChange(navigation = new Navigation(this)));  
		this.addKeyListener(new ListenerKeyboard(this));
		
		conf = new MTConfiguration();
		conf.setAlgorithmAStarActive(true);
		conf.setAvoidCollisionWithObstacles(true);
		conf.setAvoidCollisionWithOpponent(false);
		conf.setMinimumTime(800); // 0,8 Sekunden
		conf.setShotGetsOwnThread(true); // nicht unbegrenzte Anzahl Schüsse
		conf.setOpponentStartWaitTime(5000); // 5 Sekunden am Anfang Schlaf
		conf.setOpponentWaitTime(2000); // Gegner warten vor jedem Zug 2 Sekunden
		conf.setShotWaitTime(500); // ein Schuss benötigt eine halbe Sekunde
		conf.setRandomOpponentWaitTime(false); // keine zufällige Wartezeit
		conf.setDynamicOpponentWaitTime(false); // immer gleichlang warten
		prepareNewGame();		
		startNewGame();
	}	
	/**
	 * Statische Funktion die automatisch beim Start der Anwendung aufgerufen wird (Einstiegspunkt der Anwendung)
	 * 
	 * @param params Startparameter der Anwendung
	 */	
	public static void main(String params[] ) {
		new MainWindow("DionaRap", 16, 16); // Fenster an Position 16,16 Positionieren
	}
	/** Gibt die Instanz des Spielfeldes zurück */	
	public GameField getGameField() {
		return gameField;
	}
	/** Gibt die Instanz des Navigationsfenster zurück */	
	public Navigation getNavigation() {
		return navigation;
	}	
	/** zeigt die Speilbeschriebung an */	
	public void showGameDescription() {
		gameDescription.setVisible(true);
	}		
	/** Zeigt den Spielfiguren-Info Dialog an */	
	public void showPawnInfo() {
		pawnInfo.setVisible(true);
	}	
	/** Zeigt den Leveleditor Dialog an */		
	public void showLevelEditorDialog() {
		levelEditorDialog.setVisible(true);
	}	
	/** Gibt die Instanz des DionaRap Models zurück */		
	public DionaRapModel getModel() {
		return model;
	}
	/** Gibt die Instanz des Themas zurück */		
	public Theme getTheme() {
		return theme;
	}	
	/** Gibt die Instanz des DionaRap Controllers zurück */	
	public DionaRapController getContoller() {
		return ctrl;
	}
	/** Bereitet ein neues Spiel vor. Hierbei wird ein neue Instanz der Klassen DionaRapModel und DionaRapController erzeugt. Dem Model wird anschliessen eine neue Instanz des Model-Listeners (ListenerModel) hinzugefügt.*/		
	public void prepareNewGame() {
		ctrl = new DionaRapController(model = new DionaRapModel());
		model.addModelChangedEventListener(new ListenerModel(this));			
	}
	/** Startet ein neues Spiel.
	 * Dabei wird die Groesse des Spielfelds angepasst und in den Mult-Threading Modus geschalten.
	 */
	public void startNewGame() {
		int sx = model.getGrid().getGridSizeX(), sy = model.getGrid().getGridSizeY();
		gameField.resize(sx, sy);
		gameField.setPreferredSize(new Dimension(sx * FIELD_WIDTH, sy * FIELD_HEIGHT));	
		pack();
		navigation.setScore(0);
		navigation.updateLocation();
		gameField.updatePawns(model.getAllPawns());		
		ctrl.setMultiThreaded(conf);
		navigation.enableNewGame(false);
		mainMenuBar.enableLevelCommands(false);
	}
	/** Setzt die GUI in den Spielende-Modus. 
	 * Dabei wird der "Neues Spiel" Button aktiviert und die Menuentraege "Level laden..." und "Level erstellen" aktiviert.
	 */	
	public void prepareEndOfGame() {
		navigation.setScore(model.getScore());
		gameField.updatePawns(model.getAllPawns());
		navigation.enableNewGame(true);
		mainMenuBar.enableLevelCommands(true);
	}
	/** Setzt den angegebenen Darstellungsstiels für alle Fenster 
	 * @param className ClassName des zu setzenden Darstellungsstiels
	 */		
	public void setLookAndFeel(String className) {
	    try {
			UIManager.setLookAndFeel(className);
	    }
		catch (Exception ex) {
			ex.printStackTrace();
		}
	    SwingUtilities.updateComponentTreeUI(this);	
	    SwingUtilities.updateComponentTreeUI(navigation); //Für Naviationsfester seperat aufrunfen (wird sonst nicht neu gezeichnet )
	    SwingUtilities.updateComponentTreeUI(gameDescription);
	    SwingUtilities.updateComponentTreeUI(pawnInfo);
	    SwingUtilities.updateComponentTreeUI(levelEditorDialog);
	    navigation.pack(); //Größe ändert sich möglicherweise durch Änderung des Skins
	    pawnInfo.pack();
	    levelEditorDialog.pack();
	    pack();
	}
	/** 
	 * Öffnet einen Dateiauswahldialog fuer Leveldateien und startet den ausgewaehlten Level anschliessend.
	 */		
	public void loadLevel() {
		JFileChooser choose = new JFileChooser();
		String prevPath = System.getProperty("user.dir");
		choose.showOpenDialog(this);
		System.setProperty("user.dir", prevPath); // Da showOpenDialog() das aktuelle Verzeichnis ändert
		if (choose.getSelectedFile() != null) // Datei ausgewählt
		{
			prepareNewGame();
			String levelname = choose.getSelectedFile().getAbsolutePath();
			LevelReader lr = new LevelReader(conf, model);
			try {
				lr.readLevel(levelname);
				startNewGame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}