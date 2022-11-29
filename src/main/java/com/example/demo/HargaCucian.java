package com.example.demo;

public class HargaCucian {
    String JenisCucian;
    String TipeCucian;
    int HargaCuci;

    public HargaCucian(String jenisCucian, String tipeCucian, int hargaCuci) {
        JenisCucian = jenisCucian;
        TipeCucian = tipeCucian;
        HargaCuci = hargaCuci;
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

    public int getHargaCuci() {
        return HargaCuci;
    }

    public void setHargaCuci(int hargaCuci) {
        HargaCuci = hargaCuci;
    }
}
