package com.esilv.clothstoremanagement.controller;

import com.esilv.clothstoremanagement.model.entity.Accessory;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;

import java.util.List;

/*
 * This class is used to manage the accessory view
 * author: Stefan Radovanovic
 * author: Yannick li
 * */
public class AccessoryController extends AbstractProductController<Accessory>{


    @Override
    protected List<Accessory> fetch(int pageNumber, String searchValue) {
        return RepositoryProvider
                .provider()
                .getRepository(Accessory.class)
                .findAll(pageSize(), pageNumber, searchValue);
    }

    @Override
    protected long count(String searchValue) {
        return RepositoryProvider.provider().getRepository(Accessory.class).count();
    }

    @Override
    protected void create() {
        RepositoryProvider
                .provider()
                .getRepository(Accessory.class)
                .save(Accessory.builder()
                        .name(nameTextField.getText())
                        .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                        .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                        .stock(Integer.parseInt(stockTextField.getText()))
                        .discount(Float.parseFloat(discountTextField.getText()))
                        .build());
    }

    @Override
    protected void delete(Accessory item) {
        RepositoryProvider.provider().getRepository(Accessory.class).delete(item);
    }

    @Override
    protected Accessory retrieveUpdated() {
        return tableView
                .getSelectionModel()
                .getSelectedItem()
                .toBuilder()
                .retailPrice(Float.parseFloat(retailPriceTextField.getText()))
                .resellPrice(Float.parseFloat(resellPriceTextField.getText()))
                .discount(Float.parseFloat(discountTextField.getText()))
                .stock(Integer.parseInt(stockTextField.getText()))
                .build();
    }

    @Override
    protected void update(Accessory item) {
        RepositoryProvider.provider().getRepository(Accessory.class).update(item);
    }

    @Override
    protected void updateStock(int quantity, Accessory item) {
        RepositoryProvider.provider().getRepository(Accessory.class).update(
                item.toBuilder().stock(item.getStock() + quantity).build()
        );
    }
}
