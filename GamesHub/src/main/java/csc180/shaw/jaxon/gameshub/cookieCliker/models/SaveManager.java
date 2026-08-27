package csc180.shaw.jaxon.gameshub.cookieCliker.models;

public class SaveManager {
    private Long cookies = 0L;
    private int clickPower = 1;
    private int clickPerSecond = 0;
    private int cookieUpgrade = 0;
    private int critChance = 0;
    private double critMultiplier = 2.0;
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
    private int testPassiveIncome = 0;
    private int grandmaPassiveIncome = 0;
    private int farmPassiveIncome = 0;
    private int minePassiveIncome = 0;
    private int factoryPassiveIncome = 0;
    private int bankPassiveIncome = 0;
    private int templePassiveIncome = 0;
    private int wizardTowerPassiveIncome = 0;

    public SaveManager(Long cookies, int clickPower, int clickPerSecond, int cookieUpgrade, int critChance, double critMultiplier, int cursorPrice, int cookiePrice, int crit1Price, int crit2Price, int extraClickPrice, int grandmaPrice, int farmPrice, int minePrice, int factoryPrice, int bankPrice, int templePrice, int wizardTowerPrice, int testPassiveIncome, int grandmaPassiveIncome, int farmPassiveIncome, int minePassiveIncome, int factoryPassiveIncome, int bankPassiveIncome, int templePassiveIncome, int wizardTowerPassiveIncome) {
        this.cookies = cookies;
        this.clickPower = clickPower;
        this.clickPerSecond = clickPerSecond;
        this.cookieUpgrade = cookieUpgrade;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.cursorPrice = cursorPrice;
        this.cookiePrice = cookiePrice;
        this.crit1Price = crit1Price;
        this.crit2Price = crit2Price;
        this.extraClickPrice = extraClickPrice;
        this.grandmaPrice = grandmaPrice;
        this.farmPrice = farmPrice;
        this.minePrice = minePrice;
        this.factoryPrice = factoryPrice;
        this.bankPrice = bankPrice;
        this.templePrice = templePrice;
        this.wizardTowerPrice = wizardTowerPrice;
        this.testPassiveIncome = testPassiveIncome;
        this.grandmaPassiveIncome = grandmaPassiveIncome;
        this.farmPassiveIncome = farmPassiveIncome;
        this.minePassiveIncome = minePassiveIncome;
        this.factoryPassiveIncome = factoryPassiveIncome;
        this.bankPassiveIncome = bankPassiveIncome;
        this.templePassiveIncome = templePassiveIncome;
        this.wizardTowerPassiveIncome = wizardTowerPassiveIncome;
    }
    public SaveManager(){

    }

    public Long getCookies() {
        return cookies;
    }

    public void setCookies(Long cookies) {
        this.cookies = cookies;
    }

    public int getClickPower() {
        return clickPower;
    }

    public void setClickPower(int clickPower) {
        this.clickPower = clickPower;
    }

    public int getClickPerSecond() {
        return clickPerSecond;
    }

    public void setClickPerSecond(int clickPerSecond) {
        this.clickPerSecond = clickPerSecond;
    }

    public int getCookieUpgrade() {
        return cookieUpgrade;
    }

    public void setCookieUpgrade(int cookieUpgrade) {
        this.cookieUpgrade = cookieUpgrade;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double critMultiplier) {
        this.critMultiplier = critMultiplier;
    }

    public int getCursorPrice() {
        return cursorPrice;
    }

    public void setCursorPrice(int cursorPrice) {
        this.cursorPrice = cursorPrice;
    }

    public int getCookiePrice() {
        return cookiePrice;
    }

    public void setCookiePrice(int cookiePrice) {
        this.cookiePrice = cookiePrice;
    }

    public int getCrit1Price() {
        return crit1Price;
    }

    public void setCrit1Price(int crit1Price) {
        this.crit1Price = crit1Price;
    }

    public int getCrit2Price() {
        return crit2Price;
    }

    public void setCrit2Price(int crit2Price) {
        this.crit2Price = crit2Price;
    }

    public int getExtraClickPrice() {
        return extraClickPrice;
    }

    public void setExtraClickPrice(int extraClickPrice) {
        this.extraClickPrice = extraClickPrice;
    }

    public int getGrandmaPrice() {
        return grandmaPrice;
    }

    public void setGrandmaPrice(int grandmaPrice) {
        this.grandmaPrice = grandmaPrice;
    }

    public int getFarmPrice() {
        return farmPrice;
    }

    public void setFarmPrice(int farmPrice) {
        this.farmPrice = farmPrice;
    }

    public int getMinePrice() {
        return minePrice;
    }

    public void setMinePrice(int minePrice) {
        this.minePrice = minePrice;
    }

    public int getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(int factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public int getBankPrice() {
        return bankPrice;
    }

    public void setBankPrice(int bankPrice) {
        this.bankPrice = bankPrice;
    }

    public int getTemplePrice() {
        return templePrice;
    }

    public void setTemplePrice(int templePrice) {
        this.templePrice = templePrice;
    }

    public int getWizardTowerPrice() {
        return wizardTowerPrice;
    }

    public void setWizardTowerPrice(int wizardTowerPrice) {
        this.wizardTowerPrice = wizardTowerPrice;
    }

    public int getTestPassiveIncome() {
        return testPassiveIncome;
    }

    public void setTestPassiveIncome(int testPassiveIncome) {
        this.testPassiveIncome = testPassiveIncome;
    }

    public int getGrandmaPassiveIncome() {
        return grandmaPassiveIncome;
    }

    public void setGrandmaPassiveIncome(int grandmaPassiveIncome) {
        this.grandmaPassiveIncome = grandmaPassiveIncome;
    }

    public int getFarmPassiveIncome() {
        return farmPassiveIncome;
    }

    public void setFarmPassiveIncome(int farmPassiveIncome) {
        this.farmPassiveIncome = farmPassiveIncome;
    }

    public int getMinePassiveIncome() {
        return minePassiveIncome;
    }

    public void setMinePassiveIncome(int minePassiveIncome) {
        this.minePassiveIncome = minePassiveIncome;
    }

    public int getFactoryPassiveIncome() {
        return factoryPassiveIncome;
    }

    public void setFactoryPassiveIncome(int factoryPassiveIncome) {
        this.factoryPassiveIncome = factoryPassiveIncome;
    }

    public int getBankPassiveIncome() {
        return bankPassiveIncome;
    }

    public void setBankPassiveIncome(int bankPassiveIncome) {
        this.bankPassiveIncome = bankPassiveIncome;
    }

    public int getTemplePassiveIncome() {
        return templePassiveIncome;
    }

    public void setTemplePassiveIncome(int templePassiveIncome) {
        this.templePassiveIncome = templePassiveIncome;
    }

    public int getWizardTowerPassiveIncome() {
        return wizardTowerPassiveIncome;
    }

    public void setWizardTowerPassiveIncome(int wizardTowerPassiveIncome) {
        this.wizardTowerPassiveIncome = wizardTowerPassiveIncome;
    }
}
