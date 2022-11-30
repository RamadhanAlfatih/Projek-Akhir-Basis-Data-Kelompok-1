package com.example.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

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
            String str3 = null;
            if (rs.next()){
                str1 = rs.getString("JenisCucian");
                str2 = rs.getString("TipeCucian");
                str3 = rs.getString("BeratCucian");
            }
            //get harga
            Statement st1 = connectDB.createStatement();
            String sql1 = "Execute dbo.GetHarga ('"+str1+"', '"+str2+"')";
            ResultSet rs1 = st1.executeQuery(sql1);
            String str11 = null;
            if (rs1.next()){
                str11 = rs1.getString("HargaCucian");
            }
            //get TotalHarga
            Statement st2 = connectDB.createStatement();
            String sql2 = "Execute dbo.GetTotalHarga ('"+str1+"', '"+str2+"')";
            ResultSet rs2 = st2.executeQuery(sql2);
            String total = null;
            if (rs2.next()){
                total = rs2.getString("HargaCucian");
            }

            sqlStatement.setString(3, total);
            sqlStatement.setString(4, nocucianText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Data berhasil disimpan!");
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
}
