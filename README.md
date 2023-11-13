##### Server: [TPSIT-Chat-Server](https://github.com/MatteoFrosinii/ChatServer)
---
## Protocollo Utilizzato
|Codice                 |Direzione       |Contenuto Data   |Azione                                                                          |
|-----------------------|----------------|-----------------|--------------------------------------------------------------------------------|
|hi                     |Client -> Server|Nome utente      |Invio del nome utente dopo il login                                             |
|bye                    |Client -> Server|1                |Notifica al Server l'intenzione di chiudere la connessione                      |
|usersListRequest       |Client -> Server|1                |Richiedi la lista di tutti gli utenti online                                    |
|usersList              |Server -> Client|Nomi utenti      |Risposta del server con la lista degli utenti online es: "Mario;Luigi;Paolo"    |
|msgToEveryone          |Client -> Server|1                |Notifica al Server l'intenzione di inviare un messaggio in broadcast            |
|msgToUser              |Client -> Server|Nome destinatario|Invia il nome del destinatario es: "Mario"                                      |
|msgToMultipleUsers     |Client -> Server|Nomi destinatari |Invia una stringa con i nomi concatenati dei destinatari es: "Mario;Luigi;Paolo"|
|msgRequest             |Server -> Client|1                |Notifica al client che il server attende il messaggio                           |
|msg                    |Client -> Server|Messaggio        |Messaggio da inviare                                                            |

Esempio della comunicazione tra client-server<br>
"key":"value" -> "Hi":"Mario"