/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keretakita;


public class pemesanan {

    private final String namapemesan;
    private final String namapenumpang;
    private final String nik;
    private final String asal;
    private final String tujuan;
    private final String jadwal;
    private final String kelas;
    private final String kereta;

    
    public pemesanan(String NamaPemesan,String NamaPenumpang,String Nik,
            String JadwalBerangkat, String Asal, String Tujuan, String Kelas, String Kereta) {
        
        this.namapemesan = NamaPemesan;
        this.namapenumpang = NamaPenumpang;
        this.nik = Nik;
        this.asal = Asal;
        this.tujuan = Tujuan;
        this.jadwal = JadwalBerangkat;
        this.kelas = Kelas;
        this.kereta = Kereta;
        
    }
    
    

    public String getNamapemesan() {
        return namapemesan;
    }

    public String getNamapenumpang() {
        return namapenumpang;
    }

    public String getNik() {
        return nik;
    }

    public String getAsal() {
        return asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getJadwal() {
        return jadwal;
    }

    public String getKelas() {
        return kelas;
    }
    
    public String getKereta() {
        return kereta;
    }
}
