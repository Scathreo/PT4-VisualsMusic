import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Classe qui permettra de g�rer le son lorsque 
 * l'utilisateur le modifie sur le slider
 * 
 * @author
 * Goodwin
 *  Impl�mentation de la classe
 */
public class Controller_Slider extends Controller implements ChangeListener {

	/**
	 * Constructeur utilisant le Constructeur Parent
	 * 
	 * @param model   : Instanciant le Model
	 */
	public Controller_Slider(Model model) {
		
		super(model);
		
	}
	
	/**
	 * M�tode de l'interface ChangeListener
	 * 
	 * Quand on modifie le slider, on modifie le son
	 */
	public void stateChanged(ChangeEvent arg0) {
		
		JSlider volume = (JSlider) arg0.getSource();
		
		model.setVolume(volume.getValue());
		
	}
}
