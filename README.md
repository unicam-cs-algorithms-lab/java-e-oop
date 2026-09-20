# Java e programmazione orientata agli oggetti

Questo progetto accompagna le slide **Java e Object-Oriented Programming: richiami e concetti di base** del modulo di Laboratorio di Algoritmi e Strutture Dati.

L'esempio sviluppa una piccola applicazione per rappresentare e risolvere equazioni di secondo grado con soluzioni reali. Il codice non serve soltanto a mostrare la formula risolutiva: mette in evidenza come organizzare un programma Java secondo i principi della programmazione orientata agli oggetti.

## Obiettivi didattici

Nel progetto si possono riconoscere i concetti introdotti nelle slide:

- classi e oggetti come rappresentazioni di entità del dominio;
- stato degli oggetti, incapsulamento e immutabilità;
- metodi pubblici come API della classe;
- separazione tra logica applicativa e front-end;
- uso di parametri, valori di ritorno ed eccezioni per comunicare con la logica applicativa;
- documentazione delle API con Javadoc;
- test manuali e test automatici con JUnit 5;
- riferimenti a oggetti, stack delle attivazioni e heap;
- uguaglianza logica mediante `equals` e `hashCode`;
- ordinamento naturale mediante `Comparable` e `compareTo`;
- rappresentazione testuale degli oggetti mediante `toString`.

Questi concetti preparano all'uso corretto delle interfacce e delle classi del Java Collections Framework, che si basano sui contratti di uguaglianza, hashing e ordinamento degli oggetti.

## Struttura del progetto

Il package `it.unicam.cs.asdl2526.slides.javaeoop` contiene due gruppi di classi.

### Logica applicativa

- `EquazioneSecondoGrado` rappresenta un'equazione tramite i coefficienti `a`, `b` e `c`. La classe è immutabile, ridefinisce `equals`, `hashCode` e `toString` e implementa `Comparable<EquazioneSecondoGrado>`.
- `SoluzioneEquazioneSecondoGrado` rappresenta una soluzione vuota, una soluzione doppia oppure due soluzioni distinte. Anche questa classe è immutabile.
- `RisolutoreEquazioneDiSecondoGrado` associa un risolutore a una sola equazione, memorizzata nel suo stato.
- `RisolutoreEquazioniSecondoGrado` non conserva lo stato di una particolare equazione. Lo stesso oggetto può quindi risolvere più equazioni ricevute come argomento del metodo `solve`.

Le classi della logica applicativa non leggono dati dall'utente e non stampano risultati. Ricevono gli input attraverso costruttori o parametri, restituiscono oggetti e segnalano gli usi non validi mediante eccezioni.

### Front-end e test

- `EquazioniTextualFrontEnd` gestisce l'interazione tramite input e output testuale.
- `EquazioniGUIFrontEnd` offre una semplice interfaccia grafica Swing e usa la stessa logica applicativa del front-end testuale.
- `EquazioniTestAMano` mostra un controllo eseguito da un metodo `main`, con esito comunicato sullo standard output.
- `EquazioneSecondoGradoTest` e `RisolutoreEquazioniSecondoGradoTest`, nella cartella `src/test`, mostrano test automatici con JUnit 5.

La presenza di due front-end rende visibile la separazione tra presentazione e logica applicativa: l'interazione con l'utente può cambiare senza modificare le classi che rappresentano e risolvono le equazioni.

## Stato, riferimenti e memoria

Le variabili dichiarate dentro un metodo sono variabili locali e appartengono alla relativa attivazione sullo stack del thread. Le espressioni con `new` creano oggetti nell'heap; le variabili di tipo classe contengono riferimenti a tali oggetti.

Per esempio, in un front-end la variabile locale `eq` contiene il riferimento a un oggetto `EquazioneSecondoGrado`. Quando `eq` viene passato a `solve`, il metodo riceve una copia dello stesso riferimento: non viene creata automaticamente una copia dell'equazione.

I coefficienti di `EquazioneSecondoGrado` sono `private` e `final`; tutti i campi di `SoluzioneEquazioneSecondoGrado` sono `final` e nessuno fa parte dell'API pubblica. Dopo il costruttore lo stato non cambia; i metodi pubblici consentono di osservarlo senza renderlo modificabile.

## Uguaglianza, hashing e ordinamento

`EquazioneSecondoGrado.equals` definisce l'uguaglianza logica in base ai tre coefficienti. `hashCode` usa gli stessi dati, come richiede il contratto generale: oggetti uguali devono produrre lo stesso codice hash. Questo requisito diventa essenziale quando gli oggetti vengono inseriti in collezioni basate su hashing, come `HashSet` e `HashMap`.

L'ordinamento naturale confronta prima `a`, poi `b` e infine `c`. Il valore restituito da `compareTo` indica soltanto se l'oggetto corrente precede, coincide o segue l'altro oggetto: non deve essere interpretato come una distanza.

Nell'esempio si assume che i coefficienti siano numeri finiti e che per `b` e `c` non si distinguano `0.0` e `-0.0`. Con valori `NaN`, infiniti o zeri con segno diverso, l'implementazione didattica di `compareTo` non garantisce la piena compatibilità con `equals`. Questa precondizione è importante quando si usano collezioni ordinate come `TreeSet` e `TreeMap`.

## Rappresentazione testuale

I metodi `toString` costruiscono e restituiscono una stringa comprensibile. Non stampano direttamente sullo standard output. Sono i front-end o i test a decidere se e dove mostrare quella stringa.

Il codice usa `StringBuffer`, disponibile nelle versioni storiche di Java considerate nel corso, per accumulare le parti del risultato senza creare esplicitamente molte stringhe intermedie.

## Esecuzione e test

Il progetto usa Maven:

```text
mvn test
```

Per avviare un front-end si può eseguire il relativo metodo `main` dall'IDE.

Il codice della logica applicativa e dei front-end usa volutamente costrutti di base. Il progetto Maven richiede tuttavia Java 8 perché JUnit 5 e il test di un'eccezione tramite espressione lambda non sono disponibili in Java 5. Questa scelta riguarda l'infrastruttura di test, non i concetti di programmazione mostrati dalle classi principali.

## Generazione della Javadoc

Dalla radice del progetto si può generare la documentazione delle classi della logica applicativa con un comando analogo al seguente:

```text
javadoc -d docs -sourcepath src/main/java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/EquazioneSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/SoluzioneEquazioneSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/RisolutoreEquazioneDiSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/RisolutoreEquazioniSecondoGrado.java
```

La Javadoc descrive il contratto pubblico delle classi: significato dei parametri, valore restituito, eccezioni e condizioni d'uso. I dettagli interni dell'algoritmo restano invece nei commenti ordinari, vicino al codice che spiegano.
