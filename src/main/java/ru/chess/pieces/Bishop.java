package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Класс слона.
 */
public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что слон движется по диагонали
        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // Проверяем, что на пути слона нет других фигур
            int deltaX = (toColumn - column) > 0 ? 1 : -1; // Направление по оси X
            int deltaY = (toLine - line) > 0 ? 1 : -1; // Направление по оси Y

            int currentX = column + deltaX;
            int currentY = line + deltaY;

            while (currentX != toColumn && currentY != toLine) {
                if (chessBoard.getPieceAt(currentY, currentX) != null) {
                    return false; // Если на пути есть фигура, ход невозможен
                }
                currentX += deltaX;
                currentY += deltaY;
            }

            return true;
        }

        return false; // Если ход не по диагонали, возвращаем false
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
