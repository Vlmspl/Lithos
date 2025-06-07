package net.vladitandlplayer.lithos.misc;

import java.util.Arrays;
import java.util.List;

public class BraceletModel extends Model {

    protected BraceletModel() {
        super(
                new float[] {
                        // Front (Z = 0)
                        0f,   0f, 0f,
                        0f,   0.5f, 0f,
                        1f,   0.5f, 0f,
                        1f,   0f, 0f,

                        // Back (Z = 1)
                        1f,   0f, 1f,
                        1f,   0.5f, 1f,
                        0f,   0.5f, 1f,
                        0f,   0f, 1f,

                        // Right (X = 0)
                        0f,   0f, 1f,
                        0f,   0.5f, 1f,
                        0f,   0.5f, 0f,
                        0f,   0f, 0f,

                        // Left (X = 1)
                        1f,   0f, 0f,
                        1f,   0.5f, 0f,
                        1f,   0.5f, 1f,
                        1f,   0f, 1f,
                },
                new float[] {
                        // Front (Blue)
                        0f, 0f,
                        0f, 1f,
                        0.25f, 1f,     // 16/64 = 0.25f
                        0.25f, 0f,

                        // Back (Yellow)
                        0.5f, 0f,      // 32/64 = 0.5f
                        0.5f, 1f,
                        0.25f, 1f,
                        0.25f, 0f,

                        // Right (Purple)
                        0.5f, 0f,
                        0.5f, 1f,
                        0.75f, 1f,     // 48/64 = 0.75f
                        0.75f, 0f,

                        // Left (Green)
                        0.75f, 0f,
                        0.75f, 1f,
                        1f, 1f,        // 64/64 = 1f
                        1f, 0f,
                }
        );
    }
}
