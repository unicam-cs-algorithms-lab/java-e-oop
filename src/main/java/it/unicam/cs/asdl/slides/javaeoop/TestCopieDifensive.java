package it.unicam.cs.asdl.slides.javaeoop;

/**
 * Semplice front-end testuale per osservare il funzionamento delle copie
 * difensive usate da {@link EquazioneSecondoGradoConCoefficienti}.
 * <p>
 * L'esempio mostra separatamente i due punti in cui e' necessario proteggere
 * lo stato interno quando si usano oggetti mutabili: all'ingresso, nel
 * costruttore, e all'uscita, nel getter.
 *
 * @author Luca Tesei
 */
public class TestCopieDifensive {

    public static void main(String[] args) {
        Coefficienti originali = new Coefficienti(1.0, 2.0, 1.0);
        EquazioneSecondoGradoConCoefficienti equazione =
                new EquazioneSecondoGradoConCoefficienti(originali);

        System.out.println("Coefficienti originali: " + originali);
        System.out.println("Coefficienti dell'equazione: "
                + equazione.getCoefficienti());

        /*
         * Modifichiamo l'oggetto passato al costruttore. L'equazione non cambia:
         * il costruttore non ha conservato questo riferimento, ma ha creato una copia tramite il copy constructor.
         */
        System.out.println("\nModifico l'oggetto Coefficienti originale...");
        originali.setA(10.0);
        System.out.println("Coefficienti originali: " + originali);
        System.out.println("Coefficienti dell'equazione: "
                + equazione.getCoefficienti());

        /*
         * Ora modifichiamo l'oggetto restituito dal getter. Anche questa modifica
         * non raggiunge lo stato interno: il getter crea e restituisce una nuova copia tramite il copy constructor.
         */
        System.out.println("\nModifico l'oggetto restituito dal getter...");
        Coefficienti ottenutiDalGetter = equazione.getCoefficienti();
        ottenutiDalGetter.setB(20.0);
        System.out.println("Oggetto restituito dal getter: "
                + ottenutiDalGetter);
        System.out.println("Coefficienti dell'equazione: "
                + equazione.getCoefficienti());
    }
}
