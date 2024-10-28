package ru.chess.pieces;

import ru.chess.ChessBoard;

/**
 * Абстрактный класс фигуры от которого все наследуются.
 */
public abstract class ChessPiece {
    private final String color;
    private boolean check;

    ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public abstract boolean canMoveToPosition (ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
