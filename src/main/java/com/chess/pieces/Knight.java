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

import static com.chess.board.Move.*;

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
    public Collection<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
        {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                if(isFirstColumnExclusion(this.piecePosition , currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition , currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition , currentCandidateOffset) ||
                        isEighthColumnExclusion(this.piecePosition , currentCandidateOffset))
                {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove(board,
                            this,
                            candidateDestinationCoordinate));
                }
                else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance){
                        //The piece that is on the destination is an enemy piece
                        //Which means the move will be an attack move
                        legalMoves.add(new AttackMove(board,
                                this,
                                candidateDestinationCoordinate,
                                pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    //There is some edge cases if the knight is on the first or the second column
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10)
        || (candidateOffset == 6) || (candidateOffset == 15));
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == 6) || (candidateOffset == -10));
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == 10)
                || (candidateOffset == -6) || (candidateOffset == 17));
    }
}
