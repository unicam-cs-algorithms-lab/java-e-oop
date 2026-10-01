package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Rappresenta un'equazione di secondo grado nella forma
 * {@code a x^2 + b x + c = 0}.
 * <p>
 * I tre coefficienti costituiscono lo stato dell'oggetto. Lo stato è
 * incapsulato in campi non accessibili direttamente dall'esterno ed è
 * immutabile: dopo la costruzione può essere osservato attraverso i metodi
 * pubblici, ma non può essere modificato.
 * <p>
 * La classe definisce inoltre l'uguaglianza logica, il codice hash,
 * l'ordinamento naturale e una rappresentazione testuale. Questi contratti
 * permettono di usare correttamente le equazioni nelle collezioni Java.
 *
 * @author Luca Tesei
 *
 */
public class EquazioneSecondoGrado
        implements Comparable<EquazioneSecondoGrado> {
    /* Soglia usata soltanto per stabilire se il coefficiente a è zero. */
    private static final double EPSILON = 1.0E-15;

    /*
     * I campi sono final perché questa classe è immutabile. Ogni oggetto
     * conserva nello heap i coefficienti ricevuti dal costruttore per tutta la
     * propria vita.
     */
    final private double a;

    final private double b;

    final private double c;

    /**
     * Costruisce un'equazione di secondo grado con i coefficienti indicati.
     *
     * @param a coefficiente del termine {@code x^2}, diverso da zero
     * @param b coefficiente del termine {@code x}
     * @param c termine noto
     * @throws IllegalArgumentException se il valore assoluto di {@code a} è
     *         minore della soglia usata dalla classe
     */
    public EquazioneSecondoGrado(double a, double b, double c) {
        if (Math.abs(a) < EPSILON) // Un'equazione di secondo grado richiede a diverso da zero.
            throw new IllegalArgumentException("L'equazione di secondo grado"
                    + " non può avere coefficiente a uguale a zero");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Restituisce il coefficiente del termine di secondo grado.
     *
     * @return il coefficiente {@code a}
     */
    public double getA() {
        return a;
    }

    /**
     * Restituisce il coefficiente del termine di primo grado.
     *
     * @return il coefficiente {@code b}
     */
    public double getB() {
        return b;
    }

    /**
     * Restituisce il termine noto.
     *
     * @return il coefficiente {@code c}
     */
    public double getC() {
        return c;
    }

    /*
     * Due oggetti distinti nello heap sono logicamente uguali quando i tre
     * coefficienti hanno la stessa rappresentazione double.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EquazioneSecondoGrado))
            return false;
        EquazioneSecondoGrado other = (EquazioneSecondoGrado) obj;
        /*
         * Il confronto usa la rappresentazione a 64 bit di ogni double. Valori
         * soltanto vicini, anche se distano meno di EPSILON, non sono uguali.
         */
        if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a))
            return false;
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b))
            return false;
        if (Double.doubleToLongBits(this.c) != Double.doubleToLongBits(other.c))
            return false;
        return true;
    }

    /**
     * Restituisce un codice hash coerente con {@link #equals(Object)}.
     * <p>
     * Oggetti uguali secondo {@code equals} devono produrre lo stesso codice
     * hash, requisito fondamentale per l'uso corretto nelle collezioni basate
     * su hashing.
     *
     * @return il codice hash di questa equazione
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(a);
        // Combina con XOR i 32 bit alti e i 32 bit bassi del long,
        // ottenendo un valore int a cui contribuiscono tutti i 64 bit.
        /*
                       long temp (64 bit)
        ┌──────────────┬──────────────┐
        │  32 bit ALTI │ 32 bit BASSI │
        └──────────────┴──────────────┘
                │              │
                └────── XOR ───┘
                       │
                       ▼
                    32 bit
                       │
                    (int)
         */
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Confronta questa equazione con un'altra secondo l'ordinamento naturale
     * lessicografico dei coefficienti: prima {@code a}, poi {@code b}, infine
     * {@code c}.
     * <p>
     * Il confronto dei coefficienti usa {@link Double#compare(double, double)}.
     * In questo modo l'ordinamento è coerente con il criterio adottato da
     * {@link #equals(Object)} anche per valori particolari di tipo
     * {@code double}, come {@code 0.0}, {@code -0.0} e {@code NaN}.
     * In particolare, {@code compareTo} restituisce zero se e solo se le due
     * equazioni sono considerate uguali da {@code equals}.
     *
     * @param o equazione con cui effettuare il confronto
     * @return un valore negativo se questa equazione precede {@code o}, zero se
     *         le equazioni sono uguali, un valore positivo se questa equazione
     *         segue {@code o}
     * @throws NullPointerException se {@code o} è {@code null}
     */
    @Override
    public int compareTo(EquazioneSecondoGrado o) {
        if (o == null)
            throw new NullPointerException("Tentativo di confrontare con null");
        int comparison = Double.compare(this.a, o.a);
        if (comparison != 0)
            return comparison;
        comparison = Double.compare(this.b, o.b);
        if (comparison != 0)
            return comparison;
        return Double.compare(this.c, o.c);
    }

    /**
     * Costruisce una rappresentazione leggibile dell'equazione. Il metodo
     * restituisce una stringa e non esegue operazioni di stampa.
     *
     * @return l'equazione scritta nella forma algebrica usuale
     */
    @Override
    public String toString() {
        // StringBuffer accumula le parti della stringa risultato.
        StringBuffer s = new StringBuffer();
        s.append(a + " x^2 ");
        if (b != 0)
            s.append("+ " + b + " x ");
        if (c != 0)
            s.append("+ " + c + " = 0");
        return s.toString();
    }

}
