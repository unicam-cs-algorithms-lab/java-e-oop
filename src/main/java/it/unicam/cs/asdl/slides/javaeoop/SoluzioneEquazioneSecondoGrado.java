package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Rappresenta le soluzioni reali di una specifica equazione di secondo grado.
 * Un oggetto può descrivere una soluzione vuota, due soluzioni coincidenti
 * oppure due soluzioni distinte.
 * <p>
 * Tutti i campi sono {@code final}: il costruttore stabilisce lo stato e i
 * metodi pubblici permettono soltanto di osservarlo. L'oggetto risultato può
 * così essere restituito dalla logica applicativa e interpretato da front-end
 * diversi senza essere modificato.
 *
 * @author Luca Tesei
 *
 */
public class SoluzioneEquazioneSecondoGrado {
    // Gli oggetti di questa classe sono immutabili.

    /*
     * Equazione a cui appartiene la soluzione. La visibilità di package evita
     * di esporre il campo come parte dell'API pubblica.
     */
    final EquazioneSecondoGrado e;

    // Prima soluzione, oppure NaN quando non è definita.
    final private double s1;

    // Seconda soluzione, oppure NaN quando non è definita.
    final private double s2;

    // Indica che l'equazione non ha soluzioni reali.
    final private boolean emptySolution;

    // Indica che le due soluzioni reali coincidono.
    final private boolean oneSolution;

    /**
     * Costruisce il risultato di un'equazione priva di soluzioni reali.
     *
     * @param e equazione a cui si riferisce il risultato
     * @throws NullPointerException se {@code e} è {@code null}
     */
    public SoluzioneEquazioneSecondoGrado(EquazioneSecondoGrado e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di creare una soluzione di una equazione nulla");
        this.e = e;
        this.emptySolution = true;
        this.oneSolution = false;
        this.s1 = Double.NaN;
        this.s2 = Double.NaN;
    }

    /**
     * Costruisce il risultato di un'equazione con due soluzioni coincidenti.
     *
     * @param e equazione a cui si riferisce il risultato
     * @param s1 valore della soluzione doppia
     * @throws NullPointerException se {@code e} è {@code null}
     */
    public SoluzioneEquazioneSecondoGrado(EquazioneSecondoGrado e, double s1) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di creare una soluzione di una equazione nulla");
        this.e = e;
        this.emptySolution = false;
        this.oneSolution = true;
        this.s1 = s1;
        this.s2 = Double.NaN;
    }

    /**
     * Costruisce il risultato di un'equazione con due soluzioni reali distinte.
     *
     * @param e equazione a cui si riferisce il risultato
     * @param s1 prima soluzione
     * @param s2 seconda soluzione
     * @throws NullPointerException se {@code e} è {@code null}
     */
    public SoluzioneEquazioneSecondoGrado(EquazioneSecondoGrado e, double s1,
                                          double s2) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di creare una soluzione di una equazione nulla");
        this.e = e;
        this.emptySolution = false;
        this.oneSolution = false;
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Restituisce la prima soluzione.
     *
     * @return la prima soluzione, oppure {@link Double#NaN} se non è definita
     */
    public double getS1() {
        return s1;
    }

    /**
     * Restituisce la seconda soluzione.
     *
     * @return la seconda soluzione, oppure {@link Double#NaN} se non è definita
     */
    public double getS2() {
        return s2;
    }

    /**
     * Determina se la soluzione è vuota.
     *
     * @return {@code true} se non esistono soluzioni reali, {@code false}
     *         altrimenti
     */
    public boolean isEmptySolution() {
        return emptySolution;
    }

    /**
     * Determina se la soluzione contiene due soluzioni coincidenti.
     *
     * @return {@code true} se le due soluzioni coincidono, {@code false}
     *         altrimenti
     */
    public boolean isOneSolution() {
        return oneSolution;
    }

    /*
     * Due risultati sono logicamente uguali quando tutti i campi che ne
     * descrivono lo stato immutabile sono uguali.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SoluzioneEquazioneSecondoGrado))
            return false;
        SoluzioneEquazioneSecondoGrado other = (SoluzioneEquazioneSecondoGrado) obj;
        if (e == null) {
            if (other.e != null)
                return false;
        } else if (!e.equals(other.e))
            return false;
        if (emptySolution != other.emptySolution)
            return false;
        if (oneSolution != other.oneSolution)
            return false;
        if (Double.doubleToLongBits(s1) != Double.doubleToLongBits(other.s1))
            return false;
        if (Double.doubleToLongBits(s2) != Double.doubleToLongBits(other.s2))
            return false;
        return true;
    }

    /*
     * Il codice hash usa gli stessi campi considerati da equals, così oggetti
     * uguali producono lo stesso codice hash.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((e == null) ? 0 : e.hashCode());
        result = prime * result + (emptySolution ? 1231 : 1237);
        result = prime * result + (oneSolution ? 1231 : 1237);
        long temp;
        temp = Double.doubleToLongBits(s1);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(s2);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Costruisce una descrizione leggibile dell'equazione e delle sue
     * soluzioni. Il metodo restituisce una stringa e non stampa direttamente.
     *
     * @return rappresentazione testuale del risultato
     */
    @Override
    public String toString() {
        // StringBuffer accumula le diverse parti della stringa risultato.
        StringBuffer s = new StringBuffer();
        s.append(
                "=== Soluzione di Equazione di Secondo Grado ===\nEquazione originale: "
                        + e.toString() + "\n");
        if (this.isEmptySolution())
            s.append("Soluzione vuota.");
        else if (this.isOneSolution())
            s.append("Due soluzioni coincidenti: " + s1);
        else
            s.append("Prima soluzione: " + s1 + "\n" + "Seconda soluzione: "
                    + s2);
        s.append("\n=== Fine Soluzione ===");
        return s.toString();
    }

}
