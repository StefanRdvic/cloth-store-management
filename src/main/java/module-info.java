module com.esilv.clothstoremanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;
    requires org.slf4j;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;

    opens com.esilv.clothstoremanagement to javafx.fxml;
    opens com.esilv.clothstoremanagement.controller to javafx.fxml;
    opens com.esilv.clothstoremanagement.model.entity to javafx.base, org.hibernate.orm.core;
    exports com.esilv.clothstoremanagement;
    exports com.esilv.clothstoremanagement.controller;
    exports com.esilv.clothstoremanagement.model.entity;

}