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
    private void clickBayar() throws IOException, SQLException {
        bayarLaundry();
    }
    @FXML
    private void clickCekHarga() throws IOException, SQLException {
        cekHarga();
    }
    public void bayarLaundry() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String noCucian = nocucianText.getText();
        String query = "Insert into Pembayaran (JenisPembayaran, TotalHarga, NomorCucian) values (?,?,?)";

        Statement cek = connectDB.createStatement();
        String sequel = "Select BeratCucian from Pesanan where NomorCucian= '"+nocucianText.getText()+"'";
        ResultSet resultSet = cek.executeQuery(sequel);
        String stringgg = null;
        try {
            if (resultSet.next()){
                stringgg = resultSet.getString("BeratCucian");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (stringgg == null){
            connectNow.MyAlert("warning", "Warning", "Berat laundry masih belum ditimbang!");
        }else {
            try {
                PreparedStatement sqlStatement = connectDB.prepareStatement(query);
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
                sqlStatement.setString(1, value.trim());
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

                sqlStatement.setString(2, String.valueOf(total));
                sqlStatement.setString(3, nocucianText.getText().trim());
                sqlStatement.execute();


                connectNow.MyAlert("info", "Informasi", "Laundry berhasil dibayar!");
            } catch (Exception e) {
                e.printStackTrace();
                connectNow.MyAlert("info", "Warning", String.valueOf(e));
            }
        }




    }
    public void cekHarga() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String noCucian = nocucianText.getText();

        Statement cek = connectDB.createStatement();
        String sequel = "Select BeratCucian from Pesanan where NomorCucian= '"+nocucianText.getText()+"'";
        ResultSet resultSet = cek.executeQuery(sequel);
        String stringgg = null;
        try {
            if (resultSet.next()){
                stringgg = resultSet.getString("BeratCucian");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (stringgg == null){
            connectNow.MyAlert("warning", "Warning", "Berat laundry masih belum ditimbang!");
        }else {
            try {
                //get jeniscucian dan tipecucian
                Statement st = connectDB.createStatement();
                String sql = "Select TOP 1 JenisCucian, TipeCucian, BeratCucian from Pesanan a, Pembayaran b " +
                        "where a.NomorCucian = '"+nocucianText.getText()+"'";
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
}
