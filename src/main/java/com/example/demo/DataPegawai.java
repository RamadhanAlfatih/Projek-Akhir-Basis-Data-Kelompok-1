package com.example.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DataPegawai implements Initializable {
    @FXML
    private void clickTambah() throws IOException {
        tambahPegawai();
    }
    @FXML
    private void clickEdit() throws IOException {
        editPegawai();
    }
    @FXML
    private void clickHapus() throws IOException {
        hapusData();
    }
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
    @FXML
    private TableColumn<Pegawai, String> alamatCol;

    @FXML
    private TextField alamatText;

    @FXML
    private TextField cariText;

    @FXML
    private Button edit;

    @FXML
    private Button hapus;

    @FXML
    private TableColumn<Pegawai, Integer> idPegawaiCol;

    @FXML
    private TextField idPegawaiText;

    @FXML
    private TableColumn<Pegawai, String> jeniskelaminCol;

    @FXML
    private TextField jenisKelaminText;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<Pegawai, String> namaCol;

    @FXML
    private TextField namaText;

    @FXML
    private TableColumn<Pegawai, String> notelpCol;

    @FXML
    private TextField notelpText;

    @FXML
    private TableView<Pegawai> pegawaiTable;
    @FXML
    private void clickTabel() throws IOException {
        index = pegawaiTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idPegawaiText.setText(String.valueOf(idPegawaiCol.getCellData(index)));
        namaText.setText(namaCol.getCellData(index));
        jenisKelaminText.setText(jeniskelaminCol.getCellData(index));
        notelpText.setText(notelpCol.getCellData(index));
        alamatText.setText(alamatCol.getCellData(index));
    }

    @FXML
    private Button tambah;
    ObservableList<Pegawai> listM;
    ObservableList<Pegawai> dataList;
    int index = -1;
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
        cariData();
    }
    public void Update() {
        idPegawaiCol.setCellValueFactory(new PropertyValueFactory<>("IdPegawai"));
        namaCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Nama"));
        jeniskelaminCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("JenisKelamin"));
        notelpCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("NoTelp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Alamat"));

        listM = DataPegawai.getDatausers();
        pegawaiTable.setItems(listM);
    }
    public static ObservableList<Pegawai> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Pegawai> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("Select * from Pegawai");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new Pegawai(result.getInt("IdPegawai"), result.getString("Nama"), result.getString("JenisKelamin"),
                        result.getString("NoTelp"), result.getString("Alamat")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void cariData() {
        idPegawaiCol.setCellValueFactory(new PropertyValueFactory<Pegawai, Integer>("IdPegawai"));
        namaCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Nama"));
        jeniskelaminCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("JenisKelamin"));
        notelpCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("NoTelp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Alamat"));

        dataList = DataPegawai.getDatausers();
        pegawaiTable.setItems(dataList);
        FilteredList<Pegawai> filteredData = new FilteredList<>(dataList, b -> true);
        cariText.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(person.getIdPegawai()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getNama().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getJenisKelamin().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getNoTelp().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getAlamat().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        }));
        SortedList<Pegawai> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(pegawaiTable.comparatorProperty());
        pegawaiTable.setItems(sortedData);
    }
    public void tambahPegawai() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Insert into Pegawai (Nama, JenisKelamin, NoTelp, Alamat) values (?,?,?,?)";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, namaText.getText().trim());
            sqlStatement.setString(2, jenisKelaminText.getText().trim());
            sqlStatement.setString(3, notelpText.getText());
            sqlStatement.setString(4, alamatText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Data berhasil disimpan!");
            Update();
        } catch (SQLServerException conflict) {
            connectNow.MyAlert("info", "Warning", String.valueOf(conflict));
//            conflict.getStackTrace();
        } catch (Exception e) {
//            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void editPegawai() {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String value1 = idPegawaiText.getText().trim();
            String value2 = namaText.getText().trim();
            String value3 = jenisKelaminText.getText().trim();
            String value4 = notelpText.getText().trim();
            String value5 = alamatText.getText().trim();
            String query = "Update Pegawai Set Nama='" + value2 + "',JenisKelamin='" + value3 + "',NoTelp='" + value4 + "',Alamat='" +
                    value5 + "' where IdPegawai='" + value1 + "'";
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();
            connectNow.MyAlert("info", "Informasi", "Data berhasil diedit!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void hapusData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Delete from Pegawai where IdPegawai = ?";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, idPegawaiText.getText());
            sqlStatement.execute();
            connectNow.MyAlert("info", "Warning", "Data berhasil dihapus!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }


}
