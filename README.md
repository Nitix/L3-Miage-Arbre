L3-Miage-Arbre
==============

Convention :
Pour ce projet nous avons posés comme convention le fais qu’une valeur est stocké a gauche avec comme contrainte inférieur ou égale. 
Ainsi cela nous donne quelque chose comme à ça : 
			5
1 2 3 4 5			6 7 8 9 10

Stratégie de Fusion : 
On fusionne avec le noeud de gauche sauf si il n’y en a pas alors la on fusionne à droite.

Utilisation : 
Lors du lancement du main il y a un certain nombre d’action à faire : 
donner l’ordre des arbres,
puis noté le nom du dossier renfermant les fichiers à lire.

A partir de là deux arbres sont créer l’un renfermant les différentes valeurs pour l’âge et l’autre les noms. Plusieurs actions sont disponibles : 
Chercher le fichier contenant la référence : la recherche peut se faire par âge ou par nom. Cela nous renvoie le nom du fichier et nous pouvons choisir de l’ouvrir ou non pour accéder aux infos ; Lire le nom, l’âge. Comme lire une ligne dans un base de données.
Voir les arbres : permet d’avoir un visuel des arbres avec la méthode toString implémentée.
Supprimer une valeur : permet la suppression de la valeur dans l’arbre. Il faut néanmoins choisir le type de valeur âge ou nom. Après suppression, elle supprime également son équivalent dans le deuxième arbre. 
Quitter : qui met fin au programme.

Limitations :
L’interface console est très limitée, elle lit juste les fichiers et supprime des données dans l’arbre.
L’interface ne permet pas d’ajouter des valeurs dans l’arbre à partir de la console. 
Si il manque un fichier dans l’ordre par exemple : 1 2 3 5 6, ici le 4, le fichier 6 ne sera pas lu, il compte le nombre de fichier présent (hors fichier caché) 
La suppression ne supprime pas le fichier, il est donc de nouveau chargé après rechargement du programme.
