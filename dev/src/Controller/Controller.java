package Controller;

import Model.Model;

/**
 * Classe permettant de faciliter la structure MVC
 * D�finit un Model qui sera commun � tout les Controller
 * 	et un Constructeur initialisant ce model
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public abstract class Controller {

	/**
	 * Model permettant la structure MVC
	 * Sera partager par toute les classes filles
	 */
	protected Model model;

	/**
	 * Constructeur instanciant le Mod�le permettant la 
	 * 	structure MVC
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller(Model model) {
		
		super();
		
		this.model = model;
		
	}
	
}
