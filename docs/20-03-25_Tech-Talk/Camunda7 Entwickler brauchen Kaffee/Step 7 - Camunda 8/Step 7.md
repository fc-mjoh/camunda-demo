* Beide Process-Engine
* Camunda Platform 7 (**Activiti-Engine**)
	* Monolith
* Camunda Platform 8 (Weiterentwicklung Camunda Cloud) (**Zeebe-Process-Engine**)
	* horizontal-linear-skalierbar, eignet sich für die Cloud 
	* setzt nicht auf Relationalen-Datenbanken auf
	* Bearbeitung von Tasks losgelöst von der Process-Engine (Vorhin haben wir  sowas schon gehört, kümmert sich nur noch um die Reihenfolge der Tasks) (siehe Camunda Platform 7, ExternalTask) JobWorker anstelle von Delegates
	* Benutzerfreundliche Connectoren
	* Docker-Compose (self-managed)

# Migration

?

