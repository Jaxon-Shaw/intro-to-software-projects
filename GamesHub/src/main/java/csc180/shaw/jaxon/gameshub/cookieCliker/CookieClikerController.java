package csc180.shaw.jaxon.gameshub.cookieCliker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;
import static javafx.animation.Animation.INDEFINITE;

public class CookieClikerController {
    //region Class Variables
    //region editable stats
    //region displayed Stats
    private Long cookies = 0L;
    private int clickPower = 1;
    private int clickPerSecond = 0;
    private int cookieUpgrade = 0;
    private int critChance = 0;
    private double critMultiplier = 2.0;
    //endregion
    //region Starting Prices
    private int cursorPrice = 15;
    private int cookiePrice = 10000;
    private int crit1Price = 200;
    private int crit2Price = 400;
    private int extraClickPrice = 50;
    private int grandmaPrice = 100;
    private int farmPrice = 500;
    private int minePrice = 2500;
    private int factoryPrice = 10000;
    private int bankPrice = 50000;
    private int templePrice = 250000;
    private int wizardTowerPrice = 1000000;
    //endregion
    //region Passive Income
    private int testPassiveIncome = 0;
    private int grandmaPassiveIncome = 0;
    private int farmPassiveIncome = 0;
    private int minePassiveIncome = 0;
    private int factoryPassiveIncome = 0;
    private int bankPassiveIncome = 0;
    private int templePassiveIncome = 0;
    private int wizardTowerPassiveIncome = 0;
    //endregion
    //endregion
    //region GUI IDs
    public Circle cookieClick;
    //region Text IDs
    public Text cookieCount;
    public Text cookieUpgradePrice;
    public Text cursorUpgradePrice;
    public Text critChancePrice;
    public Text critMultiplierPrice;
    public Text cookieUpgradeText;
    public Text clickPowerText;
    public Text critChanceText;
    public Text multiplierText;
    public Text clickPerSecondText;
    public Text extraClickerUpgradePrice;
    public Text grandmaUpgradePrice;
    public Text farmUpgradePrice;
    public Text mineUpgradePrice;
    public Text factoryUpgradePrice;
    public Text bankUpgradePrice;
    public Text templeUpgradePrice;
    public Text wizardTowerUpgradePrice;
    //endregion
    //region Box IDs
    public Rectangle clickPowerBox;
    public Rectangle globalBox;
    public Rectangle critChanceBox;
    public Rectangle critMultiplierBox;
    public Rectangle extraClickerBox;
    public Rectangle davidBox;
    public Rectangle farmBox;
    public Rectangle mineBox;
    public Rectangle factoryBox;
    public Rectangle bankBox;
    public Rectangle templeBox;
    public Rectangle wizardTowerBox;
    //endregion
    //endregion
    //endregion




    //region Gui Check
    @FXML
    protected void initialize(){
        checkBuyGui();
    }
    protected void checkBuyGui(){
        Timeline checkGUI = new Timeline(new KeyFrame(Duration.seconds(.01), e -> {
            if(cookies < cursorPrice) {
                clickPowerBox.setFill(Color.GRAY);
            }else{
                clickPowerBox.setFill(Color.DARKGREY);
            }
            if(cookies < cookiePrice) {
                globalBox.setFill(Color.GRAY);
            }else{
                globalBox.setFill(Color.DARKGREY);
            }
            if(cookies < crit1Price) {
                critChanceBox.setFill(Color.GRAY);
            }else{
                critChanceBox.setFill(Color.DARKGREY);
            }
            if(cookies < crit2Price) {
                critMultiplierBox.setFill(Color.GRAY);
            }else{
                critMultiplierBox.setFill(Color.DARKGREY);
            }
            if(cookies < extraClickPrice){
                extraClickerBox.setFill(Color.GRAY);
            }else{
                extraClickerBox.setFill(Color.DARKGREY);
            }
            if(cookies < grandmaPrice) {
                davidBox.setFill(Color.GRAY);
            }else{
                davidBox.setFill(Color.DARKGREY);
            }
            if(cookies < farmPrice) {
                farmBox.setFill(Color.GRAY);
            }else{
                farmBox.setFill(Color.DARKGREY);
            }
            if(cookies < minePrice) {
                mineBox.setFill(Color.GRAY);
            }else{
                mineBox.setFill(Color.DARKGREY);
            }
            if(cookies < factoryPrice) {
                factoryBox.setFill(Color.GRAY);
            }else{
                factoryBox.setFill(Color.DARKGREY);
            }
            if(cookies < bankPrice) {
                bankBox.setFill(Color.GRAY);
            }else{
                bankBox.setFill(Color.DARKGREY);
            }
            if(cookies < templePrice) {
                templeBox.setFill(Color.GRAY);
            }else{
                templeBox.setFill(Color.DARKGREY);
            }
            if(cookies < wizardTowerPrice) {
                wizardTowerBox.setFill(Color.GRAY);
            }else {
                wizardTowerBox.setFill(Color.DARKGREY);
            }
        }));
        checkGUI.setCycleCount(INDEFINITE);
        checkGUI.play();
    }
    protected void clickPerSecondGUI(){
        int globalPassive = clickPerSecond;
        int tempHold = 0;
        if(testPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 1);
        }
        if(grandmaPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 2);
        }
        if(farmPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 4);
        }
        if(minePassiveIncome >= 1){
            tempHold += (cookieUpgrade * 10);
        }
        if(factoryPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 20);
        }
        if(bankPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 40);
        }
        if(templePassiveIncome >= 1){
            tempHold += (cookieUpgrade * 100);
        }
        if(wizardTowerPassiveIncome >= 1){
            tempHold += (cookieUpgrade * 200);
        }
        int globalPerSecond = globalPassive + tempHold;
        clickPerSecondText.setText("per second: " + globalPerSecond);
    }
    //endregion
    //region Exit Button
    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false, false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        return getT(viewName, title, maximized, centered);
    }
    //endregion
    //region Main Click
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
    //endregion
    //region Click Upgrades
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
            double tempHold = cookiePrice * 2.5;
            cookiePrice = (int) tempHold;
            cookieUpgradePrice.setText(cookiePrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            cookieUpgradeText.setText("Extra Global Cookies: " + cookieUpgrade);
        }
        clickPerSecondGUI();
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
    //endregion
    //region Passive Income Upgrades
    @FXML
    protected void testPassive() {
        if(cookies >= extraClickPrice) {
            cookies = cookies - extraClickPrice;
            extraClickerUpgradePrice.textProperty().setValue(extraClickPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            testPassiveIncome += 1;
            clickPerSecond += 1;
            clickPerSecondGUI();
            double tempHold = extraClickPrice * 1.15;
            extraClickPrice = (int) tempHold;
            extraClickerUpgradePrice.textProperty().setValue(extraClickPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            cookies = cookies + testPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (testPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void grandmaUpgrade(){
        if(cookies >= grandmaPrice) {
            cookies = cookies - grandmaPrice;
            grandmaUpgradePrice.textProperty().setValue(grandmaPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            grandmaPassiveIncome += 1;
            clickPerSecond += 2;
            clickPerSecondGUI();
            double tempHold = grandmaPrice * 1.16;
            grandmaPrice = (int) tempHold;
            grandmaUpgradePrice.textProperty().setValue(grandmaPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
            cookies = cookies + grandmaPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (grandmaPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void farmUpgrade(){
        if(cookies >= farmPrice) {
            cookies = cookies - farmPrice;
            farmUpgradePrice.textProperty().setValue(farmPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            farmPassiveIncome += 1;
            clickPerSecond += 4;
            clickPerSecondGUI();
            double tempHold = farmPrice * 1.17;
            farmPrice = (int) tempHold;
            farmUpgradePrice.textProperty().setValue(farmPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> {
            cookies = cookies + farmPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (farmPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void mineUpgrade(){
        if(cookies >= minePrice) {
            cookies = cookies - minePrice;
            mineUpgradePrice.textProperty().setValue(minePrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            minePassiveIncome += 1;
            clickPerSecond += 10;
            clickPerSecondGUI();
            double tempHold = minePrice * 1.18;
            minePrice = (int) tempHold;
            mineUpgradePrice.textProperty().setValue(minePrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            cookies = cookies + minePassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (minePassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void factoryUpgrade(){
        if(cookies >= factoryPrice) {
            cookies = cookies - factoryPrice;
            factoryUpgradePrice.textProperty().setValue(factoryPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            factoryPassiveIncome += 1;
            clickPerSecond += 20;
            clickPerSecondGUI();
            double tempHold = factoryPrice * 1.19;
            factoryPrice = (int) tempHold;
            factoryUpgradePrice.textProperty().setValue(factoryPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            cookies = cookies + factoryPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (factoryPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void bankUpgrade(){
        if(cookies >= bankPrice) {
            cookies = cookies - bankPrice;
            bankUpgradePrice.textProperty().setValue(bankPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            bankPassiveIncome += 1;
            clickPerSecond += 40;
            clickPerSecondGUI();
            double tempHold = bankPrice * 1.20;
            bankPrice = (int) tempHold;
            bankUpgradePrice.textProperty().setValue(bankPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.025), e -> {
            cookies = cookies + bankPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (bankPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void templeUpgrade(){
        if(cookies >= templePrice) {
            cookies = cookies - templePrice;
            templeUpgradePrice.textProperty().setValue(templePrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            templePassiveIncome += 1;
            clickPerSecond += 100;
            clickPerSecondGUI();
            double tempHold = templePrice * 1.21;
            templePrice = (int) tempHold;
            templeUpgradePrice.textProperty().setValue(templePrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> {
            cookies = cookies + templePassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (templePassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    @FXML
    protected void wizardTowerUpgrade(){
        if(cookies >= wizardTowerPrice) {
            cookies = cookies - wizardTowerPrice;
            wizardTowerUpgradePrice.textProperty().setValue(wizardTowerPrice + " Cookies");
            cookieCount.setText("Cookies: " + cookies);
            wizardTowerPassiveIncome += 1;
            clickPerSecond += 200;
            clickPerSecondGUI();
            double tempHold = wizardTowerPrice * 1.22;
            wizardTowerPrice = (int) tempHold;
            wizardTowerUpgradePrice.textProperty().setValue(wizardTowerPrice + " Cookies");
        }
        Timeline autoClickLoop = new Timeline(new KeyFrame(Duration.seconds(0.005), e -> {
            cookies = cookies + wizardTowerPassiveIncome + cookieUpgrade;
            cookieCount.setText("Cookies: " + cookies);
        }));
        autoClickLoop.setCycleCount(INDEFINITE);
        if (wizardTowerPassiveIncome == 1) {
            autoClickLoop.play();
        }
    }
    //endregion
}
