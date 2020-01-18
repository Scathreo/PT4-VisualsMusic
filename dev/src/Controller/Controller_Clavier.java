package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import model.Model;

/**
 * Classe impl�mentant KeyListener
 * Sert � mettre des racourcis clavier
 * 	Espace : pause/lecture
 *	Echap	: quitte l'application
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Controller_Clavier extends Controller implements KeyListener {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Clavier(Model model) {

		super(model);

	}

	/**
	 * Quand une touche est press� fait l'action correpondante
	 * Espace	: pause/lecture
	 * Echap	: quitte l'application
	 */
	public void keyPressed(KeyEvent e) {

		//si touche ECHAP, quitte l'app
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			model.quitApp(true);
			
			return;
			
		}

		//Si touche +, augmente le volume
		if(e.getKeyCode() == KeyEvent.VK_ADD) {

			model.increaseVolume();
			
			return;

		}

		//Si touche -, diminue le volume
		if(e.getKeyCode() == KeyEvent.VK_SUBTRACT) {

			model.decreaseVolume();
			
			return;

		}
		
		// si le fichier n'est pas lu alors on affiche une erreur
		if (!model.isFileLoaded()) {

			model.setErreur(new FileNotFoundException());
			
			return;

		}
		
		//Si touche SPACE, pause/lecture
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

			if (model.isPause()) {

				model.lectureFichier();
				model.setPause(false); 

			}

			else 
				model.setPause(true);
			
			return;

		}
	}

	/**
	 * Quand une touche est relacher
	 * 	Non impl�menter
	 */
	public void keyReleased(KeyEvent arg0) {}

	/**
	 * Quand une touche est taper
	 * 	Non impl�menter
	 */
	public void keyTyped(KeyEvent arg0) {}
}
