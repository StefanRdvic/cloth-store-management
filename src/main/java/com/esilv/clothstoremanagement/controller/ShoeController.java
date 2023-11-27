package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Shoe;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ShoeController extends AbstractProductController<Shoe>{
    @FXML
    private ChoiceBox<Float> sizeChoiceBox;

    @FXML
    private TableColumn<Shoe, Integer> sizeColumn;

    @Override
    protected void initialize() {
        super.initialize();
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    @Override
    protected void onSelection(Shoe item) {
        super.onSelection(item);
        sizeChoiceBox.setDisable(false);
        sizeChoiceBox.setValue(item.getSize());
    }

    @Override
    protected void onDeselection() {
        super.onDeselection();
        sizeChoiceBox.setDisable(false);
        sizeChoiceBox.setValue(null);
        sizeChoiceBox.getSelectionModel().clearSelection();
    }

    @Override
    protected void onMultipleSelection() {
        super.onMultipleSelection();
        sizeChoiceBox.getSelectionModel().clearSelection();
        sizeChoiceBox.setValue(null);
        sizeChoiceBox.setDisable(true);
    }

    @Override
    protected List<Shoe> fetch(int pageNumber, String searchValue) {
        return RepositoryProvider
                .provider()
                .getRepository(Shoe.class)
                .findAll(pageSize(), pageNumber, searchValue);
    }

    @Override
    protected long count(String searchValue) {
        return RepositoryProvider
                .provider()
                .getRepository(Shoe.class)
                .count(searchValue);
    }

    @Override
    protected void create() {
        RepositoryProvider
                .provider()
                .getRepository(Shoe.class)
                .save(Shoe.builder()
                        .name(nameTextField.getText())
                        .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                        .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                        .discount(Float.parseFloat(discountTextField.getText()))
                        .size(sizeChoiceBox.getValue())
                        .build());
    }

    @Override
    protected void delete(Shoe item) {
        RepositoryProvider.provider().getRepository(Shoe.class).delete(item);
    }

    @Override
    protected Shoe retrieveUpdated() {
        return tableView
                .getSelectionModel()
                .getSelectedItem()
                .toBuilder()
                .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                .discount(Float.parseFloat(discountTextField.getText()))
                .stock(Integer.parseInt(stockTextField.getText()))
                .size(sizeChoiceBox.getValue())
                .build();
    }

    @Override
    protected void update(Shoe item) {
        RepositoryProvider.provider().getRepository(Shoe.class).update(item);
    }
}
