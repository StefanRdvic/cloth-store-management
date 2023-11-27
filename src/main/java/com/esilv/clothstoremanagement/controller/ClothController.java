package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Cloth;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ClothController extends AbstractProductController<Cloth>{

    @FXML
    private TableColumn<Cloth, Integer> sizeColumn;

    @FXML
    private TextField sizeTextField;

    @Override
    protected void initialize() {
        super.initialize();
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    @Override
    protected void onSelection(Cloth item) {
        super.onSelection(item);
        sizeTextField.setDisable(false);
        sizeTextField.setText(String.valueOf(item.getSize()));
    }

    @Override
    protected void onDeselection() {
        super.onDeselection();
        sizeTextField.setDisable(false);
        sizeTextField.setText("");
    }

    @Override
    protected void onMultipleSelection() {
        super.onMultipleSelection();
        sizeTextField.setText("");
        sizeTextField.setDisable(true);
    }

    @Override
    protected List<Cloth> fetch(int pageNumber, String searchValue) {
        return RepositoryProvider
                .provider()
                .getRepository(Cloth.class)
                .findAll(pageSize(), pageNumber, searchValue);
    }

    @Override
    protected long count(String searchValue) {
        return RepositoryProvider
                .provider()
                .getRepository(Cloth.class)
                .count(searchValue);
    }

    @Override
    protected void create() {
        RepositoryProvider
                .provider()
                .getRepository(Cloth.class)
                .save(Cloth.builder()
                        .name(nameTextField.getText())
                        .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                        .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                        .discount(Float.parseFloat(discountTextField.getText()))
                        .size(Integer.parseInt(sizeTextField.getText()))
                        .build());
    }

    @Override
    protected void delete(Cloth item) {
        RepositoryProvider.provider().getRepository(Cloth.class).delete(item);
    }

    @Override
    protected Cloth retrieveUpdated() {
        return tableView
                .getSelectionModel()
                .getSelectedItem()
                .toBuilder()
                .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                .discount(Float.parseFloat(discountTextField.getText()))
                .stock(Integer.parseInt(stockTextField.getText()))
                .size(Integer.parseInt(sizeTextField.getText()))
                .build();
    }

    @Override
    protected void update(Cloth item) {
        RepositoryProvider.provider().getRepository(Cloth.class).update(item);
    }

}

