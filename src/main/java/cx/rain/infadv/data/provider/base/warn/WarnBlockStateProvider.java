package cx.rain.infadv.data.provider.base.warn;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.io.IOException;

public abstract class WarnBlockStateProvider extends BlockStateProvider {

    private final String modid;
    private final WarnBlockModelProvider blockModels;
    private final WarnItemModelProvider itemModels;

    public WarnBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        this.modid = modid;
        this.blockModels = new WarnBlockModelProvider(gen, modid, exFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
        this.itemModels = new WarnItemModelProvider(gen, modid, this.blockModels.existingFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        super.run(output);
        blockModels.printAllExceptions();
        itemModels.printAllExceptions();
    }

    protected abstract void registerStatesAndModels();

    public BlockModelProvider models() {
        return blockModels;
    }

    public ItemModelProvider itemModels() {
        return itemModels;
    }

    @Nonnull
    @Override
    public String getName() {
        return "BlockStates: " + modid;
    }
}
