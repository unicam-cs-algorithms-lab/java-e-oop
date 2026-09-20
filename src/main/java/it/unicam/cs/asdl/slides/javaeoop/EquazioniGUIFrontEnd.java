package it.unicam.cs.asdl.slides.javaeoop;

import javax.swing.JOptionPane;

/**
 * Front-end grafico Swing per usare in modo interattivo le classi della logica
 * applicativa che rappresentano e risolvono le equazioni di secondo grado.
 * <p>
 * Questa classe si occupa dell'input e dell'output: legge i coefficienti dai
 * componenti grafici, invoca l'API pubblica della logica applicativa e mostra
 * il risultato. Le classi del nucleo non conoscono Swing e possono quindi
 * essere riutilizzate anche dal front-end testuale.
 *
 * Le componenti della GUI sono state generate automaticamente con Apache
 * NetBeans IDE 12.0 - https://netbeans.org/
 *
 * @author Luca Tesei
 */
public class EquazioniGUIFrontEnd extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    /*
     * Risolutore per tutte le equazioni generate con la GUI
     */
    private static final RisolutoreEquazioniSecondoGrado solver = new RisolutoreEquazioniSecondoGrado();

    /**
     * Costruisce e inizializza la finestra del front-end grafico.
     */
    public EquazioniGUIFrontEnd() {
        initComponents();
    }

    /**
     * Inizializza i componenti grafici della finestra. Il Form Editor genera
     * automaticamente il contenuto di questo metodo.
     */
    // <editor-fold defaultstate="collapsed" desc="Codice generato">
    private void initComponents() {

        solveButton = new javax.swing.JButton();
        aInput = new javax.swing.JTextField();
        bInput = new javax.swing.JTextField();
        cInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sol1Output = new javax.swing.JLabel();
        sol2Output = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Risolutore Equazioni di Secondo Grado");
        setMinimumSize(new java.awt.Dimension(425, 207));

        solveButton.setText("Risolvi");
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        aInput.setText("1.0");

        bInput.setText("-3.0");

        cInput.setText("2.0");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Parametro a");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Parametro b");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Parametro c");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Soluzione 1");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Soluzione 2");

        sol1Output.setText("2.0");

        sol2Output.setText("1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGroup(layout
                                .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(
                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addGap(4, 4, 4))
                                                .addGroup(
                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                        layout.createSequentialGroup()
                                                                .addGroup(layout
                                                                        .createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(
                                                                                jLabel4)
                                                                        .addComponent(
                                                                                jLabel3))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(
                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cInput)
                                                .addComponent(bInput,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        78, Short.MAX_VALUE)
                                                .addComponent(aInput)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45).addComponent(solveButton)))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(33, 33, 33)
                                        .addComponent(sol1Output,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(33, 33, 33)
                                        .addComponent(sol2Output,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)))
                        .addGap(73, 73, 73)));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5).addComponent(sol1Output))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6).addComponent(sol2Output))
                        .addGap(84, 84, 84))
                .addGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(aInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(18, 18, 18).addComponent(solveButton))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap().addComponent(jSeparator1))));

        pack();
    }// </editor-fold>

    /*
     * Gestisce la pressione del pulsante "Risolvi". Il metodo traduce l'input
     * della GUI in oggetti del dominio e traduce il risultato della logica
     * applicativa in testo mostrato all'utente.
     */
    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Queste variabili locali appartengono all'attivazione del metodo.
        double a = 0;
        double b = 0;
        double c = 0;
        // Legge il valore corrente di a dalla GUI e controlla il formato.
        try {
            a = Double.parseDouble(aInput.getText());
        } catch (NumberFormatException e) {
            // Il front-end comunica all'utente l'errore di formato.
            JOptionPane.showMessageDialog(this,
                    "Il parametro a deve essere un numero");
            return;
        }
        // Il front-end anticipa il controllo che esegue anche il costruttore.
        if (Math.abs(a) < EPSILON) {
            // La logica applicativa non mostra finestre di dialogo.
            JOptionPane.showMessageDialog(this,
                    "Il parametro a non può essere zero");
            return;
        }
        // Legge il valore corrente di b dalla GUI e controlla il formato.
        try {
            b = Double.parseDouble(bInput.getText());
        } catch (NumberFormatException e) {
            // Il front-end comunica all'utente l'errore di formato.
            JOptionPane.showMessageDialog(this,
                    "Il parametro b deve essere un numero");
            return;
        }
        // Legge il valore corrente di c dalla GUI e controlla il formato.
        try {
            c = Double.parseDouble(cInput.getText());
        } catch (NumberFormatException e) {
            // Il front-end comunica all'utente l'errore di formato.
            JOptionPane.showMessageDialog(this,
                    "Il parametro c deve essere un numero");
            return;
        }
        // new crea l'equazione nello heap; e contiene un riferimento all'oggetto.
        EquazioneSecondoGrado e = new EquazioneSecondoGrado(a, b, c);
        SoluzioneEquazioneSecondoGrado s = solver.solve(e);
        // Il front-end sceglie come presentare i diversi tipi di risultato.
        // Caso senza soluzioni reali.
        if (s.isEmptySolution()) {
            sol1Output.setText("undef");
            sol2Output.setText("undef");
            return;
        }
        // Caso con due soluzioni coincidenti.
        if (s.isOneSolution()) {
            Double s1 = new Double(s.getS1());
            sol1Output.setText(s1.toString());
            sol2Output.setText("undef");
            return;
        }
        // Caso con due soluzioni reali distinte.
        Double s1 = new Double(s.getS1());
        sol1Output.setText(s1.toString());
        Double s2 = new Double(s.getS2());
        sol2Output.setText(s2.toString());
    }

    /**
     * Avvia il front-end grafico.
     *
     * @param args argomenti della riga di comando, non utilizzati
     */
    public static void main(String args[]) {
        /* Se disponibile, usa l'aspetto grafico Nimbus. */
        // <editor-fold defaultstate="collapsed" desc="Impostazione facoltativa dell'aspetto grafico">
        /*
         * Se Nimbus non è disponibile, mantiene l'aspetto grafico predefinito.
         * Per i dettagli si veda
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
         * html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger
                    .getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger
                    .getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger
                    .getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger
                    .getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Crea e mostra la finestra nel thread degli eventi di Swing. */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EquazioniGUIFrontEnd().setVisible(true);
            }
        });
    }

    // Dichiarazione dei componenti generata dal Form Editor.
    private javax.swing.JTextField aInput;

    private javax.swing.JTextField bInput;

    private javax.swing.JTextField cInput;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JSeparator jSeparator1;

    private javax.swing.JLabel sol1Output;

    private javax.swing.JLabel sol2Output;

    private javax.swing.JButton solveButton;
    // Fine della dichiarazione dei componenti.
}
