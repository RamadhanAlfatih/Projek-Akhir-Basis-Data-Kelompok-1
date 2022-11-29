package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DataHarga implements Initializable {
    @FXML
    private Button edit;

    @FXML
    private TableColumn<HargaCucian, Integer> hargacuciCol;

    @FXML
    private TextField hargacuciText;

    @FXML
    private TableView<HargaCucian> hargacucianTabel;

    @FXML
    private TableColumn<HargaCucian, String> jeniscucianCol;

    @FXML
    private Label jeniscucianLabel;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<HargaCucian, String> tipecucianCol;

    @FXML
    private Label tipecucianLabel;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
    @FXML
    private void clickEdit() throws IOException {
        editJenis();
    }
    @FXML

    int index = -1;
    @FXML
    private void clickhargaTabel() throws IOException {
        index = hargacucianTabel.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        hargacuciText.setText(String.valueOf(hargacuciCol.getCellData(index)));
        jeniscucianLabel.setText(jeniscucianCol.getCellData(index));
        tipecucianLabel.setText(tipecucianCol.getCellData(index));
    }
    @FXML
    public static ObservableList<HargaCucian> getDatausers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<HargaCucian> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("Select * from HargaCuci");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(new HargaCucian(result.getString("JenisCucian"),result.getString("TipeCucian"), result.getInt("HargaCucian")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<HargaCucian> listM;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
    }
    public void Update() {
        jeniscucianCol.setCellValueFactory(new PropertyValueFactory<HargaCucian, String>("JenisCucian"));
        tipecucianCol.setCellValueFactory(new PropertyValueFactory<HargaCucian, String>("TipeCucian"));
        hargacuciCol.setCellValueFactory(new PropertyValueFactory<HargaCucian, Integer>("HargaCuci"));

        listM = DataHarga.getDatausers();
        hargacucianTabel.setItems(listM);
    }
    public void editJenis() {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String value1 = hargacuciText.getText();
            String value2 = jeniscucianLabel.getText();
            String query = "Update HargaCuci Set HargaCucian='" + value1 + "' where JenisCucian='" + value2 + "'";
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();
            connectNow.MyAlert("info", "Informasi", "Data berhasil diedit!");
            Update();
        } catch (Exception e) {
            connectNow.MyAlert("info", "Warning", String.valueOf(e));
        }
    }
}
