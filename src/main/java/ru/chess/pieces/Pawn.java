package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Класс пешки.
 */
public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Определяем направление движения пешки
        int direction = getColor().equals("White") ? 1 : -1; // Белые движутся вверх, черные - вниз

        // Проверяем обычный ход на одну клетку вперед
        if (toLine == line + direction && toColumn == column) {
            return true;
        }

        // Проверяем первый ход на две клетки вперед
        if ((line == (getColor().equals("White") ? 1 : 6)) && toLine == line + 2 * direction && toColumn == column) {
            return true;
        }

        // По диагонали можно рубить
        if (Math.abs(column - toColumn) == 1) {
            return true;
        }

        return false; // Если ни одно из условий не выполнено, возвращаем false
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
