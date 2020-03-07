/**
 * Classe lan�ant l'application Visual's Music
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */

public class Pricipale_VisualsMusic {

	/**
	 * Lance notre application
	 */
	public static void main (String[] args) {

		Model model = new Model();
		
		//partage du model pour les Controllers
		Vue_Fenetre Frame = new Vue_Fenetre(model);
		Vue_Parametre settings = new Vue_Parametre(model);
		
		model.addObserver(settings);
		
	}

}
