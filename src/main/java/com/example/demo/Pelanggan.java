package com.example.demo;

public class Pelanggan {
    int NoPelanggan;
    String Nama;
    String JenisKelamin;
    String NoTelp;
    String Alamat;

    public Pelanggan(int noPelanggan, String nama, String jenisKelamin, String noTelp, String alamat) {
        NoPelanggan = noPelanggan;
        Nama = nama;
        JenisKelamin = jenisKelamin;
        NoTelp = noTelp;
        Alamat = alamat;
    }

    public int getNoPelanggan() {
        return NoPelanggan;
    }

    public void setNoPelanggan(int noPelanggan) {
        NoPelanggan = noPelanggan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getNoTelp() {
        return NoTelp;
    }

    public void setNoTelp(String noTelp) {
        NoTelp = noTelp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
}
