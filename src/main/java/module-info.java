module com.student_info_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.commons.codec;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires junit;

    opens com.student_info_manager.ui to javafx.fxml;
    exports com.student_info_manager.ui;
    exports com.student_info_manager.control;
}