package csc180.shaw.jaxon.gameshub.adoku.views;


import csc180.shaw.jaxon.gameshub.adoku.models.interfaces.GameBoard;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;


public class JavaFXDisplay extends GridPane {

    private final int size;
    private final int boxSize;
    private final TextField[][] fields;

    private Consumer<CellEdit> onCellEdit;

    public record CellEdit(int row, int col, int value) {}

    //Testing size
    public JavaFXDisplay() {
        this(9);
    }

    public JavaFXDisplay(int size) {
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.fields = new TextField[size][size];

        setHgap(0);
        setVgap(0);
        setPadding(new Insets(10));

        buildCells();
    }

    private void buildCells() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                TextField field = new TextField();
                field.setPrefSize(48, 48);
                field.setAlignment(javafx.geometry.Pos.CENTER);
                field.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
                field.setBorder(borderFor(row, col));

                final int r = row;
                final int c = col;

                field.textProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal.equals(oldVal)) return;

                    String filtered = newVal.replaceAll("[^0-9]", "");
                    if (filtered.length() > 1) {
                        filtered = filtered.substring(filtered.length() - 1);
                    }
                    if (!filtered.equals(newVal)) {
                        field.setText(filtered);
                        return; // listener will fire again with the corrected value
                    }
                    if (filtered.isEmpty()) {
                        return;
                    }

                    int value = Integer.parseInt(filtered);
                    if (value < 1 || value > size) {
                        field.setText("");
                        return;
                    }
                    if (onCellEdit != null) {
                        onCellEdit.accept(new CellEdit(r, c, value));
                    }
                });

                fields[row][col] = field;
                add(field, col, row);
            }
        }
    }

    /** Thicker borders on the outer edge and every boxSize-th line to mark 3x3 boxes. */
    private Border borderFor(int row, int col) {
        double thick = 2.5;
        double thin = 0.5;
        Color color = Color.web("#333333");

        double top = (row % boxSize == 0) ? thick : thin;
        double left = (col % boxSize == 0) ? thick : thin;
        double bottom = (row == size - 1) ? thick : thin;
        double right = (col == size - 1) ? thick : thin;

        return new Border(new BorderStroke(
                color, color, color, color,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(top, right, bottom, left),
                Insets.EMPTY
        ));
    }

    /** Redraws every cell from the current board state. */
    public void render(GameBoard board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                var cell = board.getCell(row, col);
                TextField field = fields[row][col];

                int value = cell.getValue();
                field.setText(value == 0 ? "" : String.valueOf(value));

                field.setEditable(!cell.isFixed());
                if (cell.isFixed()) {
                    field.setStyle("-fx-text-fill: #1a1a1a; -fx-background-color: #e0e0e0; -fx-opacity: 1;");
                } else {
                    field.setStyle("-fx-text-fill: #1565c0; -fx-background-color: white;");
                }
            }
        }
    }


}
