module csc180.shaw.jaxon.gameshub {
    requires javafx.controls;
    requires javafx.fxml;

    opens csc180.shaw.jaxon.gameshub.adoku.views to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.adoku.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub to javafx.fxml;
    exports csc180.shaw.jaxon.gameshub;
}