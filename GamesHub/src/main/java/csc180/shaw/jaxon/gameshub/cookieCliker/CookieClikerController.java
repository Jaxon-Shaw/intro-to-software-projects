package csc180.shaw.jaxon.gameshub.cookieCliker;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class CookieClikerController {
    //region editable stats
    private Long cookies = 0L;
    private int clickPower = 1;
    private int clickPerSecond = 0;
    private int cookieUpgrade = 0;
    private int critChance = 0;
    private double critMultiplier = 2.0;
    private int cursorPrice = 15;
    private int cookiePrice = 100;
    private int crit1Price = 200;
    private int crit2Price = 400;
    //endregion
    //region GUI IDs
    public Circle cookieClick;
    public Text cookieCount;
    public Text cookieUpgradePrice;
    public Text cursorUpgradePrice;
    public Text critChancePrice;
    public Text critMultiplierPrice;
    public Text cookieUpgradeText;
    public Text clickPowerText;
    public Text critChanceText;
    public Text multiplierText;

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
        Random rand = new Random();
        int critRoll = rand.nextInt(100) + 1;
        if(critChance >= critRoll){
            double cookieCrit = critMultiplier * ((double)clickPower + cookieUpgrade);
            cookies = ((int)cookieCrit + cookies);
        }else {
            cookies = cookies + clickPower + cookieUpgrade;
        }
        cookieCount.textProperty().setValue("Cookies: " + cookies);
    }

    @FXML
    protected void upgradeCursor(){
        if(cookies >= cursorPrice){
            cookies = cookies - cursorPrice;
            clickPower = clickPower + 1;
            double tempHold = cursorPrice * 1.0666666666666666;
            cursorPrice = (int) tempHold;
            cursorUpgradePrice.setText(cursorPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            clickPowerText.setText("Click Power: " + clickPower);
        }
    }

    @FXML
    protected void upgradeCookie(){
        if(cookies >= cookiePrice){
            cookies = cookies - cookiePrice;
            cookieUpgrade = cookieUpgrade + 1;
            double tempHold = cookiePrice * 1.15;
            cookiePrice = (int) tempHold;
            cookieUpgradePrice.setText(cookiePrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            cookieUpgradeText.setText("Extra Global Cookies: " + cookieUpgrade);
        }
    }

    @FXML
    protected void upgradeCrit(){
        if(critChance < 100){
            if(cookies >= crit1Price) {
                cookies = cookies - crit1Price;
                critChance = critChance + 1;
                double tempHold = crit1Price * 1.20;
                crit1Price = (int) tempHold;
                critChancePrice.setText(crit1Price +  " Cookies");
                cookieCount.setText("Cookies: " + cookies);
                critChanceText.setText("Crit Chance: " + critChance + "%");
                if(critChance == 100){
                    critChancePrice.setText("Max Level");
                }
            }
        }else{
            critChancePrice.setText("Max Level");
        }
    }

    @FXML
    protected void upgradeMultiplier(){
        if(cookies >= crit2Price){
            cookies = cookies - crit2Price;
            critMultiplier = critMultiplier + .5;
            double tempHold = crit2Price * 1.15;
            crit2Price = (int) tempHold;
            critMultiplierPrice.setText(crit2Price +  " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            multiplierText.setText("Crit Multiplier: " + critMultiplier + "x");
        }
    }
}
