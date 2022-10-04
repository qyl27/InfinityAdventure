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
        InfAdv.getInstance().getLogger().info("Register InfAdv Blocks.");

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
    public static final RegistryObject<Block> ANCIENT_DWARF_CRATE = BLOCKS.register("ancient_dwarf_crate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)));   // Todo: crate container and base.
    public static final RegistryObject<FurnaceBlockBase> DWARF_FURNACE = BLOCKS.register("dwarf_furnace", FurnaceBlockBase::new);    // Todo: functional block.
    public static final RegistryObject<FacingBlockBase> BEER_BARREL = BLOCKS.register("beer_barrel", () -> new FacingBlockBase(BlockBehaviour.Properties.copy(Blocks.BARREL)));    // Todo: functional block.

    public static final RegistryObject<GrassBlockBase> SKY_GRASS_BLOCK = BLOCKS.register("sky_grass_block", () -> new GrassBlockBase(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));    // Todo: Material and grass grow.
    public static final RegistryObject<RotatedPillarBlock> SKY_LOG = BLOCKS.register("sky_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SKY_PLANKS = BLOCKS.register("sky_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<LeavesBlock> SKY_LEAVES = BLOCKS.register("sky_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SKY_STONE = BLOCKS.register("sky_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5f, 4.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> HEAVEN_ROCK = BLOCKS.register("heaven_rock", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2, 4.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> HEAVEN_BRICKS = BLOCKS.register("heaven_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2, 4.0f).sound(SoundType.STONE)));
    public static final RegistryObject<OreBlockDropExperienceBase> MICA_ORE = BLOCKS.register("mica_ore", () -> new OreBlockDropExperienceBase(2, 3, UniformInt.of(2, 5)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(3.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MITHRIL_BLOCK = BLOCKS.register("mithril_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MICA_BLOCK = BLOCKS.register("mica_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8f, 1.0f).sound(SoundType.STONE)));
}
