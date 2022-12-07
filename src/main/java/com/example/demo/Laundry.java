package com.example.demo;
import java.sql.Date;

public class Laundry {
    int NomorCucian;
    Date TglCucian;
    String JenisCucian;
    String TipeCucian;
    String BeratCucian;
    int NoPelanggan;

    public Laundry(int nomorCucian, Date tglCucian, String jenisCucian, String tipeCucian, String beratCucian, int noPelanggan) {
        NomorCucian = nomorCucian;
        TglCucian = tglCucian;
        JenisCucian = jenisCucian;
        TipeCucian = tipeCucian;
        BeratCucian = beratCucian;
        NoPelanggan = noPelanggan;
    }

    public int getNomorCucian() {
        return NomorCucian;
    }

    public void setNomorCucian(int nomorCucian) {
        NomorCucian = nomorCucian;
    }

    public Date getTglCucian() {
        return TglCucian;
    }

    public void setTglCucian(Date tglCucian) {
        TglCucian = tglCucian;
    }

    public String getJenisCucian() {
        return JenisCucian;
    }

    public void setJenisCucian(String jenisCucian) {
        JenisCucian = jenisCucian;
    }

    public String getTipeCucian() {
        return TipeCucian;
    }

    public void setTipeCucian(String tipeCucian) {
        TipeCucian = tipeCucian;
    }

    public String getBeratCucian() {
        return BeratCucian;
    }

    public void setBeratCucian(String beratCucian) {
        BeratCucian = beratCucian;
    }

    public int getNoPelanggan() {
        return NoPelanggan;
    }

    public void setNoPelanggan(int noPelanggan) {
        NoPelanggan = noPelanggan;
    }
}
