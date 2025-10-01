package it.unicam.cs.asdl2526.slides.javaeoop;

/**
 * Classe che implementa dei test tramite un metodo main che usa alcune
 * funzionalità delle classi relative alle equazioni di secondo grado e comunica
 * i risultati del test come testo sullo standard output.
 * 
 * @author Luca Tesei
 */
public class EquazioniTestAMano {

    public static void main(String[] args) {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        // Test toString()
        System.out.println("Equazione 1: " + e1);
        RisolutoreEquazioniSecondoGrado r = new RisolutoreEquazioniSecondoGrado();
        SoluzioneEquazioneSecondoGrado se1 = r.solve(e1);
        // Test risultato 1
        System.out.println(se1);
        if (se1.isEmptySolution())
            System.out.println("Test Risultato 1 OK");
        else
            System.out.println("Test Risultato 1 Non OK");
        // Test risultato 2 che richiede il controllo interattivo dell'utente
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, -2);
        System.out.println("Equazione 2: " + e2);
        // Test CompareTo
        if (e1.compareTo(e2) > 0)
            System.out.println("Test CompareTo OK");
        else
            System.out.println("Test CompareTo Non OK");
        SoluzioneEquazioneSecondoGrado se2 = r.solve(e2);
        // Test risultato 3 che richiede il controllo interattivo dell'utente
        System.out.println(se2);
    }

}
