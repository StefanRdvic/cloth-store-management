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
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;

    opens com.esilv.clothstoremanagement to javafx.fxml;
    exports com.esilv.clothstoremanagement;
}