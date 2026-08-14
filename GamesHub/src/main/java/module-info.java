module csc180.shaw.jaxon.gameshub {
    requires javafx.controls;
    requires javafx.fxml;

    opens csc180.shaw.jaxon.gameshub.adoku to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.battleship to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.cookieCliker to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.blockFill.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub to javafx.fxml;
    exports csc180.shaw.jaxon.gameshub;
}