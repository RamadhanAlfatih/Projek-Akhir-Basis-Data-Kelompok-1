package com.example.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DataPembayaran implements Initializable {
    @FXML
    private TextField cariText;

    @FXML
    private Button edit;

    @FXML
    private Button hapus;

    @FXML
    private TableColumn<Pembayaran, String> idpegawaiCol;

    @FXML
    private TextField idpegawaiText;

    @FXML
    private TableColumn<Pembayaran, String> jenispembayaranCol;

    @FXML
    private TextField jenispembayaranText;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<Pembayaran, String> nocucianCol;

    @FXML
    private TextField nocucianText;

    @FXML
    private TableColumn<Pembayaran, String> nopembayaranCol;

    @FXML
    private TextField nopembayaranText;

    @FXML
    private TableView<Pembayaran> pembayaranTabel;

    @FXML
    private Button tambah;

    @FXML
    private TableColumn<Pembayaran, Float> totalhargaCol;

    @FXML
    private TextField totalhargaText;

    @FXML
    private void clickTambah() throws IOException {
        tambahPembayaran();
    }
    @FXML
    private void clickEdit() throws IOException {
        editPembayaran();
    }
    @FXML
    private void clickHapus() throws IOException {
        hapusData();
    }
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
    int index = -1;
    @FXML
    private void clickTabel() throws IOException {
        index = pembayaranTabel.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        nopembayaranText.setText(nopembayaranCol.getCellData(index));
        jenispembayaranText.setText(jenispembayaranCol.getCellData(index));
        nocucianText.setText(nocucianCol.getCellData(index));
        idpegawaiText.setText(idpegawaiCol.getCellData(index));
        totalhargaText.setText(String.valueOf(totalhargaCol.getCellData(index)));
    }
    ObservableList<Pembayaran> listM;
    ObservableList<Pembayaran> dataList;

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
        nopembayaranCol.setCellValueFactory(new PropertyValueFactory<>("NoPembayaran"));
        jenispembayaranCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("JenisPembayaran"));
        nocucianCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("NomorCucian"));
        idpegawaiCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("IdPegawai"));
        totalhargaCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, Float>("TotalHarga"));

        listM = DataPembayaran.getDatausers();
        pembayaranTabel.setItems(listM);
    }
    public static ObservableList<Pembayaran> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Pembayaran> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("Select * from Pembayaran");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new Pembayaran(result.getString("NoPembayaran"), result.getString("JenisPembayaran"), result.getFloat("TotalHarga"),
                        result.getString("NomorCucian"), result.getString("IdPegawai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void cariData() {
        nopembayaranCol.setCellValueFactory(new PropertyValueFactory<>("NoPembayaran"));
        jenispembayaranCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("JenisPembayaran"));
        nocucianCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("NomorCucian"));
        idpegawaiCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, String>("IdPegawai"));
        totalhargaCol.setCellValueFactory(new PropertyValueFactory<Pembayaran, Float>("TotalHarga"));

        dataList = DataPembayaran.getDatausers();
        pembayaranTabel.setItems(dataList);
        FilteredList<Pembayaran> filteredData = new FilteredList<>(dataList, b -> true);
        cariText.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (person.getNoPembayaran().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getJenisPembayaran().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getNomorCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getIdPegawai().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(person.getTotalHarga()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        }));
        SortedList<Pembayaran> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(pembayaranTabel.comparatorProperty());
        pembayaranTabel.setItems(sortedData);
    }
    public void tambahPembayaran() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Insert into Pembayaran values (?,?,?,?,?)";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, nocucianText.getText().trim());
            sqlStatement.setString(2, jenispembayaranText.getText().trim());
            sqlStatement.setString(3, totalhargaText.getText().trim());
            sqlStatement.setString(4, nocucianText.getText());
            sqlStatement.setString(5, idpegawaiText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Data berhasil disimpan!");
            Update();
        } catch (SQLServerException conflict) {
            connectNow.MyAlert("info", "Warning", String.valueOf(conflict));
            conflict.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void editPembayaran() {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String value1 = nopembayaranText.getText().trim();
            String value2 = jenispembayaranText.getText().trim();
            String value3 = totalhargaText.getText().trim();
            String value4 = nocucianText.getText().trim();
            String value5 = idpegawaiText.getText().trim();
            String query = "Update Pembayaran Set NoPembayaran='" + value1 + "',JenisPembayaran='" + value2 + "',TotalHarga='" + value3 + "',NomorCucian='" + value4 + "',IdPegawai='" +
                    value5 + "' where NoPembayaran='" + value1 + "'";
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
        String query = "Delete from Pembayaran where NoPembayaran = ?";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, nopembayaranText.getText());
            sqlStatement.execute();
            connectNow.MyAlert("info", "Warning", "Data berhasil dihapus!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("warning", "Warning", String.valueOf(e));
        }
    }
}
