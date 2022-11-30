package com.example.demo;

import java.sql.Date;

public class HistoriLaundryObj {
    String NomorCucian;
    Date TglCucian;
    String JenisCucian;
    String TipeCucian;
    float BeratCucian;

    public HistoriLaundryObj(String nomorCucian, Date tglCucian, String jenisCucian, String tipeCucian, float beratCucian) {
        NomorCucian = nomorCucian;
        TglCucian = tglCucian;
        JenisCucian = jenisCucian;
        TipeCucian = tipeCucian;
        BeratCucian = beratCucian;
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

    public float getBeratCucian() {
        return BeratCucian;
    }

    public void setBeratCucian(float beratCucian) {
        BeratCucian = beratCucian;
    }
}
