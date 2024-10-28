package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Класс лошади.
 */
public class King extends ChessPiece {

    public King(String color) {
        super(color);
        this.setCheck(true);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что король движется на одну клетку в любом направлении
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        if (deltaX <= 1 && deltaY <= 1) {
            this.setCheck(false);

            return true;
        }

        return false; // Если ход не соответствует правилам, возвращаем false
    }

    @Override
    public String getSymbol() {
        return "K";
    }

}
