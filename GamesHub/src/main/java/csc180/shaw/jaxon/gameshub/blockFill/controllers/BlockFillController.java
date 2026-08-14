package csc180.shaw.jaxon.gameshub.blockFill.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.io.IOException;
import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class BlockFillController {
    private Random rand = new Random();
    private int rowNum;
    private int colNum;

    public int getRowNum() { return rowNum; }
    private void setRowNum(int rowNumber) { this.rowNum = rowNumber; }
    public int getColNum() { return colNum; }
    private void setColNum(int colNumber) { this.colNum = colNumber; }

    @FXML
    public void initialize() {

    }

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onEasyButtonClick() {
        try {
            switch(rand.nextInt(1)) {
                case 0:
                    setRowNum(6);
                    setColNum(6);
                    changeScene("blockFillViews/block-fill-game-board-easy0.fxml", "Block Fill", true);
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void onHardButtonClick() {
        try {
            switch(rand.nextInt(1)) {
                case 0 :
                    setRowNum(8);
                    setColNum(7);
                    changeScene("blockFillViews/block-fill-game-board-hard0.fxml", "Block Fill", true);
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public <T> T changeScene(String viewName, String title, boolean maximized) throws IOException {
        return this.getT(viewName, title, maximized);
    }

    public <T> T getT(String viewName, String title, boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/csc180/shaw/jaxon/gameshub/" + viewName));
        Parent root = loader.load();

        Object controller = loader.getController();

        if (controller instanceof BlockFillBoardController bfbc){
            bfbc.setRowNumber(getRowNum());
            bfbc.setColNumber(getColNum());
        }


        Stage stage = HelloApplication.primaryStage;
        Scene scene = new Scene(root);
        stage.setMaximized(maximized);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        return loader.getController();
    }
}