package it.unicam.cs.asdl.slides.javaeoop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

/**
 * Test dedicati alle copie difensive di
 * {@link EquazioneSecondoGradoConCoefficienti}.
 */
public class EquazioneSecondoGradoConCoefficientiTest {

    @Test
    public void testCopiaDifensivaNelCostruttore() {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(originali);

        // Modificare l'oggetto passato al costruttore non deve modificare
        // l'equazione: nello stato interno e' stata memorizzata una copia.
        originali.setA(10.0);

        assertEquals(1.0, equazione.getCoefficienti().getA());
    }

    @Test
    public void testCopiaDifensivaNelGetter() {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(originali);

        Coefficienti restituiti = equazione.getCoefficienti();

        // Modificare il risultato del getter non deve modificare l'equazione:
        // il getter restituisce una copia, non il riferimento interno.
        restituiti.setB(20.0);

        assertEquals(2.0, equazione.getCoefficienti().getB());
    }

    @Test
    public void testGetterRestituisceOggettiDistinti() {
        Coefficienti primaCopia = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(primaCopia);

        Coefficienti secondaCopia = equazione.getCoefficienti();

        // I valori coincidono, ma i riferimenti devono indicare oggetti
        // distinti nello heap. assertNotSame verifica l'identita', non equals.
        assertNotSame(primaCopia, secondaCopia);
        assertEquals(primaCopia.getA(), secondaCopia.getA());
        assertEquals(primaCopia.getB(), secondaCopia.getB());
        assertEquals(primaCopia.getC(), secondaCopia.getC());
    }

    @Test
    public void testDueChiamateAlGetterRestituisconoCopieDistinte() {
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(
                        new Coefficienti(1.0, 2.0, 1.0));

        Coefficienti c1 = equazione.getCoefficienti();
        Coefficienti c2 = equazione.getCoefficienti();

        // Ogni chiamata crea una nuova copia difensiva.
        assertNotSame(c1, c2);
        assertEquals(c1.getA(), c2.getA());
        assertEquals(c1.getB(), c2.getB());
        assertEquals(c1.getC(), c2.getC());
    }
}
