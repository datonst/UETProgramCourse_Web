module futuresubject.uetprogram {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens futuresubject.uetprogram to javafx.fxml;
    exports futuresubject.uetprogram;
}