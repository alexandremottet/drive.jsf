Compte rendu Drive.jsf
=====================

Drive.jsf est une solution équivalente à Google Drive, implémentée en JSF avec la bibliothèque Primefaces.

----------

Auteurs
---------
- Kevin Renella <kevin.renella@gmail.com>
- Alexandre Mottet <mottetalexandre@gmail.com>


Mise en place du projet
---------
Nous avons utilisé Maven pour gérer les dépendances de notre projet (cf. pom.xml). Nous avons utilisé l'IDE IntelliJ pour construire et déployer notre application sur le serveur [TomEE][1] version 1.6.0.
	
Tout d'abord, importez le fichier de base de données dans votre base locale :

	/database/schema.sql
	
Définissez les informations de connexion de la base de données dans le fichier `hibernate.cfg.xml ` :
	
	/drive.jsf/src/main/resources/hibernate.cfg.xml 

Déployez votre application sur l'adresse suivante pour que la configuration OAuth Google reconnaisse l'application :

	http://localhost:8080/

Une fois l'application déployée, vous aurez accés à Drive.jsf, notre solution de gestion de fichier en ligne.

Fonctionnalités
---------
Les fonctionnalités implémentées sont les suivantes :

- Authentification OAuth Google pour se connecter à l'application
- Déconnexion avec retour à la page d'accueil
- Gestion d'une page 404 personnalisée
- Page d'accueil personnalisée qui incite l'utilisateur à se connecter.
- Page de gestion des fichiers avec les possibilités suivantes :
    - Navigation dans l'arborescence des fichiers par double click sur les dossiers
    - Suppression d'un fichier / dossier (Si suppression d'un dossier, suppression automatique de tous les fichiers contenus)
    - Téléchargement d'un fichier
    - Téléchargement d'un dossier (Le dossier et tout son contenu est zippé avant d'être téléchargé)
    - Partage d'un dossier / fichier avec un autre utilisateur
    - Upload d'un fichier (ou plusieurs)
    - Création d'un dossier au nom spécifié
    - Recherche dans les documents possédés
- Page de gestion des dossiers / fichiers partagés
    - Vision des dossiers / fichiers que l'on a partagé
    - Vision des dossiers / fichiers qui nous sont partagés
    - Navigation dans les dossiers
    - Suppression d'un partage
- Gestion des menus contextuels

Pour ce projet, nous avons utilisé une structure de base de donnée intelligente qui stock les données de manière découplée. Cette méthode nous permet la duplication de fichiers illimitée à coût réduit. Un fichier a un lien logique vers une donnée. Pour savoir si le data d'un fichier existe en base, nous utilisons le checksum MD5 des données.

Schema de la base de données : 

![Schema BDD][2]

Screenshots
---------
Context Menu

![Context Menu][3]

Folder Popup

![Folder Popup][4]

Home Screen

![Home Screen][5]

Search Table

![Search Table][6]

Share Popup

![Share popup][7]

Share Screen

![Share Screen][8]

Welcome Screen

![Welcome Screen][9]

  [1]: http://tomee.apache.org/apache-tomee.html
  [2]: /database/schema.png
  [3]: /screenshots/context_menu.png
  [4]: /screenshots/folder_popup.png
  [5]: /screenshots/home_screen.png
  [6]: /screenshots/search_table.png
  [7]: /screenshots/share_popup.png
  [8]: /screenshots/share_screen.png
  [9]: /screenshots/welcome_screen.png