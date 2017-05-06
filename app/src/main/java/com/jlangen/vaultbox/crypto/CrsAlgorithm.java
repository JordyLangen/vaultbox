package com.jlangen.vaultbox.crypto;

public enum CrsAlgorithm {

    Null(0),
    ArcFourVariant(1),
    Salsa20(2),
    ChaCha20(3);

    public static final int count = 4;
    public final int id;

    private CrsAlgorithm(int num) {
        id = num;
    }

    public static CrsAlgorithm fromId(int num) {
        for (CrsAlgorithm e : CrsAlgorithm.values()) {
            if (e.id == num) {
                return e;
            }
        }

        return null;
    }
}
