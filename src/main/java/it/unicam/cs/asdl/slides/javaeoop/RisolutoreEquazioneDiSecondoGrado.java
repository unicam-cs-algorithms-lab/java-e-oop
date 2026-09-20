package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Risolve una specifica equazione di secondo grado cercandone le soluzioni
 * reali.
 * <p>
 * L'equazione ricevuta dal costruttore costituisce lo stato del risolutore. Il
 * campo è {@code private} e {@code final}, quindi il risolutore è immutabile e
 * rimane associato alla stessa equazione per tutta la propria vita. Per
 * risolvere un'altra equazione occorre creare un altro oggetto.
 * <p>
 * Questa classe appartiene alla logica applicativa: non legge dati dall'utente
 * e non stampa il risultato. Restituisce invece un oggetto
 * {@link SoluzioneEquazioneSecondoGrado}, lasciando al front-end la scelta di
 * come presentarlo.
 *
 * @author Luca Tesei
 *
 */
public class RisolutoreEquazioneDiSecondoGrado {

    /* Soglia usata per considerare nullo il discriminante. */
    private static final double EPSILON = 1.0E-15;

    /* L'equazione incapsulata nello stato immutabile del risolutore. */
    private final EquazioneSecondoGrado e;

    /**
     * Costruisce un risolutore associato a una data equazione.
     *
     * @param e equazione che il risolutore dovrà risolvere
     * @throws NullPointerException se {@code e} è {@code null}
     */
    public RisolutoreEquazioneDiSecondoGrado(EquazioneSecondoGrado e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di costruire un risolutore di una equazione nulla");
        this.e = e;
    }

    /**
     * Restituisce l'equazione conservata nello stato del risolutore.
     *
     * @return l'equazione associata a questo risolutore
     */
    public EquazioneSecondoGrado getE() {
        return e;
    }

    /* Due risolutori sono logicamente uguali se contengono equazioni uguali. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RisolutoreEquazioneDiSecondoGrado))
            return false;
        RisolutoreEquazioneDiSecondoGrado other = (RisolutoreEquazioneDiSecondoGrado) obj;
        if (e == null) {
            if (other.e != null)
                return false;
        } else if (!e.equals(other.e))
            return false;
        return true;
    }

    /*
     * Il codice hash usa lo stesso campo considerato da equals, così rispetta
     * il contratto richiesto dalle collezioni basate su hashing.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((e == null) ? 0 : e.hashCode());
        return result;
    }

    /**
     * Calcola e restituisce la soluzione dell'equazione di secondo grado di questo
     * risolutore.
     *
     * @return la soluzione dell'equazione
     */
    public SoluzioneEquazioneSecondoGrado getSolution() {
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
