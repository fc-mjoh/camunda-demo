1. Open in Browser
	1. show application.yaml | demo / demo
	2. Explain Process Definition:
		1. Struktur des Prozesses: BPMN!
2. name process: **coffee_process**
3. Exkurs: ExternalTaskClient / ExternalTaskHandler
   Sicherlich das komplizierteste heute
	1. Die Prozess-Engine (API stellt verschiedene Services zur Verfügung TaskService, RuntimeService, ManagementService, History-Service) kümmert sich nicht wie beim Delegate (synchron) um den Task
	2. Entkopplung von der Prozess-Engine
	3. Tasks werden in eine Liste geschrieben,  per Rest-Aufruf werden diese aus der Liste geholt
	4. asynchron
4. create **service-task**
	2. ID: **get-coffee-price**
	3. Implementation - Type: External
	4. Topic: get-coffee-price

![[Pasted image 20250316095423.png]]

**Deployment**

**Task generieren**

**Siehe Output External Task Client**