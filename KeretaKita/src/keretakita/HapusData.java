/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keretakita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author faisal
 */
public class HapusData extends javax.swing.JFrame {
    
    private static final long serialVersionUID=1L;
    Connection koneksi=null;
    ResultSet rs;
    Statement st;
    String url=("jdbc:mysql://localhost/keretakita");
    String User=("root");
    String Password=("");

    
    public Connection getConnection(){
   try{
       koneksi = DriverManager.getConnection(url,User,Password);
   }catch(SQLException e){
       System.out.println(e.getMessage());
   }
   return koneksi;
  }
    
    private void hapusdata(){
        koneksi = getConnection();
        Statement statement = null;
        String nik = TxtNik.getText();
        final String query ="DELETE FROM pemesanan WHERE nik = "+"\""+nik+"\"";
        try {
            statement=koneksi.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Di hapus ke database");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<pemesanan> listData(String isiFieldSearch){
      ArrayList<pemesanan> dataList=new ArrayList<pemesanan>();
      try{
          koneksi=getConnection();
          st=koneksi.createStatement();
          String Query=("SELECT * FROM `pemesanan`"
                  +"WHERE CONCAT (`nama_pemesan`,`nama_penumpang`,`nik`,"
                  + "`asal`,`tujuan`,`nama_kereta`,`kelas`)"
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
      ArrayList<pemesanan>Psn2 = listData(TxtNik.getText());
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
      
      
    public HapusData() {
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

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtNik = new javax.swing.JTextField();
        BtnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableData = new javax.swing.JTable();
        BtnHapus = new javax.swing.JButton();
        BtnKembali = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Masukan NIK Pemesan Yang Akan Di Hapus");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 290, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("NIK : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        TxtNik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNikActionPerformed(evt);
            }
        });
        getContentPane().add(TxtNik, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 150, -1));

        BtnCari.setText("Cari");
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 90, -1));

        TableData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableData);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 350, 90));

        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(BtnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 70, -1));

        BtnKembali.setText("Kembali");
        BtnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(BtnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/keretakita/Group (2).png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNikActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here
        int dialogButton = JOptionPane.YES_NO_OPTION;
        if(TxtNik.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Harap Masukan NIK",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
         if(JOptionPane.showConfirmDialog (null, "Hapus Pesanan ?","Perhatian",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                hapusdata();
                TableData.setModel(new DefaultTableModel());
        }   
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKembaliActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_BtnKembaliActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        // TODO add your handling code here:
        TampilkanPemesanan();
    }//GEN-LAST:event_BtnCariActionPerformed

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
            java.util.logging.Logger.getLogger(HapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HapusData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCari;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKembali;
    private javax.swing.JTable TableData;
    private javax.swing.JTextField TxtNik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
