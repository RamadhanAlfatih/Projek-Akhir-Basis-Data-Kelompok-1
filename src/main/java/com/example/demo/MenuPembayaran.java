package com.example.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MenuPembayaran {
    @FXML
    private Button bayar;

    @FXML
    private RadioButton cashRB;

    @FXML
    private RadioButton gopayRB;

    @FXML
    private Label hargaLabel;

    @FXML
    private Button kembali;

    @FXML
    private ToggleGroup metodePembayaranRB;

    @FXML
    private TextField nocucianText;

    @FXML
    private RadioButton qrisRB;

    @FXML
    private RadioButton shopeepayRB;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }
    @FXML
    private void clickBayar() throws IOException {
        bayarLaundry();
    }
    @FXML
    private void clickCekHarga() throws IOException{
        cekHarga();
    }
    public void bayarLaundry(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "Insert into Pembayaran (NoPembayaran,JenisPembayaran,TotalHarga,NomorCucian) values (?,?,?,?)";
        //random gen
        int min = 1000;
        int max = 1000000;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String randomGen = String.valueOf(random_int);

        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, randomGen.trim());
            String value;
            if (metodePembayaranRB.getSelectedToggle().toString().contains("Cash")) {
                value = "Cash";
            } else if (metodePembayaranRB.getSelectedToggle().toString().contains("Qris")){
                value = "Qris";
            } else if (metodePembayaranRB.getSelectedToggle().toString().contains("Gopay")) {
                value = "Gopay";
            } else {
                value = "Shopeepay";
            }
            sqlStatement.setString(2, value.trim());
            //get jeniscucian dan tipecucian
            Statement st = connectDB.createStatement();
            String sql = "Select JenisCucian, TipeCucian, BeratCucian from Pesanan a, Pembayaran b " +
                    "where a.NomorCucian = b.NomorCucian and a.NomorCucian = '"+nocucianText.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            String str1 = null;
            String str2 = null;
            double str3 = 0;
            if (rs.next()){
                str1 = rs.getString("JenisCucian");
                str2 = rs.getString("TipeCucian");
                str3 = rs.getDouble("BeratCucian");
            }
            //get harga
            Statement st1 = connectDB.createStatement();
            String sql1 = "Execute dbo.GetHarga '"+str1+"', '"+str2+"'";
            ResultSet rs1 = st1.executeQuery(sql1);
            double str11 = 0;
            if (rs1.next()){
                str11 = rs1.getDouble("HargaCucian");
            }
            //get TotalHarga
            Statement st2 = connectDB.createStatement();
            String sql2 = "Select dbo.GetTotalHarga ('"+str3+"', '"+str11+"') as totalharga";
            ResultSet rs2 = st2.executeQuery(sql2);
            double total = 0;
            if (rs2.next()){
                total = rs2.getDouble("totalharga");
            }

            sqlStatement.setString(3, String.valueOf(total));
            sqlStatement.setString(4, nocucianText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Laundry berhasil dibayar!");
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void cekHarga() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String noCucian = nocucianText.getText();

        try {
            //get jeniscucian dan tipecucian
            Statement st = connectDB.createStatement();
            String sql = "Select JenisCucian, TipeCucian, BeratCucian from Pesanan a, Pembayaran b " +
                    "where a.NomorCucian = b.NomorCucian and a.NomorCucian = '"+nocucianText.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            String str1 = null;
            String str2 = null;
            double str3 = 0;
            if (rs.next()){
                str1 = rs.getString("JenisCucian");
                str2 = rs.getString("TipeCucian");
                str3 = rs.getDouble("BeratCucian");
            }
            //get harga
            Statement st1 = connectDB.createStatement();
            String sql1 = "Execute dbo.GetHarga '"+str1+"', '"+str2+"'";
            ResultSet rs1 = st1.executeQuery(sql1);
            double str11 = 0;
            if (rs1.next()){
                str11 = rs1.getDouble("HargaCucian");
            }
            //get TotalHarga
            Statement st2 = connectDB.createStatement();
            String sql2 = "Select dbo.GetTotalHarga ('"+str3+"', '"+str11+"') as totalharga";
            ResultSet rs2 = st2.executeQuery(sql2);
            double total = 0;
            if (rs2.next()){
                total = rs2.getDouble("totalharga");
            }
            hargaLabel.setText(String.valueOf(total));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
