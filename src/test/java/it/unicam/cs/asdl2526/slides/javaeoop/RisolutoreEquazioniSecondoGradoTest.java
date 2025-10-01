package it.unicam.cs.asdl2526.slides.javaeoop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Classe di test JUnit 5 per il risolutore "singoletto" di equazioni di secondo
 * grado.
 * 
 * @author Luca Tesei
 *
 */
class RisolutoreEquazioniSecondoGradoTest {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    static final double EPSILON = 1.0E-15;

    @Test
    final void testSolve() {
        // creo l'oggetto risolutore che mi servirà per tutti i test
        RisolutoreEquazioniSecondoGrado r = new RisolutoreEquazioniSecondoGrado();
        // equazione senza soluzioni reali
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        SoluzioneEquazioneSecondoGrado s1 = r.solve(e1);
        assertTrue(s1.isEmptySolution());
        // equazione con due soluzioni reali distinte
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, -3, 2);
        SoluzioneEquazioneSecondoGrado s2 = r.solve(e2);
        assertFalse(s2.isEmptySolution());
        assertFalse(s2.isOneSolution());
        // non so l'ordine in cui vengono date le soluzioni, quindi provo
        // entrambe le possibilità
        assertTrue(s2.getS1() == 1 || s2.getS1() == 2); // rischioso usare ==
        assertTrue(s2.getS2() == 1 || s2.getS2() == 2); // rischioso usare ==
        // per confrontare due numeri a virgola mobile è meglio usare una soglia
        // piccola EPSILON e controllare che la loro differenza in valore
        // assoluto è minore della soglia fissata, come segue
        assertTrue(Math.abs(s2.getS1() - 1) < EPSILON
                || Math.abs(s2.getS1() - 2) < EPSILON);
        assertTrue(Math.abs(s2.getS2() - 1) < EPSILON
                || Math.abs(s2.getS2() - 2) < EPSILON);
        assertFalse(Math.abs(s2.getS1() - s2.getS2()) < EPSILON);
        // equazione con due soluzioni reali coincidenti
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, -2, 1);
        SoluzioneEquazioneSecondoGrado s3 = r.solve(e3);
        assertFalse(s3.isEmptySolution());
        assertTrue(s3.isOneSolution());
        assertTrue(Math.abs(s3.getS1() - 1) < EPSILON);
        // equazione con due soluzioni reali con decimali e parametro a diverso
        // da 1
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
