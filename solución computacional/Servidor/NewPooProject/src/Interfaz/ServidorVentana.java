package Interfaz;
import javax.swing.*;
import java.io.File;
import Logica.*;//<---------------------------------------------------------------------Despues le borro
import static Logica.Funciones.cargaEstadoTiket;
import static Logica.Funciones.cargaInfoTiket;
import static Logica.Funciones.cargaInfoTiketAmarillo;
import static Logica.Funciones.cargaInfoTiketRojo;
import static Logica.Funciones.cargaInfoTiketVerde;
import static Logica.Funciones.getIDTiket;
import static Logica.ManejadorDeListas.ListaDeAmarillos;
import static Logica.ManejadorDeListas.ListaDeRojos;
import static Logica.ManejadorDeListas.ListaDeVerdes;
import Logica.servidor.HiloDeCliente_1;
import Logica.servidor.Servidor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Fernando
 */
public class ServidorVentana extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Servidor
     */
    Servidor servidor;
    ArrayList listaEmpleados;
    boolean luis;
    boolean fernando;
    boolean junior;
    public ServidorVentana() {
       //this.servidor=servidor;
        initComponents();
        setLocationRelativeTo(null);
        
        MyExell.MegaExellGet(0);
        MyExell.MegaExellGet(1);
        
        Lista.setMultipleMode(true);
        
        ManejadorDeListas.addNewTiketsToLocalListPendientes(MyExell.Open_Load_And_ReturnListOfTickets("TICKETS.xls"));
        this.setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        
        
        
    }
   
    private void close(){
         MyExell.generateMegaExell(0,ManejadorDeListas.MegaLista);
         MyExell.generateMegaExell(1,ManejadorDeListas.MegaLiberados);
        
        JFileChooser jF1 = null;
        
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea guardar los cambios producidos en el Sistema?",
            "Guardar Cambios", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            
            
            
            jF1= new JFileChooser();
            jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jF1.setAcceptAllFileFilterUsed(false);
        
            String ruta = "";
            try{
            if(jF1.showSaveDialog(null)==jF1.APPROVE_OPTION){
                ruta = jF1.getSelectedFile().getAbsolutePath();
                //Aqui ya tiens la ruta,,,ahora puedes crear un fichero n esa ruta y escribir lo k kieras...
                //System.out.println("ESTEEE ES EL PAAAATH->"+ruta+"<-");
            
                MyExell.save_All_Changes(ruta);
                
                
               
                System.out.println("Guardo CAmbios");

        }else{
                System.out.println("FSSDFFSFSFSS");
                MyExell.generateMegaExell(0,ManejadorDeListas.MegaLista);
                MyExell.generateMegaExell(1,ManejadorDeListas.MegaLiberados);
                System.exit(0);}  // Cerrar el sistema incondicionalmente
        }catch (Exception ex){
                System.out.println("ex.getMessage()");
               
        }
        }
        
        System.exit(0);
    }    
    public void setConectados(){
        //set a los iconos con las imágenes
        ImageIcon ofLine = new ImageIcon(this.getClass().getResource("/Images/offlineResize.png"));
        ImageIcon onLine = new ImageIcon(this.getClass().getResource("/Images/onlineResize.png"));
        
        
        if(((Persona)listaEmpleados.get(1)).getEstado()){            
            jLabel2.setText(((Persona)listaEmpleados.get(1)).getNombre());
            jLabel8.setIcon(onLine);
        }else{
            jLabel2.setText(((Persona)listaEmpleados.get(1)).getNombre());
            jLabel8.setIcon(ofLine);
        }
        if(((Persona)listaEmpleados.get(2)).getEstado()){
            jLabel3.setText(((Persona)listaEmpleados.get(2)).getNombre());
            jLabel12.setIcon(onLine);
        }else{
            jLabel3.setText(((Persona)listaEmpleados.get(2)).getNombre());
            jLabel12.setIcon(ofLine);
        }
        if(((Persona)listaEmpleados.get(3)).getEstado()){
            jLabel4.setText(((Persona)listaEmpleados.get(3)).getNombre());
            jLabel13.setIcon(onLine);
        }else{
            jLabel4.setText(((Persona)listaEmpleados.get(3)).getNombre());
            jLabel13.setIcon(ofLine);
        }
    }
    public void setListaEmpleados(ArrayList lista){
        listaEmpleados=lista;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        btnCargarTickets = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSetComoVERDE = new javax.swing.JButton();
        btnSetComoAmarillo = new javax.swing.JButton();
        btnSetComoRojo = new javax.swing.JButton();
        pnEmpleadosConectados = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Lista = new java.awt.List();
        Lista4 = new java.awt.List();
        Lista5 = new java.awt.List();
        Lista6 = new java.awt.List();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btStatusMonitoreo = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("SERVIDOR"); // NOI18N
        setResizable(false);

        btnCargarTickets.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnCargarTickets.setText("Cargar tikets");
        btnCargarTickets.setToolTipText("Carga en la lista los tickets pendientes");
        btnCargarTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTicketsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Seleccione para categorizar los tickets");

        btnSetComoVERDE.setBackground(new java.awt.Color(51, 204, 0));
        btnSetComoVERDE.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnSetComoVERDE.setText("VERDE");
        btnSetComoVERDE.setEnabled(false);
        btnSetComoVERDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetComoVERDEActionPerformed(evt);
            }
        });

        btnSetComoAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        btnSetComoAmarillo.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnSetComoAmarillo.setText("AMARILLO");
        btnSetComoAmarillo.setEnabled(false);
        btnSetComoAmarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetComoAmarilloActionPerformed(evt);
            }
        });

        btnSetComoRojo.setBackground(new java.awt.Color(255, 0, 0));
        btnSetComoRojo.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnSetComoRojo.setText("ROJO");
        btnSetComoRojo.setEnabled(false);
        btnSetComoRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetComoRojoActionPerformed(evt);
            }
        });

        pnEmpleadosConectados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel2.setText("NULL");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel3.setText("NULL");

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel4.setText("NULL");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/offlineResize.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/offlineResize.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/offlineResize.png"))); // NOI18N

        javax.swing.GroupLayout pnEmpleadosConectadosLayout = new javax.swing.GroupLayout(pnEmpleadosConectados);
        pnEmpleadosConectados.setLayout(pnEmpleadosConectadosLayout);
        pnEmpleadosConectadosLayout.setHorizontalGroup(
            pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmpleadosConectadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnEmpleadosConectadosLayout.setVerticalGroup(
            pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmpleadosConectadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnEmpleadosConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Lista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ListaItemStateChanged(evt);
            }
        });
        Lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaActionPerformed(evt);
            }
        });

        Lista4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Lista4ItemStateChanged(evt);
            }
        });
        Lista4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lista4ActionPerformed(evt);
            }
        });

        Lista5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Lista5ItemStateChanged(evt);
            }
        });
        Lista5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lista5ActionPerformed(evt);
            }
        });

        Lista6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Lista6ItemStateChanged(evt);
            }
        });
        Lista6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lista6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("VERDE");

        jLabel10.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 0));
        jLabel10.setText("AMARILLO");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("ROJO");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Cuadro Informativo");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btStatusMonitoreo.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btStatusMonitoreo.setText("Status y Monitoreo ");
        btStatusMonitoreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStatusMonitoreoActionPerformed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TuCalllCenterLogoSinFondoResize.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(321, 321, 321))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(btnCargarTickets)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSetComoAmarillo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSetComoVERDE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSetComoRojo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btStatusMonitoreo, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(pnEmpleadosConectados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lista4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lista6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Lista5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnEmpleadosConectados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnSetComoVERDE)
                                .addGap(19, 19, 19)
                                .addComponent(btnSetComoAmarillo)
                                .addGap(21, 21, 21)
                                .addComponent(btnSetComoRojo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btStatusMonitoreo)
                            .addComponent(jLabel14)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarTickets)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(Lista4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(Lista6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lista5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTicketsActionPerformed
      //================FIle Chooser=========================//
        JFileChooser ventanaSeleccionArchivo = new JFileChooser();
        ventanaSeleccionArchivo.showOpenDialog(null);
        File archivoActual= ventanaSeleccionArchivo.getSelectedFile();
        String pathArchivo = archivoActual.getAbsolutePath();
        //====================================================//
       
        ManejadorDeListas.addNewTiketsToLocalListPendientes(MyExell.Open_Load_And_ReturnListOfTickets(pathArchivo));
        
        //MyExell.save_All_Changes(pathArchivo, MyExell.Open_Load_And_ReturnListOfTickets(pathArchivo),MyExell.Open_Load_And_ReturnListOfTickets(pathArchivo),MyExell.Open_Load_And_ReturnListOfTickets(pathArchivo),MyExell.Open_Load_And_ReturnListOfTickets(pathArchivo));

        Lista.repaint();
        
    }//GEN-LAST:event_btnCargarTicketsActionPerformed

    public void setServidor(Servidor servidor){
        this.servidor=servidor;
    }
    public void setLista(ArrayList lista){
        this.listaEmpleados=lista;
    }
    public void actualizarListaTiquetes() throws IOException{
        ArrayList lista=servidor.getListaConexiones();
        for(int i=0;i<lista.size();i++){
            HiloDeCliente_1 temp=((HiloDeCliente_1)lista.get(i));
            temp.actualizarLista();
            
        }
    }
    
    
    private void btnSetComoVERDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetComoVERDEActionPerformed
          jTextArea1.setText("");
        
        
        String ArregloDeSelecciones [ ] = Lista.getSelectedItems();//getSelectedValues();
        for(int i = 0; i < ArregloDeSelecciones.length; i++) {
        Lista.remove(ArregloDeSelecciones[i]);
        //Para q ponga el estado
        String pID = getIDTiket(ArregloDeSelecciones[i]);
        String pInfo = cargaEstadoTiket(pID);
        Lista4.add(ArregloDeSelecciones[i]+pInfo);        
        ManejadorDeListas.finderThenInsert(ArregloDeSelecciones[i], ListaDeVerdes);
        }
        
        
        btnSetComoVERDE.setEnabled(false);
        btnSetComoAmarillo.setEnabled(false);
        btnSetComoRojo.setEnabled(false);
        
        
    }//GEN-LAST:event_btnSetComoVERDEActionPerformed

    private void btnSetComoAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetComoAmarilloActionPerformed
     jTextArea1.setText("");
        //String pTiket = Lista.getSelectedItem();
        //Lista.remove(pTiket);
        //Lista6.add(pTiket);
        
        String ArregloDeSelecciones [ ] = Lista.getSelectedItems();//getSelectedValues();
        for(int i = 0; i < ArregloDeSelecciones.length; i++) {
        Lista.remove(ArregloDeSelecciones[i]);  
        //Para q ponga el estado
        String pID = getIDTiket(ArregloDeSelecciones[i]);
        String pInfo = cargaEstadoTiket(pID);
        Lista6.add(ArregloDeSelecciones[i]+pInfo);
        ManejadorDeListas.finderThenInsert(ArregloDeSelecciones[i], ListaDeAmarillos);
        }
        
        btnSetComoVERDE.setEnabled(false);
        btnSetComoAmarillo.setEnabled(false);
        btnSetComoRojo.setEnabled(false);
        
      
        
    }//GEN-LAST:event_btnSetComoAmarilloActionPerformed

    private void btnSetComoRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetComoRojoActionPerformed
 jTextArea1.setText("");
        
        
        
        String ArregloDeSelecciones [ ] = Lista.getSelectedItems();//getSelectedValues();
        for(int i = 0; i < ArregloDeSelecciones.length; i++) {
        Lista.remove(ArregloDeSelecciones[i]); 
        //Para q ponga el estado
        String pID = getIDTiket(ArregloDeSelecciones[i]);
        String pInfo = cargaEstadoTiket(pID);
        Lista5.add(ArregloDeSelecciones[i]+pInfo);         
        ManejadorDeListas.finderThenInsert(ArregloDeSelecciones[i], ListaDeRojos);
        }
        
        
        btnSetComoVERDE.setEnabled(false);
        btnSetComoAmarillo.setEnabled(false);
        btnSetComoRojo.setEnabled(false);
        
    }//GEN-LAST:event_btnSetComoRojoActionPerformed

    public void actualizarCliente(){
       
    }
    private void ListaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ListaItemStateChanged
    btnSetComoVERDE.setEnabled(true);
        btnSetComoAmarillo.setEnabled(true);
        btnSetComoRojo.setEnabled(true);
        
        /*Validasiones Producto de la multiple seleccion*/
        String ArregloDeSelecciones [ ] = Lista.getSelectedItems();//getSelectedValues();
        if(ArregloDeSelecciones.length<2 && ArregloDeSelecciones.length>0){
        
            
            /*============================================================================*/    
            jTextArea1.setText("             **INFORMACIÓN DEL TIKET**");        
            String pID = getIDTiket(Lista.getSelectedItem().toString());
            String pInfo = cargaInfoTiket(pID);
            jTextArea1.append(pInfo);        
            jTextArea1.append("\n___________________________________________");
            /*============================================================================*/    
            
        
        }
        else{
            jTextArea1.setText("\n          TICKETS SELECCIONADOS: "+ArregloDeSelecciones.length);        
            
            
            jTextArea1.append("\n\n___________________________________________");
        }
    }//GEN-LAST:event_ListaItemStateChanged

    private void ListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListaActionPerformed

    private void Lista4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Lista4ItemStateChanged
        // TODO add your handling code here:
        jTextArea1.setText("             **INFORMACIÓN DEL TIKET**");        
        String pID = getIDTiket(Lista4.getSelectedItem().toString());
        String pInfo = cargaInfoTiketVerde(pID);
        jTextArea1.append(pInfo);        
        jTextArea1.append("\n___________________________________________");
    }//GEN-LAST:event_Lista4ItemStateChanged

    private void Lista4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lista4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Lista4ActionPerformed

    private void Lista5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Lista5ItemStateChanged
        // TODO add your handling code here:
        jTextArea1.setText("             **INFORMACIÓN DEL TIKET**");        
        String pID = getIDTiket(Lista5.getSelectedItem().toString());
        String pInfo = cargaInfoTiketRojo(pID);
        jTextArea1.append(pInfo);        
        jTextArea1.append("\n___________________________________________");
    }//GEN-LAST:event_Lista5ItemStateChanged

    private void Lista5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lista5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Lista5ActionPerformed

    private void Lista6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Lista6ItemStateChanged
        // TODO add your handling code here:
        jTextArea1.setText("             **INFORMACIÓN DEL TIKET**");        
        String pID = getIDTiket(Lista6.getSelectedItem().toString());
        String pInfo = cargaInfoTiketAmarillo(pID);
        jTextArea1.append(pInfo);        
        jTextArea1.append("\n___________________________________________");
    }//GEN-LAST:event_Lista6ItemStateChanged

    private void Lista6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lista6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Lista6ActionPerformed

    private void btStatusMonitoreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStatusMonitoreoActionPerformed
        VnServidorReportes ventanaMonitoreo = new VnServidorReportes(this);
        Reporte.actualizarReportesDeCantidades();
        ManejadorDeListas.setActividadReciente(this.servidor.getHistorial());
        ventanaMonitoreo.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btStatusMonitoreoActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.List Lista;
    public static java.awt.List Lista4;
    public static java.awt.List Lista5;
    public static java.awt.List Lista6;
    private javax.swing.JButton btStatusMonitoreo;
    private javax.swing.JButton btnCargarTickets;
    private javax.swing.JButton btnSetComoAmarillo;
    private javax.swing.JButton btnSetComoRojo;
    private javax.swing.JButton btnSetComoVERDE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnEmpleadosConectados;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
    }

   
    
   
}
