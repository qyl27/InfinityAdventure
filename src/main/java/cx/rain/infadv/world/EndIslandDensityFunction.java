package cx.rain.infadv.world;

import com.mojang.serialization.MapCodec;
import cx.rain.infadv.data.provider.IALevelProvider;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public enum EndIslandDensityFunction implements DensityFunction.SimpleFunction {

    INSTANCE;

    public static final KeyDispatchDataCodec<EndIslandDensityFunction> CODEC = KeyDispatchDataCodec.of(MapCodec.unit(INSTANCE));

    private final SimplexNoise islandNoise;

    EndIslandDensityFunction() {
        LegacyRandomSource randomSource = new LegacyRandomSource(0);
        randomSource.consumeCount(17292);
        this.islandNoise = new SimplexNoise(randomSource);
    }

    @Override
    public double compute(FunctionContext context) {
        int x = context.blockX() / 8;
        int z = context.blockZ() / 8;
        float f = 100 - Mth.sqrt(x * x + z * z) * 8;
        f = Mth.clamp(f, -100, 80);
        for (int o = -12; o <= 12; o++) {
            for (int p = -12; p <= 12; p++) {
                long sx = x / 2 + o;
                long sz = z / 2 + p;
                if (sx * sx + sz * sz <= 4096 || !(islandNoise.getValue(sx, sz) < -0.9)) {
                    continue;
                }
                float g = (Mth.abs(sx) * 3439 + Mth.abs(sz) * 147) % 13 + 9;
                float h = x % 2 - o * 2;
                float s = z % 2 - p * 2;
                float t = 100 - Mth.sqrt(h * h + s * s) * g;
                t = Mth.clamp(t, -100, 80);
                f = Math.max(f, t);
            }
        }
        // return f
        return (f - 8) / 128;
    }

    @Override
    public double minValue() {
        return -0.84375;
    }

    @Override
    public double maxValue() {
        return 0.5625;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}
