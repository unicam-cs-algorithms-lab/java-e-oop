package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Variante didattica di una equazione di secondo grado in cui i coefficienti
 * sono raccolti in un oggetto separato di tipo {@link Coefficienti}.
 * <p>
 * Questa classe serve a mostrare perche' l'incapsulamento non coincide solo con
 * la dichiarazione {@code private} dei campi. Il campo {@code coefficienti} e'
 * privato, ma l'oggetto a cui fa riferimento e' mutabile. Per evitare che il
 * chiamante possa modificare indirettamente lo stato dell'equazione, il
 * costruttore e il getter usano copie difensive create tramite il copy
 * constructor di {@link Coefficienti}.
 * <p>
 * In questo modo lo stato dell'equazione puo' cambiare solo attraverso i metodi
 * pubblici della classe stessa, come previsto dall'idea di oggetto come entita'
 * che incapsula il proprio stato e fornisce servizi tramite le proprie API.
 *
 * @author Luca Tesei
 */
public class EquazioneSecondoGradoConCoefficienti {

    /* Soglia usata soltanto per stabilire se il coefficiente a è zero. */
    private static final double EPSILON = 1.0E-15;

    private final Coefficienti coefficienti;

    /**
     * Costruisce una equazione di secondo grado a partire da una terna di
     * coefficienti.
     * <p>
     * Il parametro non viene memorizzato direttamente. Il copy constructor di
     * {@link Coefficienti} crea invece un nuovo oggetto con gli stessi valori,
     * in modo che eventuali modifiche successive all'oggetto passato dal
     * chiamante non possano alterare lo stato interno di questa equazione.
     *
     * @param coefficienti coefficienti dell'equazione
     * @throws NullPointerException     se {@code coefficienti} e' {@code null}
     * @throws IllegalArgumentException se il coefficiente {@code a} e' zero e
     *                                  quindi l'equazione non e' di secondo
     *                                  grado
     */
    public EquazioneSecondoGradoConCoefficienti(Coefficienti coefficienti) {
        if (coefficienti == null)
            throw new NullPointerException("Coefficienti nulli");
        if (Math.abs(coefficienti.getA()) < EPSILON)
            throw new IllegalArgumentException(
                    "Il coefficiente a deve essere diverso da zero");

        // Copia difensiva in ingresso: il copy constructor crea un nuovo
        // oggetto Coefficienti. Il chiamante conserva il proprio oggetto,
        // mentre l'equazione ne possiede uno distinto nello heap.
        this.coefficienti = new Coefficienti(coefficienti);
    }

    /**
     * Restituisce i coefficienti dell'equazione.
     * <p>
     * Anche in uscita viene restituita una copia difensiva: il chiamante puo'
     * modificare liberamente l'oggetto ricevuto, ma non puo' modificare lo stato
     * interno dell'equazione.
     *
     * @return una copia dei coefficienti di questa equazione
     */
    public Coefficienti getCoefficienti() {
        // Copia difensiva in uscita: non rendiamo accessibile il riferimento
        // interno, ma costruiamo un nuovo oggetto con gli stessi valori.
        return new Coefficienti(this.coefficienti);
    }

    /**
     * Restituisce una rappresentazione testuale dell'equazione.
     *
     * @return una stringa che descrive questa equazione
     */
    @Override
    public String toString() {
        return coefficienti.getA() + "x^2 + " + coefficienti.getB() + "x + "
                + coefficienti.getC() + " = 0";
    }
}
