package csc180.shaw.jaxon.gameshub.adoku.controllers;

import csc180.shaw.jaxon.gameshub.adoku.views.JavaFXDisplay;
import csc180.shaw.jaxon.gameshub.adoku.models.BoardChecker;
import csc180.shaw.jaxon.gameshub.adoku.models.BoardGenerator;
import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;
import csc180.shaw.jaxon.gameshub.adoku.models.boards.StandardBoard;

import csc180.shaw.jaxon.gameshub.adoku.views.NumberPalette;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static csc180.shaw.jaxon.gameshub.HelloController.getT;

public class adokuController {


    @FXML
    private ImageView adoImage;

    @FXML
    private ImageView AdoYouGotThis;

    private final Image Ado1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado1.png")));
    private final Image Ado2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado2.png")));
    private final Image Ado3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado3.png")));
    private final Image Ado4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado4.png")));
    private final Image Ado5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado5.png")));
    private final Image Ado6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado6.png")));
    private final Image Ado7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado7.png")));
    private final Image Ado8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado8.jpg")));
    private final Image Ado9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado9.png")));
    private final Image Ado10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado10.jpg")));
    private final Image Ado11 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado11.jpg")));
    private final Image Ado12 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado12.jpg")));
    private final Image Ado13 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado13.jpg")));
    private final Image Ado14 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado14.jpg")));
    private final Image Ado15 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado15.jpg")));
    private final Image Ado16 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado16.jpg")));
    private final Image Ado17 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado17.jpg")));
    private final Image Ado18 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado18.jpg")));
    private final Image Ado19 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado19.jpg")));
    private final Image Ado20 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado20.jpg")));
    private final Image Ado21 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado21.jpg")));
    private final Image Ado22 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado22.png")));
    private final Image Ado23 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado23.jpg")));
    private final Image Ado24 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado24.jpg")));
    private final Image Ado25 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado25.jpg")));
    private final Image Ado26 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/adoimages/ado26.jpg")));


    private final Image ClownAdo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/csc180/shaw/jaxon/gameshub/sudokuViews/Clawdo.jpg")));



    @FXML
    private JavaFXDisplay JavaFXDisplay;

    @FXML
    private Label statusLabel;

    ButtonType buttonReplay = new ButtonType("Play Again?");
    ButtonType buttonQuit = new ButtonType("Back to GamesHub");

    private GameBoard board;

    int incorrectMoves = 10;

    @FXML
    public void initialize(int difficulty) {
        board = BoardGenerator.generate(new StandardBoard(), difficulty);
        JavaFXDisplay.render(board);
        JavaFXDisplay.setOnCellEdit(this::handleCellEdit);
        refreshPaletteAvailability();

    }


     public void setBoard(int difficulty) {
         board = BoardGenerator.generate(new StandardBoard(), difficulty);
    }

    @FXML
    protected void changeAdoImage(){
        Random rng = new Random();
        int adokuNumber = rng.nextInt(26) + 1;
        switch (adokuNumber) {
            case 1:
                adoImage.setImage(Ado1);
                break;
            case 2:
                adoImage.setImage(Ado2);
                break;
            case 3:
                adoImage.setImage(Ado3);
                break;
            case 4:
                adoImage.setImage(Ado4);
                break;
            case 5:
                adoImage.setImage(Ado5);
                break;
            case 6:
                adoImage.setImage(Ado6);
                break;
            case 7:
                adoImage.setImage(Ado7);
                break;
            case 8:
                adoImage.setImage(Ado8);
                break;
            case 9:
                adoImage.setImage(Ado9);
                break;
            case 10:
                adoImage.setImage(Ado10);
                break;
            case 11:
                adoImage.setImage(Ado11);
                break;
            case 12:
                adoImage.setImage(Ado12);
                break;
            case 13:
                adoImage.setImage(Ado13);
                break;
            case 14:
                adoImage.setImage(Ado14);
                break;
            case 15:
                adoImage.setImage(Ado15);
                break;
            case 16:
                adoImage.setImage(Ado16);
                break;
            case 17:
                adoImage.setImage(Ado17);
                break;
            case 18:
                adoImage.setImage(Ado18);
                break;
            case 19:
                adoImage.setImage(Ado19);
                break;
            case 20:
                adoImage.setImage(Ado20);
                break;
            case 21:
                adoImage.setImage(Ado21);
                break;
            case 22:
                adoImage.setImage(Ado22);
                break;
            case 23:
                adoImage.setImage(Ado23);
                break;
            case 24:
                adoImage.setImage(Ado24);
                break;
            case 25:
                adoImage.setImage(Ado25);
                break;
            case 26:
                adoImage.setImage(Ado26);
                break;
            default:
        }
    }

    @FXML
    protected void onExitButtonClick() {
        try {
            changeScene("menu-view.fxml", "Main Menu", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void backToDifficultyClick() {
        try {
            changeScene("sudokuViews/AdokuDifficulty.fxml", "Adoku Difficulty", false, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private NumberPalette numberPalette;

    public <T> T changeScene(String viewName, String title, boolean maximized, boolean centered) throws IOException {
        return getT(viewName, title, maximized, centered);
    }

    private void refreshPaletteAvailability() {
        int size = board.getSize();
        int[] counts = new int[size + 1];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int value = board.getCell(row, col).getValue();
                if (value != 0) {
                    counts[value]++;
                }
            }
        }

        for (int digit = 1; digit <= size; digit++) {
            numberPalette.setDigitAvailable(digit, counts[digit] < size);
        }
    }


    private void handleCellEdit(JavaFXDisplay.CellEdit edit) {
        if (BoardChecker.isValidMove(board, edit.row(), edit.col(), edit.value())) {
            board.setValue(edit.row(), edit.col(), edit.value());
            board.getCell(edit.row(), edit.col()).setFixed(true);
            statusLabel.setText("Correct!");
            refreshPaletteAvailability();

            if (board.isComplete()) {
                statusLabel.setText("Solved! Nice work.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Congratulations! You solved Adoku!");
                alert.getButtonTypes().setAll(buttonReplay, buttonQuit);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonReplay) {
                    try {
                        changeScene("sudokuViews/AdokuDifficulty.fxml", "Adoku Difficulty", false, true);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                else {
                    try {
                        changeScene("menu-view.fxml", "Main Menu", false, true);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        } else {
            incorrectMoves--;
            statusLabel.setText("Not quite - try again. " +  (incorrectMoves) + " chances remaining");
            if (incorrectMoves == 0){
                adoImage.setImage(ClownAdo);
                AdoYouGotThis.setImage(ClownAdo);
                statusLabel.setText("You put in too many moves.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You wrong moves, game tis over");
                alert.setTitle("Game Over");
                alert.setHeaderText("");
                ImageView loseScreen = new ImageView(ClownAdo);
                alert.setGraphic(loseScreen);
                alert.setX(300);
                alert.setY(300);
                alert.getButtonTypes().setAll(buttonReplay, buttonQuit);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonReplay) {
                    try {
                        changeScene("sudokuViews/AdokuDifficulty.fxml", "Adoku Difficulty", false, true);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                else {
                    try {
                        changeScene("menu-view.fxml", "Main Menu", false, true);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
        JavaFXDisplay.render(board);
    }

}
