package com.chess.pieces;

import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.Move;

import java.util.List;

public class Knight extends Piece{
    Knight(final int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
