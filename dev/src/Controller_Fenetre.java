import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe impl�mentant WindowListener
 * Sert � mettre des action lors des fermeture des fen�tres
 * 
 * @author
 * Goodwin
 *  Cr�ation et impl�mentation de la classe enti�re
 */
public class Controller_Fenetre extends Controller implements WindowListener {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Fenetre(Model model) {

		super(model);

	}

	/** Non impl�menter */
	public void windowActivated(WindowEvent arg0) {}

  /** Non impl�menter */
	public void windowClosed(WindowEvent arg0) {}

  /** Action lors de la fermeture d'une fen�tre */
	public void windowClosing(WindowEvent arg0) {

		JFrame frame = (JFrame) arg0.getSource();

		if (frame.getTitle().equals("Visuals Music")) {

			int confirmation = JOptionPane.showConfirmDialog(null, 
					"�tes-vous s�r de vouloir fermer l'application ?",
					"Fermer l'application ?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (confirmation == JOptionPane.YES_OPTION)
				model.quitApp(true);

			return;

		}

    if (frame.getTitle().equals("Visuals Music - Settings")) {

      int confirmation = JOptionPane.showConfirmDialog(null, 
          "Tout �l�ments non sauvegard� ne sera pas pris en compte"
              + "\n\n"
              + "�tes-vous sur de vouloir continuer ?",
              "Fermer les param�tres ?", 
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE);

      if (confirmation == JOptionPane.YES_OPTION)
        model.setPrintSettings(false);
      
      return;

    }

    if (frame.getTitle().equals("Visuals Music - Mode Chooser")) {

      int confirmation = JOptionPane.showConfirmDialog(null, 
          "Tout �l�ments non sauvegard� ne sera pas pris en compte"
              + "\n\n"
              + "�tes-vous sur de vouloir continuer ?",
              "Fermer les param�tres ?", 
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE);

      if (confirmation == JOptionPane.YES_OPTION)
        model.setPrintModeChooser(false);
      
      return;

    }    
	}

  /** Non impl�menter */
	public void windowDeactivated(WindowEvent arg0) {}

  /** Non impl�menter */
	public void windowDeiconified(WindowEvent arg0) {}

  /** Non impl�menter */
	public void windowIconified(WindowEvent arg0) {}

  /** Non impl�menter */
	public void windowOpened(WindowEvent arg0) {}

}
