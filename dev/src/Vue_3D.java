import java.util.Observable;
import java.util.Observer;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

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
   * Contient tout les ratios qui seront affich� au premier plan
   */
  private double[] ratioFrequenceForeground;

  /**
   * Contient tout les ratios qui seront affich� en fond
   */
  private double[] ratioFrequenceBackground;

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

    this.ratioFrequenceForeground = new double[4];
    this.ratioFrequenceBackground = new double[4];

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

          if (model.getMode().equals("Ton")) {
            
            for (int index = 0; index < nombre_rectangle; index ++) {

              try {
                
                ratioFrequenceBackground[index] = ratioFrequenceBackground[index + 1];

              }

              catch (IndexOutOfBoundsException e) {

                ratioFrequenceBackground[index] = model.getRatioFrequence()[1];

              }
            }

            cubes.setRatioFrequenceBackground(ratioFrequenceBackground);

          }

          for (int index = 0; index < nombre_rectangle; index ++) {

            try {

              ratioFrequenceForeground[index] = ratioFrequenceForeground[index + 1];

            }

            catch (IndexOutOfBoundsException e) {

              ratioFrequenceForeground[index] = model.getRatioFrequence()[0];

            }
          }

          cubes.setRatioFrequenceForeground(ratioFrequenceForeground);

        }
      }
    }
  }

  //https://www.tutorialspoint.com/jogl/jogl_quick_guide.htm

}
