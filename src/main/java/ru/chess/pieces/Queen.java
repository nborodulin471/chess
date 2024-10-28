package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Класс королевы.
 */
public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что королева движется по вертикали, горизонтали или диагонали
        if (toLine == line || toColumn == column || Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // Проверяем, что на пути королевы нет других фигур
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

            return true;
        }

        return false; // Если ход не соответствует правилам, возвращаем false
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
