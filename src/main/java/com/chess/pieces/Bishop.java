package com.chess.pieces;

import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.Move;

import java.util.Collection;

public class Bishop extends Piece{
    private final static int[] CANDIDATE_LEGAL_MOVES = {7 , -7 , 9 ,-9};
    Bishop(final int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
