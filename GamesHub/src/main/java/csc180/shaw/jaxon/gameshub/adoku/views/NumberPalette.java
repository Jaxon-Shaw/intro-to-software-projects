package csc180.shaw.jaxon.gameshub.adoku.views;

import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

public class NumberPalette extends HBox {

    public static final DataFormat SUDOKU_DIGIT = new DataFormat("sudoku-digit");
    private static final int SIZE = 9;

    private static final String AVAILABLE_STYLE =
            "-fx-background-color: #eeeeee; -fx-border-color: #999999; "
                    + "-fx-font-size: 18px; -fx-font-weight: bold;";
    private static final String USED_UP_STYLE =
            "-fx-background-color: #cccccc; -fx-border-color: #aaaaaa; "
                    + "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #888888;";

    private final Label[] tiles = new Label[SIZE + 1];

    public NumberPalette() {
        setSpacing(8);
        for (int n = 1; n <= SIZE; n++) {
            Label tile = buildTile(n);
            tiles[n] = tile;
            getChildren().add(tile);
        }
    }

    private Label buildTile(int value) {
        Label tile = new Label(String.valueOf(value));
        tile.setPrefSize(80, 80);
        tile.setAlignment(javafx.geometry.Pos.CENTER);
        tile.setStyle(AVAILABLE_STYLE);

        tile.setOnDragDetected(event -> {
            var dragboard = tile.startDragAndDrop(TransferMode.COPY);
            var content = new ClipboardContent();
            content.put(SUDOKU_DIGIT, value);
            dragboard.setContent(content);
            event.consume();
        });

        return tile;
    }

    public void setDigitAvailable(int digit, boolean available) {
        Label tile = tiles[digit];
        tile.setDisable(!available);
        tile.setStyle(available ? AVAILABLE_STYLE : USED_UP_STYLE);
    }
}