package com.example.demo;

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

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoriLaundry implements Initializable {
    @FXML
    private TableColumn<HistoriLaundryObj, Float> beratcucinCol;

    @FXML
    private TextField cari;

    @FXML
    private TableView<HistoriLaundryObj> historylaundryTabel;

    @FXML
    private TableColumn<HistoriLaundryObj, String> jeniscucianCol;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<HistoriLaundryObj, Integer> nocucianCol;

    @FXML
    private TextField nocucianText;

    @FXML
    private Button nota;

    @FXML
    private TableColumn<HistoriLaundryObj, Date> tglcucianCol;

    @FXML
    private TableColumn<HistoriLaundryObj, String> tipecucianCol;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }
    public static int nomorCucian;
    public static int getNomorCucian() {
        return nomorCucian;
    }

    public void setNomorCucian(int nomorCucian) {
        HistoriLaundry.nomorCucian = nomorCucian;
    }

    @FXML
    private void clickNota() throws IOException {
        setNomorCucian(Integer.parseInt(nocucianText.getText()));
        HelloApplication.setRoot("FrontEnd/TampilanNota");
    }
    int index = -1;
    @FXML
    private void clickTabel() throws IOException {
        index = historylaundryTabel.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        nocucianText.setText(String.valueOf(nocucianCol.getCellData(index)));
    }
    ObservableList<HistoriLaundryObj> listM;
    ObservableList<HistoriLaundryObj> dataList;
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
        nocucianCol.setCellValueFactory(new PropertyValueFactory<>("NomorCucian"));
        jeniscucianCol.setCellValueFactory(new PropertyValueFactory<>("JenisCucian"));
        tipecucianCol.setCellValueFactory(new PropertyValueFactory<>("TipeCucian"));
        beratcucinCol.setCellValueFactory(new PropertyValueFactory<>("BeratCucian"));
        tglcucianCol.setCellValueFactory(new PropertyValueFactory<>("TglCucian"));

        listM = HistoriLaundry.getDatausers();
        historylaundryTabel.setItems(listM);
    }
    public static ObservableList<HistoriLaundryObj> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<HistoriLaundryObj> list = FXCollections.observableArrayList();
        try {
            Statement st = connectDB.createStatement();
            String sql = "Select Username from Temp";
            ResultSet rs = st.executeQuery(sql);
            String str1 = null;
            if (rs.next()){
                str1 = rs.getString("Username");
            }

            Statement st1 = connectDB.createStatement();
            String sql1 = "Select NoPelanggan from Pelanggan Where Nama = (Select Nama from LoginDataPelanggan where Username='"+str1+"') " +
                    "and NoTelp = (Select NoTelp from LoginDataPelanggan where Username='"+str1+"')";
            ResultSet rs1 = st1.executeQuery(sql1);
            String str11 = null;
            if (rs1.next()){
                str11 = rs1.getString("NoPelanggan");
            }

            PreparedStatement ps = connectDB.prepareStatement("Select NomorCucian, TglCucian, JenisCucian, TipeCucian, BeratCucian from Pesanan where NoPelanggan = '"+str11+"'");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new HistoriLaundryObj(result.getInt("NomorCucian"), result.getDate("TglCucian"), result.getString("JenisCucian"),
                        result.getString("TipeCucian"), result.getFloat("BeratCucian")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void cariData() {
        nocucianCol.setCellValueFactory(new PropertyValueFactory<>("NomorCucian"));
        jeniscucianCol.setCellValueFactory(new PropertyValueFactory<>("JenisCucian"));
        tipecucianCol.setCellValueFactory(new PropertyValueFactory<>("TipeCucian"));
        beratcucinCol.setCellValueFactory(new PropertyValueFactory<>("BeratCucian"));
        tglcucianCol.setCellValueFactory(new PropertyValueFactory<>("TglCucian"));

        dataList = HistoriLaundry.getDatausers();
        historylaundryTabel.setItems(dataList);
        FilteredList<HistoriLaundryObj> filteredData = new FilteredList<>(dataList, b -> true);
        cari.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(person.getNomorCucian()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getTglCucian().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getJenisCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (person.getTipeCucian().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(person.getBeratCucian()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        }));
        SortedList<HistoriLaundryObj> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(historylaundryTabel.comparatorProperty());
        historylaundryTabel.setItems(sortedData);
    }
}
