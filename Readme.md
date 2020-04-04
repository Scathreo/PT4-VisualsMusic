===============================================================================
################################ Visuals Music ################################
===============================================================================


================================
########### Exécution ##########
================================
Pour exécuter :
Lancer le fichier "VisualsMusic.jar"
Ou "java -jar VisualsMusic.jar"



================================
########### Ajouts #############
================================
Correction erreur lors de la fermeture d'une fenêtre. (Fermeture si on appuyait
sur "oui" ou "non")
Raccourcis clavier "+" et "-" afin de modifier le volume
Bouton Précédent
Bouton Suivant
Bouton Random
Bouton Loop
Possibilité d'ouvrir un dossier
Lecture des fichier dans une liste (tableau) permettant l'utilisation des
boutons précédemment cité
Signe d'activation d'un des boutons (vert et rouge, si activé ou non)
Affichage 3D sur une ligne (2 suivant le mode)
Fenêtre de choix de mode de visualisation
Mode de visualisation
  Analyse des fréquences : 
    Prend toutes les fréquences d'un son,
    Triage par valeurs croissante,
    Séparation en deux par rapport à la moyenne des valeur.
      1ere partie : valeurs minimales, 2eme partie : valeurs maximales
  Max : Prend la médiane de la 2eme partie
  Min : Prend la médiane de la 1ere partie
  Moy : Prend la moyenne entre la somme du Max et du Min (voir plus haut)
  Ton : Prend le Min et le Max en même temps (voir plus haut)
Passage des fréquence entre Vue et Model grâce à un tableau
  Dans le cas du Ton (voir plus haut), j'ai besoin de deux valeur en même temps



================================
########### Tests ##############
================================
Mode de visualisation
  Analyse des fréquences : 
    Prend toutes les fréquences d'un son,
    Triage par valeurs croissante,
    Séparation en deux par rapport à la moyenne des valeur.
      1ere partie : valeurs minimales, 2eme partie : valeurs maximales
  Max : Prend la médiane de la 2eme partie
  Min : Prend la médiane de la 1ere partie
  Moy : Prend la moyenne entre la somme du Max et du Min (voir plus haut)
  Ton : Prend le Min et le Max en même temps (voir plus haut)
Passage des fréquence entre Vue et Model grâce à un tableau
  Dans le cas du Ton (voir plus haut), j'ai besoin de deux valeur en même temps

=>

Au début, j'ai fait :
  Max : Prend la fréquence maximale d'un son
  Min : Prend la fréquence minimale d'un son
  Moy : Prend la fréquence moyenne d'un son
  Ton : Prend le Min et le Max en même temps (voir plus haut)
L'affichage était correcte et réprésentatif des noms des modes. Cependant, lors
de l'affichage de la valeur minimale, la barre (2D) se confondait avec la
droite au centre de l'écran. Donc pour y voir plus clair, j'ai affiché le ratio
fréquence/taille de la fenêtre. C'était des valeurs de type 10^-n, avec n
souvent plus grand que 5. Donc quasi égales à 0.
Pour contrer ce problème j'ai essayer de supprimer le ratio pour la valeur Min.
Par contre des fois la valeur Min était supérieur au ratio de la valeur Max.
Donc totalement incohérent.
Je me suis donc dis que j'allais séparer les valeur en deux par rapport à la 
moyenne, puis que j'allais prendre la moyenne des deux parties. Sur le papier
c'était une bonne idée, malheureusement l'affichage du Min était encore trop 
proche de 0, vue que la majorité des valeurs de la 1ere parties sont en 
puissances de 10^-n
C'est à ce moment là que je me suis dis que j'allais prendre la médianne.
Malheureusement avec cette technique je perds l'aspect de relief. Une solution
aurait été de garder l'ancienne technique pour le Max, et de faire différement
pour le Min. Cependant je n'ai pas voulu perdre en cohérence entre des modes
relativement proche entre eux (juste une histoire d'opposé).



================================
########### Remerciements ######
================================
Merci de votre aide tout au long du PT3 et 4. 
A +