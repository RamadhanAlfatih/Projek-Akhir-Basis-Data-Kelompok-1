package com.example.demo;

public class Pegawai {
    String IdPegawai;
    String Nama;
    String JenisKelamin;
    String NoTelp;
    String Alamat;

    public Pegawai(String idPegawai, String nama, String jenisKelamin, String noTelp, String alamat) {
        IdPegawai = idPegawai;
        Nama = nama;
        JenisKelamin = jenisKelamin;
        NoTelp = noTelp;
        Alamat = alamat;
    }

    public String getIdPegawai() {
        return IdPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        IdPegawai = idPegawai;
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
