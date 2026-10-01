package it.unicam.cs.asdl.slides.javaeoop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test dedicati al copy constructor di {@link Coefficienti} e alle copie
 * difensive usate da {@link EquazioneSecondoGradoConCoefficienti}.
 */
public class EquazioneSecondoGradoConCoefficientiTest {

    @Test
    public void testCopyConstructorCreaOggettoIndipendente() {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        Coefficienti copia = new Coefficienti(originali);

        // Il copy constructor crea un nuovo oggetto: i valori coincidono, ma i
        // riferimenti non indicano la stessa zona dello heap.
        assertNotSame(originali, copia);
        assertEquals(originali.getA(), copia.getA());
        assertEquals(originali.getB(), copia.getB());
        assertEquals(originali.getC(), copia.getC());

        // Dopo una modifica della copia, l'originale conserva il proprio stato.
        copia.setA(10.0);
        assertEquals(1.0, originali.getA());
    }

    @Test
    public void testCopyConstructorRifiutaNull() {
        // Non esiste uno stato da copiare se il riferimento ricevuto è null.
        assertThrows(NullPointerException.class,
                () -> new Coefficienti((Coefficienti) null));
    }

    @Test
    public void testCopiaDifensivaNelCostruttore() {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(originali);

        // Modificare l'oggetto passato al costruttore non deve modificare
        // l'equazione: nello stato interno è stato memorizzato un nuovo
        // oggetto creato tramite il copy constructor.
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
    public void testGetterRestituisceOggettoDistintoDaQuelloOriginale() {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(originali);

        Coefficienti restituiti = equazione.getCoefficienti();

        // I valori coincidono, ma i riferimenti devono indicare oggetti
        // distinti nello heap. assertNotSame verifica l'identità, non equals.
        assertNotSame(originali, restituiti);
        assertEquals(originali.getA(), restituiti.getA());
        assertEquals(originali.getB(), restituiti.getB());
        assertEquals(originali.getC(), restituiti.getC());
    }

    @Test
    public void testDueChiamateAlGetterRestituisconoCopieDistinte() {
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(
                        new Coefficienti(1.0, 2.0, 1.0));

        Coefficienti c1 = equazione.getCoefficienti();
        Coefficienti c2 = equazione.getCoefficienti();

        // Ogni chiamata usa nuovamente il copy constructor e crea quindi una
        // copia difensiva distinta.
        assertNotSame(c1, c2);
        assertEquals(c1.getA(), c2.getA());
        assertEquals(c1.getB(), c2.getB());
        assertEquals(c1.getC(), c2.getC());
    }
}
