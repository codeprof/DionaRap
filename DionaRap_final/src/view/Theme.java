package view;

import java.io.File;
import java.util.*;
import javax.swing.ImageIcon;

/** Klasse für die Verwaltung von Icons von verschiedenen Themen */
public class Theme {
	/** Hashtable mit Bildern */
	private Hashtable<String, ImageIcon> images = new Hashtable<String, ImageIcon>();
	/** Stringliste mit Namen der verschiedenen Themen */	
	private Vector<String> themes = new Vector<String>();
	/** erstellt eine neue Instanz. Diese beinhaltet einer Liste aller verfügbaren Themen.
	 *  Diese Liste kann mithilfe der Methode getThemes() abgefragt werden.
	 *  Standardmaessig werden alle Icons des ersten Themas (erster Verzeichnisname) geladen.
	 */
	public Theme() {		
		File dir = new File( new File(System.getProperty("user.dir")), "images");
		for(File f : dir.listFiles()) {
			if (f.isDirectory()) themes.add(f.getName()); //Ordner zur liste der Themen hinzufügen 
		}
		loadTheme(themes.get(0)); //Das erste Thema laden
	}
	/** Laedt ein neues Thema aus dem Verzeichnis mit dem angegebenen Namen.
	 * @param Name Relativer Pfad des Ordners in der sich die Icons fuer das Thema befinden (moegliche Ordnernamen koennen mithilfe der Methode getThemes() ermittelt werden)
	 */
	public void loadTheme(String Name) {
		File dir = new File( new File(System.getProperty("user.dir")), "images" + File.separator + Name);
		for(File f : dir.listFiles()) //Bilder laden
		{
			if (f.isDirectory()) continue; //Ordner ignorieren
		    images.put(f.getName(), new ImageIcon(f.getAbsolutePath()) );
		}		
	}
	/** Gibt die Instanz eines Icons mit dem angegeben Dateinamne zurück */		
	public ImageIcon getIcon(String fileName) {
		return images.get(fileName);
	}
	/** Gibt eine Liste der verfügbaren Themen zurück (Verzeichnisnamen)*/
	public final Vector<String> getThemes() {
		return themes;
	}
	
}
