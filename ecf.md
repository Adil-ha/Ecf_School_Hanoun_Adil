## ECF - jeudi 18/01/2024

### 1 - Enoncé:

Vous devez créer une application qui va vous permettre de gérer un etablissement scolaire.
L'etablissement est composé de plusieurs departements avec un simple nom comme le departement "Alpha", "Beta" ou autres noms.</br>
L'etablissement dispose d'enseignants qui sont rattachés à un seul et unique département. Un enseignant a un matricule qui est généré automatiquement lors de sa création, un nom, un prenom, un age, un grade et il peut être professeur principal ou pas. <br/> <br/>
Chaque département est dirigé par un responsable de departement qui un des enseignants. Les professeurs peuvent enseigner plusieurs matières et les matières peuvent être enseignés par plusieurs professeurs. 
Une matière dispose d'un intitulé, d'une description, d'une durée en minutes et d'un coefficient. Le coefficient ne peut excéder 5 et est au minimum de 1. <br/> <br/>
Les etudiants appartiennent à une seule classe. Un etudiant a un nom, un prenom et une date de naissance et un email pour que l'on puisse le joindre. Chaque classe est rattachée à un seul département. La classe a un nom et un niveau (5ème, Terminal, etc.).<br/> <br/>
Chaque enseignant peut enseigner à plusieurs classes. Les etudiants sont notés pendant l'année. Cette note se compose d'une valeur, d'un commentaire et est associé à une matière. <br/> <br/>
Chaque etudiant à une note par matière. La moyenne d'un etudiant se calcule en prenant en compte le coefficient des matières. <br/> <br/>
Les etudiants disposent d'un emploi du temps qui leur permet de savoir à quel jour, à quel heure et la matière qui sera enseignée pour leur classe.

### 2 - Votre projet devra répondre aux fonctionnalités suivantes : 


Create : 

 - Créer un département, un enseignant, un etudiant, une matière, une note, une classe et un emploi du temps.
  
Read : 

 - Afficher la liste des classes (sans les eleves). 
 - Afficher le nombre de matière d'un élève. 
 - Afficher la liste des notes d'un eleve (avec les détails).
 - Aficher la moyenne d'un eleve.
 - Afficher le nombre d'eleve d'un département.
 - Afficher tous les noms des eleves d'un niveau.

Delete : 

 - Suppression d'un eleve, supprimera sa note mais pas sa classe.
 - Suppression classe => supprime uniquement les éléves de cette classe.
 - Suppression d'un departement => Supprime toutes les classes et tous les professeurs.
  

### 4 - Quelques contraintes : 

 - Les noms des éléves et des professeurs ne peuvent être nuls et doivent contenir au moins 3 caractères.
 - Les professeurs doivent être majeurs pour être enregistrés en base.
 - Les adresses mails des etudiants doivent être de ce format : "@gmail.com".
 - La valeur d'une note ne peut dépasser 20 et être négative.


### 5 - Pour construire ce projet, il faudra effectuer les etapes suivantes : 

Conception : 

 - Dictionnaire de donnée.
 - MCD
 - MLD
 - MPD

Developpement (structuration du projet): 

 - Controller => IHM
 - Service
 - Dao => interface(générique)
 - DaoImpl

### Technologies et Outils (Rappel) : 

 - Java : Langage de programmation principal (JDK 17 ou 19).
 - Hibernate : ORM pour le mappage objet-relationnel.
 - Base de données : MySQL
 - Maven: Outils de construction et gestion de dépendances.
 - Git : Système de contrôle de version.


