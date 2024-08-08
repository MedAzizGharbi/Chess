package com.chess.pieces;

import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.Move;

import java.util.List;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    Piece(final int piecePosition, final Alliance pieceAlliance)
    {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }
    public abstract List<Move> calculateLegalMoves(final Board board);
}
