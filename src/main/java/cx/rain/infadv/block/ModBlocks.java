package cx.rain.infadv.block;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.base.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    protected static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv blocks.");

        BLOCKS.register(bus);
    }

    public static DeferredRegister<Block> getRegistry() {
        return BLOCKS;
    }

    public static final RegistryObject<OreBlockDropExperienceBase> SILVER_ORE = BLOCKS.register("silver_ore", () -> new OreBlockDropExperienceBase(3, 3, UniformInt.of(0, 2)));
    public static final RegistryObject<OreBlockBase> MITHRIL_ORE = BLOCKS.register("mithril_ore", () -> new OreBlockBase(3, 4));
    public static final RegistryObject<OreBlockBase> DEEPSLATE_MITHRIL_ORE = BLOCKS.register("deepslate_mithril_ore", () -> new OreBlockBase(4.5f, 4));
    public static final RegistryObject<OreBlockBase> ADAMANTINE_ORE = BLOCKS.register("adamantine_ore", () -> new OreBlockBase(3, 6));
    public static final RegistryObject<OreBlockBase> DEEPSLATE_ADAMANTINE_ORE = BLOCKS.register("deepslate_adamantine_ore", () -> new OreBlockBase(4.5f, 6));
    public static final RegistryObject<OreBlockDropExperienceBase> RUBY_ORE = BLOCKS.register("ruby_ore", () -> new OreBlockDropExperienceBase(3, 3, UniformInt.of(1, 3)));
    public static final RegistryObject<OreBlockDropExperienceBase> AQUAMARINE_ORE = BLOCKS.register("aquamarine_ore", () -> new OreBlockDropExperienceBase(3, 2, UniformInt.of(2, 5)));
    public static final RegistryObject<CloudBlock> WHITE_CLOUD = BLOCKS.register("white_cloud", () -> new CloudBlock(CloudBlock.NORMAL_CLOUD_MATERIAL)); // Todo: qyl27: cloud materials.
    public static final RegistryObject<CloudBlock> LIGHT_GREY_CLOUD = BLOCKS.register("light_grey_cloud", () -> new CloudBlock(CloudBlock.NORMAL_CLOUD_MATERIAL));   // Todo: qyl27: cloud materials.
    public static final RegistryObject<CloudBlock> DARK_GREY_CLOUD = BLOCKS.register("dark_grey_cloud", () -> new CloudBlock(CloudBlock.NORMAL_CLOUD_MATERIAL)); // Todo: qyl27: cloud materials.
    public static final RegistryObject<CloudBlock> BLACK_CLOUD = BLOCKS.register("black_cloud", () -> new CloudBlock(CloudBlock.NORMAL_CLOUD_MATERIAL)); // Todo: qyl27: cloud materials.
    public static final RegistryObject<CloudBlock> RED_CLOUD = BLOCKS.register("red_cloud", () -> new CloudBlock(CloudBlock.NORMAL_CLOUD_MATERIAL)); // Todo: qyl27: cloud materials.
    public static final RegistryObject<Block> DWARF_BRICKS = BLOCKS.register("dwarf_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> DWARF_PILLAR_BRICKS = BLOCKS.register("dwarf_pillar_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<Block> ANCIENT_DWARF_BRICKS = BLOCKS.register("ancient_dwarf_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<FurnaceBlockBase> DWARF_FURNACE = BLOCKS.register("dwarf_furnace", () -> new FurnaceBlockBase());    // Todo: functional block.
    public static final RegistryObject<FacingBlockBase> BEER_BARREL = BLOCKS.register("beer_barrel", () -> new FacingBlockBase(BlockBehaviour.Properties.copy(Blocks.BARREL)));    // Todo: functional block.
    public static final RegistryObject<GrassBlockBase> SKY_GRASS_BLOCK = BLOCKS.register("sky_grass_block", () -> new GrassBlockBase(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));    // Todo: Material.
    public static final RegistryObject<RotatedPillarBlock> SKY_LOG = BLOCKS.register("sky_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SKY_PLANKS = BLOCKS.register("sky_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<LeavesBlock> SKY_LEAVES = BLOCKS.register("sky_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

}
