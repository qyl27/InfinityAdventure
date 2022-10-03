package cx.rain.infadv.block;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.base.OreBlockDropExperienceBase;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockItems {
    protected static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv Block Items.");

        BLOCK_ITEMS.register(bus);
    }

    public static DeferredRegister<Item> getRegistry() {
        return BLOCK_ITEMS;
    }

    public static final RegistryObject<BlockItem> SILVER_ORE = BLOCK_ITEMS.register("silver_ore", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> MITHRIL_ORE = BLOCK_ITEMS.register("mithril_ore", () -> new BlockItem(ModBlocks.MITHRIL_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> DEEPSLATE_MITHRIL_ORE = BLOCK_ITEMS.register("deepslate_mithril_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_MITHRIL_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> ADAMANTINE_ORE = BLOCK_ITEMS.register("adamantine_ore", () -> new BlockItem(ModBlocks.ADAMANTINE_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> DEEPSLATE_ADAMANTINE_ORE = BLOCK_ITEMS.register("deepslate_adamantine_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_ADAMANTINE_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> RUBY_ORE = BLOCK_ITEMS.register("ruby_ore", () -> new BlockItem(ModBlocks.RUBY_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> AQUAMARINE_ORE = BLOCK_ITEMS.register("aquamarine_ore", () -> new BlockItem(ModBlocks.AQUAMARINE_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> WHITE_CLOUD = BLOCK_ITEMS.register("white_cloud", () -> new BlockItem(ModBlocks.WHITE_CLOUD.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> LIGHT_GREY_CLOUD = BLOCK_ITEMS.register("light_grey_cloud", () -> new BlockItem(ModBlocks.LIGHT_GREY_CLOUD.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> DARK_GREY_CLOUD = BLOCK_ITEMS.register("dark_grey_cloud", () -> new BlockItem(ModBlocks.DARK_GREY_CLOUD.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> BLACK_CLOUD = BLOCK_ITEMS.register("black_cloud", () -> new BlockItem(ModBlocks.BLACK_CLOUD.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> RED_CLOUD = BLOCK_ITEMS.register("red_cloud", () -> new BlockItem(ModBlocks.RED_CLOUD.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> DWARF_BRICKS = BLOCK_ITEMS.register("dwarf_bricks", () -> new BlockItem(ModBlocks.DWARF_BRICKS.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> DWARF_PILLAR_BRICKS = BLOCK_ITEMS.register("dwarf_pillar_bricks", () -> new BlockItem(ModBlocks.DWARF_PILLAR_BRICKS.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> ANCIENT_DWARF_BRICKS = BLOCK_ITEMS.register("ancient_dwarf_bricks", () -> new BlockItem(ModBlocks.ANCIENT_DWARF_BRICKS.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> ANCIENT_DWARF_CRATE = BLOCK_ITEMS.register("ancient_dwarf_crate", () -> new BlockItem(ModBlocks.ANCIENT_DWARF_CRATE.get(), new Item.Properties().tab(ModTabs.FUNCTIONAL_BLOCKS)));
    public static final RegistryObject<BlockItem> DWARF_FURNACE = BLOCK_ITEMS.register("dwarf_furnace", () -> new BlockItem(ModBlocks.DWARF_FURNACE.get(), new Item.Properties().tab(ModTabs.FUNCTIONAL_BLOCKS)));
    public static final RegistryObject<BlockItem> BEER_BARREL = BLOCK_ITEMS.register("beer_barrel", () -> new BlockItem(ModBlocks.BEER_BARREL.get(), new Item.Properties().tab(ModTabs.FUNCTIONAL_BLOCKS)));

    public static final RegistryObject<BlockItem> SKY_GRASS_BLOCK = BLOCK_ITEMS.register("sky_grass_block", () -> new BlockItem(ModBlocks.SKY_GRASS_BLOCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> SKY_LOG = BLOCK_ITEMS.register("sky_log", () -> new BlockItem(ModBlocks.SKY_LOG.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> SKY_PLANKS = BLOCK_ITEMS.register("sky_planks", () -> new BlockItem(ModBlocks.SKY_PLANKS.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> SKY_LEAVES = BLOCK_ITEMS.register("sky_leaves", () -> new BlockItem(ModBlocks.SKY_LEAVES.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> SKY_STONE = BLOCK_ITEMS.register("sky_stone", () -> new BlockItem(ModBlocks.SKY_STONE.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> HEAVEN_ROCK = BLOCK_ITEMS.register("heaven_rock", () -> new BlockItem(ModBlocks.HEAVEN_ROCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> HEAVEN_BRICKS = BLOCK_ITEMS.register("heaven_bricks", () -> new BlockItem(ModBlocks.HEAVEN_BRICKS.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> MICA_ORE = BLOCK_ITEMS.register("mica_ore", () -> new BlockItem(ModBlocks.MICA_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<BlockItem> SILVER_BLOCK = BLOCK_ITEMS.register("silver_block", () -> new BlockItem(ModBlocks.SILVER_BLOCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> MITHRIL_BLOCK = BLOCK_ITEMS.register("mithril_block", () -> new BlockItem(ModBlocks.MITHRIL_BLOCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> RUBY_BLOCK = BLOCK_ITEMS.register("ruby_block", () -> new BlockItem(ModBlocks.RUBY_BLOCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> MICA_BLOCK = BLOCK_ITEMS.register("mica_block", () -> new BlockItem(ModBlocks.MICA_BLOCK.get(), new Item.Properties().tab(ModTabs.BUILDING_BLOCKS)));

}
