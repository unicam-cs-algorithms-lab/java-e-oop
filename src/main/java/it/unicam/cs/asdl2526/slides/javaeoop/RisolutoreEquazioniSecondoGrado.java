package it.unicam.cs.asdl2526.slides.javaeoop;

/**
 * Un oggetto di questa classe è un attore che risolve equazioni di secondo
 * grado cercando soluzioni reali. Questo è un tipico esempio di classe
 * "singoletto". E' sufficiente creare un solo oggetto della classe, che viene
 * riutilizzato più volte. L'oggetto non ha stato e i suoi input vengono passati
 * tramite i parametri dei metodi. L'output viene dato come valore di ritorno
 * dei metodi.
 * 
 * @author Luca Tesei
 *
 */
public class RisolutoreEquazioniSecondoGrado {

    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    /**
     * Risolve una certa equazione di secondo grado.
     * 
     * @param e
     *              una equazione di secondo grado
     * @return un oggetto SoluzioneEquazioneSecondoGrado che contiene le
     *         soluzioni calcolate
     * @throws NullPointerException
     *                                  se l'equazione passata è null
     */
    public SoluzioneEquazioneSecondoGrado solve(EquazioneSecondoGrado e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di risolvere una equazione nulla");
        double delta = e.getB() * e.getB() - 4 * e.getA() * e.getC();
        // delta == 0
        if (Math.abs(delta) < EPSILON)
            // ritorna la soluzione con due valori coincidenti
            return new SoluzioneEquazioneSecondoGrado(e,
                    (-e.getB()) / (2 * e.getA()));
        // delta < 0
        if (delta < 0)
            // ritorna la soluzione vuota
            return new SoluzioneEquazioneSecondoGrado(e);
        // delta > 0
        double tmp = Math.sqrt(delta);
        // ritorna le due soluzioni calcolate
        return new SoluzioneEquazioneSecondoGrado(e,
                (-e.getB() + tmp) / (2 * e.getA()),
                (-e.getB() - tmp) / (2 * e.getA()));
    }
}
