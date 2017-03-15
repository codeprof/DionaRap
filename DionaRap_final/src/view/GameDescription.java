package view;

import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 *	Eine eigene Fensterklasse abgeleitet von JDialog, welche das Spielbeschreibungsfenster repräsentiert
 */
public class GameDescription extends JDialog {
	
	/** Interne Identifikationsnummer für Serialisierung */	
	private static final long serialVersionUID = -4472619961314629391L;

	/**
	 * Erstellt eine neue Instanz des Spielbeschreibungsfensters
	 * 
	 * @param parent Instanz des Vaterfensters
	 */
	public GameDescription(MainWindow parent)
	{
		super(parent);
		setSize(640, 480);
		setTitle("Spielbeschreibnung");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); // Nicht DISPOSE!
		
		JEditorPane editorPane = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane(editorPane, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		editorPane.setEditable(false);
	
		try {
			editorPane.setPage(new File( new File(System.getProperty("user.dir")), 
					"help" + File.separator + "Spielbeschreibung.html" ).toURI().toURL());
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		add(scrollPane);
	}
}
