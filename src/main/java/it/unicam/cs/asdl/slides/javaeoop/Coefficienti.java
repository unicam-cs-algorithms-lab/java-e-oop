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
 * Il metodo {@link #clone()} permette di creare una copia indipendente dei
 * coefficienti. In questo esempio la copia e' semplice perche' lo stato e'
 * formato solo da valori primitivi di tipo {@code double}.
 *
 * @author Luca Tesei
 */
public class Coefficienti implements Cloneable {

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
     * Crea una nuova istanza di {@code Coefficienti} con gli stessi valori di
     * questa istanza.
     * <p>
     * La copia restituita e' indipendente: modificare la copia non modifica
     * l'oggetto originale e viceversa. Questo metodo viene usato negli esempi
     * sulle copie difensive.
     *
     * @return una copia indipendente di questi coefficienti
     */
    @Override
    public Coefficienti clone() {
        return new Coefficienti(this.a, this.b, this.c);
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
