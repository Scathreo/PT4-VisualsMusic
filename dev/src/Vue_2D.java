import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Classe repr�sentant le visualisateur de l'IG
 * Affiche des formes g�om�trique 2D en fonction de la musique �cout�
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Vue_2D extends JPanel implements Observer {

	/**
	 * Taille Maximale de la fen�tre de l'application
	 * sur l'axe des abscisses (horizontal)
	 */
	private int taille_fenetre_x;

	/**
	 * Taille Maximale de la fen�tre de l'application
	 * sur l'axe des ordonn�es (vertical)
	 */
	private int taille_fenetre_y;

	/**
	 * Epaisseur de chacun des triangles
	 * 
	 * Valeur par d�fault: 60
	 * 	Permet d'avoir des formes de bonne taille
	 */
	private int epaisseur_rectangle;	

	/**
	 * Distance en pixel entre les forme 
	 * et les bords droits et gauche
	 */
	private int margin_forme_fenetre;	

	/**
	 * Epaisseur de la ligne centrale
	 */
	private int epaisseur_ligne = 2;	

	/**
	 * Nombre de rectangle � afficher
	 */
	private int nombre_rectangle;

	/**
	 * Contient tout les ratios qui seront affich� au premier plan
	 */
	private double[] ratioFrequenceForeground;

	/**
	 * Contient tout les ratios qui seront affich� en fond
	 */
	private double[] ratioFrequenceBackground;

	/**
	 * Un tableau contenant un nombre de couleur �gal
	 * au nombre de rectangle
	 * 
	 * Contiendra N-Couleur qui seront utilis� pour
	 * tracer l'ext�rieur de chacun des cubes
	 */
	private Color[] couleurs_trait;

	/**
	 * Un tableau contenant un nombre de couleur �gal
	 * au nombre de rectangle
	 * 
	 * Contiendra N-Couleur qui seront utilis� pour
	 * tracer l'int�rieur de chacun des cubes
	 * 
	 * Utilis� si l'utilisateur n'entre aucune
	 * couleurs sp�cifiques
	 */
	private Color[] couleurs_forme;

	/**
	 * Une couleur unique pour tracer l'ext�rieur des cubes
	 * 
	 * Utilis� si l'utilisateur entre une
	 * couleurs sp�cifiques
	 */
	private Color couleur_trait;

	/**
	 * Une couleur unique pour tracer l'int�rieur des cubes
	 * 
	 * Utilis� si l'utilisateur entre une
	 * couleurs sp�cifiques
	 */
	private Color couleur_forme;

	/**
	 * L'espacement entre chaque rectangle, en pixel
	 * 
	 * Valeur par d�fault: 0
	 * 	N'affiche pas d'espace entre les formes
	 */
	private int espacement;

	/**
	 * Constructeur de la classe
	 * 
	 * Initialise les donn�s sensibles
	 */
	public Vue_2D() {

		super();

		this.taille_fenetre_x = 800;
		this.taille_fenetre_y = 450;

		this.epaisseur_rectangle = 60;
		this.margin_forme_fenetre = 100;

		this.nombre_rectangle = (
				(taille_fenetre_x - 2 * margin_forme_fenetre)
				- 2 * epaisseur_rectangle)
				/ (epaisseur_rectangle);

		this.epaisseur_ligne = 2;

		this.ratioFrequenceForeground = new double[nombre_rectangle];

		this.espacement = 0;

	}

	/**
	 * D�finition de la m�thode paint
	 * 
	 * Affiche des formes g�om�triques (Rectangles)
	 * en fonction de la musique et centre l'affichage
	 */
	public void paint(Graphics g) { 

		//Nettoie la fen�tre / l'affichage pr�cedent
		g.clearRect(0, 0, taille_fenetre_x, taille_fenetre_y);

		//Affiche une ligne epaisse au centre de la fen�tre
		g.fillRect(margin_forme_fenetre, 
				taille_fenetre_y / 2 - epaisseur_ligne/2, 
				taille_fenetre_x - 2*margin_forme_fenetre, 
				epaisseur_ligne);

		if ((nombre_rectangle % 2) == 0)
			paintPaire(g);

		else
			paintImpaire(g);

	}

	/**
	 * Centre l'affichage des rectangle si le nombre de rectangle
	 * � affich� est un nombre paire
	 * 
	 * @param g : de type Graphics, sert � trac� les rectangles
	 */
	private void paintPaire(Graphics g) {

		int j;
		j = 0;

		for (int i = taille_fenetre_x / 2 
				- (epaisseur_rectangle * nombre_rectangle / 2)
				- (nombre_rectangle - 1) / 2 * espacement
				- espacement / 2;
				j < nombre_rectangle;
				i += epaisseur_rectangle + espacement) {

			paintRectCouleur(g, i, j);
			j ++;

		}
	}

	/**
	 * Centre l'affichage des rectangle si le nombre de rectangle
	 * � affich� est un nombre impaire
	 * 
	 * @param g : de type Graphics, sert � trac� les rectangles
	 */
	private void paintImpaire(Graphics g) {

		int j;
		j = 0;

		for (int i = taille_fenetre_x / 2 
				- (epaisseur_rectangle * nombre_rectangle / 2)
				- (nombre_rectangle - 1) / 2 * espacement;
				j < nombre_rectangle;
				i += epaisseur_rectangle + espacement) {

			paintRectCouleur(g, i, j);
			j ++;

		}
	}

	/**
	 * Trace un rectangle avec les coordonn�s fournis
	 * et lui fournis un couleur sp�cifique
	 * 
	 * @param g : de type Graphics, sert � trac� les rectangles
	 * @param x : l'absisce o� commenc� � tracer le rectangle
	 * @param numero_rectangle : Quel rectangle doit �tre affich�
	 */
	private void paintRectCouleur(Graphics g, int x, int numero_rectangle) {
		// On trace le rectangle
		// la couleur correspond au dedans du rectangle

		g.setColor(new Color(
				(float) Math.random(),
				(float) Math.random(),
				(float) Math.random()));

		if (ratioFrequenceForeground[numero_rectangle] != 0) {

			if (couleur_forme == null && couleur_trait == null) { //si pas de couleur unique

				g.setColor(couleurs_forme[numero_rectangle]);
				g.fillRect(x,
						(int) (taille_fenetre_y / 2-ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y / 2/2),
						epaisseur_rectangle,
						(int) (ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y/2));

				g.setColor(couleurs_trait[numero_rectangle]);
				g.drawRect(x,
						(int) (taille_fenetre_y / 2-ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y / 2/2),
						epaisseur_rectangle,
						(int) (ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y/2));

			}

			else if (couleurs_forme == null && couleurs_trait == null) { //si pas de couleurs

				g.setColor(couleur_forme);
				g.fillRect(x,
						(int) (taille_fenetre_y / 2-ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y / 2/2),
						epaisseur_rectangle,
						(int) (ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y/2));

				g.setColor(couleur_trait);
				g.drawRect(x,
						(int) (taille_fenetre_y / 2-ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y / 2/2),
						epaisseur_rectangle,
						(int) (ratioFrequenceForeground[numero_rectangle]*taille_fenetre_y/2));

			}
		}
	}

	/**
	 * Met � jour la vue
	 * 
	 * Importe la frequence du model et la stock dans le 
	 * tableau ratioFrequence tout en d�calant chaque
	 * �l�ment vers la gauche
	 */
	public void update(Observable m, Object obj) {

		Model model = (Model) m;

		if (model.getErreur() == null) {

			if (model.isThreeDimension())
				return; //evite les calcul inutile

			else {

				epaisseur_rectangle = model.getEpaisseur();
				espacement = model.getEspacement();

				nombre_rectangle = (
						(taille_fenetre_x - 2 * margin_forme_fenetre)
						- 2 * epaisseur_rectangle)
						/ (epaisseur_rectangle);

				if (espacement != 0) {

					int plage = 
							(taille_fenetre_x - 2 * margin_forme_fenetre)
							- 2 * epaisseur_rectangle;

					int i  = nombre_rectangle * epaisseur_rectangle
							+ (nombre_rectangle - 1 ) * espacement;

					while (i > plage) {

						i = i - epaisseur_rectangle - espacement;

						nombre_rectangle --;

					}
				}

				if (model.isCouleur_2d_random()) {

					couleur_forme = null;
					couleur_trait = null;

					couleurs_forme = new Color[nombre_rectangle];

					for (int index = 0; index < couleurs_forme.length; index ++) {

						couleurs_forme[index] = new Color(
								(float) Math.random(),
								(float) Math.random(),
								(float) Math.random()); 

					}

					couleurs_trait = new Color[nombre_rectangle];

					for (int index = 0; index < couleurs_trait.length; index ++) {

						couleurs_trait[index] = new Color(
								(float) Math.random(),
								(float) Math.random(),
								(float) Math.random()); 

					}
				}

				else {

					couleurs_trait = null;
					couleurs_forme = null;

					couleur_forme = new Color( 
							model.getCouleur_2d_forme_r(),
							model.getCouleur_2d_forme_g(),
							model.getCouleur_2d_forme_b());

					couleur_trait = new Color( 
							model.getCouleur_2d_trait_r(),
							model.getCouleur_2d_trait_g(),
							model.getCouleur_2d_trait_b());

				}

				double[] sauvegarde_rationFrequenceFor = ratioFrequenceForeground;
				ratioFrequenceForeground = new double[nombre_rectangle];

				double[] sauvegarde_rationFrequenceBac = ratioFrequenceBackground;
				ratioFrequenceBackground = new double[nombre_rectangle];

				if (sauvegarde_rationFrequenceFor != null) {

					int taille_min = 
							sauvegarde_rationFrequenceFor.length > ratioFrequenceForeground.length ?
							ratioFrequenceForeground.length : sauvegarde_rationFrequenceFor.length;

					for (int index = 0; index < taille_min; index ++) {

						ratioFrequenceForeground[index] = sauvegarde_rationFrequenceFor[index];

					}
				}

				if (sauvegarde_rationFrequenceBac != null) {

					int taille_min = 
							sauvegarde_rationFrequenceBac.length > ratioFrequenceBackground.length ?
									ratioFrequenceBackground.length : sauvegarde_rationFrequenceBac.length;

					for (int index = 0; index < taille_min; index ++) {

						ratioFrequenceBackground[index] = sauvegarde_rationFrequenceBac[index];

					}
				}

				if (model.isFileLoaded() 
						&& model.getMusique().isLoad()
						&& !model.getMusique().isPause()) {

					for (int index = 0; index < nombre_rectangle; index ++) {

						try {

							ratioFrequenceForeground[index] = ratioFrequenceForeground[index + 1];
							ratioFrequenceBackground[index] = ratioFrequenceBackground[index + 1];

						}

						catch (IndexOutOfBoundsException e) {

							if (model.getRatioFrequence().length > 1)
								ratioFrequenceBackground[index] = model.getRatioFrequence()[1];
							ratioFrequenceForeground[index] = model.getRatioFrequence()[0];

						}
					}
				}

				repaint();

			}
		}
	}
}
