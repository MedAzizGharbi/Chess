package com.chess.pieces;

import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.BoardUtils;
import com.chess.board.Move;
import com.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {1 , -1  , 8 , -8};
    Rook(final int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for(int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                if(isFirstColumnExclusion(candidateDestinationCoordinate , candidateCoordinateOffset) ||
                isEighthColumnExclusion(candidateDestinationCoordinate , candidateCoordinateOffset))
                {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
                {
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board,
                                this,
                                candidateDestinationCoordinate));
                    }
                    else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            //The piece that is on the destination is an enemy piece
                            //Which means the move will be an attack move
                            legalMoves.add(new Move.AttackMove(board,
                                    this,
                                    candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        //Here the break in case the rook encounters a piece in the vector path.
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1);
    }
}
