package View;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import Model.Model;

/**
 * Classe affichant un message d'erreur quand il y en a une
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Vue_Erreur extends JOptionPane implements Observer {

	/**
	 * Met � jour la vue
	 * 
	 * Importe l'erreur du Model
	 * Regarde de quelle type elle est, 
	 * et affiche un message correspondant au type
	 */
	public void update(Observable m, Object arg1) {

		Model model = (Model) m;

		
		if (model.getErreur() instanceof FileNotFoundException) {
			
			JOptionPane.showMessageDialog(null, 
					"Le fichier que vous voulez ouvrir n'a pas �t� trouv�", 
					"Fichier non trouv�",
					ERROR_MESSAGE);
			
			return;
			
		}
		
		
		if (model.getErreur() instanceof NullPointerException) {
			
			JOptionPane.showMessageDialog(null, 
					"Probl�me de donn�e\n\nL'application a du mal � retrouver certaine donn�e, merci de red�marrer l'application", 
					"Impossible de retrouver certaines donn�es",
					ERROR_MESSAGE);
			
			return;
			
		}
		
		
		if (model.getErreur() instanceof UnsupportedAudioFileException) {
			
			JOptionPane.showMessageDialog(null, 
					"Impossible de lire ce fichier:\nEssayer d'ouvrir un fichier \".wav\".", 
					"Format de fichier non support�",
					ERROR_MESSAGE);
			
			return;
			
		}
		
		
		if (model.getErreur() instanceof LineUnavailableException) {
			
			JOptionPane.showMessageDialog(null, 
					"Probl�me lors de la lecture du fichier\n\nLa lecture � �t� interrompus car l'application n'a pas pus acc�der aux donn�es audio", 
					"Audio compromis !!",
					ERROR_MESSAGE);
			
			return;
			
		}
		
		
		if (model.getErreur() instanceof Exception) {
			
			JOptionPane.showMessageDialog(null, 
					"Probl�me inconnus...\n\nLe probl�me est inconnu, merci de faire parvenir un ScreenShot de l'application avec le message d'erreur.\n\nstephane.goodwin3@etu.univ-lorraine.fr", 
					"Erreur !!",
					ERROR_MESSAGE);
			
			return;
			
		}
	}
}
