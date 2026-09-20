package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Esegue alcuni test manuali attraverso un metodo {@code main}.
 * <p>
 * A differenza di un test JUnit, questa classe comunica gli esiti sullo
 * standard output e, in alcuni casi, richiede al programmatore di controllare
 * visivamente il risultato. L'esempio mostra perché questo approccio diventa
 * poco pratico quando aumentano classi e casi da verificare.
 *
 * @author Luca Tesei
 */
public class EquazioniTestAMano {

    /**
     * Esegue in sequenza gli scenari di prova.
     *
     * @param args argomenti della riga di comando, non utilizzati
     */
    public static void main(String[] args) {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        // Verifica manuale di toString: l'utente deve leggere l'output.
        System.out.println("Equazione 1: " + e1);
        RisolutoreEquazioniSecondoGrado r = new RisolutoreEquazioniSecondoGrado();
        SoluzioneEquazioneSecondoGrado se1 = r.solve(e1);
        // Questo controllo produce automaticamente un semplice esito testuale.
        System.out.println(se1);
        if (se1.isEmptySolution())
            System.out.println("Test Risultato 1 OK");
        else
            System.out.println("Test Risultato 1 Non OK");
        // Prepara un secondo scenario di prova.
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, -2);
        System.out.println("Equazione 2: " + e2);
        // Verifica il segno restituito da compareTo, non un valore preciso.
        if (e1.compareTo(e2) > 0)
            System.out.println("Test CompareTo OK");
        else
            System.out.println("Test CompareTo Non OK");
        SoluzioneEquazioneSecondoGrado se2 = r.solve(e2);
        // La correttezza delle soluzioni richiede un controllo visivo dell'utente.
        System.out.println(se2);
    }

}
