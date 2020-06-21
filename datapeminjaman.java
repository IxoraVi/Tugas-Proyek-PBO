import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import koneksi.koneksi;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class datapeminjaman extends javax.swing.JFrame {
    Statement st;
    Connection koneksi;
    ResultSet Res;
    ResultSet Resbuku;
    ResultSet Resanggota;
    String tanggal="";
    
    
    public datapeminjaman() {
        initComponents();
        koneksi();
        tampilpinjam();
        pilihuser();
        pilihbuku();
        waktu();
    }
    
    private void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan","root","");
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    
    private void tampilpinjam() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Peminjaman");
        model.addColumn("Stambuk");
        model.addColumn("Nama");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Total Pinjam");
        
        tabelpinjam.setModel(model);
        try {
            st = koneksi.createStatement();
            Res = st.executeQuery("select * from peminjaman");
            while (Res.next()) {
                model.addRow(new Object[]{
                    Res.getString("noPeminjaman"),
                    Res.getString("stambuk"),
                    Res.getString("namaAnggota"),
                    Res.getDate("tanggalPinjam"),
                    Res.getInt("totalPinjam"),
                });
                tabelpinjam.setModel(model);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "Gagal");
        }
    }
    
    private void pilihuser() {
        cbtstambuk.removeAllItems();
        cbtstambuk.addItem("Select");
        try {
            String sql = "select * from anggota";
            st = koneksi.createStatement();
            Resanggota = st.executeQuery(sql);
            while (Resanggota.next()) {
                String stambuk = Resanggota.getString("stambuk");
                cbtstambuk.addItem(stambuk);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal");
        }
    }
    
    private void pilihbuku() {
        cbtkode.removeAllItems();
        cbtkode.addItem("Select");
        try {
            String sql = "select * from buku";
            st = koneksi.createStatement();
            Resbuku = st.executeQuery(sql);
            while (Resbuku.next()) {
                String kode = Resbuku.getString("kodeBuku");
                cbtkode.addItem(kode);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal");
        }
    }
    
    private void waktu(){
        Date tgl = new Date();
        jpinjam.setDate(tgl);
    }
    
    private void prosestambah() {
        try {
            DefaultTableModel model = (DefaultTableModel) detailpeminjaman.getModel();
            String[]data = new String[5];
            data[0]=String.valueOf(cbtstambuk.getSelectedItem());
            data[1]=txtnama.getText();
            data[2]=String.valueOf(cbtkode.getSelectedItem());
            data[3]=txtjudul.getText();
            data[4]=txtjml.getText();
            model.addRow(data);
        }
        catch (Exception e) {
            
        }
    }
    
    private void total_pinjam() {
        int jmlbaris = detailpeminjaman.getRowCount();
        int jmlpin =0, totpin = 0;
        
        TableModel tbl;
        tbl = detailpeminjaman.getModel();
        for(int a=0; a<jmlbaris; a++) {
            totpin=jmlpin;
            jmlpin=totpin+1;
        }
        txtjml.setText(String.valueOf(totpin));
        txttot.setText(String.valueOf(jmlpin));
        
    }
    
    private void simpandetail() {
        int jmlbaris = detailpeminjaman.getRowCount();
        if ( jmlbaris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tabel masih kosng");
        }
        else {
            try {
                int i=0;
                while (i<jmlbaris) {
                    st.executeUpdate("insert into detailpinjam"
                    +"(noPeminjaman,kodeBuku,jumlahPinjam)"
                    +"values('"+txtnopinjam.getText()+"',"
                    +"'"+detailpeminjaman.getValueAt(i, 0)+"',"
                    +"'"+txtjml.getText()+"')");
                i++;
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal menyimpan"+e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpinjam = new javax.swing.JTable();
        txtnopinjam = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        cbtstambuk = new javax.swing.JComboBox<>();
        cbtkode = new javax.swing.JComboBox<>();
        txtjudul = new javax.swing.JTextField();
        jpinjam = new com.toedter.calendar.JDateChooser();
        txtjml = new javax.swing.JTextField();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        bpengembalian = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailpeminjaman = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttot = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DATA PEMINJAMAN BUKU");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setText("STAMBUK");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setText("NAMA");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("KODE BUKU");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("JUDUL BUKU");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("TANGGAL PINJAM");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setText("JUMLAH PINJAM");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setText("NO PEMINJAMAN");

        tabelpinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelpinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpinjamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpinjam);

        txtnopinjam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnopinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnopinjamKeyPressed(evt);
            }
        });

        txtnama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbtstambuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbtstambuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtstambukActionPerformed(evt);
            }
        });

        cbtkode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbtkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtkodeActionPerformed(evt);
            }
        });

        txtjudul.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jpinjam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jpinjamPropertyChange(evt);
            }
        });

        txtjml.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        bsimpan.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bhapus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bbatal.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bbatal.setText("BATAL");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        bkeluar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bkeluar.setText("KELUAR");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        btambah.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btambah.setText("+ BUKU");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bpengembalian.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bpengembalian.setText("PENGEMBALIAN");
        bpengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpengembalianActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setText("DATA PEMINJAM");

        detailpeminjaman.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        detailpeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stambuk", "Nama", "Kode Buku", "Judul", "Jumlah Pinjam"
            }
        ));
        jScrollPane2.setViewportView(detailpeminjaman);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setText("DATA PEMINJAMAN BUKU");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setText("TOTAL PINJAM");

        txttot.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setText("Maksimal 3 Buku");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bpengembalian)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txttot, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnopinjam)
                    .addComponent(cbtstambuk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnama)
                    .addComponent(cbtkode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtjudul)
                    .addComponent(jpinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btambah)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(299, 299, 299))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bbatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bkeluar)
                .addGap(248, 248, 248))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtnopinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbtstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtjudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83)
                                .addComponent(jpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsimpan)
                    .addComponent(bhapus)
                    .addComponent(bbatal)
                    .addComponent(bkeluar))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(txttot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bpengembalian))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbtstambukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtstambukActionPerformed
        try {
            String sql = "select * from anggota where stambuk='"+cbtstambuk.getSelectedItem()+"'";
            st = koneksi.createStatement();
            Resanggota = st.executeQuery(sql);
            while (Resanggota.next()) {
                txtnama.setText(Resanggota.getString("namaAnggota"));
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_cbtstambukActionPerformed

    private void cbtkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtkodeActionPerformed
        try {
            String sql = "select * from buku where kodeBuku='"+cbtkode.getSelectedItem()+"'";
            st = koneksi.createStatement();
            Resbuku = st.executeQuery(sql);
            while (Resbuku.next()) {
                txtjudul.setText(Resbuku.getString("judulBuku"));
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_cbtkodeActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        prosestambah();
        total_pinjam();
    }//GEN-LAST:event_btambahActionPerformed

    private void jpinjamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpinjamPropertyChange
        if (jpinjam.getDate() !=null) {
            SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
            tanggal = String.valueOf(format.format(jpinjam.getDate()));
            }
    }//GEN-LAST:event_jpinjamPropertyChange

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String nopin = txtnopinjam.getText();
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
            tanggal = String.valueOf(format.format(jpinjam.getDate()));
        String stambuk = cbtstambuk.getItemAt(cbtstambuk.getSelectedIndex()).toString();
        String nama = txtnama.getText();
        String kode = cbtkode.getItemAt(cbtkode.getSelectedIndex()).toString();
        String judul = txtjudul.getText();
        String tot = txttot.getText();
        simpandetail();
        try {
            st = koneksi.createStatement();
            st.executeUpdate("insert into peminjaman VALUES ('"+ nopin +"','"
                    + stambuk +"','"
                    + nama +"','"
                    + tanggal +"','"
                    + tot +"');");
            tampilpinjam();
            JOptionPane.showMessageDialog(null, "Data Behasil Disimpan");
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
         String nopin = txtnopinjam.getText();
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
            tanggal = String.valueOf(format.format(jpinjam.getDate()));
        String stambuk = cbtstambuk.getItemAt(cbtstambuk.getSelectedIndex()).toString();
        String nama = txtnama.getText();
        String kode = cbtkode.getItemAt(cbtkode.getSelectedIndex()).toString();
        String judul = txtjudul.getText();
        String jml = txtjml.getText();
        
        try {
            st = koneksi.createStatement();
            st.executeUpdate("delete from peminjaman where stambuk='"+stambuk+"' OR kodeBuku='"+kode+"';");
            tampilpinjam();
            JOptionPane.showMessageDialog(null, "Terhapus");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Terhapus\n"+e.getMessage());
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void tabelpinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpinjamMouseClicked
        int a = tabelpinjam.getSelectedRow();
        if (a == -1){
            return;
        }
        String nopin = tabelpinjam.getValueAt(a, 0).toString();
        txtnopinjam.setText(nopin);
        
        String stambuk = tabelpinjam.getValueAt(a, 1).toString();
        cbtstambuk.setSelectedItem(stambuk);
        
        String nama = tabelpinjam.getValueAt(a, 2).toString();
        txtnama.setText(nama);      
        
        String pinjam = tabelpinjam.getValueAt(a, 5).toString();
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=f.parse(pinjam);
            jpinjam.setDate(d);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        String jml = tabelpinjam.getValueAt(a, 6).toString();
        txtjml.setText(jml);
    }//GEN-LAST:event_tabelpinjamMouseClicked

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        cbtstambuk.setSelectedItem("Select");
        txtnama.setText("");
        txtnopinjam.setText("");
        cbtkode.setSelectedItem("Select");
        txtjudul.setText("");
        txtjml.setText("");
        txtnopinjam.requestFocus();
    }//GEN-LAST:event_bbatalActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Apakah anda ingin Keluar?","Yakin",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_bkeluarActionPerformed

    private void bpengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpengembalianActionPerformed
        new pengembalian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bpengembalianActionPerformed

    private void txtnopinjamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnopinjamKeyPressed
        String nopin = txtnopinjam.getText();
        int ascii = evt.getKeyCode();
        if(ascii==10){
            try {
                String sql = "select * from peminajaman where noPeminjaman='"+nopin+"'";
                st = koneksi.createStatement();
                Res = st.executeQuery(sql);
                while (Res.next()) {
                    jpinjam.setDateFormatString(Res.getString("tanggalPinjam"));
                    cbtstambuk.setSelectedItem(Res.getString("stambuk"));
                    cbtkode.setSelectedItem(Res.getString("kodeBuku"));
                    txttot.setText(Res.getString("totalPinjam"));
                }
            }
            catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Data tidak ditemukan"+e.getMessage());
                jpinjam.requestFocus();
            }
        }
    }//GEN-LAST:event_txtnopinjamKeyPressed

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
            java.util.logging.Logger.getLogger(datapeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datapeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datapeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datapeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datapeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bpengembalian;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JComboBox<String> cbtkode;
    private javax.swing.JComboBox<String> cbtstambuk;
    private javax.swing.JTable detailpeminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jpinjam;
    private javax.swing.JTable tabelpinjam;
    private javax.swing.JTextField txtjml;
    private javax.swing.JTextField txtjudul;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnopinjam;
    private javax.swing.JTextField txttot;
    // End of variables declaration//GEN-END:variables
}