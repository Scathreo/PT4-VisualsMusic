import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Classe Définissant une fenetre essentiellement composé
 * de TextField permettant de modifié des paramètres.
 * 
 * C'est paramètres peuvent être utilisé pour modifié
 * l'affichage
 * 
 * @author
 * Goodwin
 * 	Création et implémentation de la classe entière
 */
public class Vue_ChoixMode extends JFrame implements Observer {

  //TODO
  private String[] str_list = {
      "Maximum",
      "Minimum",
      "Moyenne",
  "Ton"};

  /**
   * Toute l'interface
   */
  private JPanel panel_fenetre;

  /**
   * Les contraintes du GridBagLayout relatif à la fenentre
   */
  private GridBagConstraints gbc_fenetre;

  /**
   * Première partie de l'IG
   */
  private JPanel panel_section1;

  /**
   * Les contraintes du GridBagLayout relatif à la partie 1
   */
  private GridBagConstraints gbc_1;

  /**
   * Deuxieme partie de l'IG
   */
  private JPanel panel_section2;

  /**
   * Un "Selectionnez"
   */
  private JLabel label_select = new JLabel("Selectionnez");

  /**
   * Un texte décrivant le mode sélectionné
   */
  private JLabel label_mode = new JLabel();

  //TODO
  private JComboBox<String> select_list = new JComboBox<String>(str_list);

  /**
   * Bouton de validation
   */
  private JButton submit = new JButton("Appliquer & quitter");

  /**
   * Créateur de l'IG toute entière
   */
  public Vue_ChoixMode(Model model) {

    super();

    this.addWindowListener(new Controller_Fenetre(model));

    /*
     * Creation des composants
     */

    panel_fenetre 	= new JPanel(new GridBagLayout());
    gbc_fenetre   = new GridBagConstraints();

    panel_section1 	= new JPanel(new GridBagLayout());
    gbc_1   = new GridBagConstraints();

    panel_section2 	= new JPanel();


    /*
     * Modification des éléments
     */

    //Modification taille 
    //list
    select_list.setPreferredSize(new Dimension(120, 30));
    //panel
    panel_section1.setPreferredSize(new Dimension(150, 300));
    panel_section2.setPreferredSize(new Dimension(500, 300));

    /*
     * Ajout du controlleur en interne anonyme
     * pour faciliter l'interaction entre le model et 
     * les valeur donner par l'utilisateur
     */
    submit.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        model.setMode((String) select_list.getSelectedItem());

        model.setPrintModeChooser(false);
        model.parametersChanged(true);

      }
    });

    select_list.addActionListener(new ActionListener() { 

      public void actionPerformed(ActionEvent e) {

        String str;

        if (select_list.getSelectedItem().equals("Ton")) {

          str = "<html> <div style='text-align: center;'>"
              + "Affichage qui affiche les sons graves et aigus"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Concrètement :"
              + "<br/>"
              + "Les sons graves sont des sons à fréquences basses."
              + "<br/>"
              + "Les sons aigus sont des sons à fréquences hautes"
              + "<br/>"
              + "<br/>"
              + "Affiche donc la fréquence minimale devant la fréquence maximale"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Aspect graphique :"
              + "<br/>"
              + "L'affichage est plus coloré que les autres et parrait plus complet"
              + "</div> </html>";
          
          label_mode.setText(str);

        }

        if (select_list.getSelectedItem().equals("Maximum")) {

          str = "<html> <div style='text-align: center;'>"
              + "Affichage qui affiche les sons aigus"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Concrètement :"
              + "<br/>"
              + "Les sons graves sont des sons à fréquences basses."
              + "<br/>"
              + "Les sons aigus sont des sons à fréquences hautes"
              + "<br/>"
              + "<br/>"
              + "Affiche donc la fréquence maximale"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Aspect graphique :"
              + "<br/>"
              + "L'affichage n'est pas linéaire, on a donc un aspect de relief"
              + "</div> </html>";
          
          label_mode.setText(str);

        }

        if (select_list.getSelectedItem().equals("Minimum")) {

          str = "<html> <div style='text-align: center;'>"
              + "Affichage qui affiche les sons graves"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Concrètement :"
              + "<br/>"
              + "Les sons graves sont des sons à fréquences basses."
              + "<br/>"
              + "Les sons aigus sont des sons à fréquences hautes"
              + "<br/>"
              + "<br/>"
              + "Affiche donc la fréquence minimale"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Aspect graphique :"
              + "<br/>"
              + "L'affichage n'est pas linéaire, on a donc un aspect de relief"
              + "</div> </html>";
          
          label_mode.setText(str);

        }

        if (select_list.getSelectedItem().equals("Moyenne")) {

          str = "<html> <div style='text-align: center;'>"
              + "Affichage qui affiche les fréquences moyenne de chaque sons"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Concrètement :"
              + "<br/>"
              + "Les sons graves sont des sons à fréquences basses."
              + "<br/>"
              + "Les sons aigus sont des sons à fréquences hautes"
              + "<br/>"
              + "<br/>"
              + "Affiche donc la fréquence moyenne de chaque sons"
              + "<br/>"
              + "<br/>"
              + "<br/>"
              + "Aspect graphique :"
              + "<br/>"
              + "L'affichage est pas linéaire, on ne distingue aucune fréquence particulière"
              + "</div> </html>";
          
          label_mode.setText(str);

        }
      }
    });

    gbc_1.anchor = GridBagConstraints.CENTER;
    panel_section1.add(label_select, gbc_1);
    gbc_1.gridy = 1;
    panel_section1.add(select_list, gbc_1);

    panel_section2.add(label_mode);

    panel_fenetre.add(panel_section1, gbc_fenetre);
    gbc_fenetre.gridx = 1;
    panel_fenetre.add(panel_section2, gbc_fenetre);
    gbc_fenetre.gridwidth = 2;
    gbc_fenetre.gridx = 0;
    gbc_fenetre.gridy = 1;
    panel_fenetre.add(submit, gbc_fenetre);

    this.add(panel_fenetre);

    //Ne rend pas la fenêtre visible quand on la créé

    this.setTitle("Visuals Music - Mode Chooser");
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setResizable(false);
    this.pack();

  }

  /**
   * Implémentation de la méthode de l'interface Observer
   * 
   * Pré-remplis les champs
   */
  public void update(Observable o, Object arg) {

    Model model = (Model) o;

    if (model.getErreur() == null) {

      if (model.isPrintModeChooser()) {

        select_list.setSelectedItem(model.getMode());

        this.setVisible(true);

      }

      else 

        this.setVisible(false);

    }
  }
}
