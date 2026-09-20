package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Risolve equazioni di secondo grado cercandone le soluzioni reali.
 * <p>
 * Gli oggetti di questa classe non conservano lo stato di una particolare
 * equazione: ogni input arriva come parametro di {@link #solve} e ogni output
 * viene restituito come valore di ritorno. Per questo motivo lo stesso oggetto
 * risolutore può essere riutilizzato per più equazioni, come fanno i due
 * front-end del progetto.
 * <p>
 * La classe appartiene alla logica applicativa e non dipende da una particolare
 * interfaccia utente. Non è un'implementazione del pattern Singleton: il codice
 * può creare più istanze, anche se una sola è sufficiente.
 *
 * @author Luca Tesei
 *
 */
public class RisolutoreEquazioniSecondoGrado {

    /* Soglia usata per considerare nullo il discriminante. */
    private static final double EPSILON = 1.0E-15;

    /**
     * Costruisce un risolutore privo di stato, riutilizzabile per più
     * equazioni.
     */
    public RisolutoreEquazioniSecondoGrado() {
    }

    /**
     * Calcola le soluzioni reali di una data equazione di secondo grado.
     *
     * @param e equazione da risolvere
     * @return un oggetto che rappresenta le soluzioni reali calcolate
     * @throws NullPointerException se {@code e} è {@code null}
     */
    public SoluzioneEquazioneSecondoGrado solve(EquazioneSecondoGrado e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di risolvere una equazione nulla");
        double delta = e.getB() * e.getB() - 4 * e.getA() * e.getC();
        // Caso delta uguale a zero entro la soglia scelta.
        if (Math.abs(delta) < EPSILON)
            // Il valore di ritorno rappresenta due soluzioni coincidenti.
            return new SoluzioneEquazioneSecondoGrado(e,
                    (-e.getB()) / (2 * e.getA()));
        // Caso delta negativo: non esistono soluzioni reali.
        if (delta < 0)
            // Il valore di ritorno rappresenta una soluzione vuota.
            return new SoluzioneEquazioneSecondoGrado(e);
        // Caso delta positivo: si calcolano due soluzioni reali distinte.
        double tmp = Math.sqrt(delta);
        // L'oggetto risultato raccoglie entrambe le soluzioni calcolate.
        return new SoluzioneEquazioneSecondoGrado(e,
                (-e.getB() + tmp) / (2 * e.getA()),
                (-e.getB() - tmp) / (2 * e.getA()));
    }
}
