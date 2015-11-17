package whatscucu;

import javax.swing.JOptionPane;

/**
 *
 * @author Cito
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        setLocationRelativeTo(null);
        
        //  Obbliga a inserire un nome
        try{
            while(WhatsCucu.getName().equals(""))
                WhatsCucu.setName(JOptionPane.showInputDialog("Inserisci il tuo nome")); 
        } catch (Exception ex){
            //  Se si genera un'eccezione chiude il programma
            System.out.println("Errore Nome\nUscita in corso..."); 
            System.exit(0);
        }
        
        WhatsCucu.startSocket();
    }
    
    //  Chiamata quando schiacci il tasto "Invia!"senzailpuntoesclamativo
    private void inviaCucu(){
        //  Prende il testo dal TextField
        String message = jTxNewMessage.getText();
        //  Invia il meassaggio solo se non è vuoto
        if (!message.equals(""))
            WhatsCucu.send(message);
        
        //  Sbianca il TextField
        jTxNewMessage.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBExit = new javax.swing.JButton();
        jTxNewMessage = new javax.swing.JTextField();
        jBtSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxAChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        jBExit.setText("Esci");
        jBExit.setFocusable(false);
        jBExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExitActionPerformed(evt);
            }
        });

        jTxNewMessage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxNewMessage.setName("Novo Messaggio"); // NOI18N
        jTxNewMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxNewMessageKeyReleased(evt);
            }
        });

        jBtSend.setText("Invia");
        jBtSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSendActionPerformed(evt);
            }
        });

        jTxAChat.setEditable(false);
        jTxAChat.setColumns(20);
        jTxAChat.setRows(5);
        jScrollPane1.setViewportView(jTxAChat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 743, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jTxNewMessage)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jBtSend))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jBExit)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jBExit)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTxNewMessage)
                        .addComponent(jBtSend, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jTxNewMessage.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExitActionPerformed
        //  Stoppa tutto
        WhatsCucu.setStopped(true);
        //  Da il tempo a tutto di stopparsi
        try {   Thread.sleep(100);
        } catch (InterruptedException ex) {}
        //  Chiude la grafica
        System.exit(0);
    }//GEN-LAST:event_jBExitActionPerformed

    private void jBtSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSendActionPerformed
        inviaCucu();
    }//GEN-LAST:event_jBtSendActionPerformed

    private void jTxNewMessageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxNewMessageKeyReleased
        //  Invia il messaggio quando schiecci il tasto invio
        if(evt.getKeyCode() == '\n') inviaCucu();
    }//GEN-LAST:event_jTxNewMessageKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBExit;
    private javax.swing.JButton jBtSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxAChat;
    private javax.swing.JTextField jTxNewMessage;
    // End of variables declaration//GEN-END:variables
}
