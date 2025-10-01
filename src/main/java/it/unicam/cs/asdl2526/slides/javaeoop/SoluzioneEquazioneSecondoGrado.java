package it.unicam.cs.asdl2526.slides.javaeoop;

/**
 * Un oggetto di questa classe rappresenta le soluzioni di una certa equazione
 * di secondo grado. Le soluzioni possono essere zero, una o due.
 * 
 * @author Luca Tesei
 *
 */
public class SoluzioneEquazioneSecondoGrado {
    // gli oggetti di questa classe sono immutabili

    // l'equazione di cui questo oggetto è soluzione
    final EquazioneSecondoGrado e;

    // soluzione 1
    final private double s1;

    // soluzione 2
    final private double s2;

    // indica se la soluzione è vuota
    final private boolean emptySolution;

    // indica se ci sono due soluzioni coincidenti
    final private boolean oneSolution;

    /**
     * Costruisce una soluzione vuota.
     * 
     * @param e
     *              l'equazione originale di cui questo oggetto è una soluzione.
     * @throws NullPointerException
     *                                  se l'equazione passata è nulla
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
     * Costruisce una soluzione con due soluzioni coincidenti.
     * 
     * @param e
     *               l'equazione originale di cui questo oggetto è una
     *               soluzione.
     * @param s1
     *               l'unica soluzione
     * @throws NullPointerException
     *                                  se l'equazione passata è nulla
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
     * Costruisce una soluzione con due soluzioni diverse.
     * 
     * @param e
     *               l'equazione originale di cui questo oggetto è una
     *               soluzione.
     * @param s1
     *               la prima soluzione
     * @param s2
     *               la seconda soluzione
     * @throws NullPointerException
     *                                  se l'equazione passata è nulla
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
     * @return la prima soluzione
     */
    public double getS1() {
        return s1;
    }

    /**
     * @return la seconda soluzione
     */
    public double getS2() {
        return s2;
    }

    /**
     * Determina se la soluzione è vuota.
     * 
     * @return true, se la soluzione è vuota, false altrimenti
     */
    public boolean isEmptySolution() {
        return emptySolution;
    }

    /**
     * Determina se la soluzione contiene due soluzioni coincidenti.
     * 
     * @return true, se la soluzione contiene due soluzioni coincidenti, false
     *         altrimenti
     */
    public boolean isOneSolution() {
        return oneSolution;
    }

    /*
     * Due Soluzioni sono uguali se hanno tutti i campi immutabili uguali.
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
     * L'hashcode di una soluzione si calcola a partire da tutti i campi
     * utilizzati per determinare l'uguaglianza.
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

    @Override
    public String toString() {
        // Usiamo uno string buffer per accumulare le varie parti della stringa
        // risultato.
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
