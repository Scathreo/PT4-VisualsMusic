import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//TODO
public class Controller_Fenetre extends Controller implements WindowListener {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Fenetre(Model model) {

		super(model);

	}

	//TODO
	public void windowActivated(WindowEvent arg0) {



	}

	//TODO
	public void windowClosed(WindowEvent arg0) {



	}

	//TODO
	public void windowClosing(WindowEvent arg0) {

		JFrame frame = (JFrame) arg0.getSource();

		if (frame.getTitle().equals("Visuals Music")) {

			int confirmation = JOptionPane.showConfirmDialog(null, 
					"Êtes-vous sûr de vouloir fermer l'application ?",
					"Fermer l'application ?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (confirmation == JOptionPane.YES_OPTION)
				model.quitApp(true);

			return;

		}

		if (frame.getTitle().equals("Visuals Music - Settings")) {

			int confirmation = JOptionPane.showConfirmDialog(null, 
					"Tout éléments non sauvegardé ne sera pas pris en compte"
							+ "\n\n"
							+ "Êtes-vous sur de vouloir continuer ?",
							"Fermer les paramètres ?", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

			if (confirmation == JOptionPane.YES_OPTION)
				model.setPrintSettings(false);
			
			return;

		}
	}

	//TODO
	public void windowDeactivated(WindowEvent arg0) {



	}

	//TODO
	public void windowDeiconified(WindowEvent arg0) {



	}

	//TODO
	public void windowIconified(WindowEvent arg0) {



	}

	//TODO
	public void windowOpened(WindowEvent arg0) {



	}

}
