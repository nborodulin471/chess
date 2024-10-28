package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Класс ладьи.
 */
public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
        this.setCheck(true);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что ладья движется по вертикали или горизонтали
        if (toLine == line || toColumn == column) {
            // Проверяем, что на пути ладьи нет других фигур
            int deltaX = Integer.compare(toColumn - column, 0); // Направление по оси X
            int deltaY = Integer.compare(toLine - line, 0); // Направление по оси Y

            int currentX = column + deltaX;
            int currentY = line + deltaY;

            while (currentX != toColumn || currentY != toLine) {
                if (chessBoard.getPieceAt(currentY, currentX) != null) {
                    return false; // Если на пути есть фигура, ход невозможен
                }
                currentX += deltaX;
                currentY += deltaY;
            }
            this.setCheck(false);

           return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
