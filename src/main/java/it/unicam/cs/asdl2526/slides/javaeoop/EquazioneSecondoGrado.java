package it.unicam.cs.asdl2526.slides.javaeoop;

/**
 * Una equazione di secondo grado è un oggetto il cui stato contiene i parametri
 * che identificano l'equazione stessa.
 * 
 * @author Luca Tesei
 *
 */
public class EquazioneSecondoGrado
        implements Comparable<EquazioneSecondoGrado> {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    /*
     * Questa classe è immutabile, cioè tutte le variabili istanza sono final:
     * lo stato di un oggetto, una volta creato, non può più essere modificato
     */
    final private double a;

    final private double b;

    final private double c;

    /**
     * Costruisce una equazione di secondo grado.
     * 
     * @param a
     *              coefficiente del termine x^2, deve essere diverso da zero.
     * @param b
     *              coefficiente del termine x
     * @param c
     *              termine noto
     * @throws IllegalArgumentException
     *                                      se il parametro <code>a</code> è
     *                                      zero
     */
    public EquazioneSecondoGrado(double a, double b, double c) {
        if (Math.abs(a) < EPSILON) // controllo se uguale a zero
            throw new IllegalArgumentException("L'equazione di secondo grado"
                    + " non può avere coefficiente a uguale a zero");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return il parametro a di questa equazione
     */
    public double getA() {
        return a;
    }

    /**
     * @return il parametro b di questa equazione
     */
    public double getB() {
        return b;
    }

    /**
     * @return il parametro c di questa equazione
     */
    public double getC() {
        return c;
    }

    // due equazioni sono uguali se e solo se hanno gli stessi parametri
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EquazioneSecondoGrado))
            return false;
        EquazioneSecondoGrado other = (EquazioneSecondoGrado) obj;
        // controllo di uguaglianza di double effettuato bit a bit sulla
        // rappresentazione a 64 bit vista come un long
        // in questo caso consideriamo due equazioni uguali solo se hanno
        // la stessa rappresentazione dei numeri a, b, e c. Cioè non
        // consideriamo
        // uguali equazioni con valori dei parametri molto vicini (distanza <
        // EPSILON)
        if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a))
            return false;
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b))
            return false;
        if (Double.doubleToLongBits(this.c) != Double.doubleToLongBits(other.c))
            return false;
        return true;
    }

    // l'hashcode di un oggetto equazione si calcola a partire dai 3 parametri
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        // si usa il valore intero corrispondente alla rappresentazione del
        // double bit a bit (64 bit, cioè un long)
        temp = Double.doubleToLongBits(a);
        // si fa il bitwise XOR tra i 64 bit originali e il loro shift a destra
        // di 32 bit, poi si fa il cast a int
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /*
     * L'ordinamento naturale naturale tra equazioni di secondo grado segue
     * l'ordinamento dei numeri double. Per prima cosa vengono confrontati i
     * parametri a delle due equazioni. Se sono diversi allora l'equazione con
     * il parametro a più grande è l'equazione più grande nell'ordinamento
     * naturale tra le equazioni. Se i parametri a sono uguali si passa a
     * confrontare i parametri b e, se sono uguali, i parametri c. Se tutti e
     * tre i parametri sono uguali allora le due equazioni sono uguali, in
     * accordo con il metodo equals.
     * 
     */
    @Override
    public int compareTo(EquazioneSecondoGrado o) {
        if (o == null)
            throw new NullPointerException("Tentativo di confrontare con null");
        if (this.a < o.a)
            return -1;
        else if (this.a > o.a)
            return 1;
        // le a sono uguali
        // controllo le b
        if (this.b < o.b)
            return -1;
        else if (this.b > o.b)
            return 1;
        // le b sono uguali
        // controllo le c
        if (this.c < o.c)
            return -1;
        else if (this.c > o.c)
            return 1;
        // uguali
        return 0;
    }

    // ridefiniamo il metodo toString per creare una stringa che rappresenta
    // l'equazione nel modo classico
    @Override
    public String toString() {
        // usiamo uno string buffer per accumulare la stringa risultato
        StringBuffer s = new StringBuffer();
        s.append(a + " x^2 ");
        if (b != 0)
            s.append("+ " + b + " x ");
        if (c != 0)
            s.append("+ " + c + " = 0");
        return s.toString();
    }

}
