package com.example.demo;

public class Pembayaran {
    int NoPembayaran;
    String JenisPembayaran;
    float TotalHarga;
    int NomorCucian;
    int IdPegawai;

    public Pembayaran(int noPembayaran, String jenisPembayaran, float totalHarga, int nomorCucian, int idPegawai) {
        NoPembayaran = noPembayaran;
        JenisPembayaran = jenisPembayaran;
        TotalHarga = totalHarga;
        NomorCucian = nomorCucian;
        IdPegawai = idPegawai;
    }

    public int getNoPembayaran() {
        return NoPembayaran;
    }

    public void setNoPembayaran(int noPembayaran) {
        NoPembayaran = noPembayaran;
    }

    public String getJenisPembayaran() {
        return JenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        JenisPembayaran = jenisPembayaran;
    }

    public float getTotalHarga() {
        return TotalHarga;
    }

    public void setTotalHarga(float totalHarga) {
        TotalHarga = totalHarga;
    }

    public int getNomorCucian() {
        return NomorCucian;
    }

    public void setNomorCucian(int nomorCucian) {
        NomorCucian = nomorCucian;
    }

    public int getIdPegawai() {
        return IdPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        IdPegawai = idPegawai;
    }
}
