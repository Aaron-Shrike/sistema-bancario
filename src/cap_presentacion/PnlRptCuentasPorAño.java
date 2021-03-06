/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap_presentacion;

import cap_logica.Cuenta;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sh
 */
public class PnlRptCuentasPorAño extends javax.swing.JPanel {

    /**
     * Creates new form PnlRptCuentasPorAño
     */
    public PnlRptCuentasPorAño() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txTitulo = new javax.swing.JLabel();
        txAño = new javax.swing.JLabel();
        ycAño = new com.toedter.calendar.JYearChooser();
        btListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLista = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 204));
        setLayout(new java.awt.GridBagLayout());

        txTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txTitulo.setText("> Listado de Cuentas creadas en un año especifico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 70, 0, 0);
        add(txTitulo, gridBagConstraints);

        txAño.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txAño.setText("Año:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 200, 0, 0);
        add(txAño, gridBagConstraints);

        ycAño.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ycAño.setPreferredSize(new java.awt.Dimension(63, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 10, 0, 0);
        add(ycAño, gridBagConstraints);

        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh x32.png"))); // NOI18N
        btListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 200, 0, 0);
        add(btListar, gridBagConstraints);

        tbLista.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(tbLista);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 50, 50);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        // TODO add your handling code here:
        try{
            double auxAño = ycAño.getYear();
            DefaultTableModel modelo = new DefaultTableModel();
            Cuenta objC = new Cuenta();
            ResultSet  resultado = objC.listarCuentasPorAño(auxAño);
            
            int nroColumnas = resultado.getMetaData().getColumnCount();
        
            for (int i=0; i<nroColumnas; i++){
                modelo.addColumn(resultado.getMetaData().getColumnLabel(i+1).toUpperCase());
            }
        
            Object datos[] = new Object[nroColumnas];
            
            while (resultado.next()){
                for(int i=0; i<nroColumnas;i++){
                    datos[i] = resultado.getObject(i+1);
                }
                
                modelo.addRow(datos);
            }
            tbLista.setModel(modelo);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(this,error.getMessage(),
                "ERROR DEL SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btListar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbLista;
    private javax.swing.JLabel txAño;
    private javax.swing.JLabel txTitulo;
    private com.toedter.calendar.JYearChooser ycAño;
    // End of variables declaration//GEN-END:variables
}
