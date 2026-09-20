package it.unicam.cs.asdl.slides.javaeoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Front-end testuale per usare le classi della logica applicativa che
 * rappresentano e risolvono le equazioni di secondo grado.
 * <p>
 * Questa classe gestisce l'interazione con l'utente attraverso lo standard
 * input e lo standard output. Dopo aver controllato e convertito i dati,
 * invoca le API pubbliche del nucleo applicativo. La stessa logica viene usata
 * anche dal front-end grafico, che adotta una modalità di interazione diversa.
 *
 * @author Luca Tesei
 */
public class EquazioniTextualFrontEnd {
    /* Soglia usata dal front-end per rifiutare un coefficiente a nullo. */
    private static final double EPSILON = 1.0E-15;

    /**
     * Avvia il ciclo di interazione testuale.
     *
     * @param args argomenti della riga di comando, non utilizzati
     */
    public static void main(String[] args) {
        // Il risolutore non conserva stato e può essere riutilizzato nel ciclo.
        RisolutoreEquazioniSecondoGrado solver = new RisolutoreEquazioniSecondoGrado();
        boolean terminate = false;
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));
        Double a = null;
        Double b = null;
        Double c = null;
        while (!terminate) {
            // Legge il parametro a e ripete la richiesta finché non è valido.
            boolean retry = false;
            do {
                // Ogni iterazione riparte assumendo che il nuovo input sia valido.
                retry = false;
                System.out.println(
                        "Inserisci il valore del parametro a e premi INVIO");
                try {
                    String aInput = input.readLine();
                    a = Double.parseDouble(aInput);
                    // Un'equazione di secondo grado richiede a diverso da zero.
                    if (Math.abs(a.doubleValue()) < EPSILON) {
                        System.out.println(
                                "Errore: Il valore del parametro a non può essere zero! Ritenta...");
                        retry = true;
                    }
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro a deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);
            // Legge il parametro b; a questo punto retry vale false.
            do {
                // Ogni iterazione riparte assumendo che il nuovo input sia valido.
                retry = false;
                System.out.println(
                        "Inserisci il valore del parametro b e premi INVIO");
                try {
                    String aInput = input.readLine();
                    b = Double.parseDouble(aInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro b deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);
            // Legge il parametro c; a questo punto retry vale false.
            do {
                // Ogni iterazione riparte assumendo che il nuovo input sia valido.
                retry = false;
                System.out.println(
                        "Inserisci il valore del parametro c e premi INVIO");
                try {
                    String aInput = input.readLine();
                    c = Double.parseDouble(aInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro c deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);
            /*
             * new crea l'equazione nello heap; eq contiene il riferimento che
             * viene poi passato al metodo solve.
             */
            EquazioneSecondoGrado eq = new EquazioneSecondoGrado(
                    a.doubleValue(), b.doubleValue(), c.doubleValue());
            SoluzioneEquazioneSecondoGrado sol = solver.solve(eq);
            // La stampa usa implicitamente il metodo toString della soluzione.
            System.out.println(sol);
            // Il front-end decide se avviare una nuova iterazione.
            System.out.println(
                    "Vuoi risolvere un'altra equazione? Inserisci 's' o 'S' per continuare, qualsiasi altro carattere per uscire e premi INVIO");
            String resp = null;
            try {
                String aInput = input.readLine();
                resp = aInput.trim().toUpperCase();
            } catch (IOException e) {
                System.err.println("Errore di Input/Output!");
                System.exit(1);
            }
            if (!resp.equals("S"))
                terminate = true;
        }
    }

}
