package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Cloth;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.validation.Validator;

import java.util.List;

import static com.esilv.clothstoremanagement.controller.AbstractProductController.SelectionState.MULTIPLE_SELECTION;

@Slf4j
public class ClothController extends AbstractProductController<Cloth>{

    @FXML
    private TableColumn<Cloth, Integer> sizeColumn;

    @FXML
    private ChoiceBox<String> sizeChoiceBox;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void setItemValidation() {
        super.setItemValidation();
        itemValidation.registerValidator(sizeChoiceBox, Validator.createEmptyValidator("size is required"));
    }

    @Override
    protected void setTableView() {
        super.setTableView();
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    @Override
    protected void setFormState(SelectionState state) {
        super.setFormState(state);
        sizeChoiceBox.setDisable(state.equals(MULTIPLE_SELECTION));
    }

    @Override
    protected void setFormValue(Cloth item) {
        super.setFormValue(item);
        sizeChoiceBox.getSelectionModel().select(item == null ? "" : item.getSize());
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
                        .retailPrice(Double.parseDouble(retailPriceTextField.getText()))
                        .resellPrice(Double.parseDouble(resellPriceTextField.getText()))
                        .stock(Integer.parseInt(stockTextField.getText()))
                        .discount(Double.parseDouble(discountTextField.getText()))
                        .size(sizeChoiceBox.getValue())
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
                .retailPrice(Double.parseDouble(retailPriceTextField.getText()))
                .resellPrice(Double.parseDouble(resellPriceTextField.getText()))
                .discount(Double.parseDouble(discountTextField.getText()))
                .size(sizeChoiceBox.getValue())
                .build();
    }

    @Override
    protected void update(Cloth item) {
        RepositoryProvider.provider().getRepository(Cloth.class).update(item);
    }

    @Override
    protected void updateStock(int quantity, Cloth item) {
        RepositoryProvider.provider().getRepository(Cloth.class).update(
                item.toBuilder().stock(item.getStock() + quantity).build()
        );
    }
}

