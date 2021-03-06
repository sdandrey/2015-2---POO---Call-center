/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.Tickets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class TiketVentana extends javax.swing.JFrame {

    /**
     * Creates new form TiketVentana
     */
    boolean botonRes = false;
    Tickets actual;
    int indice;
    ClienteVentana parent;
    
    
    public TiketVentana(ClienteVentana parent,int indice) {
        initComponents();
        setLocationRelativeTo(null);


        this.setVisible(true);
        this.indice=indice;
        this.parent=parent;
        
        System.out.println(":::::::::::::::::::::::::::");
        this.actual=(Tickets)parent.cliente.getListaTickets().get(indice);
        System.out.println(actual.getAsunto());
        System.out.println("ESTE ES EL ASUNTO");
        this.iniciarCrono();
        jTextArea1.setText(actual.toString());


    }
    public void iniciarCrono(){
        btnPausa.setEnabled(true);
        hilo.start();
    }
    
    
    public boolean issuspended = false;//para saber si el hilo esta suspendido o pausado
    int hora = 00, min = 00, seg = 00, ds = 00;//unidades de medida
    Thread hilo = new Thread() {//declaramos el hilo

        @Override
        public void run() {
            try {
                while (true) {//ciclo infinito
                    if (ds == 99) {//si los decisegundos son iguales a 99
                        ds = 00;//decisegundo vuelve a empezar en cero
                        seg++;//y aumenta un segundo
                    }
                    if (seg == 59) {//si los segundos son iguales a 59
                        seg = 00;//segundo vuelve a empezar en cero
                        min++;//y aumenta un minuto
                    }
                    if (min == 59) {//si los minutos son iguales a 59
                        min = 00;//minuto vuelve a empezar en cero
                        hora++;//y aumenta una hora
                    }
                    ds++;//aumentan las decimas de segundo

                    lblTime.setText(hora + ":" + min + ":" + seg + ":" + ds);//se muestra en el jlabel

                    hilo.sleep(10);//que duerma una decima de segundo
                }
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        btnPausa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tcomentario = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Atención al tiket");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Atención del tiket");

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel1.setText("Descripción del tiket");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cronómetro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 14))); // NOI18N

        lblTime.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("0:0:0:0");

        btnPausa.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnPausa.setText("Pausar");
        btnPausa.setEnabled(false);
        btnPausa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPausaMouseClicked(evt);
            }
        });
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btnPausa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPausa)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Comentario");

        tcomentario.setColumns(20);
        tcomentario.setRows(5);
        jScrollPane2.setViewportView(tcomentario);

        jButton2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton2.setText("Terminado");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton1.setText("Posponer");
        jButton1.setToolTipText("Envia el tiket a pendiente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TuCalllCenterLogoAT.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(173, 173, 173))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(tcomentario.getText().equals("")){
            JOptionPane.showMessageDialog(this, "¡Debe de colocar un comentario para poder continuar!");
        }
        else{            
            try {
                parent.cliente.modificarEstadoTicketAtendido(indice, tcomentario.getText(), this.lblTime.getText());
            } catch (IOException ex) {
                Logger.getLogger(TiketVentana.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed
        
    }//GEN-LAST:event_btnPausaActionPerformed

    private void btnPausaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPausaMouseClicked
        if(botonRes == true){
            btnPausa.setText("Pausar");
            hilo.resume();//el hilo se reanuda
            issuspended = false;
            botonRes = false;
        }
        else{
            btnPausa.setText("Reanudar");
            hilo.suspend();//se suspende o pausa el hilo
            issuspended = true;
            botonRes = true;
        }
    }//GEN-LAST:event_btnPausaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Liberando");
        try {
            this.parent.cliente.liberarTicket(indice, tcomentario.getText());
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(TiketVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TiketVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TiketVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TiketVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TiketVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TiketVentana(null,0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextArea tcomentario;
    // End of variables declaration//GEN-END:variables
}
