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

public class DataPelanggan implements Initializable {
    @FXML
    private void clickTambah() throws IOException {
        tambahPelanggan();
    }
    @FXML
    private void clickEdit() throws IOException {
        editPelanggan();
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
    private TableColumn<Pelanggan,String> alamatCol;

    @FXML
    private TextField alamatText;

    @FXML
    private TextField cariText;

    @FXML
    private Button edit;

    @FXML
    private Button hapus;

    @FXML
    private TextField noPelangganText;

    @FXML
    private TableColumn<Pelanggan,Integer> nopelangganCol;

    @FXML
    private TextField jenisKelaminText;

    @FXML
    private TableColumn<Pelanggan,String> jeniskelaminCol;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<Pelanggan,String> namaCol;

    @FXML
    private TextField namaText;

    @FXML
    private TableColumn<Pelanggan,String> notelpCol;

    @FXML
    private TextField notelpText;

    @FXML
    private TableView<Pelanggan> pelangganTabel;

    @FXML
    private Button tambah;
    int index = -1;
    @FXML
    private void clickTabel() throws IOException {
        index = pelangganTabel.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        noPelangganText.setText(String.valueOf(nopelangganCol.getCellData(index)));
        namaText.setText(namaCol.getCellData(index));
        jenisKelaminText.setText(jeniskelaminCol.getCellData(index));
        notelpText.setText(notelpCol.getCellData(index));
        alamatText.setText(alamatCol.getCellData(index));
    }

    ObservableList<Pelanggan> listM;
    ObservableList<Pelanggan> dataList;

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
        nopelangganCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, Integer>("NoPelanggan"));
        namaCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("Nama"));
        jeniskelaminCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("JenisKelamin"));
        notelpCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("NoTelp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("Alamat"));

        listM = DataPelanggan.getDatausers();
        pelangganTabel.setItems(listM);
    }
    public static ObservableList<Pelanggan> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Pelanggan> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("Select * from Pelanggan");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new Pelanggan(result.getInt("NoPelanggan"), result.getString("Nama"), result.getString("JenisKelamin"),
                        result.getString("NoTelp"), result.getString("Alamat")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void cariData() {
        nopelangganCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, Integer>("NoPelanggan"));
        namaCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("Nama"));
        jeniskelaminCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("JenisKelamin"));
        notelpCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("NoTelp"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("Alamat"));

        dataList = DataPelanggan.getDatausers();
        pelangganTabel.setItems(dataList);
        FilteredList<Pelanggan> filteredData = new FilteredList<>(dataList, b -> true);
        cariText.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(person.getNoPelanggan()).toLowerCase().contains(lowerCaseFilter)) {
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
        SortedList<Pelanggan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(pelangganTabel.comparatorProperty());
        pelangganTabel.setItems(sortedData);
    }
    public void tambahPelanggan() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Insert into Pelanggan (Nama, JenisKelamin, NoTelp, Alamat) values (?,?,?,?)";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);

            sqlStatement.setString(1, namaText.getText().trim());
            sqlStatement.setString(2, jenisKelaminText.getText().trim());
            sqlStatement.setString(3, notelpText.getText());
            sqlStatement.setString(4, alamatText.getText().trim());
            sqlStatement.execute();

            connectNow.MyAlert("info", "Informasi", "Data berhasil disimpan!");
            Update();
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void editPelanggan() {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String value1 = noPelangganText.getText().trim();
            String value2 = namaText.getText().trim();
            String value3 = jenisKelaminText.getText().trim();
            String value4 = notelpText.getText().trim();
            String value5 = alamatText.getText().trim();
            String query = "Update Pelanggan Set Nama='" + value2 + "',JenisKelamin='" + value3 + "',NoTelp='" + value4 + "',Alamat='" +
                    value5 + "' where NoPelanggan='" + value1 + "'";
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();
            connectNow.MyAlert("info", "Informasi", "Data berhasil diedit!");
            Update();
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
    public void hapusData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Delete from Pelanggan where NoPelanggan = ?";
        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.setString(1, noPelangganText.getText());
            sqlStatement.execute();
            connectNow.MyAlert("info", "Warning", "Data berhasil dihapus!");
            Update();
        } catch (Exception e) {
            e.printStackTrace();
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }

}
