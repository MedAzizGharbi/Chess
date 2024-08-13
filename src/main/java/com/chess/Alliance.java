package com.chess;

public enum Alliance {
    WHITE {
        @Override
        int getDirection() {
            return -1;
        }
    },
    BLACK {
        @Override
        int getDirection() {
            return 1;
        }
    };

    abstract int getDirection();
}
