package view;

import java.awt.BorderLayout;
import javax.swing.*;

import de.dionarap.leveleditor.gui.LevelEditor;
import de.dionarap.leveleditor.model.GameSettingsModel;

/**
 *	Eine eigene Fensterklasse abgeleitet von JDialog, welche das Leveleditor-Fenster repr�sentiert
 */
public class LevelEditorDialog extends JDialog {

	/** Interne Identifikationsnummer f�r Serialisierung */	
	private static final long serialVersionUID = -5622102658675041088L;

	/** 
	 * Erstellt einen neue Instanz des Leveleditors in einem <code>JDialog</code>
	 * @param parent Instanz des Vaterfensters 
	 */
	public LevelEditorDialog(MainWindow parent) {
		super(parent);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); // Nicht DISPOSE!
		add(new LevelEditor(new GameSettingsModel()) , BorderLayout.CENTER);
		pack();
		
	}
	
}
