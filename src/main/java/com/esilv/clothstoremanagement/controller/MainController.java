package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Action;
import com.esilv.clothstoremanagement.model.entity.Company;
import com.esilv.clothstoremanagement.model.repository.RepositoryListener;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDateTime;

public class MainController implements RepositoryListener {


    @FXML
    private TableColumn<Action, String> actionColumn;

    @FXML
    private TableView<Action> actionTableView;

    @FXML
    private TextField capitalTextField;

    @FXML
    private TableColumn<Action, LocalDateTime> dateColumn;

    @FXML
    private TextField globalCostTextField;

    @FXML
    private TextField globalIncomeTextFIeld;

    @FXML
    private TextField lastUpdateTextField;

    @FXML
    void initialize() {
        RepositoryProvider.provider()
                        .listen(this);

        setCompanyInfo();
        setActionTableView();
    }

    @Override
    public void onChange(Action action) {
        setCompanyInfo();
        actionTableView.getItems().add(action);
        lastUpdateTextField.setText(action.getDate().toString());
    }

    private void setCompanyInfo(){
        Company company = RepositoryProvider.provider().getRepository(Company.class).findFirst();

        capitalTextField.setText(company.getCapital() + " $");
        globalCostTextField.setText(company.getGlobalCost() + " $");
        globalIncomeTextFIeld.setText(company.getGlobalIncome() + " $");
    }

    private void setActionTableView(){
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        dateColumn.setCellValueFactory(new  PropertyValueFactory<>("date"));
    }


}
