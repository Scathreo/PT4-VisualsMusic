package view;

import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;

import model.Model;

/**
 * Classe repr�sentant le visualisateur de l'IG
 * Affiche des formes g�om�trique 3D en fonction de la musique �cout�
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Vue_3D extends GLCanvas implements Observer {

	/**
	 * Nombre de rectangle � afficher
	 */
	private int nombre_rectangle;

	/**
	 * Contient tout les ratios qui seront affich�
	 */
	private double[] ratioFrequence;

	/**
	 * Outils utilis� pour l'affichage 3D
	 */
	private final static GLProfile PROFILE = GLProfile.get(GLProfile.GL2);

	/**
	 * Outils utilis� pour l'affichage 3D
	 */	
	private static GLCapabilities CAPABILITIES = new GLCapabilities(Vue_3D.PROFILE);

	/**
	 * Outils utilis� pour l'affichage 3D
	 * 
	 * Permet des changement d'affichage
	 * 	par exemple quand la fr�quence change
	 */
	final FPSAnimator animator = new FPSAnimator(this, 300, true);

	/**
	 * Les 4 cubes � affich�
	 */
	private Forme_Cube cubes;

	/**
	 * Constructeur de la classe
	 * 
	 * Initialise les donn�s sensibles
	 */
	public Vue_3D() {

		super(Vue_3D.CAPABILITIES);

		this.nombre_rectangle = 4;
		
		this.ratioFrequence = new double[4];

		this.cubes = new Forme_Cube();

		this.addGLEventListener(cubes);

	}

	/**
	 * Met � jour la vue
	 * 
	 * Importe la frequence du model et la stock dans le 
	 * tableau ratioFrequence
	 */
	public void update(Observable m, Object obj) {

		Model model = (Model) m;

		if (model.getErreur() == null) {

			if (!model.isThreeDimension()) {

				if (animator.isAnimating())
					animator.stop();
				
				return;

			}

			else {

				if (model.isFileLoaded() 
						&& model.getMusique().isLoad()
						&& !model.getMusique().isPause()) {

					if (!animator.isStarted())
						animator.start();

					if (model.isCouleur_3d_random()) {

						cubes.setCouleurR(
								new double[] {
										Math.random(),
										Math.random(), 
										Math.random(), 
										Math.random()
								});
						cubes.setCouleurB(
								new double[] {
										Math.random(),
										Math.random(), 
										Math.random(), 
										Math.random()
								});
						cubes.setCouleurG(
								new double[] {
										Math.random(),
										Math.random(), 
										Math.random(), 
										Math.random()
								});

					}

					else {

						cubes.setCouleurR(
								new double[] {
										(double) model.getCouleur_3d_cube1_r() / 255,
										(double) model.getCouleur_3d_cube2_r() / 255,
										(double) model.getCouleur_3d_cube3_r() / 255,
										(double) model.getCouleur_3d_cube4_r() / 255
								});

						cubes.setCouleurG(
								new double[] {
										(double) model.getCouleur_3d_cube1_g() / 255,
										(double) model.getCouleur_3d_cube2_g() / 255,
										(double) model.getCouleur_3d_cube3_g() / 255,
										(double) model.getCouleur_3d_cube4_g() / 255
								});

						cubes.setCouleurB(
								new double[] {
										(double) model.getCouleur_3d_cube1_b() / 255,
										(double) model.getCouleur_3d_cube2_b() / 255,
										(double) model.getCouleur_3d_cube3_b() / 255,
										(double) model.getCouleur_3d_cube4_b() / 255
								});

					}

					for (int index = 0; index < nombre_rectangle; index ++) {

						try {

							ratioFrequence[index] = ratioFrequence[index + 1];

						}

						catch (IndexOutOfBoundsException e) {

							ratioFrequence[index] = model.getRatioFrequence();

						}
					}
				}

				cubes.setRatioFrequence(ratioFrequence);

			}
		}
	}

	//https://www.tutorialspoint.com/jogl/jogl_quick_guide.htm
	
}
