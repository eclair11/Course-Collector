# Course Collector

## Membres du groupe
* Solofo
* Ibrahima
* Elias

## Sujet du projet
 - Notre idée de projet consiste à créer une application Web "Course Collector".
 "Course Collector" a pour but de rendre le suivi des cours et la préparation des examens pour les étudiants plus faciles.
 Chaque étudiant aura la possibilité de demander la publication d'un cours qu'il lui manque ou de la même façon publier un cours suite à une demande de publication.
 Il aura également la possibilité de voter un cours pour faire en sorte que les cours soient triés selon la qualité.
 Nous utiliserons la technologie OCR (Optical Character Recognition) pour extraire le contenu des images de cours dans un document texte sous format PDF.
 Les documents extraits permettront par la suite aux étudiants de faire une recherche sur le contenu des cours.

## Instructions pour obtenir et lancer le projet

### Recuperer le projet
 - `git clone https://github.com/Enjana-mavitrika/ADV_WEB_2019.git`
 - `git checkout master`

### Installer tesseract-ocr sur votre machine (obligatoire pour lancer l'application) 
 - `sudo apt install tesseract-ocr`
 - En cas de problème suivre le tuto **[ici](https://github.com/tesseract-ocr/tesseract/wiki/Compiling#linux)**

### Lancer l'application 
 - Ouvrir le dossier src qui se trouve dans ADV_WEB_2019 avec visual Studio Code ou Netbeans
 - Lancer l'application course-collector à travers le Spring-Boot Dashboard
 - Se connecter à l'adresse **[localhost](http://localhost:8080/)**

## Organisation des fichiers 
 - _doc_ : diapos de présentation, diagrammes UML, vidéo de démonstration, ...
 - _src_ : code source du projet
 - _src/main/java/org/coursecolletor/esi : contient les classes de Configuration et les classes Controlleur de l'application
 - _src/main/java/org/coursecolletor/esi/api/ : contient les classes REST Api de l'application
 - _src/main/java/org/coursecolletor/esi/service/ : contient les classes services de l'application
 - _src/main/java/org/coursecolletor/esi/model/ : contient les classes Model et CrudRepository de l'application

## Description de l'architecture

 - Les fichiers html qui se trouvent dans le dossier templates constituent la vue de notre application, ils interagissent directement avec les contrôleurs qui se trouvent dans le dossier main/java/org/coursecollector/esi par l'intermédiaire de Thymeleaf.
 - Les fichiers java qui se trouvent dans le dossier model constituent les entités de notre application, ils intéragissent directement avec une base de données en mémoire H2.
 - Le dossier static regroupe toutes les ressources qui complètent les vues comme les feuilles de style, les scripts JS, les images...
 - Le dossier service contient les classes pour l'intégration de l'OCR et la sécurité

### Diagramme Entités-relations des modèles
 ![diagramme entités-relations des modèles](https://github.com/Enjana-mavitrika/ADV_WEB_2019/blob/dev/doc/diagramme_entit%C3%A9s_relations_des_mod%C3%A8les)

## Méthode de travail 
 - Code et commentaires en Anglais.
 - Nom de variable en Camel Case.
 - Mettre à jour le diagramme de classe du Model en cas de modification dans le code.
 - Rajouter les liens, auteurs et licences des bibliothèques ou codes externes intégré à l'application.

## Liens utiles
 - Diagramme de classe du Model : https://www.draw.io/?state=%7B%22ids%22:%5B%221Bo0PLwGzm4GqWp639xA17IJqcUhRvAqL%22%5D,%22action%22:%22open%22,%22userId%22:%22{userId}%22%7D#G1Bo0PLwGzm4GqWp639xA17IJqcUhRvAqL

## Codes adaptés
 - https://github.com/necolas/normalize.css | MIT License
 - https://o7planning.org/en/11679/spring-boot-file-upload-example | Open Source
 - https://codepen.io/maheshambure21/pen/VYJQYG | Open Source
 - https://w3schools.com/howto/tryit.asp?filename=tryhow_js_slideshow  | Open Source
 - https://www.roseindia.net/java/java-conversion/TextToPDF.shtml | Open Source
 - https://stackabuse.com/tesseract-simple-java-optical-character-recognition/
 
## Librairies
 - https://fontawesome.com/license/free
 - Modernizr 2.7.1 (Custom Build) | MIT & BSD
 - Webfont | http://www.apache.org/licenses/LICENSE-2.0
 - **[iText PDF](https://itextpdf.com/fr)** | Open Source AGPL
 - **[Tesseract OCR](https://github.com/tesseract-ocr)** | Apache License 2.0

## Images des cours
 - https://jedecouvrelavie.wordpress.com/cours-papier/

## Version 1.4.1
 - Migration page admin vers VueJS et ajout RestAPI
 - Modifier mot de passe de l'utilisateur
 - Correction des bugs sur la version 1.0.0