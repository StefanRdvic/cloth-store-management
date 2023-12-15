package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Shoe;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.validation.ValidationResult;

import java.util.List;

import static com.esilv.clothstoremanagement.controller.AbstractProductController.SelectionState.MULTIPLE_SELECTION;

/**
 * This class is used to manage the shoe view
 * author: Stefan Radovanovic
 * author: Yannick li
 */
public class ShoeController extends AbstractProductController<Shoe>{
    @FXML
    private TextField sizeTextField;

    @FXML
    private TableColumn<Shoe, Integer> sizeColumn;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void setItemValidation() {
        super.setItemValidation();
        itemValidation.registerValidator(sizeTextField, (Control c, String newValue) ->
                ValidationResult.fromErrorIf(c, "The size must be a positive number greater than 0.1",
                        !newValue.matches("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?") || Double.parseDouble(newValue) < 1));
    }

    @Override
    protected void setTableView() {
        super.setTableView();
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    @Override
    protected void setFormState(SelectionState state) {
        super.setFormState(state);
        sizeTextField.setDisable(state.equals(MULTIPLE_SELECTION));
    }

    @Override
    protected void setFormValue(Shoe item) {
        super.setFormValue(item);
        sizeTextField.setText(item == null ? "" : item.getSize() + "");
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
                        .retailPrice(Double.parseDouble(retailPriceTextField.getText()))
                        .resellPrice(Double.parseDouble(resellPriceTextField.getText()))
                        .stock(Integer.parseInt(stockTextField.getText()))
                        .discount(Double.parseDouble(discountTextField.getText()))
                        .size(Double.parseDouble(sizeTextField.getText()))
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
                .retailPrice(Double.parseDouble(retailPriceTextField.getText()))
                .resellPrice(Double.parseDouble(resellPriceTextField.getText()))
                .discount(Double.parseDouble(discountTextField.getText()))
                .size(Double.parseDouble(sizeTextField.getText()))
                .build();
    }

    @Override
    protected void update(Shoe item) {
        RepositoryProvider.provider().getRepository(Shoe.class).update(item);
    }

    @Override
    protected void updateStock(int quantity, Shoe item) {
        RepositoryProvider.provider().getRepository(Shoe.class).update(
                item.toBuilder().stock(item.getStock() + quantity).build()
        );
    }


}
