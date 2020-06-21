import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import koneksi.koneksi;

public class login extends javax.swing.JFrame {

    public login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtpass = new javax.swing.JTextField();
        blogin = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        bregis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        nama.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        nama.setText("USERNAME");

        pass.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        pass.setText("PASSWORD");

        txtnama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        blogin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        blogin.setText("LOGIN");
        blogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloginActionPerformed(evt);
            }
        });

        bexit.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bexit.setText("EXIT");
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });

        bregis.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bregis.setText("REGISTER");
        bregis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bregisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pass)
                                .addGap(18, 18, 18)
                                .addComponent(txtpass))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nama)
                                .addGap(18, 18, 18)
                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(blogin)
                        .addGap(18, 18, 18)
                        .addComponent(bexit)
                        .addGap(18, 18, 18)
                        .addComponent(bregis)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pass)
                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blogin)
                    .addComponent(bexit)
                    .addComponent(bregis))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloginActionPerformed
        String username = txtnama.getText();
        String password = txtpass.getText();
        this.setVisible(false);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan","root","");
            Statement statement = koneksi.createStatement();
            ResultSet res = statement.executeQuery("select * from login where username='"+username+"'and password='"+password+"'");
            System.out.println("Koneksi Berhasil");
            if (res.next()){
                JOptionPane.showMessageDialog(null, "Login Berhasil","Informasi",JOptionPane.INFORMATION_MESSAGE);
                new menuutama().setVisible(true);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Maaf Username atau Password salah","Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Gagal, Silahkan Ulangi","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bloginActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Apakah anda ingin Keluar?","Yakin",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_bexitActionPerformed

    private void bregisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bregisActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Apakah anda ingin membuat akun?","Yakin",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            new akun().show();
            this.dispose();
        } 
    }//GEN-LAST:event_bregisActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexit;
    private javax.swing.JButton blogin;
    private javax.swing.JButton bregis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel pass;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpass;
    // End of variables declaration//GEN-END:variables
}
