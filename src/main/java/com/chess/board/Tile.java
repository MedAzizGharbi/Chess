package com.chess.board;

import com.chess.pieces.Piece;
//Guava library made by google
//(Check Effective Java book)
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

//This class is immutable
public abstract class Tile {
    protected final int tileCoordinate; // Can be set once after the object is constructed (Immutable)
    private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTile();
    //this method is going to create an empty "board"
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile() {
        final Map<Integer , EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0 ; i < 64 ; i++)
        {
            emptyTileMap.put(i, new EmptyTile(i));

        }
        return ImmutableMap.copyOf(emptyTileMap);
        // The created possible empty tiles are final and Immutable
    }
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
        //Only method everyone will be allowed to use.
    }
    private Tile(int tileCoordinate){
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
