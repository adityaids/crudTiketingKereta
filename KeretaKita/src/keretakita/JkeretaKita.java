/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keretakita;

import com.toedter.calendar.JCalendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author faisal
 */
public class JkeretaKita extends javax.swing.JFrame {
    int type;
    Connection koneksi=null;
    ResultSet rs;
    Statement st;
    String url=("jdbc:mysql://localhost/keretakita");
    String User=("root");
    String Password=("");
    int jumlahpenumpang, hargakereta, totalharga;
    int hargadewasa = 100000;
    String namapemesan, asal, tujuan, kelas;
    DecimalFormat df = new DecimalFormat("#,###");
    
    
    public Connection getConnection(){
   try{
       koneksi =DriverManager.getConnection(url,User,Password);
   }catch(SQLException e){
       System.out.println(e.getMessage());
   }
   return koneksi;
  }
    
    public ArrayList<pemesanan> listData(String isiFieldSearch){
      ArrayList<pemesanan> dataList=new ArrayList<pemesanan>();
      try{
          koneksi=getConnection();
          st=koneksi.createStatement();
          String Query=("SELECT * FROM `pemesanan`"
                  +"WHERE CONCAT (`nama_pemesan`,`nama_penumpang`,"
                  + "`nik`,`asal`,`tujuan`,`nama_kereta`,`kelas`)"
                  +"LIKE '%"+isiFieldSearch+"%'");
          rs=st.executeQuery(Query);
          pemesanan psn;
          while (rs.next()){
              psn = new pemesanan(rs.getString("Nama_pemesan"),
                      rs.getString("Nama_penumpang"),
                      rs.getString("Nik"),
                      rs.getString("Asal"),
                      rs.getString("Tujuan"),
                      rs.getString("Jadwal_Berangkat"),
                      rs.getString("Kelas"),
                      rs.getString("nama_kereta"));
              dataList.add(psn);
          }
      }catch (SQLException e){
          System.out.println(e.getMessage());
      }
      return dataList;
          }
    
    
    public void TampilkanPemesanan(){
      ArrayList<pemesanan>Psn2 = listData(TxtNamaPemesan.getText());
      DefaultTableModel tModel = new DefaultTableModel();
      tModel.setColumnIdentifiers(new Object[]{"NIK","Nama Pemesan","Nama penumpang",
          "Asal","Tujuan","Jadwal","Kelas", "Kereta"});
      Object[] row = new Object[8];
      for (int i=0; i < Psn2.size(); i++){
      row[0]=Psn2.get(i).getNik();
      row[1]=Psn2.get(i).getNamapemesan();
      row[2]=Psn2.get(i).getNamapenumpang();
      row[3]=Psn2.get(i).getAsal();
      row[4]=Psn2.get(i).getTujuan();
      row[5]=Psn2.get(i).getJadwal();
      row[6]=Psn2.get(i).getKelas();
      row[7]=Psn2.get(i).getKereta();
      
      tModel.addRow(row);
  }
      TableData.setModel(tModel);
  }
    
    private void loadData(){
        koneksi = getConnection();
        Statement statement = null;
        namapemesan = TxtNamaPemesan.getText();
        String namapenumpang = TxtNamaPenumpang.getText();
        String nik = TxtNik.getText();
        asal = (String) CmbAsal.getSelectedItem();
        tujuan = (String) CmbTujuan.getSelectedItem();
        String namakereta = TxtNamaKereta.getText();
        String jadwal = String.valueOf(jDateChooser1.getDate());
        TxtJadwal.setText(jadwal);
        if(RdEkonomi.isSelected() == true ){
              kelas = "Ekonomi";
          }else if (RdBisnis.isSelected() == true){
              kelas = "Bisnis";
          }else {
              kelas = "Exclusive";
       }
        final String query ="INSERT INTO pemesanan (`nama_pemesan`,`nama_penumpang`,"
                + "`nik`,`asal`, `tujuan`, `jadwal_berangkat`, `kelas`, `nama_kereta`)"
                + "VALUES("+"\""+namapemesan+"\""+","+"\""
                +namapenumpang+"\""+","+"\""+nik+"\""+","+"\""+asal+"\""+","
                +"\""+tujuan+"\""+","+"\""+jadwal+"\""+","+"\""
                +kelas+"\""+","+"\""+namakereta+"\""+");";
        try {
            statement=koneksi.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Di simpan ke database");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    public JkeretaKita() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        TxtNamaPemesan = new javax.swing.JTextField();
        CmbAsal = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CmbTujuan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtJadwal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SpnDewasa = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        SpnAnak = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        TxtNamaPenumpang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TxtNik = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        RdEkonomi = new javax.swing.JRadioButton();
        RdBisnis = new javax.swing.JRadioButton();
        RdExecutive = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        CmbKodeKereta = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        TxtNamaKereta = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        TxtTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableData = new javax.swing.JTable();
        BtnSimpan = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        btnhitung = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KeretaKIta Pesan Tiket Online");
        setPreferredSize(new java.awt.Dimension(718, 588));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtNamaPemesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamaPemesanActionPerformed(evt);
            }
        });
        getContentPane().add(TxtNamaPemesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 160, 20));

        CmbAsal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kota", "Bandung", "Jakarta", "Yogyakarta", "Magelang", "Bekasi", "Banten", "Surabaya" }));
        getContentPane().add(CmbAsal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 160, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Kota Asal : ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nama Pemesan : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Kota Tujuan :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, 20));

        CmbTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kota", "Bandung", "Jakarta", "Yogyakarta", "Magelang", "Bekasi", "Banten", "Surabaya" }));
        getContentPane().add(CmbTujuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 160, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tanggal Keberangkatan : ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Jadwal Keberangkatan : ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        TxtJadwal.setEditable(false);
        TxtJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtJadwalActionPerformed(evt);
            }
        });
        getContentPane().add(TxtJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 140, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Jumlah Penumpang :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Dewasa");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));
        getContentPane().add(SpnDewasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 40, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Balita");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));
        getContentPane().add(SpnAnak, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 40, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Nama Penumpang :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        TxtNamaPenumpang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamaPenumpangActionPerformed(evt);
            }
        });
        getContentPane().add(TxtNamaPenumpang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 160, -1));

        jLabel11.setText("NIK         :");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        TxtNik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtNikKeyTyped(evt);
            }
        });
        getContentPane().add(TxtNik, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 160, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Kelas");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        buttonGroup1.add(RdEkonomi);
        RdEkonomi.setText("Ekonomi");
        RdEkonomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdEkonomiActionPerformed(evt);
            }
        });
        getContentPane().add(RdEkonomi, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        buttonGroup1.add(RdBisnis);
        RdBisnis.setText("Bisnis");
        RdBisnis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdBisnisActionPerformed(evt);
            }
        });
        getContentPane().add(RdBisnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        buttonGroup1.add(RdExecutive);
        RdExecutive.setText("Executive");
        getContentPane().add(RdExecutive, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Kode Kereta : ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        CmbKodeKereta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Salah Satu", "ARG", "PRH", "PTS" }));
        CmbKodeKereta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbKodeKeretaItemStateChanged(evt);
            }
        });
        CmbKodeKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbKodeKeretaActionPerformed(evt);
            }
        });
        getContentPane().add(CmbKodeKereta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 110, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nama Kereta : ");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        TxtNamaKereta.setEditable(false);
        TxtNamaKereta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(TxtNamaKereta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 110, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Total Harga : ");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        TxtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotalActionPerformed(evt);
            }
        });
        getContentPane().add(TxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 110, -1));

        TableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Jadwal", "Asal", "Tujuan", "Kelas", "Kereta"
            }
        ));
        jScrollPane1.setViewportView(TableData);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 700, 100));

        BtnSimpan.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(BtnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 500, 70, -1));

        BtnBatal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BtnBatal.setText("Batal");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(BtnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 60, -1));

        BtnHapus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(BtnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 70, -1));

        BtnKeluar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BtnKeluar.setText("Keluar");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, -1, -1));

        btnhitung.setText("Hitung");
        btnhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhitungActionPerformed(evt);
            }
        });
        getContentPane().add(btnhitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 160, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/keretakita/Group (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNamaPemesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamaPemesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaPemesanActionPerformed

    private void TxtJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtJadwalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtJadwalActionPerformed

    private void TxtNamaPenumpangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamaPenumpangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaPenumpangActionPerformed

    private void TxtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        new HapusData().setVisible(true);
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
            
            if(JOptionPane.showConfirmDialog (null, "Yakin Anda Ingin Keluar ?",
                    "Perhatian", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        // TODO add your handling code here:
        TableData.setModel(new DefaultTableModel());
        jDateChooser1.setDate(null);
        TxtNamaPemesan.setText("");
        TxtNamaPenumpang.setText("");
        TxtNik.setText("");
        TxtJadwal.setText("");
        CmbTujuan.setSelectedIndex(0);
        CmbAsal.setSelectedIndex(0);
        CmbKodeKereta.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        SpnDewasa.setValue(0);
        SpnAnak.setValue(0);
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void TxtNikKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNikKeyTyped
        // TODO add your handling code here:
        type = evt.getKeyChar();
        if(!Character.isDigit(type)){
            JOptionPane.showMessageDialog(null,"Hanya Berisi Angka", "Warning",
                    JOptionPane.WARNING_MESSAGE);
          evt.consume(); 
        }
        
        if(TxtNik.getText().length() > 20){
            JOptionPane.showMessageDialog(null,"NIK Tidak Lebih Dari 20 Karakter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
          evt.consume(); 
        }
    }//GEN-LAST:event_TxtNikKeyTyped

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        if(TxtNamaPemesan.getText() == null){
            JOptionPane.showMessageDialog(null, "Harap Masukan Nama Pemesan",
                    "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        if (TxtNamaKereta.getText() == null){
            JOptionPane.showMessageDialog(null, "Harap Pilih Kode Kereta",
                    "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        if(TxtNamaPenumpang.getText() != null && TxtNik.getText() != null
                && buttonGroup1.getSelection() != null && CmbAsal.getSelectedIndex() != 0
                && CmbTujuan.getSelectedIndex() != 0){
            loadData();
            TampilkanPemesanan(); 
       }
       
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void CmbKodeKeretaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbKodeKeretaItemStateChanged
        // TODO add your handling code here:
        switch (CmbKodeKereta.getSelectedIndex()) {
            case 1:
                TxtNamaKereta.setText("Argowillis");
                hargakereta = 110000;
                break;
            case 2:
                TxtNamaKereta.setText("Parahiangan");
                hargakereta = 130000;
                break;
            case 3:
                TxtNamaKereta.setText("patas");
                hargakereta = 150000;
                break;    
            default:
                TxtNamaKereta.setText("");
                break;
        }
    }//GEN-LAST:event_CmbKodeKeretaItemStateChanged

    private void btnhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhitungActionPerformed
        // TODO add your handling code here:
               
        int dewasa = (int) SpnDewasa.getValue();
        int anak = (int) SpnAnak.getValue();
        jumlahpenumpang = dewasa + anak;
        totalharga = (hargadewasa + hargakereta) * dewasa;
        
        if(TxtNamaPemesan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Anda Belum Mengisi Nama Penumpang",
                    "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        if(TxtNamaPenumpang.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Anda Belum Mengisi Nama Penumpang",
                    "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        if(TxtNik.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Anda Belum Mengisi Nik Penumpang",
                    "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        if(buttonGroup1.getSelection() == null){
            JOptionPane.showMessageDialog(null, "Harap Pilih Kelas", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        if(CmbAsal.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Asal Belum Dipilih", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        if(CmbTujuan.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Tujuan Belum Dipilih", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        if(TxtNamaPenumpang.getText() != null && TxtNik.getText() != null
                && buttonGroup1.getSelection() != null && CmbAsal.getSelectedIndex() != 0
                && CmbTujuan.getSelectedIndex() != 0){
            Date date = jDateChooser1.getDate();
            TxtJadwal.setText(String.valueOf(date));
            TxtTotal.setText(String.valueOf(df.format(totalharga)));
        }
        
    }//GEN-LAST:event_btnhitungActionPerformed

    private void CmbKodeKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbKodeKeretaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbKodeKeretaActionPerformed

    private void RdBisnisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdBisnisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RdBisnisActionPerformed

    private void RdEkonomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdEkonomiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RdEkonomiActionPerformed

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
            java.util.logging.Logger.getLogger(JkeretaKita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JkeretaKita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JkeretaKita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JkeretaKita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JkeretaKita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JComboBox<String> CmbAsal;
    private javax.swing.JComboBox<String> CmbKodeKereta;
    private javax.swing.JComboBox<String> CmbTujuan;
    private javax.swing.JRadioButton RdBisnis;
    private javax.swing.JRadioButton RdEkonomi;
    private javax.swing.JRadioButton RdExecutive;
    private javax.swing.JSpinner SpnAnak;
    private javax.swing.JSpinner SpnDewasa;
    private javax.swing.JTable TableData;
    private javax.swing.JTextField TxtJadwal;
    private javax.swing.JTextField TxtNamaKereta;
    private javax.swing.JTextField TxtNamaPemesan;
    private javax.swing.JTextField TxtNamaPenumpang;
    private javax.swing.JTextField TxtNik;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JButton btnhitung;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
