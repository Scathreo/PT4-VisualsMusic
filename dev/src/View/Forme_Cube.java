package View;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Classe qui permet de tracer des cubes en 3D
 * 
 * @author
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Forme_Cube implements GLEventListener {

	/**
	 * Tableau de N �l�ment r�f�ren�ant le taux de couleur rouge
	 * pour les N-Cubes � affich�
	 * 
	 * N �tant le nombre de cubes � affich�
	 */
	private double[] couleur_red;
	
	/**
	 * Tableau de N �l�ment r�f�ren�ant le taux de couleur verte
	 * pour les N-Cubes � affich�
	 * 
	 * N �tant le nombre de cubes � affich�
	 */
	private double[] couleur_green;

	/**
	 * Tableau de N �l�ment r�f�ren�ant le taux de couleur bleu
	 * pour les N-Cubes � affich�
	 * 
	 * N �tant le nombre de cubes � affich�
	 */
	private double[] couleur_blue;

	/**
	 * Tableau de N �l�ments r�f�ren�ant les fr�quences des
	 * N-Cubes � afficher
	 */
	private double[] ratioFrequence;
	
	/**
	 * Largeur initiale des cubes
	 * 
	 * cube = (x, y, z)
	 * ici, x
	 */
	private double 	 largeur;
	
	/**
	 * Profondeur initiale des cubes
	 * 
	 * cube = (x, y, z)
	 * ici, z
	 */
	private double 	 profondeur;
	
	/**
	 * hauteur initiale des N-Cubes � afficher
	 * 
	 * cube = (x, y, z)
	 * ici, y
	 */
	private double[] hauteur;

	/**
	 * position X du premier cube
	 */
	private double cube1_posX = -4;

	/**
	 * position Y du premier cube
	 */
	private double cube1_posY = -5;

	/**
	 * position Z du premier cube
	 */
	private double cube1_posZ = -14;

	/**
	 * position X du deuxieme cube
	 */
	private double cube2_posX =  -1.5;

	/**
	 * position Y du deuxieme cube
	 */
	private double cube2_posY = -4;

	/**
	 * position Z du deuxieme cube
	 */
	private double cube2_posZ = -16;

	/**
	 * position X du troisieme cube
	 */
	private double cube3_posX = 1.5;

	/**
	 * position Y du troisieme cube
	 */
	private double cube3_posY = -4;

	/**
	 * position Z du troisieme cube
	 */
	private double cube3_posZ = -16;

	/**
	 * position X du quatrieme cube
	 */
	private double cube4_posX = 4;

	/**
	 * position Y du quatrieme cube
	 */
	private double cube4_posY = -5;

	/**
	 * position Z du quatrieme cube
	 */
	private double cube4_posZ = -14;

	/**
	 * Num�ro du premier cube
	 */
	private static int CUBE_ONE = 0;

	/**
	 * Num�ro du deuxieme cube
	 */
	private static int CUBE_TWO = 1;

	/**
	 * Num�ro du troisieme cube
	 */
	private static int CUBE_THREE = 2;

	/**
	 * Num�ro du quatrieme cube
	 */
	private static int CUBE_FOUR = 3;

	/**
	 * Outils utilis� pour l'affichage en trois dimensions
	 */
	private GLU glu = new GLU();
	
	/**
	 * Amplitude initiale des formes
	 * 
	 * Valeur de d�fault : 5
	 *  Permet d'avoir des formes relativement 
	 *  grande d�s le d�part
	 */
	private double amplitude;
	
	/**
	 * Constructeur de la classe
	 * 
	 * Initialise les donn�es non partag� entre la vue et 
	 * les dessins des formes
	 */
	public Forme_Cube() {
		
		amplitude	= 5;
		largeur 	= 1;
		profondeur 	= 1;
		hauteur 	= new double[4];
		
		ratioFrequence = new double[4];
		
		couleur_red   = new double[4];
		couleur_green = new double[4];
		couleur_blue  = new double[4];
		
		for (int i = 0; i < 4; i ++) {

			couleur_red[i] = Math.random();
			couleur_green[i] = Math.random();
			couleur_blue[i] = Math.random();
			
		}
		
	}
	
	/**
	 * Methode qui permet d'afficher les cubes
	 */
	public void display(GLAutoDrawable drawable) {
		
		for (int index = 0; 
				index < ratioFrequence.length;
				index ++) {

			hauteur[index] = amplitude * ratioFrequence[index];

		}

		final GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
		
		gl.glLoadIdentity();	//reset l'origine
		gl.glTranslated(		//d�place l'origine
				cube1_posX,
				cube1_posY + hauteur[Forme_Cube.CUBE_ONE], 
				/*
				 * Evite que la forme augmente de taille des deux cot�s
				 * 
				 * Ne fonctionne pas vraiment avec le rotated
				 */
				cube1_posZ);
		gl.glRotated(45, 0.2, 0.5, 0.1);
		gl.glColor3d(
				couleur_red[Forme_Cube.CUBE_ONE],
				couleur_green[Forme_Cube.CUBE_ONE],
				couleur_blue[Forme_Cube.CUBE_ONE]);
		this.paintCube(gl, Forme_Cube.CUBE_ONE);

		gl.glLoadIdentity();
		gl.glTranslated(
				cube2_posX,
				cube2_posY + hauteur[Forme_Cube.CUBE_TWO],
				cube2_posZ); 
		gl.glRotated(45, 0.2, 0.5, 0.1);
		gl.glColor3d(
				couleur_red[Forme_Cube.CUBE_TWO],
				couleur_green[Forme_Cube.CUBE_TWO],
				couleur_blue[Forme_Cube.CUBE_TWO]);
		this.paintCube(gl, Forme_Cube.CUBE_TWO);

		gl.glLoadIdentity();
		gl.glTranslated(
				cube3_posX,
				cube3_posY + hauteur[Forme_Cube.CUBE_THREE],
				cube3_posZ); 
		gl.glRotated(45, 0.2, 0.5, 0.1);
		gl.glColor3d(
				couleur_red[Forme_Cube.CUBE_THREE],
				couleur_green[Forme_Cube.CUBE_THREE],
				couleur_blue[Forme_Cube.CUBE_THREE]);
		this.paintCube(gl, Forme_Cube.CUBE_THREE);

		gl.glLoadIdentity();
		gl.glTranslated(
				cube4_posX,
				cube4_posY + hauteur[Forme_Cube.CUBE_FOUR],
				cube4_posZ); 
		gl.glRotated(45, 0.2, 0.5, 0.1);
		gl.glColor3d(
				couleur_red[Forme_Cube.CUBE_FOUR],
				couleur_green[Forme_Cube.CUBE_FOUR],
				couleur_blue[Forme_Cube.CUBE_FOUR]);
		this.paintCube(gl, Forme_Cube.CUBE_FOUR);
		
		gl.glFlush();

	}

	/**
	 * Dessine un cube
	 * 
	 * @param gl outils pour pouvoir dessiner en 3D
	 * @param numeroCube	le num�ro du cube a affich�
	 */
	private void paintCube(GL2 gl, int numeroCube) {

		gl.glBegin(GL2.GL_QUADS); // Start Drawing The Cube

		gl.glVertex3d( largeur,  hauteur[numeroCube], -profondeur); 	// Top Right Of The Quad (Top)
		gl.glVertex3d(-largeur,  hauteur[numeroCube], -profondeur); 	// Top Left Of The Quad (Top)
		gl.glVertex3d(-largeur,  hauteur[numeroCube],  profondeur); 	// Bottom Left Of The Quad (Top)
		gl.glVertex3d( largeur,  hauteur[numeroCube],  profondeur); 	// Bottom Right Of The Quad (Top)

		gl.glVertex3d( largeur, -hauteur[numeroCube], -profondeur);		// Top Right Of The Quad (Bot)
		gl.glVertex3d(-largeur,  hauteur[numeroCube], -profondeur); 	// Top Left Of The Quad (Bot)
		gl.glVertex3d(-largeur,  hauteur[numeroCube],  profondeur);		// Bottom Left Of The Quad (Bot)
		gl.glVertex3d( largeur,  hauteur[numeroCube],  profondeur); 	// Bottom Right Of The Quad (Bot)

		gl.glVertex3d( largeur,  hauteur[numeroCube],  profondeur);		// Top Right Of The Quad (Front)
		gl.glVertex3d(-largeur,  hauteur[numeroCube],  profondeur);		// Top Left Of The Quad (Front)
		gl.glVertex3d(-largeur, -hauteur[numeroCube],  profondeur);		// Bottom Left Of The Quad
		gl.glVertex3d( largeur, -hauteur[numeroCube],  profondeur );	// Bottom Right Of The Quad 

		gl.glVertex3d( largeur, -hauteur[numeroCube], -profondeur ); 	// Bottom Left Of The Quad
		gl.glVertex3d(-largeur, -hauteur[numeroCube], -profondeur );	// Bottom Right Of The Quad
		gl.glVertex3d(-largeur,  hauteur[numeroCube], -profondeur ); 	// Top Right Of The Quad (Back)
		gl.glVertex3d( largeur,  hauteur[numeroCube], -profondeur );	// Top Left Of The Quad (Back)

		gl.glVertex3d(-largeur,  hauteur[numeroCube],  profondeur ); 	// Top Right Of The Quad (Left)
		gl.glVertex3d(-largeur,  hauteur[numeroCube], -profondeur ); 	// Top Left Of The Quad (Left)
		gl.glVertex3d(-largeur, -hauteur[numeroCube], -profondeur ); 	// Bottom Left Of The Quad
		gl.glVertex3d(-largeur, -hauteur[numeroCube],  profondeur ); 	// Bottom Right Of The Quad 

		gl.glVertex3d( largeur,  hauteur[numeroCube], -profondeur ); 	// Top Right Of The Quad (Right)
		gl.glVertex3d( largeur,  hauteur[numeroCube],  profondeur ); 	// Top Left Of The Quad
		gl.glVertex3d( largeur, -hauteur[numeroCube],  profondeur ); 	// Bottom Left Of The Quad
		gl.glVertex3d( largeur, -hauteur[numeroCube], -profondeur ); 	// Bottom Right Of The Quad

		gl.glEnd(); // Done Drawing The Quad

	}

	/**
	 * M�thode de GLEventListener
	 * 
	 * Non impl�menter
	 */
	public void dispose( GLAutoDrawable drawable ) {}

	/**
	 * Methoide appel� pour initialiser le Canva
	 */
	public void init( GLAutoDrawable drawable ) {

		GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
		glu = new GLU();                         // get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f);      // set clear depth value to farthest
		gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
		gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting

	}

	/**
	 * M�thode appel� quand on modifie les dessins
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

		if (height == 0) height = 1;   // prevent divide by zero
		float aspect = (float)width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset

	}
	/**
	 * Permet d'envoyer aux dessins les ratios de fr�quence � afficher
	 * 
	 * ratioFrequence inf�rieur 1
	 */
	public void setRatioFrequence(double[] ratioFrequence) {

		this.ratioFrequence = ratioFrequence;

	}
	
	/**
	 * Permet de moidifi� les taux de couleur rouge des forme
	 * 
	 * @param red inf�rieur 1
	 */
	public void setCouleurR(double[] red) {
		
		couleur_red = red;
		
	}
	
	/**
	 * Permet de moidifi� les taux de couleur bleu des forme
	 * 
	 * @param blue inf�rieur 1
	 */
	public void setCouleurB(double[] blue) {
		
		couleur_blue = blue;
		
	}

	/**
	 * Permet de moidifi� les taux de couleur verte des forme
	 * 
	 * @param green inf�rieur 1
	 */
	public void setCouleurG(double[] green) {
		
		couleur_green = green;
		
	}
}
