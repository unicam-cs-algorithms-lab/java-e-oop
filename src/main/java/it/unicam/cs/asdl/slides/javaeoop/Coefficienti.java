package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Rappresenta i tre coefficienti {@code a}, {@code b} e {@code c} di una
 * equazione di secondo grado.
 * <p>
 * Questa classe e' volutamente mutabile: i coefficienti possono essere
 * modificati dopo la costruzione tramite i metodi setter. La mutabilita' rende
 * la classe adatta a mostrare un punto importante dell'incapsulamento: se un
 * oggetto conserva direttamente un riferimento a un oggetto mutabile ricevuto
 * dall'esterno, il suo stato puo' cambiare senza che venga chiamato un suo
 * metodo.
 * <p>
 * Oltre al costruttore ordinario, la classe mette a disposizione un
 * <em>copy constructor</em>, cioe' un costruttore che crea un nuovo oggetto
 * copiando lo stato di un altro oggetto {@code Coefficienti}. Il nuovo oggetto
 * contiene gli stessi valori ma occupa una zona distinta dello heap e puo'
 * quindi essere modificato indipendentemente dall'originale.
 *
 * @author Luca Tesei
 */
public class Coefficienti {

    private double a;
    private double b;
    private double c;

    /**
     * Costruisce una terna di coefficienti.
     *
     * @param a coefficiente del termine di secondo grado
     * @param b coefficiente del termine di primo grado
     * @param c termine noto
     */
    public Coefficienti(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Costruisce una nuova terna copiando i valori di un'altra istanza di
     * {@code Coefficienti}.
     * <p>
     * Il nuovo oggetto e' indipendente da quello ricevuto: i due oggetti hanno
     * inizialmente gli stessi valori, ma modificare uno dei due non modifica
     * l'altro. Questo costruttore viene utilizzato negli esempi per realizzare
     * copie difensive.
     *
     * @param other coefficienti da copiare
     * @throws NullPointerException se {@code other} e' {@code null}
     */
    public Coefficienti(Coefficienti other) {
        if (other == null)
            throw new NullPointerException("Coefficienti nulli");
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    /**
     * @return il coefficiente del termine di secondo grado
     */
    public double getA() {
        return a;
    }

    /**
     * @param a nuovo coefficiente del termine di secondo grado
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return il coefficiente del termine di primo grado
     */
    public double getB() {
        return b;
    }

    /**
     * @param b nuovo coefficiente del termine di primo grado
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * @return il termine noto
     */
    public double getC() {
        return c;
    }

    /**
     * @param c nuovo termine noto
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * Restituisce una rappresentazione testuale dei coefficienti.
     *
     * @return una stringa che descrive questa terna di coefficienti
     */
    @Override
    public String toString() {
        return "Coefficienti[a=" + a + ", b=" + b + ", c=" + c + "]";
    }
}
