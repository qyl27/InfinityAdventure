package cx.rain.infadv.unrealized;

import com.google.gson.JsonElement;
import cx.rain.infadv.InfAdv;
import cx.rain.infadv.data.provider.base.LanguageProviderBase;
import cx.rain.infadv.data.provider.lang.ModLanguageProviderENUS;
import cx.rain.infadv.data.provider.lang.ModLanguageProviderZHCN;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static cx.rain.infadv.unrealized.UnrealizedElements.dreamBed;
import static cx.rain.infadv.unrealized.UnrealizedElements.tab;

public class UnrealizedProvider implements DataProvider {

    private final BlockModelProvider blockModels;
    private final LanguageProvider languages;

    public UnrealizedProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
        blockModels = new BlockModelProvider(generator, InfAdv.MODID, exFileHelper);
        languages = new LanguageProvider(generator);
    }

    private void collectAll() {
        blockModels.collect(map -> {
            ModelTemplates.BED_INVENTORY.create(ModelLocationUtils.getModelLocation(dreamBed().asItem()),
                    TextureMapping.particle(Blocks.PINK_WOOL),
                    map::put);
        });
        languages.put(tab(), "InfinityAdventure | Unrealized",  "无尽冒险 | 未实现");
        languages.put(dreamBed(), "Dream Bed", "织梦床");
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        collectAll();
        blockModels.run(output);
        languages.run(output);
    }

    @Override
    public String getName() {
        return "Unrealized Elements";
    }
}

class BlockModelProvider extends BlockStateProvider {

    private final Map<ResourceLocation, Supplier<JsonElement>> map = new HashMap<>();
    private final DataGenerator.PathProvider modelPathProvider;
    private Consumer<Map<ResourceLocation, Supplier<JsonElement>>> collector;

    public BlockModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        modelPathProvider = gen.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "models");
    }

    public void collect(Consumer<Map<ResourceLocation, Supplier<JsonElement>>> collector) {
        this.collector = collector;
    }

    @Override
    protected void registerStatesAndModels() {
        collector.accept(map);
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        super.run(output);
        saveCollection(output, map, modelPathProvider::json);
    }

    private <T> void saveCollection(CachedOutput output, Map<T, ? extends Supplier<JsonElement>> objectToJsonMap, Function<T, Path> resolveObjectPath) {
        objectToJsonMap.forEach((object, supplier) -> {
            Path path = resolveObjectPath.apply(object);
            try {
                DataProvider.saveStable(output, supplier.get(), path);
            } catch (Exception exception) {
                InfAdv.getInstance().getLogger().error("Couldn't save {}", path, exception);
            }
        });
    }
}

class LanguageProvider implements DataProvider {

    private LanguageProviderBase en, zh;

    public LanguageProvider(DataGenerator generator) {
        for (DataProvider provider : generator.getProviders()) {
            if (provider instanceof ModLanguageProviderENUS e) {
                en = e;
            } else if (provider instanceof ModLanguageProviderZHCN z) {
                zh = z;
            }
        }
    }

    public void put(Block block, String en, String zh) {
        this.en.add(block, en);
        this.zh.add(block, zh);
    }

    public void put(CreativeModeTab tab, String en, String zh) {
        this.en.addCreativeTab(tab, en);
        this.zh.addCreativeTab(tab, zh);
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        en.run(output);
        zh.run(output);
    }

    @Override
    public String getName() {
        return "Unrealized Languages";
    }
}
