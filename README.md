# ProcrastinatorExpress
## Aplicatie care genereaza scuze in functie de domeniul sau pentru un input


<!-- TOC -->



<!-- /TOC -->


## API-uri folosite

### API Scuze salvate pe categorii



### API Chat


## Structura Fisierelor

```sh
ProcrastinatorExpress
 │   build.gradle
 │   gradle.properties
 │   settings.gradle
 │
 ├───app
 │   │   build.gradle
 │   │
 │   └───src
 │       └───main
 │           │   AndroidManifest.xml
 │           │
 │           ├───java
 │           │   └───ro
 │           │       └───example
 │           │           └───ProcrastinatorExpress
 │           │               │
 │           │               ├───activities
 │           │               │       MainActivity.java
 │           │               │		 CatListActivity.java
 │           │               │       DetailActivity.java
 │			 │				 │       ChatActivity.java
 │           │               │
 │           │               └───services
 │           │                       CategoryService.java
 │			 │						 ChatService.java
 │           │
 │           └───res
 │               ├───drawable
 │               │       ic_launcher.png
 │               │       ic_launcher_round.png
 │               │
 │               ├───layout
 │               │       activity_main.xml
 │               │       cat_list_activity.xml
 │               │       detail_activity.xml
 │				 │		 chat_activity.xml
 │               │
 │               ├───layout-land
 │               │       activity_main.xml
 │               │       cat_list_activity.xml
 │               │       detail_activity.xml
 │				 │		 chat_activity.xml
 │               │
 │               └───values
 │                       colors.xml
 │                       themes.xml
 │                       api_key.xml
 │
```


Folosim minim api level 26 pentru Autosize TextViews