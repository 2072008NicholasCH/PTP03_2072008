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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class barangController {

    @FXML
    private TableView <barangModel> tableBarang;
    @FXML
    private TableColumn <Integer, barangModel> idBarang;
    @FXML
    private TableColumn <String, barangModel> namaBarang;
    @FXML
    private TableColumn <String, barangModel> suppBarang;
    @FXML
    private TextField idBrg;
    @FXML
    private TextField namaBrg;
    @FXML
    private ComboBox<String> cBoxSup;
    @FXML
    private ObservableList <barangModel> barangList;
    private ObservableList <supplierModel> suppList;
    private ObservableList <String> namaSupp;
    private FXMLLoader loader;
    private Stage stage;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label idLabel;
    private int indeks;

    public void initialize() throws IOException {
        barangList = FXCollections.observableArrayList();
        tableBarang.setItems(barangList);
        idBarang.setCellValueFactory(new PropertyValueFactory <> ("id"));
        namaBarang.setCellValueFactory(new PropertyValueFactory <> ("nama"));
        suppBarang.setCellValueFactory(new PropertyValueFactory <> ("supplier"));
        loader = new FXMLLoader(HelloApplication.class.getResource("supplier-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage = new Stage();
        stage.setTitle("Supplier Management");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public void showSupplier(ActionEvent actionEvent) {
        stage.showAndWait();
        supplierController supController = loader.getController();
        suppList = supController.getSuppList();
        namaSupp = FXCollections.observableArrayList();
        for(supplierModel supp: suppList) {
            namaSupp.add(supp.getNama());
        }
        cBoxSup.setItems(namaSupp);
        cBoxSup.getSelectionModel().select(0);

    }

    public void closeApplication(ActionEvent actionEvent) {
        idBrg.getScene().getWindow().hide();
    }

    public void getSelectionItem(MouseEvent mouseEvent) {
        if (!tableBarang.getSelectionModel().getSelectedCells().isEmpty()) {
            btnAdd.setDisable(true);
            btnReset.setDisable(false);
            btnUpdate.setDisable(false);
            indeks = tableBarang.getSelectionModel().getSelectedIndex();
        }
        idBrg.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItem().getId()));
        namaBrg.setText(tableBarang.getSelectionModel().getSelectedItem().getNama());
        cBoxSup.setValue(tableBarang.getSelectionModel().getSelectedItem().getSupplier());
    }

    public void addBarang() {
        barangList.add(new barangModel(Integer.parseInt(idBrg.getText()), namaBrg.getText(), cBoxSup.getValue()));
        reset();
    }

    public void reset() {
        idBrg.clear();
        namaBrg.clear();
        cBoxSup.getSelectionModel().select(0);
        btnAdd.setDisable(false);
        btnReset.setDisable(false);
        btnUpdate.setDisable(false);
    }

    public void updateBarang() {
        barangList.set(indeks, new barangModel(Integer.parseInt(idBrg.getText()), namaBrg.getText(), cBoxSup.getValue()));
        reset();
    }
}
