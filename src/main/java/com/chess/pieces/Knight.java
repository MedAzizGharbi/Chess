package com.chess.pieces;
import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.Move;
import com.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
//This is the class for the chess piece knight
//The Knight can move to 8 places in a perfect scenario
//The value of a knight is typically (3)
public class Knight extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATES
            = {-17 , -15 , -10 , -6 , 6, 10 , 15, 17};
    Knight(final int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES)
        {
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;
            if(true /* is a valid tile coordinate*/)
            {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move());
                }
                else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance){
                        //The piece that is on the destination is an enemy piece
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
