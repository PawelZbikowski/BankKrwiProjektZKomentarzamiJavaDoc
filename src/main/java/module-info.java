module edu.ib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.sql.rowset;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.controls;
    requires de.jensd.fx.glyphs.fontawesome;
    requires cachedrowset.wrapper;
    requires org.apache.commons.codec;
    requires com.google.gson;

    opens edu.ib to javafx.fxml;
    exports edu.ib;
}