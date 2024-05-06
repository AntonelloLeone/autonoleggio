# autonoleggio
![pic](https://github.com/AntonelloLeone/autonoleggio/blob/main/Cover_prjJava.webp)
![pic](https://github.com/AntonelloLeone/autonoleggio/blob/main/back.PNG)
![pic](https://github.com/AntonelloLeone/autonoleggio/blob/main/menuA.PNG)

FILE jar testato e funzionante, leggere sotto!

Partecipanti: Antonio Carosi, Luca Veroli, Antonello Leone.
Preliminarmente abbiamo sviluppato la struttura, ed in seguito diviso tutti i blocchi più
elementari tra noi, così da limitare i conflitti eventuali.
Il codice è estendibile e divide quanto più possibile l'interazione tra moduli, grazie anche all'utilizzo dei pattern DAO,Factory, MVC(sempiicato)


##Nota importante: nella cartella DB_MOCK ci sono i file txt che rendono piu veloce i test!
Questi vanno copiati nella cartella in cui si trova il jar per avere almeno la possibilità di accesso per admin/batman!
Di seguito le credenziali per gli accessi, se importati i file in DB_MOCK, per eseguire i test!
##        USERNAME - password
MANAGER) ADMIN123 - ADMIN12%
MANAGER) BATROOMS - BAT9999!
USER)    sararosi - sararo6%
USER)    aleviola - alevio7%

## TEST - RICHIESTE
RICHIESTA ok - GESTIONE DEI LOG IN PER UTENTI DI TIPO DIVERSO ED DUE SPECIALI(BATMAN ED ADMIN, UTILE PER LO START DEL CODICE)
RICHIESTA ok - DELETE AUTOMOBILE DISPONIBILE E NON OCCUPATA
RICHIESTA ok - DELETE OCCUPATA NON POSSIBILE
RICHIESTA ok - noleggia automonile (solo se disponibile e se cliente ha patente)
RICHIESTA ok - consegna automobile ( registra durante il log in il cliente e se possibile può consegnare la sua auto, scoprendo il conto da pagare)

RICHIESTA OK- findByName, implementato su tipo car (estendibile per inizia per..)
RICHIESTA OK- findByPrice ( estendibile per range di prezzo)

RICHIESTA ok - view batmobili
RICHIESTA ok - add batmobili

RICHIESTA OK - MENU BATMAN
RICHIESTA OK - MENU MANAGER
RICHIESTA ok - MENU CLIENTE, registra il cliente che si logga per la "sessione"


