package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import model.Model;

/**
 * Classe impl�mentant ActionListener et MenuListener
 * Sert � g�rer tous les actions de la barre de menu
 * 
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Controller_Menu extends Controller implements ActionListener, MenuListener  {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Menu(Model model) {

		super(model);

	}

	/**
	 * M�thode de l'interface parente ActionListener
	 * 
	 * Ouvrir un fichier ... : cr�ation d'un FileChooser (permettant de choisir 
	 * 	un fichier sur l'espace disque) qui renvoi et ouvre le fichier choisit
	 * 
	 * 2D : affiche le visualiseur 2D
	 * 3D : affiche le visualiseur 3D
	 */
	public void actionPerformed(ActionEvent arg0) {

		JMenuItem menuItem = (JMenuItem) arg0.getSource();		

		if (menuItem.getText().equals("Ouvrir un fichier...")) {
			
			JFileChooser fc = new JFileChooser();
			int valeur_de_retour = fc.showOpenDialog(null);

			if (valeur_de_retour == JFileChooser.APPROVE_OPTION)
				model.setFichier(fc.getSelectedFile());
			
			return;
			
		}

		if (menuItem.getText().equals("2D") && menuItem.isSelected()) {

			model.setIsThreeDimension(false);
			model.setChangingDimension(true);
			
			return;
		}

		if (menuItem.getText().equals("3D") && menuItem.isSelected()) {

			model.setIsThreeDimension(true);
			model.setChangingDimension(true);
			
			return;

		}		
	}

	/**
	 * Quand un menu est annul�
	 * 	Non impl�menter
	 */
	public void menuCanceled(MenuEvent arg0) {}

	/**
	 * Quand un menu est d�-s�lectionn�
	 * 	Non impl�menter
	 */
	public void menuDeselected(MenuEvent arg0) {}

	/**
	 * Quand on selectionne un menu
	 * 
	 * Param�tre : affiche la fen�tre des param�tres
	 */
	public void menuSelected(MenuEvent arg0) {

		JMenu menu = (JMenu) arg0.getSource();

		if (menu.getText().equals("Param�tres")) {

			model.setPrintSettings(true);
			
			return;

		}
	}
}

