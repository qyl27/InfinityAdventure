package cx.rain.infadv.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.SectionPos;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseChunk;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class EtherIslandDensityFunction implements DensityFunction.SimpleFunction {

    public static final Codec<Layer> CODEC_LAYER = RecordCodecBuilder.create(i -> i.group(
            IntProvider.CODEC.fieldOf("height").forGetter(Layer::height),
            Codec.INT.fieldOf("depth").forGetter(Layer::depth),
            Codec.FLOAT.fieldOf("probability").forGetter(Layer::probability),
            Codec.INT.fieldOf("areaWidth").forGetter(Layer::areaWidth),
            Codec.INT.fieldOf("areaLength").forGetter(Layer::areaLength),
            Codec.INT_STREAM.comapFlatMap(stream -> DataResult.success(stream.toArray()), IntStream::of)
                    .fieldOf("radius").forGetter(Layer::radius)).apply(i, Layer::new));
    // todo: change CODEC_LAYER, support different constructors
    // public static final Codec<Layer> CODEC_LAYER2 = RecordCodecBuilder.create()

    public static final Codec<EtherIslandDensityFunction> CODEC_DIRECT = RecordCodecBuilder.create(i -> i.group(
            CODEC_LAYER.listOf().fieldOf("layers").forGetter(f -> f.layers)
    ).apply(i, EtherIslandDensityFunction::new));
    public static final KeyDispatchDataCodec<EtherIslandDensityFunction> CODEC = KeyDispatchDataCodec.of(CODEC_DIRECT);

    private final List<Layer> layers;

    public EtherIslandDensityFunction(List<Layer> layers) {
        this.layers = layers.stream().sorted((a, b) -> Integer.compare(b.height.getMaxValue(), a.height.getMaxValue())).toList();
    }

    @Override
    public double compute(FunctionContext context) {
        return context instanceof NoiseChunk chunkContext ? computeRandom(chunkContext) : computeSimple(context);
    }

    private double computeRandom(NoiseChunk context) {
        // todo use noise and random
        return computeSimple(context);
    }

    private double computeSimple(FunctionContext context) {
        int y = context.blockY();
        for (Layer layer : layers) {
            int dep = layer.height.getMaxValue() - y;
            if (dep >= 0 && dep < layer.depth) {
                int chunkX = SectionPos.blockToSectionCoord(context.blockX());
                int chunkZ = SectionPos.blockToSectionCoord(context.blockZ());
            }
        }
        return 0;
    }

    @Override
    public double minValue() {
        return -1;
    }

    @Override
    public double maxValue() {
        return 1;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }

    /**
     * 天镜岛配置
     *
     * @param height      岛屿生成的 Y 轴范围
     * @param depth       岛屿的深度（高度）
     * @param probability 每个岛在其所在区域内生成概率
     * @param areaWidth   每个岛占用的区域宽度（X，单位：区块）
     * @param areaLength  每个岛占用的区域长度（Z，单位：区块）
     * @param radius      每层的半径
     */
    public record Layer(IntProvider height, int depth, float probability, int areaWidth, int areaLength, int[] radius) {

        /**
         * 天镜岛配置
         *
         * @param height      岛屿生成的 Y 轴范围
         * @param depth       岛屿的深度（高度）
         * @param size        岛屿顶层半径
         * @param probability 每个岛在其所在区域内生成概率
         * @param areaWidth   每个岛占用的区域宽度（X，单位：区块）
         * @param areaLength  每个岛占用的区域长度（Z，单位：区块）
         */
        public Layer(IntProvider height, int size, int depth, float probability, int areaWidth, int areaLength) {
            this(height, depth, probability, areaWidth, areaLength, ((Supplier<int[]>) () -> {
                int[] r = new int[depth];
                float step = (size - 1f) / depth;
                float prev = size;
                for (int i = 0; i < r.length; i++, prev -= step) {
                    r[i] = (int) prev;
                }
                return r;
            }).get());
        }
    }
}
