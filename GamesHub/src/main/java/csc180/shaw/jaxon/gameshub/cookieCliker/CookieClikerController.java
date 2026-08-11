package csc180.shaw.jaxon.gameshub.cookieCliker;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class CookieClikerController {
    //region editable stats
    private int cookies = 0;
    private int clickPower = 1;
    private int clickPerSecond = 0;
    //endregion
    //region GUI IDs
    public Circle cookieClick;
    public Text cookieCount;
    //endregion

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return getT(viewName, title, maximized);
    }

    @FXML
    protected void clicking() {
        cookies = cookies + clickPower;
        cookieCount.textProperty().setValue("Cookies: " + cookies);
    }
}
