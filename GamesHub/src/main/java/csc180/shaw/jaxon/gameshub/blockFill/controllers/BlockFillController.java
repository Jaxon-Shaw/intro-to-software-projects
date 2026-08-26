package csc180.shaw.jaxon.gameshub.blockFill.controllers;

import csc180.shaw.jaxon.gameshub.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.io.IOException;

public class BlockFillController {
    private final Random rand = new Random();
    private int rowNum;
    private int colNum;
    private int boardNum;

    // Did I ever tell you how much I love Ado?
    public int getRowNum() { return rowNum; }
    private void setRowNum(int rowNumber) { this.rowNum = rowNumber; }
    public int getColNum() { return colNum; }
    private void setColNum(int colNumber) { this.colNum = colNumber; }
    public int getBoardNum() { return boardNum; }
    public void setBoardNum(int boardNum) { this.boardNum = boardNum; }

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected int onEasyButtonClick() {
        try {
            switch(rand.nextInt(10))
            {
                case 0:
                    setRowNum(6);
                    setColNum(6);
                    setBoardNum(0);
                    changeScene("blockFillViews/block-fill-game-board-easy0.fxml", "Block Fill", true);
                    break;
                case 1:
                    setRowNum(6);
                    setColNum(4);
                    setBoardNum(1);
                    changeScene("blockFillViews/block-fill-game-board-easy1.fxml", "Block Fill", true);
                    break;
                case 2:
                    setRowNum(7);
                    setColNum(5);
                    setBoardNum(2);
                    changeScene("blockFillViews/block-fill-game-board-easy2.fxml", "Block Fill", true);
                    break;
                case 3:
                    setRowNum(7);
                    setColNum(6);
                    setBoardNum(3);
                    changeScene("blockFillViews/block-fill-game-board-easy3.fxml", "Block Fill", true);
                    break;
                case 4:
                    setRowNum(5);
                    setColNum(5);
                    setBoardNum(4);
                    changeScene("blockFillViews/block-fill-game-board-easy4.fxml", "Block Fill", true);
                    break;
                case 5:
                    setRowNum(7);
                    setColNum(5);
                    setBoardNum(5);
                    changeScene("blockFillViews/block-fill-game-board-easy5.fxml", "Block Fill", true);
                    break;
                case 6:
                    setRowNum(7);
                    setColNum(6);
                    setBoardNum(6);
                    changeScene("blockFillViews/block-fill-game-board-easy6.fxml", "Block Fill", true);
                    break;
                case 7:
                    setRowNum(7);
                    setColNum(5);
                    setBoardNum(7);
                    changeScene("blockFillViews/block-fill-game-board-easy7.fxml", "Block Fill", true);
                    break;
                case 8:
                    setRowNum(8);
                    setColNum(6);
                    setBoardNum(8);
                    changeScene("blockFillViews/block-fill-game-board-easy8.fxml", "Block Fill", true);
                    break;
                case 9:
                    setRowNum(6);
                    setColNum(6);
                    setBoardNum(9);
                    changeScene("blockFillViews/block-fill-game-board-easy9.fxml", "Block Fill", true);
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return getBoardNum();
    }

    @FXML
    protected int onHardButtonClick() {
        try {
            switch(rand.nextInt(10)) {
                case 0 :
                    setRowNum(8);
                    setColNum(7);
                    setBoardNum(10);
                    changeScene("blockFillViews/block-fill-game-board-hard0.fxml", "Block Fill", true);
                    break;
                case 1 :
                    setRowNum(7);
                    setColNum(7);
                    setBoardNum(11);
                    changeScene("blockFillViews/block-fill-game-board-hard1.fxml", "Block Fill", true);
                    break;
                case 2 :
                    setRowNum(7);
                    setColNum(5);
                    setBoardNum(12);
                    changeScene("blockFillViews/block-fill-game-board-hard2.fxml", "Block Fill", true);
                    break;
                case 3 :
                    setRowNum(7);
                    setColNum(7);
                    setBoardNum(13);
                    changeScene("blockFillViews/block-fill-game-board-hard3.fxml", "Block Fill", true);
                    break;
                case 4 :
                    setRowNum(8);
                    setColNum(6);
                    setBoardNum(14);
                    changeScene("blockFillViews/block-fill-game-board-hard4.fxml", "Block Fill", true);
                    break;
                case 5 :
                    setRowNum(6);
                    setColNum(6);
                    setBoardNum(15);
                    changeScene("blockFillViews/block-fill-game-board-hard5.fxml", "Block Fill", true);
                    break;
                case 6 :
                    setRowNum(7);
                    setColNum(7);
                    setBoardNum(16);
                    changeScene("blockFillViews/block-fill-game-board-hard6.fxml", "Block Fill", true);
                    break;
                case 7 :
                    setRowNum(8);
                    setColNum(6);
                    setBoardNum(17);
                    changeScene("blockFillViews/block-fill-game-board-hard7.fxml", "Block Fill", true);
                    break;
                case 8 :
                    setRowNum(7);
                    setColNum(7);
                    setBoardNum(18);
                    changeScene("blockFillViews/block-fill-game-board-hard8.fxml", "Block Fill", true);
                    break;
                case 9 :
                    setRowNum(7);
                    setColNum(6);
                    setBoardNum(19);
                    changeScene("blockFillViews/block-fill-game-board-hard9.fxml", "Block Fill", true);
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return getBoardNum();
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
            bfbc.setBoardNum(getBoardNum());
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