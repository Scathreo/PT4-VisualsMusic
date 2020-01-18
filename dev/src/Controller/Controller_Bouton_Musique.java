package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import Model.Model;

/**
 * 
 * Classe impl�mentant ActionListener
 * Sert � g�rer tous les action faites sur les boutons
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Controller_Bouton_Musique extends Controller implements ActionListener {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Bouton_Musique(Model model) {

		super(model);

	}

	/**
	 * M�thode de l'interface parente ActionListener
	 * 
	 * Si aucun fichier n'est ouvert, d�finis une erreur
	 * Si on clique sur un bouton "Lecture", lecture de la musique et d�-pause
	 * Si on clique sur le bouton "Pause", musique mis en pause
	 * Si on clique sur un bouton "Stop", arret de la musique et 
	 *  r�initialisation de la musique qui �tait en cours de lecture
	 */
	public void actionPerformed(ActionEvent arg0) {

		JButton bouton = (JButton) arg0.getSource();

		/*
		 * condition avant erreur car on veut pouvoir utiliser le bouton
		 * plein �cran m�me si une musique n'est pas load
		 */
		if(bouton.getText().equals("Plein Ecran")) {

			if (model.isFullScreen())
				model.setFullScreen(false);

			else
				model.setFullScreen(true);
			
			return;

		}
		
		// si le fichier n'est pas lu alors on affiche une erreur
		if (!model.isFileLoaded()) {

			model.setErreur(new FileNotFoundException());
			
			return;

		}

		//Controle le bouton Lecture
		if (bouton.getText().equals("Lecture")) {

			if (model.isPause()) {
				
				model.lectureFichier();
				model.setPause(false); 
				
			}

			return;

		}

		//Controle le bouton pause
		if (bouton.getText().equals("Pause")) {

			if (!model.isPause()) 
				model.setPause(true);

			return;

		}

		//Controle le bouton stop
		if(bouton.getText().equals("Stop")) {

			model.stop();

			return;

		}
	}
}
