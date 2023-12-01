package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


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
    protected TableColumn<T, Float> retailPriceColumn;

    @FXML
    protected TableColumn<T, Float> resellPriceColumn;

    @FXML
    protected TableColumn<T, Integer> stockColumn;

    @FXML
    protected TableColumn<T, Float> discountColumn;

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
    protected Button createButton;

    @FXML
    protected Button deleteButton;

    @FXML
    void createOnAction(ActionEvent event) {
        create();

        //focus on the created item
        searchTextField.setText(nameTextField.getText());
        tableView.getSelectionModel().select(tableView.getItems().size() - 1);

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
        // todo
        event.consume();
    }

    @FXML
    void sellOnAction(ActionEvent event) {
        // todo
        event.consume();
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().addListener((obs, oldValue, newValue) ->{
            if(pagination.getCurrentPageIndex() == 0)
                tableView.setItems(
                        FXCollections.observableList(fetch(0, newValue))
                );
            else
                pagination.setCurrentPageIndex(1); // calls the page factory (update table view)
            pagination.setPageCount(getPageCount());
        });

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
            if(tableView.getSelectionModel().getSelectedIndices().size() > 1)
                onMultipleSelection();
            else if (newSelection != null)
                onSelection(newSelection);
            else
                onDeselection();
        });

        pagination.setPageFactory(i -> {
            tableView.setItems(FXCollections.observableList(fetch(i, searchTextField.getText())));
            return tableView;
        });
        pagination.setPageCount(getPageCount());
    }

    protected void onSelection(T item){
        nameTextField.setText(item.getName());
        retailPriceTextField.setText(String.valueOf(item.getRetailPrice()));
        resellPriceTextField.setText(String.valueOf(item.getResellPrice()));
        discountTextField.setText(String.valueOf(item.getDiscount()));
        stockTextField.setText(String.valueOf(item.getStock()));

        nameTextField.setDisable(true);
        retailPriceTextField.setDisable(false);
        resellPriceTextField.setDisable(false);
        discountTextField.setDisable(false);
        stockTextField.setDisable(true);

        updateButton.setDisable(false);
        deleteButton.setDisable(false);
        createButton.setDisable(true);

        sellButton.setDisable(false);
        purchaseButton.setDisable(false);
    }

    protected void onDeselection(){
        nameTextField.setText("");
        retailPriceTextField.setText("");
        resellPriceTextField.setText("");
        discountTextField.setText("");
        stockTextField.setText("");

        nameTextField.setDisable(false);
        retailPriceTextField.setDisable(false);
        resellPriceTextField.setDisable(false);
        discountTextField.setDisable(false);
        stockTextField.setDisable(false);

        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        createButton.setDisable(false);

        sellButton.setDisable(true);
        purchaseButton.setDisable(true);
    }

    protected void onMultipleSelection(){
        nameTextField.setText("");
        retailPriceTextField.setText("");
        resellPriceTextField.setText("");
        discountTextField.setText("");
        stockTextField.setText("");

        nameTextField.setDisable(true);
        retailPriceTextField.setDisable(true);
        resellPriceTextField.setDisable(true);
        discountTextField.setDisable(true);
        stockTextField.setDisable(true);

        updateButton.setDisable(true);
        deleteButton.setDisable(false);
        createButton.setDisable(true);

        sellButton.setDisable(false);
        purchaseButton.setDisable(false);
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
}
