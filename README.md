# ERP_ESSENCE
## English : 

## Introduction 
ERP_ESSENCE is a project that I completed during my Computer Science degree.

The project required us to develop a project management software similar to an ERP system to manage a chain of gas stations. 

As a team of 8, we had to manage the project, create mockups, and distribute tasks using Agile methodology. 

The project was divided into sprints, and two members of the group were responsible for the implementation of the first sprint (@Firelods and myself, @ThomasGorisseGit).

We decided to develop the application as a website using the Angular framework. To interact with certain data, we used a small PHP API backend.

The project was very interesting and I decided to product a final version.

## Project Development

In this repository, my goal is to continue the development of this ERP system.

It will still use the Angular framework, but its backend will be developed using a SpringBoot application. The SpringBoot application (Java) will interact with a SQL database.

The purpose of this project is to gain experience and understand how data exchanges work between SpringBoot and SQL.

## Difficulties Encountered
During the realization of this project, I worked on an application implementing the foundations of an entity-service architecture using Spring. I believe it might have been easier to create global services capable of handling entire features. For instance, instead of creating a service to create an order and another service to add an item to an order, it would have been more efficient to combine these features into the same service. This way, I wouldn't automatically set up a service for each entity.

Additionally, I faced challenges when implementing the user authentication system. It would have been beneficial to tackle the authentication feature first to gain a clear and comprehensive understanding of the subject. Learning the intricacies of Spring authentication while simultaneously integrating it into the application proved to be challenging.



## Français

## Introduction 
ERP_ESSENCE est un projet réalisé lors de mon BUT Informatique.

Le sujet demandait de réaliser la gestion de projet d'un logiciel type ERP afin d'administrer une chaîne de station service.

Il a fallu en équipe de 8, réaliser la gestion de projet, le maquettage et la distribution des tâches grâce à l'application de la méthodologie Agile. 

Le projet a donc été décomposé sous forme de sprint et 2 personnes du groupe ont du réaliser le premier Sprint. 
(@Firelods et moi @ThomasGorisseGit)

Nous avons donc décidés d'éffectuer l'application sous forme de site internet en utilisant le Framework Angular.
Afin d'intéragir avec certaines données, nous avons utilisés un petit Backend en PHP (API).

Le project étant très intéréssant j'ai décidé de le terminer.

## La réalisation du projet

Dans ce repository, j'ai pour objectif de reprendre le développement de cet ERP.

Celui ci utilisera toujous le Framework Angular, mais son backend sera fait grâce à une application SpringBoot.
L'application SpringBoot (Java) intérogerra une base de donnée SQL.

Ce projet a pour but de me faire gagner en expérience et de comprendre le fonctionnement les échanges de données entre SpringBoot et SQL.

## Difficultés rencontrées 

Lors de la réalisation de ce projet, j'ai travail sur un application mettant en oeuvre les fondements d'une architecture de type entité - service grâce à spring.
Je pense qu'il aurait été plus facile de réaliser des services globaux capable de prendre en charge toute une feature. Par exemple il n'aurait pas fallu faire un service pour créer une commande et un service pour ajouter un article a une commande, mais bien réunir les deux features dans le même service.
Ainsi je ne mettrai pas automatiquement en place un service pour chaque Entité.

Également, j'ai rencontré des difficultées lors de l'implementation du système de connexion d'un utilisateur, il aurait fallu prendre en charge la feature en premier afin d'avoir un point de vue global et clair sur le sujet. Il était difficile d'apprendre le fonctionnement d'une connexion sur Spring tout en mettant en place le système pour qu'il soit compatible avec l'application.



