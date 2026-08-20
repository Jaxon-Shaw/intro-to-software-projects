package csc180.shaw.jaxon.gameshub.adoku.views;

import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

/** A row of draggable digit tiles (1-9) for mouse-based input. */
public class NumberPalette extends HBox {

    public static final DataFormat SUDOKU_DIGIT = new DataFormat("sudoku-digit");
    private static final int SIZE = 9;

    public NumberPalette() {
        setSpacing(8);
        for (int n = 1; n <= SIZE; n++) {
            getChildren().add(buildTile(n));
        }
    }

    private Label buildTile(int value) {
        Label tile = new Label(String.valueOf(value));
        tile.setPrefSize(80, 80);
        tile.setAlignment(javafx.geometry.Pos.CENTER);
        tile.setStyle("-fx-background-color: #eeeeee; -fx-border-color: #999999; " + "-fx-font-size: 30px; -fx-font-weight: bold;");

        tile.setOnDragDetected(event -> {
            var dragboard = tile.startDragAndDrop(TransferMode.COPY);
            var content = new ClipboardContent();
            content.put(SUDOKU_DIGIT, value);
            dragboard.setContent(content);
            event.consume();
        });

        return tile;
    }
}