package com.example.ptp03_2072008.controller;

import com.example.ptp03_2072008.HelloApplication;
import com.example.ptp03_2072008.model.barangModel;
import com.example.ptp03_2072008.model.supplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class supplierController {
    @FXML
    private TableView <supplierModel> tableSupp;
    @FXML
    private TableColumn<Integer, supplierModel> idSupp;
    @FXML
    private TableColumn<String, supplierModel> namaSupp;
    @FXML
    private TableColumn<String, supplierModel> alamatSupp;
    @FXML
    private TextField idSup;
    @FXML
    private TextField namaSup;
    @FXML
    private TextField alamatSup;
    @FXML
    private ObservableList <supplierModel> suppList;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    private int indeks;

    public void initialize() {
        suppList = FXCollections.observableArrayList();
        tableSupp.setItems(suppList);
        idSupp.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaSupp.setCellValueFactory(new PropertyValueFactory<>("nama"));
        alamatSupp.setCellValueFactory(new PropertyValueFactory<>( "alamat"));
    }

    public void closeSupplier(ActionEvent actionEvent) {
        setSuppList(suppList);
        idSup.getScene().getWindow().hide();
    }

    public void addSupplier(ActionEvent actionEvent) {
        suppList.add(new supplierModel(Integer.parseInt(idSup.getText()), namaSup.getText(), alamatSup.getText()));
        reset();
    }

    public void reset() {
        idSup.clear();
        namaSup.clear();
        alamatSup.clear();
        btnAdd.setDisable(false);
        btnReset.setDisable(false);
        btnUpdate.setDisable(false);
    }

    public void updateSupplier(ActionEvent actionEvent) {
        suppList.set(indeks, new supplierModel(Integer.parseInt(idSup.getText()), namaSup.getText(), alamatSup.getText()));
        reset();
    }

    public void getSelectedItem(MouseEvent mouseEvent) {
        if (!tableSupp.getSelectionModel().getSelectedCells().isEmpty()) {
            btnAdd.setDisable(true);
            btnReset.setDisable(false);
            btnUpdate.setDisable(false);
            indeks = tableSupp.getSelectionModel().getSelectedIndex();
        }
        idSup.setText(String.valueOf(tableSupp.getSelectionModel().getSelectedItem().getId()));
        namaSup.setText(tableSupp.getSelectionModel().getSelectedItem().getNama());
        alamatSup.setText(tableSupp.getSelectionModel().getSelectedItem().getAlamat());
    }

    public ObservableList<supplierModel> getSuppList() {
        return suppList;
    }

    public void setSuppList(ObservableList<supplierModel> suppList) {
        this.suppList = suppList;
    }
}
