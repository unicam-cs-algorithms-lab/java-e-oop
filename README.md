# Java e programmazione orientata agli oggetti

Questo progetto accompagna le slide **Java e Object-Oriented Programming: richiami e concetti di base** del modulo di Laboratorio di Algoritmi e Strutture Dati.

L'esempio principale sviluppa una piccola applicazione per rappresentare e risolvere equazioni di secondo grado con soluzioni reali. Il codice non serve soltanto a mostrare la formula risolutiva: mette in evidenza come organizzare un programma Java secondo i principi della programmazione orientata agli oggetti.

Alcune classi aggiuntive riprendono lo stesso dominio delle equazioni di secondo grado per approfondire il problema della condivisione di riferimenti a oggetti mutabili e introdurre il concetto di **copia difensiva**.

## Obiettivi didattici

Nel progetto si possono riconoscere i concetti introdotti nelle slide:

- classi e oggetti come rappresentazioni di entita' del dominio;
- stato degli oggetti, incapsulamento e immutabilita';
- metodi pubblici come API della classe;
- separazione tra logica applicativa e front-end;
- uso di parametri, valori di ritorno ed eccezioni per comunicare con la logica applicativa;
- documentazione delle API con Javadoc;
- test manuali e test automatici con JUnit 5;
- riferimenti a oggetti, stack delle attivazioni e heap;
- condivisione di riferimenti a oggetti mutabili e copie difensive;
- uso di un **copy constructor** per creare un nuovo oggetto con lo stesso stato di un altro oggetto;
- uguaglianza logica mediante `equals` e `hashCode`;
- ordinamento naturale mediante `Comparable` e `compareTo`;
- rappresentazione testuale degli oggetti mediante `toString`.

Questi concetti preparano all'uso corretto delle interfacce e delle classi del Java Collections Framework, che si basano anche sui contratti di uguaglianza, hashing e ordinamento degli oggetti.

## Struttura del progetto

Il package `it.unicam.cs.asdl.slides.javaeoop` contiene le classi della logica applicativa, alcuni front-end e alcuni esempi dedicati ai concetti discussi nelle slide.

### Logica applicativa

- `EquazioneSecondoGrado` rappresenta un'equazione tramite i coefficienti `a`, `b` e `c`. La classe e' immutabile, ridefinisce `equals`, `hashCode` e `toString` e implementa `Comparable<EquazioneSecondoGrado>`.
- `SoluzioneEquazioneSecondoGrado` rappresenta una soluzione vuota, una soluzione doppia oppure due soluzioni distinte. Anche questa classe e' immutabile.
- `RisolutoreEquazioneDiSecondoGrado` associa un risolutore a una sola equazione, memorizzata nel suo stato.
- `RisolutoreEquazioniSecondoGrado` non conserva lo stato di una particolare equazione. Lo stesso oggetto puo' quindi risolvere piu' equazioni ricevute come argomento del metodo `solve`.

Le classi della logica applicativa non leggono dati dall'utente e non stampano risultati. Ricevono gli input attraverso costruttori o parametri, restituiscono oggetti e segnalano gli usi non validi mediante eccezioni.

### Copie difensive e copy constructor

Le classi `Coefficienti` ed `EquazioneSecondoGradoConCoefficienti` costituiscono un esempio separato, sempre basato sul dominio delle equazioni di secondo grado, utilizzato per mostrare un problema che si presenta quando lo stato di un oggetto contiene riferimenti ad altri oggetti mutabili.

- `Coefficienti` rappresenta i tre coefficienti `a`, `b` e `c` mediante un oggetto mutabile.
- `Coefficienti` mette a disposizione anche un **copy constructor**, cioe' un costruttore che riceve un altro oggetto `Coefficienti` e crea un nuovo oggetto con gli stessi valori.
- `EquazioneSecondoGradoConCoefficienti` memorizza nel proprio stato un oggetto `Coefficienti`, ma protegge il proprio stato mediante **copie difensive**.

Il copy constructor di `Coefficienti` ha la forma:

```java
public Coefficienti(Coefficienti other) {
    this.a = other.a;
    this.b = other.b;
    this.c = other.c;
}
```

Il nuovo oggetto contiene gli stessi valori di `other`, ma e' un oggetto distinto nello heap. Le due istanze possono quindi essere modificate indipendentemente.

Se il costruttore di `EquazioneSecondoGradoConCoefficienti` memorizzasse direttamente il riferimento ricevuto:

```java
this.coefficienti = coefficienti;
```

il chiamante conserverebbe un riferimento allo stesso oggetto e potrebbe quindi modificare indirettamente lo stato interno dell'equazione.

Per evitare questa condivisione, il costruttore crea una copia:

```java
this.coefficienti = new Coefficienti(coefficienti);
```

Lo stesso problema si presenta nella direzione opposta. Un getter che restituisse direttamente:

```java
return this.coefficienti;
```

renderebbe accessibile dall'esterno l'oggetto mutabile che costituisce lo stato interno dell'equazione. Anche il getter restituisce quindi una copia:

```java
return new Coefficienti(this.coefficienti);
```

In questo modo l'oggetto ricevuto dal costruttore, quello memorizzato internamente e quelli restituiti dai getter possono contenere gli stessi valori, ma sono **oggetti distinti nell'heap**.

La classe `TestCopieDifensive` contiene un metodo `main` che permette di osservare direttamente questo comportamento: modificare l'oggetto `Coefficienti` originale oppure un oggetto restituito dal getter non modifica i coefficienti memorizzati nell'equazione.

La classe `EquazioneSecondoGradoConCoefficientiTest`, nella cartella `src/test`, verifica lo stesso comportamento mediante test JUnit 5. I test mostrano anche l'uso di `assertNotSame` per distinguere l'identita' degli oggetti dall'uguaglianza dei dati e verificano direttamente il comportamento del copy constructor.

Il punto centrale dell'esempio non e' un particolare meccanismo di clonazione, ma il principio secondo cui la condivisione di riferimenti a oggetti mutabili puo' compromettere l'incapsulamento. Il copy constructor rende esplicita la creazione di una nuova istanza e permette di realizzare in modo semplice le copie difensive necessarie in questo esempio.

### Front-end e test

- `EquazioniTextualFrontEnd` gestisce l'interazione tramite input e output testuale.
- `EquazioniGUIFrontEnd` offre una semplice interfaccia grafica Swing e usa la stessa logica applicativa del front-end testuale.
- `EquazioniTestAMano` mostra un controllo eseguito da un metodo `main`, con esito comunicato sullo standard output.
- `TestCopieDifensive` permette di osservare tramite un metodo `main` il comportamento delle copie difensive.
- `EquazioneSecondoGradoTest`, `RisolutoreEquazioniSecondoGradoTest` ed `EquazioneSecondoGradoConCoefficientiTest`, nella cartella `src/test`, mostrano test automatici con JUnit 5.

La presenza di due front-end rende visibile la separazione tra presentazione e logica applicativa: l'interazione con l'utente puo' cambiare senza modificare le classi che rappresentano e risolvono le equazioni.

## Stato, riferimenti e memoria

Le variabili dichiarate dentro un metodo sono variabili locali e appartengono alla relativa attivazione sullo stack del thread. Le espressioni con `new` creano oggetti nell'heap; le variabili di tipo classe contengono riferimenti a tali oggetti.

Per esempio, in un front-end la variabile locale `eq` contiene il riferimento a un oggetto `EquazioneSecondoGrado`. Quando `eq` viene passato a `solve`, il metodo riceve una copia dello stesso riferimento: non viene creata automaticamente una copia dell'equazione.

Analogamente, un'assegnazione come:

```java
Coefficienti c2 = c1;
```

non crea un secondo oggetto `Coefficienti`: `c1` e `c2` contengono due copie dello stesso riferimento e indicano quindi lo stesso oggetto nell'heap. Una modifica effettuata attraverso uno dei due riferimenti e' osservabile anche attraverso l'altro.

Questa proprieta' e' particolarmente importante per l'incapsulamento. Dichiarare un campo `private` impedisce di accedere direttamente alla variabile istanza, ma puo' non essere sufficiente se attraverso costruttori o getter vengono condivisi riferimenti agli oggetti mutabili che costituiscono lo stato interno.

Le copie difensive mostrate da `EquazioneSecondoGradoConCoefficienti` impediscono questa condivisione. Il copy constructor di `Coefficienti` rende esplicita la creazione di un nuovo oggetto con lo stesso stato dell'oggetto ricevuto.

I coefficienti della classe originale `EquazioneSecondoGrado` sono invece valori primitivi `double`, dichiarati `private` e `final`; tutti i campi di `SoluzioneEquazioneSecondoGrado` sono `final` e nessuno fa parte dell'API pubblica. Dopo il costruttore lo stato non cambia e i metodi pubblici consentono di osservarlo senza renderlo modificabile.

## Uguaglianza, hashing e ordinamento

`EquazioneSecondoGrado.equals` definisce l'uguaglianza logica in base ai tre coefficienti. `hashCode` usa gli stessi dati, come richiede il contratto generale: oggetti uguali devono produrre lo stesso codice hash. Oggetti diversi possono invece produrre lo stesso codice hash.

Questo requisito diventa essenziale quando gli oggetti vengono inseriti in collezioni basate su hashing, come `HashSet` e `HashMap`.

L'ordinamento naturale confronta prima `a`, poi `b` e infine `c`. Il valore restituito da `compareTo` indica soltanto se l'oggetto corrente precede, coincide o segue l'altro oggetto: non deve essere interpretato come una distanza.

Il confronto dei coefficienti usa `Double.compare`. In questo modo l'ordinamento rimane coerente con il criterio di uguaglianza adottato dalla classe anche per valori particolari di tipo `double`, come `0.0`, `-0.0` e `NaN`. In particolare:

```java
e1.compareTo(e2) == 0
```

se e solo se:

```java
e1.equals(e2)
```

Questa compatibilita' e' importante quando gli oggetti vengono utilizzati in collezioni ordinate come `TreeSet` e `TreeMap`.

## Rappresentazione testuale

I metodi `toString` costruiscono e restituiscono una stringa comprensibile. Non stampano direttamente sullo standard output. Sono i front-end o i test a decidere se e dove mostrare quella stringa.

Il codice usa `StringBuffer`, disponibile nelle versioni storiche di Java considerate nel corso, per accumulare le parti del risultato senza creare esplicitamente molte stringhe intermedie.

## Esecuzione e test

Il progetto usa Maven:

```text
mvn test
```

Per avviare un front-end si puo' eseguire il relativo metodo `main` dall'IDE.

Per osservare direttamente il comportamento delle copie difensive si puo' eseguire anche il metodo `main` della classe:

```text
TestCopieDifensive
```

Il codice della logica applicativa e dei front-end usa volutamente costrutti di base. Il progetto Maven richiede tuttavia Java 8 perche' JUnit 5 e il test di un'eccezione tramite espressione lambda non sono disponibili in Java 5. Questa scelta riguarda l'infrastruttura di test, non i concetti di programmazione mostrati dalle classi principali.

## Generazione della Javadoc

Dalla radice del progetto si puo' generare la documentazione delle classi della logica applicativa con un comando analogo al seguente:

```text
javadoc -d docs -sourcepath src/main/java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/EquazioneSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/SoluzioneEquazioneSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/RisolutoreEquazioneDiSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/RisolutoreEquazioniSecondoGrado.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/Coefficienti.java \
  src/main/java/it/unicam/cs/asdl/slides/javaeoop/EquazioneSecondoGradoConCoefficienti.java
```

La Javadoc descrive il contratto pubblico delle classi: significato dei parametri, valore restituito, eccezioni e condizioni d'uso. I dettagli interni dell'algoritmo restano invece nei commenti ordinari, vicino al codice che spiegano.
