Le groupe est constitué de 3 personnes, Kitchiguin Dominique, Bihannic Lucas, Bisson Mathieu.

Nous avons une version du jeu qui est fonctionnelle dans la console puis une autre
que nous avons essayé de faire avec une interface vaadin mais qui a échoué.

Nous avons pour la version console une classe pour chaque type de carte qui existe dans le jeu puis une méthode abstraite qui nous permet de mettre en place des conditions d'utilisation.
Pour chaque pile, main et pioche nous avons créé une Arraylist ou un Stack dans lesquelles les cartes ont etaient stockes.


Pour ce qui concerne la version avec interface vaadin nous avons reussi d'ouvrir deux fenêtres pour chaque joueur sur lesquelles un plateau était construit avec la disposition de toute
les cartes. Nous avons réussi à initialiser la partie en distribuant les cartes de la pioche dans la main de chaque joueur puis les affichés dans l'interface sous forme d'images.
l'application du jeu se lance sur un thread à part stocké dans un singleton propre a la classe App afin que les threads de chaque joueur puissent communiquer facilement avec le jeu.


Il y a deux branches sur github, une "main" pour la version avec console et une "jeu avec interface" pour la version avec vaadin qui n'est pas finalisée.
