/**
 * 
 */
package it.unicam.cs.asdl2526.slides.javaeoop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Classe di test JUnit 5 per le equazioni di secondo grado.
 * 
 * @author Luca Tesei
 *
 */
class EquazioneSecondoGradoTest {

    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    @Test
    public void testCostruttore() {
        EquazioneSecondoGrado eq = new EquazioneSecondoGrado(1, 2, 1);
        RisolutoreEquazioneDiSecondoGrado risolutore = new RisolutoreEquazioneDiSecondoGrado(
                eq);
        assertEquals(eq, risolutore.getE());
    }

    @Test
    public void testSoluzioneCoincidente() {
        EquazioneSecondoGrado eq = new EquazioneSecondoGrado(1, -2, 1);
        RisolutoreEquazioneDiSecondoGrado risolutore = new RisolutoreEquazioneDiSecondoGrado(
                eq);
        SoluzioneEquazioneSecondoGrado sol = risolutore.getSolution();
        assertTrue(sol.isOneSolution());
        assertEquals(1, sol.getS1());
    }

    @Test
    public void testSoluzioneVuota() {
        EquazioneSecondoGrado eq = new EquazioneSecondoGrado(1, 0, 1);
        RisolutoreEquazioneDiSecondoGrado risolutore = new RisolutoreEquazioneDiSecondoGrado(
                eq);
        SoluzioneEquazioneSecondoGrado sol = risolutore.getSolution();
        assertTrue(sol.isEmptySolution());
    }

    @Test
    final void testEquazioneSecondoGradoEccezione() {
        // controllo del lancio dell'eccezione
        assertThrows(IllegalArgumentException.class,
                () -> new EquazioneSecondoGrado(0, 1, 1));
        // costruzione senza eccezione
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
    }

    @Test
    final void testEquazioneSecondoGradoGet() {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        assertTrue(Math.abs(e1.getA() - 1.0) < EPSILON);
        /*
         * Può funzionare anche == perché i valori inseriti non sono il
         * risultato di un calcolo
         */
        assertTrue(e1.getB() == 1.0);
        assertTrue(e1.getC() == 1.0);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    final void testEqualsObject() {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, 2, 1);
        // controllo l'uguaglianza in entrambe le direzioni
        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
        // controllo la disuguaglianza in entrambe le direzioni
        assertFalse(e1.equals(e3));
        assertFalse(e3.equals(e2));
        // controllo la disuguaglianza con altre classi e null
        assertFalse(e1.equals(null));
        assertFalse(e1.equals("Pippo"));
    }

    @Test
    final void testCompareTo() {
        EquazioneSecondoGrado e1 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e2 = new EquazioneSecondoGrado(1, 1, 1);
        EquazioneSecondoGrado e3 = new EquazioneSecondoGrado(1, 2, 1);
        EquazioneSecondoGrado e4 = new EquazioneSecondoGrado(1, 2, 2);
        EquazioneSecondoGrado e5 = new EquazioneSecondoGrado(2, 1, 1);
        assertTrue(e1.compareTo(e3) < 0); // e1 < e3
        assertTrue(e3.compareTo(e1) > 0); // e3 > e1
        assertTrue(e1.compareTo(e2) == 0); // e1 == e2
        assertTrue(e3.compareTo(e4) < 0); // e3 < e4
        assertTrue(e4.compareTo(e3) > 0); // e4 > e3
        assertTrue(e5.compareTo(e4) > 0); // e5 > e4
        assertTrue(e4.compareTo(e5) < 0); // e4 < e5
    }

}
