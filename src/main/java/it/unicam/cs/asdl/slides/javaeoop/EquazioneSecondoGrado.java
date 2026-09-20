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

    /*
     * Il codice hash usa gli stessi tre campi impiegati da equals. In questo
     * modo due equazioni uguali hanno sempre lo stesso codice hash, come
     * richiesto dalle collezioni basate su hashing.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        // Si parte dalla rappresentazione a 64 bit del valore double.
        temp = Double.doubleToLongBits(a);
        // Lo XOR combina le due metà del long prima della conversione a int.
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Confronta questa equazione con un'altra secondo l'ordinamento
     * lessicografico dei coefficienti: prima {@code a}, poi {@code b} e infine
     * {@code c}.
     * <p>
     * L'esempio didattico assume coefficienti finiti e non distingue
     * {@code 0.0} da {@code -0.0} per {@code b} e {@code c}. Fuori da questo
     * dominio il confronto scritto con gli operatori {@code <} e {@code >} non
     * garantisce la compatibilità con {@link #equals(Object)}.
     *
     * @param o equazione con cui effettuare il confronto
     * @return un valore negativo se questa equazione precede {@code o}, zero se
     *         coincide con {@code o}, un valore positivo se la segue
     * @throws NullPointerException se {@code o} è {@code null}
     */
    @Override
    public int compareTo(EquazioneSecondoGrado o) {
        if (o == null)
            throw new NullPointerException("Tentativo di confrontare con null");
        if (this.a < o.a)
            return -1;
        else if (this.a > o.a)
            return 1;
        // A parità di a, il confronto prosegue con b.
        if (this.b < o.b)
            return -1;
        else if (this.b > o.b)
            return 1;
        // A parità di a e b, il confronto prosegue con c.
        if (this.c < o.c)
            return -1;
        else if (this.c > o.c)
            return 1;
        // Tutti i coefficienti coincidono nel dominio assunto dall'esempio.
        return 0;
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
