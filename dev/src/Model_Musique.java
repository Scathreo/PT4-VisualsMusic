import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import flanagan.complex.Complex;
import flanagan.math.FourierTransform;

/** 
 * Classe de type Model, connu uniquement par la Classe "Model"
 * 
 * Sert � faire du MultiThreading et ainsi permettre la lecture 
 * 	audio tous en �tant en possibilit� d'affect� celle ci ou l'IG
 * 	en g�n�ral
 * 	C'est � dire que sans MultiThreading, il �tait impossible de
 * 	faire pause quand une musique se lanc�
 * 
 * Cette classe conserne uniquement la lecture de la musique � partir
 * 	d'un fichier audio compos� uniquement de donn�es brutes
 * 
 * H�rite de Observable afin de pouvoir notifi� les observer de Model
 * 	quand la fr�quence change
 * 	Concr�tement, elle n'a qu'un observer, le model.
 * 
 * @author 
 * Goodwin
 * 	Cr�ation et impl�mentation de la classe enti�re
 */
public class Model_Musique extends Observable implements Runnable {

	public static final int RESET_CODE = 100;

	/**
	 * Ligne permettant l'�coute audio
	 * Sort en son, ce qu'il y a �crit dedans
	 */
	private SourceDataLine line;

	/**
	 * Sert � transformer le fichier ouvert en 
	 * fichier avec lecture multim�dia possible
	 * 
	 * Courant d'entr�e audio
	 */
	private AudioInputStream audioInputStream;

	/**
	 * R�f�re tous les champs sp�cifique au format audio
	 */
	private AudioFormat audioFormat; 

	/**
	 * Fr�quence transmise aux vues en prenant la 
	 * fr�quence maximale d'un son
	 * 
	 * Une FFT est r�aliser pr�alablement pour obtenir 
	 * toutes les fr�quences d'un son
	 */
	private double frequenceMax;

	/**
	 * Fr�quence transmise aux vues en prenant la 
	 * fr�quence minimale d'un son
	 * 
	 * Une FFT est r�aliser pr�alablement pour obtenir 
	 * toutes les fr�quences d'un son
	 */
	private double frequenceMin;

	/**
	 * Fr�quence transmise aux vues en prenant la 
	 * fr�quence moyenne d'un son
	 * 
	 * Une FFT est r�aliser pr�alablement pour obtenir 
	 * toutes les fr�quences d'un son
	 */
	private double frequenceMoy;

	/**
	 * Fast Fourier Tranformation / Tranform� rapide de Fourrier
	 * 
	 * Sert � passer du signal temporel de la musique
	 * au spectre fr�quentiel
	 */
	private FourierTransform FFT;

	/**
	 * D�finis si la lecture doit �tre en pause ou non
	 * 
	 * true  : la lecture s'arr�te
	 * false : la lecture continue/commence
	 */
	private boolean pause;

	/**
	 * D�finis si un fichier � �t� charg� (initialis�) et convertis en line
	 */
	private boolean load;

	/**
	 * Le model de l'application
	 * 
	 * Sert a pouvoir cr�� des erreurs et a pouvoir le donn� en observer
	 */
	private Model model;

	/**
	 * Constructeur de la classe
	 * 
	 * Ajoute le model en observer afin de pouvoir actualiser
	 * l'affichage d�s que la fr�quence change
	 */
	public Model_Musique(Model m) {

		super();

		model = m;

		FFT = new FourierTransform();

		pause = true;
		load = false;

		this.addObserver(model);

	}

	/**
	 * Initialise la lecture de la musique
	 * 	A �x�cuter avant la m�thode Thread.start(), sinon erreur
	 * 
	 * @param file : le fichier audio a ouvrir
	 */
	public void initialisation(File file){

		try {

			audioInputStream = AudioSystem.getAudioInputStream(file);

		} 

		catch (UnsupportedAudioFileException e) {

			model.setErreur(e);
			return;

		} 

		catch (IOException e) {

			model.setErreur(e);
			return;

		}

		audioFormat = audioInputStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

		try {

			line = (SourceDataLine) AudioSystem.getLine(info);
			load = true;

		} 

		catch (LineUnavailableException e) {

			model.setErreur(e);
			return;

		} 		
	} 

	/**
	 * Modifie le volume (gain) de la musique
	 * @param volume
	 */
	public void setVol(float volume) {

		FloatControl control = 
				(FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
		
		control.setValue((float) (20 * Math.log10(volume)));

	}

	/**
	 * M�thode de l'interface parente Runnable
	 * 	permettant l'execution de la m�thode Thread.start()
	 * 
	 * Lit la musique en cr�ant un buffer de byte
	 * 
	 * Enfin quand la lecture et finis, ferme le fichier
	 */
	public void run() {	

		pause = false;

		try {

			line.open(audioFormat);

			model.setVolume(model.getVolume());

		} 

		catch (LineUnavailableException e) {

			model.setErreur(e);
			return;

		} 	
		
		catch (Exception e) {

			model.setErreur(e);
			return;

		} 	

		line.start();

		try {

//			double complexMax;
//			double complexMin;
      double somme;
//      double sommeMin;
//      double sommeMax;
      double moyenne;
      
      int nbMax;
      int nbMin;

			byte bytes[] = new byte[audioFormat.getSampleSizeInBits()*1024];	//taille de l'echantillon * 1024
			int bytesRead = 0;

			while (!pause) {

				bytesRead = audioInputStream.read(bytes, 0, bytes.length);

				if (bytesRead != -1) {	//si il y a des octets lus

					Complex[] comp = new Complex[bytesRead];	//taille = nb de bytes lus

					for (int index_dans_tableau = 0; 
							index_dans_tableau < bytesRead;
							index_dans_tableau ++) {

						comp[index_dans_tableau] = new Complex(bytes[index_dans_tableau]);

					}

					FFT.setData(comp);
					FFT.transform();

//					complexMax = Double.MIN_VALUE;
//					complexMin = Double.MAX_VALUE;
					frequenceMax = 0;
					frequenceMin = 0;
					frequenceMoy = 0;
					somme = 0;
					moyenne = 0;
					
					Complex[] tableau_complexe_temporaire = FFT.getTransformedDataAsComplex();
					double[] tab_freq = new double[tableau_complexe_temporaire.length];
					
					for (int index_dans_tableau = 0; 
							index_dans_tableau < tableau_complexe_temporaire.length;
							index_dans_tableau ++) {

						double complexTemporaire = Math.abs(tableau_complexe_temporaire[index_dans_tableau].getReal());
						
						tab_freq[index_dans_tableau] = complexTemporaire;
						
//						if (complexMax < complexTemporaire) complexMax = complexTemporaire;
//						if (complexMin > complexTemporaire) complexMin = complexTemporaire; 
//						
						somme += complexTemporaire;
						
					}
					
					moyenne = somme / (double) tableau_complexe_temporaire.length;
					
					Arrays.sort(tab_freq);
					
					nbMin = 0;
					nbMax = 0;
					
					for (double d : tab_freq) {
					  
					  if (d < moyenne) { 
					    
					    nbMin ++;
					    
					  }
					  else {
					    
					    nbMax ++;
					    
					  }
					}
					
					frequenceMax = tab_freq[nbMin+nbMax/2];
					frequenceMin = tab_freq[nbMin/2];
					frequenceMoy = frequenceMin + frequenceMax / 2.0;

					setChanged();
					notifyObservers();

					line.write(bytes, 0, bytesRead);

				}

				else break;

			}			
		} 

		catch (IOException io) {

			model.setErreur(io);
			return;

		} 	

		this.reset();

	}

	/**
	 * Permet de mettre en pause ou en lecture
	 * 
	 * @param b
	 * true  : sera mis en pause
	 * false : sera mis en lecture
	 */
	public void setPause(boolean b) {

		this.pause = b;

	}	

	/**
	 * Permet d'obtenir le format du fichier
	 * 
	 * @return le format du fichier
	 */
	public AudioFormat getAudioFormat() {

		return audioFormat;

	}

	/**
	 * Permet d'obtenir la fr�quence max actuelle du fichier audio
	 * 
	 * @return la fr�quence max de la musique 
	 */
	public double getFrequenceMax() {

		return frequenceMax;

	}

	/**
	 * Permet d'obtenir la fr�quence min actuelle du fichier audio
	 * 
	 * @return la fr�quence min de la musique 
	 */
	public double getFrequenceMin() {

		return frequenceMin;

	}

	/**
	 * Permet d'obtenir la fr�quence moyenne actuelle du fichier audio
	 * 
	 * @return la fr�quence moyenne de la musique 
	 */
	public double getFrequenceMoy() {

		return frequenceMoy;

	}

	/**
	 * Informe de l'�tat de la pause
	 * 
	 * @return un boolean informant de l'�tat de la pause
	 * true  : fichier en pause
	 * false : fichier en lecture
	 */
	public boolean isPause() {

		return pause;

	}

	/**
	 * La m�thode permet de connaitre
	 * si la musique est charg�
	 * 
	 * @return
	 * true : fichier charg�
	 * false : fichier non charg�
	 */
	public boolean isLoad() {

		return load;

	}

	/**
	 * Remet tous les argument � null
	 * Afin de remettre � z�ro lorsque l'utilisateur
	 * presse "stop" par exemple
	 */
	public void reset() {

		if(!pause) {
			
			model.setPause(true);	//modifie le bouton en fin de lecture
			audioFormat = null;
			audioInputStream = null;
			line.close();
			line = null;
			load = false;
			
			setChanged();
			notifyObservers(Model_Musique.RESET_CODE);

		}
	}	


}
