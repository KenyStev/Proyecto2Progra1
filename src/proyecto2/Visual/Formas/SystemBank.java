/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Visual.Formas;

import proyecto2.Visual.Logica.Banco;
import proyecto2.Visual.Logica.SystemBanc;

/**
 *
 * @author zerokull
 */
public class SystemBank extends javax.swing.JFrame {

    /**
     * Creates new form SystemBank
     */
    public SystemBank() {
        initComponents();
        lblUser.setText(SystemBanc.bank.getActivo().getNombre()+" /");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogout = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        btnAddAccount = new javax.swing.JButton();
        btnDepositBalance = new javax.swing.JButton();
        btnRemoveBalance = new javax.swing.JButton();
        btnTransferBalance = new javax.swing.JButton();
        btnRecodInterest = new javax.swing.JButton();
        btnLookAccount = new javax.swing.JButton();
        btnCancelAccount = new javax.swing.JButton();
        btnCreateUser = new javax.swing.JButton();
        btnRecords = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu del Banco");

        lblLogout.setText("Cerrar Sesion");
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });

        lblUser.setText("jLabel1");

        btnAddAccount.setText("Agregar Cuenta");

        btnDepositBalance.setText("Depositar en cuenta");

        btnRemoveBalance.setText("Retirar de Cuenta");

        btnTransferBalance.setText("Transferencia a Terceros");

        btnRecodInterest.setText("Registrar Intereses");

        btnLookAccount.setText("Desactivar cuentas");

        btnCancelAccount.setText("Cancelar Cuenta");

        btnCreateUser.setText("Crear Usuarios");

        btnRecords.setText("Reportes");

        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogout))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTransferBalance)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddAccount)
                            .addComponent(btnDepositBalance)
                            .addComponent(btnRemoveBalance))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRecodInterest)
                            .addComponent(btnLookAccount)
                            .addComponent(btnCancelAccount))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExit)
                            .addComponent(btnRecords)
                            .addComponent(btnCreateUser))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogout)
                    .addComponent(lblUser))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAccount)
                    .addComponent(btnRecodInterest)
                    .addComponent(btnCreateUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepositBalance)
                    .addComponent(btnLookAccount)
                    .addComponent(btnRecords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveBalance)
                    .addComponent(btnCancelAccount)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransferBalance)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        SystemBanc.bank.logout();
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        SystemBanc.callsMenuBank(11);
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(SystemBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SystemBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SystemBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SystemBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SystemBank().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAccount;
    private javax.swing.JButton btnCancelAccount;
    private javax.swing.JButton btnCreateUser;
    private javax.swing.JButton btnDepositBalance;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLookAccount;
    private javax.swing.JButton btnRecodInterest;
    private javax.swing.JButton btnRecords;
    private javax.swing.JButton btnRemoveBalance;
    private javax.swing.JButton btnTransferBalance;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables
}
