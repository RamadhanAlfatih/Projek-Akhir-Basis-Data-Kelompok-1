package com.example.demo;

public class Pembayaran {
    String NoPembayaran;
    String JenisPembayaran;
    float TotalHarga;
    String NomorCucian;
    String IdPegawai;

    public Pembayaran(String noPembayaran, String jenisPembayaran, float totalHarga, String nomorCucian, String idPegawai) {
        NoPembayaran = noPembayaran;
        JenisPembayaran = jenisPembayaran;
        TotalHarga = totalHarga;
        NomorCucian = nomorCucian;
        IdPegawai = idPegawai;
    }

    public String getNoPembayaran() {
        return NoPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
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

    public String getNomorCucian() {
        return NomorCucian;
    }

    public void setNomorCucian(String nomorCucian) {
        NomorCucian = nomorCucian;
    }

    public String getIdPegawai() {
        return IdPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        IdPegawai = idPegawai;
    }
}
