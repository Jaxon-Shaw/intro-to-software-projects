module csc180.shaw.jaxon.gameshub {
    requires javafx.controls;
    requires javafx.fxml;

    opens csc180.shaw.jaxon.gameshub.battleship.controllers to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.adoku to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.blockFill to javafx.fxml;
    opens csc180.shaw.jaxon.gameshub.cookieCliker to javafx.fxml;

    opens csc180.shaw.jaxon.gameshub to javafx.fxml;
    exports csc180.shaw.jaxon.gameshub;
}