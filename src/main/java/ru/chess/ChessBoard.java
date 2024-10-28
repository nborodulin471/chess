package ru.chess;

import ru.chess.pieces.ChessPiece;
import ru.chess.pieces.King;
import ru.chess.pieces.Rook;

/**
 * Класс шахматной доски.
 */
public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        // Проверяем, что фигуру принадлежит тому, кто ходит
        if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
            return false;
        }

        // Проверим, что координаты хода корректны
        if (withinBoard(startLine, startColumn, endLine, endColumn)
                && isDiffPosition(startLine, startColumn, endLine, endColumn)
                && isDifferentColor(board[startLine][startColumn], board[endLine][endColumn])) {

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // если фигура может двигаться, значит, мы передвинули фигуру
                board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Выводит доску в консоль.
     */
    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    /**
     * Проверяет, что ход нельзя совершить за пределы доски.
     */
    public static boolean withinBoard(int line, int column, int toLine, int toColumn) {
        if (line < 0 || column > 7 || toLine < 0 || toColumn > 7) {
            return false;
        }

        return true;
    }

    /**
     * Проверяет, что ход, который планируется отличаться от текущей позиции.
     */
    public boolean isDiffPosition(int line, int column, int toLine, int toColumn) {
        if (toLine == line && toColumn == column) {
            return false;
        }

        return true;
    }

    /**
     * Проверяет, что у фигур различный цвет.
     */
    public boolean isDifferentColor(ChessPiece currPiece, ChessPiece targetPiece) {
        // Проверим, что переданные значения корректны
        if (currPiece == null) {
            return false;
        } else if (targetPiece == null) { // если второй фигуры нет, тогда считаем, цвет разный
            return true;
        }

        return !targetPiece.getColor().equals(currPiece.getColor());
    }


    /**
     * Метод для получения фигуры на заданной позиции.
     */
    public ChessPiece getPieceAt(int line, int column) {
        if (line < 0 || line >= 8 || column < 0 || column >= 8) {
            return null; // Если координаты вне доски, возвращаем null
        }
        return board[line][column]; // Возвращаем фигуру на заданной позиции
    }

    /**
     * Проводит рокировку для белых.
     */
    public boolean castling0() {
        return castling("White", 0);
    }

    /**
     * Проводит рокировку для черных.
     */
    public boolean castling7() {
        return castling("Black", 7);
    }

    /**
     * Метод рокировки.
     *
     * @param color      цвет текущего игрока.
     * @param lineNumber номер ряда в котором будет проходить рокировка.
     * @return возвращает возможность совершения действия.
     */
    private boolean castling(String color, int lineNumber) {
        // Цвет фигуры совпадает с цветом текущего игрока.
        if (!nowPlayer.equals(color)) {
            return false;
        }

        // Проверим, что фигуры на месте.
        if (board[lineNumber][0] == null || board[lineNumber][4] == null) {
            return false;
        }

        if (board[lineNumber][0].getSymbol().equals("R") && board[lineNumber][4].getSymbol().equals("K") // Фигуры нужного типа
                && castlingIsPossible(lineNumber)) { // Проверим что нет мешающих фигур
            if (board[lineNumber][0].getColor().equals("White") && board[lineNumber][4].getColor().equals("White") // Фигуры одного цвета.
                    && isNeverMoved(lineNumber)) { // Фигуры ранее не двигались
                board[lineNumber][4].setCheck(false);
                board[lineNumber][4] = null;
                board[lineNumber][2] = new King("White");

                board[lineNumber][0].setCheck(false);
                board[lineNumber][0] = null;
                board[lineNumber][3] = new Rook("White");

                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Проверяет что фигуры между королем отсутствуют.
     */
    private boolean castlingIsPossible(int lineNumber) {
        return board[lineNumber][1] == null && board[lineNumber][2] == null && board[lineNumber][3] == null;
    }

    /**
     * Проверяет что фигуры короля и ладь не двигались.
     */
    private boolean isNeverMoved(int lineNumber) {
        return board[lineNumber][0].isCheck() && board[lineNumber][4].isCheck();
    }
}
