module csc180.shaw.jaxon.gameshub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires tools.jackson.databind;

    opens csc180.shaw.jaxon.gameshub.battleship.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.blockFill.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.cookieCliker to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.adoku.views to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.adoku.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.cookieCliker.models to tools.jackson.databind;


    opens csc180.shaw.jaxon.gameshub to javafx.fxml;
    exports csc180.shaw.jaxon.gameshub;
}