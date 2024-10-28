package ru.chess.pieces;

import ru.chess.ChessBoard;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что ход соответствует правилам коня (буква "Г")
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        // Конь может двигаться на 2 клетки в одном направлении и 1 клетку в другом
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
