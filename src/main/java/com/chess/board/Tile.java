package com.chess.board;

import com.chess.pieces.Piece;
//This class is immutable
public abstract class Tile {
    protected final int tileCoordinate; // Can be set once after the object is constructed (Immutable)
    Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate)
        {
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        OccupiedTile(int coordinate , Piece pieceOnTile)
        {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }


}
