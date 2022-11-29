package com.example.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;

public class DataLaundry implements Initializable {
    @FXML
    private void clickTambah() throws IOException {
        tambahLaundry();
    }

    @FXML
    private void clickEdit() throws IOException {
        editLaundry();
    }

    @FXML
    private void clickHapus() throws IOException {
        hapusLaundry();
    }

    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }

    @FXML
    private TextField beratCucianText;
    @FXML
    private TextField noCucianText;
    @FXML
    private TextField noPelangganText;
    @FXML
    private TextField cariText;
    @FXML
    private RadioButton premiumRB;

    @FXML
    private RadioButton satuanRB;
    @FXML
    private void clickSatuanRb(){
        beratCucianText.setText("0");
    }

    @FXML
    private RadioButton kiloanRB;
    @FXML
    private RadioButton standarRB;
    @FXML
    private ToggleGroup tipeCucianRB;
    @FXML
    private ToggleGroup jenisCucianRB;
    @FXML
    private DatePicker tglCucianDate;
    @FXML
    private TableView<Laundry> laundryTable;
    @FXML
    private TableColumn<Laundry, String> noCucian;
    @FXML
    private TableColumn<Laundry, String> jenisCucian;
    @FXML
    private TableColumn<Laundry, String> tipeCucian;
    @FXML
    private TableColumn<Laundry, Date> tglCucian;
    @FXML
    private TableColumn<Laundry, String> beratCucian;
    @FXML
    private TableColumn<Laundry, String> noPelanggan;

    ObservableList<Laundry> listM;
    ObservableList<Laundry> dataList;
    int index = -1;
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private void clickTabel() throws IOException {
        index = laundryTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        noCucianText.setText(noCucian.getCellData(index));
        tglCucianDate.setValue(tglCucian.getCellData(index).toLocalDate());
        if (jenisCucian.getCellData(index).equalsIgnoreCase("Satuan")) {
            jenisCucianRB.selectToggle(satuanRB);
        } else {
            jenisCucianRB.selectToggle(kiloanRB);
        }
        if (tipeCucian.getCellData(index).equalsIgnoreCase("Standar")) {
            tipeCucianRB.selectToggle(standarRB);
        } else {
            tipeCucianRB.selectToggle(premiumRB);
        }
        beratCucianText.setText(beratCucian.getCellData(index));
        noPelangganText.setText(noPelanggan.getCellData(index));
    }

    public static ObservableList<Laundry> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Laundry> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("Select * from Pesanan");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new Laundry(result.getString("NomorCucian"), result.getDate("TglCucian"), result.getString("JenisCucian"),
                        result.getString("TipeCucian"), result.getString("BeratCucian"), result.getString("NoPelanggan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
        cariLaundry();
    }

    public void tambahLaundry() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Insert into Pesanan values (?,?,?,?,?,?)";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, noCucianText.getText().trim());
            sqlStatement.setString(2, tglCucianDate.getValue().toString());
            String value3;
            if (jenisCucianRB.getSelectedToggle().toString().contains("Satuan")) {
                value3 = "Satuan";
            } else {
                value3 = "Kiloan";
            }
            String value4;
            if (tipeCucianRB.getSelectedToggle().toString().contains("Standar")) {
                value4 = "Standar";
            } else {
                value4 = "Premium";
            }
            sqlStatement.setString(3, value3);
            sqlStatement.setString(4, value4);
            sqlStatement.setString(5, beratCucianText.getText().trim());
            sqlStatement.setString(6, noPelangganText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Data berhasil disimpan!");
            Update();
        } catch (SQLServerException conflict) {
            connectNow.MyAlert("info", "Warning", String.valueOf(conflict));
//            conflict.getStackTrace();
//            connectNow.MyAlert("info", "Warning","No Pelanggan tidak ada!");
        } catch (Exception e) {
//            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }

    public void cariLaundry() {
        noCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("NomorCucian"));
        tglCucian.setCellValueFactory(new PropertyValueFactory<Laundry, Date>("TglCucian"));
        jenisCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("JenisCucian"));
        tipeCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("TipeCucian"));
        beratCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("BeratCucian"));
        noPelanggan.setCellValueFactory(new PropertyValueFactory<Laundry, String>("NoPelanggan"));

        dataList = DataLaundry.getDatausers();
        laundryTable.setItems(dataList);
        FilteredList<Laundry> filteredData = new FilteredList<>(dataList, b -> true);
        cariText.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (person.getNomorCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getJenisCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getTipeCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getTglCucian().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getBeratCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getNoPelanggan().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        }));
        SortedList<Laundry> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(laundryTable.comparatorProperty());
        laundryTable.setItems(sortedData);
    }

    public void hapusLaundry() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Delete from Pesanan where NomorCucian = ?";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, noCucianText.getText());
            sqlStatement.execute();
            connectNow.MyAlert("info", "Warning", "Data berhasil dihapus!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }

    public void Update() {
        noCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("NomorCucian"));
        tglCucian.setCellValueFactory(new PropertyValueFactory<Laundry, Date>("TglCucian"));
        jenisCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("JenisCucian"));
        tipeCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("TipeCucian"));
        beratCucian.setCellValueFactory(new PropertyValueFactory<Laundry, String>("BeratCucian"));
        noPelanggan.setCellValueFactory(new PropertyValueFactory<Laundry, String>("NoPelanggan"));

        listM = DataLaundry.getDatausers();
        laundryTable.setItems(listM);
    }

    public void editLaundry() {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String value1 = noCucianText.getText().trim();
            String value2 = tglCucianDate.getValue().toString();
            String value3;
            if (jenisCucianRB.getSelectedToggle().toString().contains("Satuan")) {
                value3 = "Satuan";
            } else {
                value3 = "Kiloan";
            }
            String value4;
            if (tipeCucianRB.getSelectedToggle().toString().contains("Standar")) {
                value4 = "Standar";
            } else {
                value4 = "Premium";
            }
            String value5 = beratCucianText.getText().trim();
            String value6 = noPelangganText.getText().trim();
            String query = "Update Pesanan Set NomorCucian='" + value1 + "',TglCucian='" + value2 + "',JenisCucian='" + value3 + "',TipeCucian='" + value4 + "',BeratCucian='" +
                    value5 + "',NoPelanggan='" + value6 + "' where NomorCucian='" + value1 + "'";
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();
            connectNow.MyAlert("info", "Informasi", "Data berhasil diedit!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
}
