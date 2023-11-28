##### Server: [TPSIT-Chat-Server](https://github.com/MatteoFrosinii/ChatServer)

# ⚠️Aggiunta l'interfaccia grafica

## Protocollo Utilizzato
|Codice                 |Direzione       |Contenuto Data     |Azione                                                                          |
|-----------------------|----------------|-------------------|--------------------------------------------------------------------------------|
|hello                  |Client -> Server|Nome utente        |Invio del nome utente dopo il login                                             |
|bye                    |Client -> Server|1                  |Notifica al Server l'intenzione di chiudere la connessione                      |
|usersListRequest       |Client -> Server|1                  |Richiedi la lista di tutti gli utenti online                                    |
|usersList              |Server -> Client|Nomi utenti        |Risposta del server con la lista degli utenti online es: "Mario;Luigi;Paolo"    |
|switchBroadcast        |Client -> Server|1                  |Notifica al Server l'intenzione di inviare un messaggio in broadcast            |
|switchToUser           |Client -> Server|Nome destinatario  |Invia il nome del destinatario                                                  |
|msgRequest             |Server -> Client|1                  |Notifica al client che il server attende il messaggio                           |
|msg                    |Client -> Server|Messaggio          |Messaggio da inviare                                                            |
|msg                    |Server -> Client|Messaggio          |Messaggio da ricevere                                                           |
|msgRecivedBroadcast    |Server -> Client|Messaggio broadcast|Messaggio in broadcast inviato dal Server a tutti i client                      |

Esempio della comunicazione tra client-server<br>
"key":"value" -> "Hi":"Mario"
