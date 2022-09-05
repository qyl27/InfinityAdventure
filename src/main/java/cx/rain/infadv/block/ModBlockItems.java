package cx.rain.infadv.block;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.base.OreBlockBase;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockItems {
    protected static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv block items.");

        BLOCK_ITEMS.register(bus);
    }

    public static DeferredRegister<Item> getRegistry() {
        return BLOCK_ITEMS;
    }

    public static final RegistryObject<Item> SILVER_ORE = BLOCK_ITEMS.register("silver_ore", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> MITHRIL_ORE = BLOCK_ITEMS.register("mithril_ore", () -> new BlockItem(ModBlocks.MITHRIL_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> ADAMANTINE_ORE = BLOCK_ITEMS.register("adamantine_ore", () -> new BlockItem(ModBlocks.ADAMANTINE_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> RUBY_ORE = BLOCK_ITEMS.register("ruby_ore", () -> new BlockItem(ModBlocks.RUBY_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> AQUAMARINE_ORE = BLOCK_ITEMS.register("aquamarine_ore", () -> new BlockItem(ModBlocks.AQUAMARINE_ORE.get(), new Item.Properties().tab(ModTabs.ORES)));

}
