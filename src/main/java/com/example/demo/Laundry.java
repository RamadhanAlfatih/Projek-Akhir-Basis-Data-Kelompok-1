package com.example.demo;
import java.sql.Date;

public class Laundry {
    String NomorCucian;
    Date TglCucian;
    String JenisCucian;
    String TipeCucian;
    String BeratCucian;
    String NoPelanggan;

    public Laundry(String nomorCucian, Date tglCucian, String jenisCucian, String tipeCucian, String beratCucian, String noPelanggan) {
        NomorCucian = nomorCucian;
        TglCucian = tglCucian;
        JenisCucian = jenisCucian;
        TipeCucian = tipeCucian;
        BeratCucian = beratCucian;
        NoPelanggan = noPelanggan;
    }

    public String getNomorCucian() {
        return NomorCucian;
    }

    public void setNomorCucian(String nomorCucian) {
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

    public String getNoPelanggan() {
        return NoPelanggan;
    }

    public void setNoPelanggan(String noPelanggan) {
        NoPelanggan = noPelanggan;
    }
}
