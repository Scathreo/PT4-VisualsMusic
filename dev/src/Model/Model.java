package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

/** 
 * Classe de type Model, permettant la structure MVC
 * 	Instanci� uniquement dans le code principale
 * 	et m�thode quasi-uniquement utilis� dans les Controller
 * 	lors d'Action
 * 
 * Cette classe conserne toute la gestion �venementiel du
 * 	programme
 * 	Elle informe �galement les Vue
 * 
 * @author 
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Model extends Observable implements Observer {

	//TODO
	private static final int VOLUME_MAX = 200;

	//TODO
	private static final int VOLUME_MIN = 0;

	//TODO
	private static final int INDEX_PREMIER_FICHIER_DANS_LISTE = 0;

	/**
	 * L'epaisseur des formes 2D et 3D
	 */
	private int epaisseur;

	/**
	 * L'amplitude des formes 2d et 3D
	 * 
	 * Prend la valeur de base et multiplie par l'amplitude
	 */
	private int amplitude;

	/**
	 * L'espacement entre deux formes 2D et 3D
	 */
	private int espacement;

	/**
	 * La valeur de couleur rouge pour l'int�rieur des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_forme_r;

	/**
	 * La valeur de couleur bleu pour l'int�rieur des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_forme_b;

	/**
	 * La valeur de couleur vert pour l'int�rieur des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_forme_g;

	/**
	 * La valeur de couleur rouge pour les traits des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_trait_r;

	/**
	 * La valeur de couleur vert pour les traits des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_trait_g;

	/**
	 * La valeur de couleur bleu pour les traits des formes 2D
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_2d_trait_b;

	/**
	 * La valeur de couleur rouge pour le cube 3d num�ro 1 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube1_r;

	/**
	 * La valeur de couleur vert pour le cube 3d num�ro 1 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube1_g;

	/**
	 * La valeur de couleur bleu pour le cube 3d num�ro 1 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube1_b;

	/**
	 * La valeur de couleur rouge pour le cube 3d num�ro 2 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube2_r;

	/**
	 * La valeur de couleur vert pour le cube 3d num�ro 2
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube2_g;

	/**
	 * La valeur de couleur bleu pour le cube 3d num�ro 2 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube2_b;

	/**
	 * La valeur de couleur rouge pour le cube 3d num�ro 3 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube3_r;

	/**
	 * La valeur de couleur vert pour le cube 3d num�ro 3
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube3_g;

	/**
	 * La valeur de couleur bleu pour le cube 3d num�ro 3 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube3_b;

	/**
	 * La valeur de couleur rouge pour le cube 3d num�ro 4 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube4_r;

	/**
	 * La valeur de couleur vert pour le cube 3d num�ro 4
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube4_g;

	/**
	 * La valeur de couleur bleu pour le cube 3d num�ro 4 
	 * 
	 * 0 <= couleur <= 255
	 */
	private int couleur_3d_cube4_b;

	/**
	 * D�finis si la musique doit se lancer automatiquement d�s quelle et ouverte
	 * ou non
	 * 
	 * true : se lancera automatiquement
	 * false: l'utilisateur devra presser "Lecture"
	 */
	private boolean autoplay;

	/**
	 * D�finis si les couleur pour l'affichage 2D seront al�atoire ou non
	 * 
	 * true : sera al�atoire
	 * false: d�finis pas l'utilisateur
	 */
	private boolean couleur_2d_random;

	/**
	 * D�finis si les couleur pour l'affichage 3D seront al�atoire ou non
	 * 
	 * true : sera al�atoire
	 * false: d�finis pas l'utilisateur
	 */
	private boolean couleur_3d_random;

	/**
	 * D�finis si la fen�tre dois se mettre en plein �cran ou non
	 * 
	 * true : sera en plein �cran
	 * false: ne le sera pas
	 */
	private boolean fullScreen;

	/**
	 * Fichier qui sera �cout�
	 */
	private File[] fichiers;

	/**
	 * Le r�pertoire o� se trouve le fichier actuellement �cout�
	 */
	private File directory;

	/**
	 * Classe de type Model, connu et instanci� uniquement ici
	 * Permet le MultiThreading et ainsi de garder la main sur le programme
	 */
	private Model_Musique musique;

	/**
	 * Permet le MultiThreading et ainsi de garder la main sur le programme
	 * Cr�� a partir de musique, attribut d�cris ci-dessus
	 */
	private Thread musiqueThread;

	/**
	 * D�finis si le programme a rencontr� une erreur
	 * 
	 * null : aucune
	 * autre : de type donn�
	 */
	private Exception erreur;

	/**
	 * D�finis si l'utilisateur utilise le visualiseur 2D ou non
	 * 
	 * true  : l'utilisateur utilise la 3D
	 * false : l'utilisateur utilise la 2D
	 */
	private boolean isThreeDimension;

	/**
	 * D�finis s'il faut changer de visualiseur ou non
	 * 
	 * true : il faut changer
	 * false: il ne faut pas changer
	 */
	private boolean changingDimension;

	/**
	 * D�finis s'il faut afficher les param�tres ou non
	 * 
	 * true : � afficher
	 * false: ne pas afficher
	 */
	private boolean printSettings;

	/**
	 * D�finis si la lecture doit �tre en pause ou non
	 * 
	 * true  : la lecture s'arr�te
	 * false : la lecture continue/commence
	 */
	private boolean pause;

	/**
	 * Le niveau du son en pourcents entier
	 */
	private int volume;

	//TODO
	private boolean random;

	//TODO
	private int currentFileIndex;

	/**
	 * Constructeur de la classe
	 * 
	 * Initialise les champs ainsi que les param�tres
	 */
	public Model() {

		erreur = null;

		isThreeDimension 	= false;
		printSettings 		= false;
		fullScreen 			= false;

		amplitude 			= 100;
		epaisseur 			= 60;
		espacement			= 0;
		autoplay 			= true;
		couleur_2d_random	= true;
		couleur_3d_random	= true;

		pause = true;

		random = false;

		changingDimension = false;

		volume = 50;

		currentFileIndex = 0;

	}

	//TODO Play methode
	
	/**
	 * Permet de lire un fichier audio tous en permettant de
	 * garder la main sur l'application gr�ce au multithreading
	 */
	public void lectureFichier() {

		if (musiqueThread == null || pause) {

			if (!musique.isLoad())
				musique.initialisation(fichiers[currentFileIndex]);	//TODO temporaire [0]

			musiqueThread = new Thread(musique);
			musiqueThread.start();

		}
	}

	//TODO
	private void setRandomList() {

		boolean processing = true;

		int random_index;
		int taille_tab_fichiers = fichiers.length;
		int index = 0;

		File[] tab_tampon = new File[taille_tab_fichiers];

		while (processing) {

			random_index = (int) (Math.random() * taille_tab_fichiers);

			if (tab_tampon[random_index] == null) {

				tab_tampon[random_index] = fichiers[index];

				index ++;

			}
		}
	}

	/**
	 * permet de modifier le fichier qui doit �tre lus
	 */
	public void setFichier(File file) {

		musique = new Model_Musique(this);
		pause = true;

		fichiers = null;

		if (musiqueThread != null)
			musiqueThread.interrupt();

		musiqueThread = null;

		if (file.exists()) {

			if (file.isFile()) {

				fichiers = new File[1];
				fichiers[0] = file;

			}

			else if (file.isDirectory()) {

				//TODO un peu barbare => voir les .wav
				fichiers = file.listFiles();

			}

			if (random) {

				this.setRandomList();

			}

			if (autoplay) {

				pause = false;

				this.lectureFichier();

			}			

		}

		else {

			setErreur(new FileNotFoundException());
			return;

		}
	}

	/**
	 * permet d'obtenir le model jouant la musique
	 * 
	 * @return le model de la musique
	 */
	public Model_Musique getMusique() {

		return musique;

	}

	/**
	 * permet d'arr�ter la musique
	 * et de remettre le m�me fichier
	 * au d�but de la lecture
	 */
	public void stop() {

		musique.reset();

	}

	/**
	 * @return un boolean qui permet de savoir
	 * si le fichier est en cours de lecture ou pas
	 * 
	 * true : le fichier est entrain d'�tre lu
	 * false : il n'y a pas de fichier en cour de lecture
	 */
	public boolean isFileLoaded() {

		if (fichiers == null)
			return false;

		for (File file : fichiers)
			if (file == null)
				return false;

		return true;

	}

	/**
	 * notify si il y a une erreur,
	 * � la vue
	 */
	public void setErreur(Exception e) {

		erreur  = e;

		setChanged();
		notifyObservers();

		erreur = null;

	}

	/**
	 * permet d'obtenir une erreur
	 * @return  Exception si il y a une erreur
	 * 			null sinon
	 */
	public Exception getErreur() {

		return erreur;

	}

	/**
	 * @return le ration de la fr�quense actuel
	 * par rapport � celle du fichier
	 */
	public double getRatioFrequence() {

		double freq = 0;

		freq = 	(musique.getFrequence() 
				* amplitude / 100)
				/ musique.getAudioFormat().getFrameRate();

		return freq;

	}

	/**
	 * M�thode permettant de mettre � jour
	 * et de notify l'observer
	 * 
	 * utiliser uniquement pas Model_Musique
	 */
	public void update(Observable o, Object arg) {

		if (arg != null) {

			if (arg.equals(Model_Musique.RESET_CODE))
				currentFileIndex ++;

		}

		setChanged();
		notifyObservers();

	}

	/**
	 * permet de notifi� les vue quand des param�tres on changer
	 */
	public void parametersChanged(boolean b) {

		if (b) {

			this.setChanged();
			this.notifyObservers();

		}

	}

	/**
	 * renvoie un boulean signalant le visualiseur utiliser
	 */
	public boolean isThreeDimension() {

		return isThreeDimension;

	}

	/**
	 * Permet de modifier le signal du visualiseur utiliser
	 */
	public void setIsThreeDimension(boolean vueChanged) {

		this.isThreeDimension = vueChanged;

	}

	/**
	 * Signal si oui ou non l'affichage est en plein �cran 	 
	 */
	public boolean isFullScreen() {

		return fullScreen;

	}

	/**
	 * Permet de signaler s'il faut afficher la fen�tre en plein �cran ou non
	 */
	public void setFullScreen(boolean fullScreen) {

		this.fullScreen = fullScreen;

		setChanged();
		notifyObservers();

	}

	/**
	 * signal s'il faut afficher les param�tres
	 */
	public boolean isPrintSettings() {

		return printSettings;

	}

	/**
	 * Permet de signaler s'il faut afficher les param�tre ou non
	 */
	public void setPrintSettings(boolean printSettings) {

		this.printSettings = printSettings;

		if (printSettings)
			this.setPause(true);

		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * Renvoie l'epaisseur
	 */
	public int getEpaisseur() {

		return epaisseur;

	}

	/**
	 * Modifie l'epaisseur
	 */
	public void setEpaisseur(int epaisseur) {

		this.epaisseur = epaisseur;

	}

	/**
	 * Renvoie l'amplitude
	 */
	public int getAmplitude() {

		return amplitude;

	}

	/**
	 * Modifie l'amplitude
	 */
	public void setAmplitude(int amplitude) {

		this.amplitude = amplitude;

	}

	/**
	 * Renvoie l'espacement
	 */
	public int getEspacement() {

		return espacement;

	}

	/**
	 * Modifie l'espacement
	 */
	public void setEspacement(int espacement) {

		this.espacement = espacement;

	}

	/**
	 * Renvoie la couleur rouge des formes 2D 
	 */
	public int getCouleur_2d_forme_r() {

		return couleur_2d_forme_r;

	}

	/**
	 * Modifie la couleur rouge des formes 2D 
	 */
	public void setCouleur_2d_forme_r(int couleur_2d_forme_r) {

		this.couleur_2d_forme_r = couleur_2d_forme_r;

	}

	/**
	 * Renvoie la couleur bleu des formes 2D 
	 */
	public int getCouleur_2d_forme_b() {

		return couleur_2d_forme_b;

	}

	/**
	 * Modifie la couleur bleu des formes 2D 
	 */
	public void setCouleur_2d_forme_b(int couleur_2d_forme_b) {

		this.couleur_2d_forme_b = couleur_2d_forme_b;

	}

	/**
	 * Renvoie la couleur verte des formes 2D 
	 */
	public int getCouleur_2d_forme_g() {

		return couleur_2d_forme_g;

	}

	/**
	 * Modifie la couleur verte des formes 2D 
	 */
	public void setCouleur_2d_forme_g(int couleur_2d_forme_g) {

		this.couleur_2d_forme_g = couleur_2d_forme_g;

	}

	/**
	 * Renvoie la couleur rouge des traits 2D 
	 */
	public int getCouleur_2d_trait_r() {

		return couleur_2d_trait_r;

	}

	/**
	 * Modifie la couleur rouge des traits 2D 
	 */
	public void setCouleur_2d_trait_r(int couleur_2d_trait_r) {

		this.couleur_2d_trait_r = couleur_2d_trait_r;

	}

	/**
	 * Renvoie la couleur verte des traits 2D 
	 */
	public int getCouleur_2d_trait_g() {

		return couleur_2d_trait_g;

	}

	/**
	 * Modifie la couleur verte des traits 2D 
	 */
	public void setCouleur_2d_trait_g(int couleur_2d_trait_g) {

		this.couleur_2d_trait_g = couleur_2d_trait_g;

	}

	/**
	 * Renvoie la couleur bleu des traits 2D 
	 */
	public int getCouleur_2d_trait_b() {

		return couleur_2d_trait_b;

	}

	/**
	 * Modifie la couleur bleu des traits 2D 
	 */
	public void setCouleur_2d_trait_b(int couleur_2d_trait_b) {

		this.couleur_2d_trait_b = couleur_2d_trait_b;

	}

	/**
	 * Renvoie la couleur rouge du cube 3D num�ro 1 
	 */
	public int getCouleur_3d_cube1_r() {

		return couleur_3d_cube1_r;

	}

	/**
	 * Modifie la couleur rouge du cube 3D num�ro 1 
	 */
	public void setCouleur_3d_cube1_r(int couleur_3d_cube1_r) {

		this.couleur_3d_cube1_r = couleur_3d_cube1_r;

	}

	/**
	 * Renvoie la couleur verte du cube 3D num�ro 1 
	 */
	public int getCouleur_3d_cube1_g() {

		return couleur_3d_cube1_g;

	}

	/**
	 * Modifie la couleur verte du cube 3D num�ro 1 
	 */
	public void setCouleur_3d_cube1_g(int couleur_3d_cube1_g) {

		this.couleur_3d_cube1_g = couleur_3d_cube1_g;

	}

	/**
	 * Renvoie la couleur bleu du cube 3D num�ro 1 
	 */
	public int getCouleur_3d_cube1_b() {

		return couleur_3d_cube1_b;

	}

	/**
	 * Modifie la couleur bleu du cube 3D num�ro 1 
	 */
	public void setCouleur_3d_cube1_b(int couleur_3d_cube1_b) {

		this.couleur_3d_cube1_b = couleur_3d_cube1_b;

	}

	/**
	 * Renvoie la couleur rouge du cube 3D num�ro 2 
	 */
	public int getCouleur_3d_cube2_r() {

		return couleur_3d_cube2_r;

	}

	/**
	 * Modifie la couleur rouge du cube 3D num�ro 2 
	 */
	public void setCouleur_3d_cube2_r(int couleur_3d_cube2_r) {

		this.couleur_3d_cube2_r = couleur_3d_cube2_r;

	}

	/**
	 * Renvoie la couleur verte du cube 3D num�ro 2 
	 */
	public int getCouleur_3d_cube2_g() {

		return couleur_3d_cube2_g;

	}

	/**
	 * Modifie la couleur verte du cube 3D num�ro 2 
	 */
	public void setCouleur_3d_cube2_g(int couleur_3d_cube2_g) {

		this.couleur_3d_cube2_g = couleur_3d_cube2_g;

	}

	/**
	 * Renvoie la couleur bleu du cube 3D num�ro 2 
	 */
	public int getCouleur_3d_cube2_b() {

		return couleur_3d_cube2_b;

	}

	/**
	 * Modifie la couleur bleu du cube 3D num�ro 2 
	 */
	public void setCouleur_3d_cube2_b(int couleur_3d_cube2_b) {

		this.couleur_3d_cube2_b = couleur_3d_cube2_b;

	}

	/**
	 * Renvoie la couleur rouge du cube 3D num�ro 3 
	 */
	public int getCouleur_3d_cube3_r() {

		return couleur_3d_cube3_r;

	}

	/**
	 * Modifie la couleur rouge du cube 3D num�ro 3 
	 */
	public void setCouleur_3d_cube3_r(int couleur_3d_cube3_r) {

		this.couleur_3d_cube3_r = couleur_3d_cube3_r;

	}

	/**
	 * Renvoie la couleur verte du cube 3D num�ro 3 
	 */
	public int getCouleur_3d_cube3_g() {

		return couleur_3d_cube3_g;

	}

	/**
	 * Modifie la couleur verte du cube 3D num�ro 3 
	 */
	public void setCouleur_3d_cube3_g(int couleur_3d_cube3_g) {

		this.couleur_3d_cube3_g = couleur_3d_cube3_g;

	}

	/**
	 * Renvoie la couleur bleu du cube 3D num�ro 3 
	 */
	public int getCouleur_3d_cube3_b() {

		return couleur_3d_cube3_b;

	}

	/**
	 * Modifie la couleur bleu du cube 3D num�ro 3 
	 */
	public void setCouleur_3d_cube3_b(int couleur_3d_cube3_b) {

		this.couleur_3d_cube3_b = couleur_3d_cube3_b;

	}

	/**
	 * Renvoie la couleur rouge du cube 3D num�ro 4 
	 */
	public int getCouleur_3d_cube4_r() {

		return couleur_3d_cube4_r;

	}

	/**
	 * Modifie la couleur rouge du cube 3D num�ro 4 
	 */
	public void setCouleur_3d_cube4_r(int couleur_3d_cube4_r) {

		this.couleur_3d_cube4_r = couleur_3d_cube4_r;

	}

	/**
	 * Renvoie la couleur verte du cube 3D num�ro 4 
	 */
	public int getCouleur_3d_cube4_g() {

		return couleur_3d_cube4_g;

	}

	/**
	 * Modifie la couleur verte du cube 3D num�ro 4 
	 */
	public void setCouleur_3d_cube4_g(int couleur_3d_cube4_g) {

		this.couleur_3d_cube4_g = couleur_3d_cube4_g;

	}

	/**
	 * Renvoie la couleur bleu du cube 3D num�ro 4 
	 */
	public int getCouleur_3d_cube4_b() {

		return couleur_3d_cube4_b;

	}

	/**
	 * Modifie la couleur bleu du cube 3D num�ro 4
	 */
	public void setCouleur_3d_cube4_b(int couleur_3d_cube4_b) {

		this.couleur_3d_cube4_b = couleur_3d_cube4_b;

	}

	/**
	 * Renvoie l'etat du autoplay 
	 */
	public boolean isAutoplay() {

		return autoplay;

	}

	/**
	 * Modifie l'etat du autoplay 
	 */
	public void setAutoplay(boolean autoplay) {

		this.autoplay = autoplay;

	}

	/**
	 * Renvoie l'etat des couleurs al�atoire pour la 2D 
	 */
	public boolean isCouleur_2d_random() {

		return couleur_2d_random;

	}

	/**
	 * Modifie l'etat des couleurs al�atoire pour la 2D 
	 */
	public void setCouleur_2d_random(boolean couleur_2d_random) {

		this.couleur_2d_random = couleur_2d_random;

	}

	/**
	 * Renvoie l'etat des couleurs al�atoire pour la 3D 
	 */
	public boolean isCouleur_3d_random() {

		return couleur_3d_random;

	}

	/**
	 * Modifie l'etat des couleurs al�atoire pour la 3D 
	 */
	public void setCouleur_3d_random(boolean couleur_3d_random) {

		this.couleur_3d_random = couleur_3d_random;

	}

	/**
	 * Signale s'il faut mettre la musique en poause ou non
	 *
	 * @param b
	 * true : pause
	 * false: lecture
	 */
	public void setPause(boolean b) {

		this.pause = b;

		if (this.musique != null)
			this.musique.setPause(b);

		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * Renvoie l'etat de la lecture
	 * 
	 * @return
	 * true : pause
	 * false: lecture
	 */
	public boolean isPause() {

		return this.pause;

	}

	/**
	 * Arrete l'application si true
	 */
	public void quitApp(boolean quit) {

		if (quit)
			System.exit(0);

	}

	/**
	 * Renvoie l'etat d'une demande de changement de visualiseur
	 */
	public boolean isChangingDimension() {

		return changingDimension;

	}

	/**
	 * Demande le changement de visualiseur
	 */
	public void setChangingDimension(boolean changingDimension) {

		this.changingDimension = changingDimension;

		this.setChanged();
		this.notifyObservers();

		this.changingDimension = false;

	}

	/**
	 * Renvoie le volume de la musique
	 */
	public int getVolume() {

		return volume;

	}

	/**
	 * Modifie le volume de la musique
	 */
	public void setVolume(int volume) {

		this.volume = volume;

		if (musique != null)
			this.musique.setVol( (float) volume / 100 );

	}

	//TODO
	public void increaseVolume() {

		if (volume <= Model.VOLUME_MAX - 2)
			this.setVolume(volume + 2);

		else
			volume = Model.VOLUME_MAX;

		this.setChanged();
		this.notifyObservers();

	}

	//TODO
	public void decreaseVolume() {

		if (volume >= Model.VOLUME_MIN + 2)
			this.setVolume(volume - 2);

		else
			volume = Model.VOLUME_MIN;

		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * Permet d'obtenir le r�pertoire actuel
	 * @return the directory
	 */
	public File getDirectory() {

		return directory;

	}

	/**
	 * Permet de modifier le r�pertoire actuel
	 * @param directory the directory to set
	 */
	public void setDirectory(File directory) {

		this.directory = directory;

	}
}
