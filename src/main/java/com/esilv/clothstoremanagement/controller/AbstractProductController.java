package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Company;
import com.esilv.clothstoremanagement.model.entity.Product;
import com.esilv.clothstoremanagement.model.repository.CrudRepository;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.esilv.clothstoremanagement.controller.AbstractProductController.SelectionState.*;


@Slf4j
public abstract class AbstractProductController<T extends Product> {

    @Getter
    @Accessors(fluent = true)
    private static final int pageSize = 10;

    @FXML
    protected TextField searchTextField;

    @FXML
    protected Pagination pagination;

    @FXML
    protected TableView<T> tableView;

    @FXML
    protected TableColumn<T, Long> idColumn;

    @FXML
    protected TableColumn<T, String> nameColumn;

    @FXML
    protected TableColumn<T, Double> retailPriceColumn;

    @FXML
    protected TableColumn<T, Double> resellPriceColumn;

    @FXML
    protected TableColumn<T, Integer> stockColumn;

    @FXML
    protected TableColumn<T, Double> discountColumn;

    @FXML
    protected Button purchaseButton;

    @FXML
    protected Button sellButton;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField retailPriceTextField;

    @FXML
    protected TextField resellPriceTextField;

    @FXML
    protected TextField discountTextField;

    @FXML
    protected TextField stockTextField;

    @FXML
    protected Button updateButton;

    @FXML
    protected TextField quantityTextField;

    @FXML
    protected Button createButton;

    @FXML
    protected Button deleteButton;

    @FXML
    protected Button clearSearchButton;

    protected ValidationSupport itemValidation = new ValidationSupport();

    protected ValidationSupport quantityValidation = new ValidationSupport();

    @FXML
    void createOnAction(ActionEvent event) {
        create();

        //focus on the created item
        searchTextField.setText(nameTextField.getText());
        event.consume();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        tableView
                .getSelectionModel()
                .getSelectedItems()
                .forEach(this::delete);

        //refresh
        tableView.setItems(FXCollections.observableList(fetch(pagination.getCurrentPageIndex(), searchTextField.getText())));
        pagination.setPageCount(getPageCount());
        event.consume();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        update(retrieveUpdated());

        //refresh
        tableView.setItems(FXCollections.observableList(fetch(pagination.getCurrentPageIndex(), searchTextField.getText())));
        event.consume();
    }

    @FXML
    void purchaseOnAction(ActionEvent event) {
        CrudRepository<Company> repository = RepositoryProvider.provider().getRepository(Company.class);

        Company company = repository.findFirst();
        double cost = (getQuantity() * getValueFromItem(T::getRetailPrice, Double.MAX_VALUE));

        repository.update(company.toBuilder()
                .capital(company.getCapital() - cost)
                .globalCost(company.getGlobalCost() + cost)
                .build());

        updateStock(getQuantity(), tableView.getSelectionModel().getSelectedItem());

        //refresh
        tableView.setItems(FXCollections.observableList(fetch(pagination.getCurrentPageIndex(), searchTextField.getText())));
        event.consume();
    }

    @FXML
    void sellOnAction(ActionEvent event) {
        CrudRepository<Company> repository = RepositoryProvider.provider().getRepository(Company.class);

        Company company = repository.findFirst();
        double cost = (getQuantity() * getValueFromItem(T::getRetailPrice, Double.MAX_VALUE)) * getValueFromItem(T::getDiscount, Double.MAX_VALUE);

        repository.update(company.toBuilder()
                .capital(company.getCapital() + cost)
                .globalIncome(company.getGlobalIncome() + cost)
                .build());

        updateStock(-getQuantity(), tableView.getSelectionModel().getSelectedItem());

        //refresh
        tableView.setItems(FXCollections.observableList(fetch(pagination.getCurrentPageIndex(), searchTextField.getText())));
        event.consume();
    }

    @FXML
    void clearSearchOnAction(ActionEvent event) {
        searchTextField.setText("");
        event.consume();
    }

    @FXML
    void initialize() {
        setItemValidation();
        setQuantityForm();
        setSearchTextField();
        setTableView();
        setPagination();
    }

    protected void setItemValidation(){
        itemValidation.registerValidator(nameTextField, Validator.createEmptyValidator("Name is required"));

        itemValidation.registerValidator(retailPriceTextField, (Control c, String newValue) ->
                ValidationResult.fromErrorIf(c, "The retail price must be a positive number greater than 0.1",
                        !newValue.matches("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?") || Double.parseDouble(newValue) < 0.1));

        itemValidation.registerValidator(resellPriceTextField, (Control c, String newValue) ->
                ValidationResult.fromErrorIf(c, "The resell price must be a positive number greater than 0.1",
                        !newValue.matches("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?") || Double.parseDouble(newValue) < 0.1));

        itemValidation.registerValidator(stockTextField, (Control c, String newValue) ->
                ValidationResult.fromErrorIf(c, "The stock must be a positive integer greater than 1",
                        !newValue.matches("^[0-9]+$") || Integer.parseInt(newValue) < 1));

        itemValidation.registerValidator(discountTextField, (Control c, String newValue) ->{
            double val;
            return ValidationResult.fromErrorIf(c, "The discount must be a positive number betwenn 0 and  1",
                    !newValue.matches("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?") ||
                            ((val = Double.parseDouble(newValue)) < 0 || val > 1));
        });

        itemValidation.invalidProperty().addListener((obs, wasInvalid, isNowInvalid) -> {
            SelectionState state = getCurrentSelectionState();
            if(state.equals(NO_SELECTION))
                createButton.setDisable(isNowInvalid);
            if(state.equals(SINGLE_SELECTION))
                updateButton.setDisable(isNowInvalid);
        });
    }

    protected void setQuantityForm(){
        quantityTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            purchaseButton.setDisable(newValue.isEmpty() || newValue.equals("0") || !canPurchase());
            sellButton.setDisable(newValue.isEmpty() || newValue.equals("0") || !canSell());
        });

        quantityValidation.registerValidator(quantityTextField, false, (Control c, String newValue) ->
                ValidationResult.fromInfoIf(c, "Quantity must be a positive integer",  !newValue.matches("^\\d+$"))
                        .addInfoIf(c, "Quantity must be greater than 0", newValue.isEmpty() || newValue.equals("0"))
        );
    }

    private SelectionState getCurrentSelectionState(){
        if(tableView.getSelectionModel().getSelectedIndices().size() > 1)
            return MULTIPLE_SELECTION;
        if(tableView.getSelectionModel().isEmpty())
            return NO_SELECTION;
        return SINGLE_SELECTION;
    }

    protected void setTableView(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        resellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("resellPrice"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.setRowFactory(t -> {
            TableRow<T> tableRow = new TableRow<>();
            tableRow.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if(t.getSelectionModel().isSelected(tableRow.getIndex())){
                    t.getSelectionModel().clearSelection();
                    e.consume();
                }
            });
            return tableRow;
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            switch (getCurrentSelectionState()){
                case NO_SELECTION -> {
                    setFormValue(null);
                    setFormState(NO_SELECTION);
                }
                case SINGLE_SELECTION -> {
                    setFormValue(newSelection);
                    setFormState(SINGLE_SELECTION);
                }
                case MULTIPLE_SELECTION -> {
                    setFormValue(null);
                    setFormState(MULTIPLE_SELECTION);
                }
            }
        });

    }

    protected void setSearchTextField(){
        searchTextField.textProperty().addListener((obs, oldValue, newValue) ->{
            if(pagination.getCurrentPageIndex() == 0)
                tableView.setItems(
                        FXCollections.observableList(fetch(0, newValue))
                );
            else
                pagination.setCurrentPageIndex(1); // calls the page factory (update table view)
            pagination.setPageCount(getPageCount());
        });
    }

    protected void setPagination(){
        pagination.setPageFactory(i -> {
            tableView.setItems(FXCollections.observableList(fetch(i, searchTextField.getText())));
            return tableView;
        });
        pagination.setPageCount(getPageCount());
    }

    public enum SelectionState {
        SINGLE_SELECTION,
        NO_SELECTION,
        MULTIPLE_SELECTION,
    }

    protected void setFormState(SelectionState state){
        nameTextField.setDisable(state.equals(SINGLE_SELECTION) || state.equals(MULTIPLE_SELECTION));
        retailPriceTextField.setDisable(state.equals(MULTIPLE_SELECTION));
        resellPriceTextField.setDisable(state.equals(MULTIPLE_SELECTION));
        discountTextField.setDisable(state.equals(MULTIPLE_SELECTION));
        stockTextField.setDisable(state.equals(SINGLE_SELECTION) || state.equals(MULTIPLE_SELECTION));

        updateButton.setDisable(state.equals(MULTIPLE_SELECTION) || state.equals(NO_SELECTION));
        deleteButton.setDisable(state.equals(NO_SELECTION));
        createButton.setDisable(state.equals(SINGLE_SELECTION) || state.equals(MULTIPLE_SELECTION));

        sellButton.setDisable(true);
        purchaseButton.setDisable(true);
        quantityTextField.setDisable(state.equals(NO_SELECTION) || state.equals(MULTIPLE_SELECTION));
    }

    protected void setFormValue(T item){
        Optional<T> itemWrap = Optional.ofNullable(item);
        nameTextField.setText(itemWrap.map(T::getName).orElse(""));
        retailPriceTextField.setText(itemWrap.map(T::getRetailPrice).orElse(0d).toString());
        resellPriceTextField.setText(itemWrap.map(T::getResellPrice).orElse(0d).toString());
        discountTextField.setText(itemWrap.map(T::getDiscount).orElse(0d).toString());
        stockTextField.setText(itemWrap.map(T::getStock).orElse(0).toString());
    }

    protected int getPageCount(){
        return Math.max( // ensure that the number is at least 1 (empty table case)
                (int) Math.ceil((double) count(searchTextField.getText()) / pageSize()), // cast to the superior int
                1
        );
    }

    protected abstract List<T> fetch(int pageNumber, String searchValue);

    protected abstract long count(String searchValue);

    protected abstract void create();

    protected abstract void delete(T item);

    protected abstract T retrieveUpdated();

    protected abstract void update(T item);

    protected abstract void updateStock(int quantity, T item);

    private int getQuantity(){
        return Optional.ofNullable(quantityTextField.getText())
                .filter(s -> s.matches("^\\d+$"))
                .map(Integer::parseInt).orElse(Integer.MAX_VALUE);
    }

    // Obscure method :)
    private <F> F getValueFromItem(Function<T, F> getter, F orElseValue){
        return Optional.ofNullable(tableView.getSelectionModel().getSelectedItem())
                .map(getter)
                .orElse(orElseValue);
    }

    protected boolean canPurchase(){
        return RepositoryProvider.provider().getRepository(Company.class).findFirst().getCapital()
                >= getQuantity() * getValueFromItem(T::getRetailPrice, Double.MAX_VALUE);
    }

    protected boolean canSell(){
        return getQuantity() <= getValueFromItem(T::getStock, Integer.MAX_VALUE);
    }
}
