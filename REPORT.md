Compte rendu Drive.jsf
=====================

Drive.jsf est une solution équivalente à Google Drive, implémentée en JSF avec la bibliothèque Primefaces.

----------

Auteurs
---------
- Kevin Renella <kevin.renella@gmail.com>
- Alexandre Mottet <alexandre.mottet@gmail.com>


Mise en place du projet
---------
Tout d'abord, il est indispensable de procéder au téléchargement des dépendances du projet. Pour cela, à la racine du projet :

	./composer.phar install
	
Importez le fichier de base de données dans votre base locale :

	/HotspotMap/app/Resources/sql/HotspotMap.sql
	
Définissez les informations de connexion de la base de données dans le fichier `index.php` :
	
	$GLOBALS['DB_DSN'] = 'mysql:host=localhost:3306;dbname=HotspotMap';
    $GLOBALS['DB_USER'] = 'root';
    $GLOBALS['DB_PASSWD'] = 'root';

Dans le répertoire du projet, lancer le serveur php avec le dossier `web` comme racine :

	php -S localhost:8090 -t web/
	
Accédez au site avec l'url `localhost:8090`

Fonctionnalités
---------
Les fonctionnalités implémentées sont les suivantes :

- Implémentation d'une API REST avec des réponses disponibles en xml, json et html
- Authentification d'un administrateur (utilisation de Silex pour l'authentification, avec gestion de rôles)
- Recherche de la position actuelle et affichage des lieux les plus proches
- Utilisation d'une carte Google Map (JavaScript) afin d'afficher les Hotspots
- Interface utilisateur avec page dynamique par l'utilisation de l'API REST (requêtes AJAX)
- Ajout d'un commentaire sur les Hotspots
- Ajout / modification d'un Hotspot
- Un administrateur authentifié peut/doit administrer les lieux et les commentaires ajouté ou mis à jour par un utilisateur
- Bouton *follow* Twitter
- Bouton *tweet* 
- Recherche d'un lieu par l'adresse ou les coordonnées géographiques

Les fonctionnalités manquantes à implémenter sont :

- Filtrage des lieux par critères
- Par souplesse d'utilisation, un utilisateur ne doit pas être authentifié pour consulter les Hotspot ou pour en ajouter
- Amélioration du tweet (ajout du lieux courant)
- Découplage du data mapper et de la connexion pour améliorer la maintenabilité
- Chargement dynamique des lieux sur la carte (lazy loading)
- Utiliser HATEOAS pour API REST

Utilisation
---------
- La création d'un lieu se fait sans authentification mais doit être modérée par l'administrateur.
- La suppression d'un lieu se fait uniquement par l'administrateur.
- La modification d'un lieu se fait sans authentification mais doit être modérée par l'administrateur.
- La création d'un commentaire se fait sans authentification mais doit être modérée par l'administrateur.
- La suppression d'un lieu se fait uniquement par l'administrateur.
- La modération se fait dans l'interface d'Admin uniquement après authentification.

Accès admin dev:

- login : admin
- pass  : foo 
