package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class cobadulu {
    @FXML
    private TextField alamatText;
    @FXML
    private TextField namaText;
    @FXML
    private TextField notelpText;

    @FXML
    private Button gasLaundry;

    @FXML
    private ToggleGroup jenisCucianRB;

    @FXML
    private Button kembali;

    @FXML
    private RadioButton kiloanRB;

    @FXML
    private RadioButton premiumRB;

    @FXML
    private RadioButton satuanRB;

    @FXML
    private RadioButton standarRB;

    @FXML
    private ToggleGroup tipeCucianRB;
    private Date localdate;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }
    @FXML
    private void clickLaundry() throws IOException, SQLException {
        GasLaundry();
    }
    public void GasLaundry() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "Update Pelanggan set Alamat = '"+alamatText.getText()+"' where Nama = '"+namaText.getText()+"' or NoTelp = '"+notelpText.getText()+"'";
        String queryPesanan = "insert into Pesanan values (?,?,?,?,?,?)";
        int min = 1000;
        int max = 1000000;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String randomGen = String.valueOf(random_int);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();

        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();

            Statement st = connectDB.createStatement();
            String sql = "Select NoPelanggan from Pelanggan where Alamat ='"+alamatText.getText()+"' or NoTelp ='"+notelpText.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            String str1 = null;
            if (rs.next()){
                str1 = rs.getString("NoPelanggan");
            }


            ResultSet execute = null;
            PreparedStatement sqlStatement1 = connectDB.prepareStatement(queryPesanan, Statement.RETURN_GENERATED_KEYS);
            sqlStatement1.setString(1,randomGen.trim());
            sqlStatement1.setString(2,now.toString());
            String value3;
            if (jenisCucianRB.getSelectedToggle().toString().contains("Satuan")) {
                value3 = "Satuan";
            } else {
                value3 = "Kiloan";
            }
            sqlStatement1.setString(3,value3);
            String value4;
            if (tipeCucianRB.getSelectedToggle().toString().contains("Standar")) {
                value4 = "Standar";
            } else {
                value4 = "Premium";
            }
            sqlStatement1.setString(4,value4);
            sqlStatement1.setString(5,null);
            sqlStatement1.setString(6,str1);
            sqlStatement1.execute();
            execute = sqlStatement1.getGeneratedKeys();
            HelloApplication.setRoot("FrontEnd/coba2");
        }catch (Exception e){
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }

    }
}
