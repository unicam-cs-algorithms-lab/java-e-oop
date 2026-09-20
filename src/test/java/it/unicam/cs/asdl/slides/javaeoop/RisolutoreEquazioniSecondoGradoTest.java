package it.unicam.cs.asdl.slides.javaeoop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test JUnit 5 per il risolutore senza stato delle equazioni di secondo grado.
 * Lo stesso oggetto viene riutilizzato in tutti gli scenari per mostrare che il
 * metodo {@code solve} dipende dall'equazione ricevuta come parametro e non da
 * uno stato conservato dal risolutore.
 *
 * @author Luca Tesei
 *
 */
class RisolutoreEquazioniSecondoGradoTest {
    /* Soglia usata nei confronti tra risultati in virgola mobile. */
    static final double EPSILON = 1.0E-15;

    @Test
    final void testSolve() {
        // Un solo risolutore senza stato è sufficiente per tutti gli scenari.
        RisolutoreEquazioniSecondoGrado r = new RisolutoreEquazioniSecondoGrado();
        // Scenario con discriminante negativo e nessuna soluzione reale.
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        SoluzioneEquazioneSecondoGrado s1 = r.solve(e1);
        assertTrue(s1.isEmptySolution());
        // Scenario con due soluzioni reali distinte.
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, -3, 2);
        SoluzioneEquazioneSecondoGrado s2 = r.solve(e2);
        assertFalse(s2.isEmptySolution());
        assertFalse(s2.isOneSolution());
        // L'API non assegna un significato particolare all'ordine delle soluzioni.
        assertTrue(s2.getS1() == 1 || s2.getS1() == 2); // rischioso usare ==
        assertTrue(s2.getS2() == 1 || s2.getS2() == 2); // rischioso usare ==
        /*
         * Per risultati ottenuti da calcoli in virgola mobile è più sicuro
         * controllare che la differenza assoluta sia minore di EPSILON.
         */
        assertTrue(Math.abs(s2.getS1() - 1) < EPSILON
                || Math.abs(s2.getS1() - 2) < EPSILON);
        assertTrue(Math.abs(s2.getS2() - 1) < EPSILON
                || Math.abs(s2.getS2() - 2) < EPSILON);
        assertFalse(Math.abs(s2.getS1() - s2.getS2()) < EPSILON);
        // Scenario limite con due soluzioni reali coincidenti.
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, -2, 1);
        SoluzioneEquazioneSecondoGrado s3 = r.solve(e3);
        assertFalse(s3.isEmptySolution());
        assertTrue(s3.isOneSolution());
        assertTrue(Math.abs(s3.getS1() - 1) < EPSILON);
        // Scenario con risultati decimali e coefficiente a diverso da uno.
        EquazioneSecondoGrado e4 = new EquazioneSecondoGrado(2, -10, 2);
        SoluzioneEquazioneSecondoGrado s4 = r.solve(e4);
        assertFalse(s4.isEmptySolution());
        assertFalse(s4.isOneSolution());
        assertTrue(Math.abs(s4.getS1() - 4.7912878474779195) < EPSILON
                || Math.abs(s4.getS1() - 0.20871215252208009) < EPSILON);
        assertTrue(Math.abs(s4.getS2() - 4.7912878474779195) < EPSILON
                || Math.abs(s4.getS2() - 0.20871215252208009) < EPSILON);

    }

}
