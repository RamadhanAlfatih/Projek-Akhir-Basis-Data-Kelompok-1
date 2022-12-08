package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TampilanNota implements Initializable {

    @FXML
    private Label beratcucianLabel;

    @FXML
    private Label jeniscucianLabel;

    @FXML
    private Label jenispembayaranLabel;

    @FXML
    private Label namapegawaiLabel;

    @FXML
    private Label namapelangganLabel;

    @FXML
    private Label nocucianLabel;

    @FXML
    private Label nopembayaranLabel;

    @FXML
    private Label tgglLabel;

    @FXML
    private Label tipecucianLabel;

    @FXML
    private Label totalhargaLabel;

    @FXML
    private Label alamatLabel;

    DatabaseConnection connectNow = new DatabaseConnection();
    @FXML
    private void clickBack() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
    }

    public void Update(){
        Connection connectDB = connectNow.getConnection();

        int nomorCucian = HistoriLaundry.getNomorCucian();
//        connectNow.MyAlert("info", "Informasi", String.valueOf(nomorCucian));

        try {

            //Get No Pembayaran
            Statement st = connectDB.createStatement();
            ResultSet rs = st.executeQuery("Select NoPembayaran from Pembayaran where NomorCucian = '"+nomorCucian+"'");
            String str = null;
            if (rs.next()){
                str = rs.getString("NoPembayaran");
            }
            nopembayaranLabel.setText(str);

            //Get Nama Pelanggan
            Statement st1 = connectDB.createStatement();
            ResultSet rs1 = st1.executeQuery("Select TOP 1 Nama from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str1 = null;
            if (rs1.next()){
                str1 = rs1.getString("Nama");
            }
            namapelangganLabel.setText(str1);

            //Get No Cucian
            nocucianLabel.setText(String.valueOf(nomorCucian));

            //Get Tanggal
            Statement st2 = connectDB.createStatement();
            ResultSet rs2 = st2.executeQuery("Select TOP 1 b.TglCucian from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str2 = null;
            if (rs2.next()){
                str2 = rs2.getString("TglCucian");
            }
            tgglLabel.setText(str2);

            //Get Pegawai
            Statement st3 = connectDB.createStatement();
            ResultSet rs3 = st3.executeQuery("Select b.Nama from Pembayaran a, Pegawai b where a.IdPegawai=b.IdPegawai and a.NomorCucian = '"+nomorCucian+"'");
            String str3 = null;
            if (rs3.next()){
                str3 = rs3.getString("Nama");
            }
            namapegawaiLabel.setText(str3);

            //Get Jenis Cucian
            Statement st4 = connectDB.createStatement();
            ResultSet rs4 = st4.executeQuery("Select TOP 1 b.JenisCucian from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str4 = null;
            if (rs4.next()){
                str4 = rs4.getString("JenisCucian");
            }
            jeniscucianLabel.setText(str4);

            //Get Tipe Cucian
            Statement st5 = connectDB.createStatement();
            ResultSet rs5 = st5.executeQuery("Select TOP 1 b.TipeCucian from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str5 = null;
            if (rs5.next()){
                str5 = rs5.getString("TipeCucian");
            }
            tipecucianLabel.setText(str5);

            //Get Berat Cucian
            Statement st6 = connectDB.createStatement();
            ResultSet rs6 = st6.executeQuery("Select TOP 1 b.BeratCucian from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str6 = null;
            if (rs6.next()){
                str6 = rs6.getString("BeratCucian");
            }
            beratcucianLabel.setText(str6);

            //Get Jenis Pembayaran
            Statement st7 = connectDB.createStatement();
            ResultSet rs7 = st7.executeQuery("Select a.JenisPembayaran from Pembayaran a, Pegawai b where a.IdPegawai=b.IdPegawai and a.NomorCucian = '"+nomorCucian+"'");
            String str7 = null;
            if (rs7.next()){
                str7 = rs7.getString("JenisPembayaran");
            }
            jenispembayaranLabel.setText(str7);

            //Get Alamat
            Statement st8 = connectDB.createStatement();
            ResultSet rs8 = st8.executeQuery("Select TOP 1 c.Alamat from Pembayaran a, Pesanan b, Pelanggan c\n" +
                    "where a.NomorCucian=b.NomorCucian and b.NoPelanggan=c.NoPelanggan and b.NomorCucian ='"+nomorCucian+"'");
            String str8 = null;
            if (rs8.next()){
                str8 = rs8.getString("Alamat");
            }
            alamatLabel.setText(str8);


            //Get Total Harga
            Statement st9 = connectDB.createStatement();
            ResultSet rs9 = st9.executeQuery("Select a.TotalHarga from Pembayaran a, Pegawai b where a.IdPegawai=b.IdPegawai and a.NomorCucian = '"+nomorCucian+"'");
            String str9 = null;
            if (rs9.next()){
                str9 = rs9.getString("TotalHarga");
            }
            totalhargaLabel.setText(str9);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
