package cx.rain.infadv.unrealized;

import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static cx.rain.infadv.InfAdv.MODID;

public class UnrealizedElements {
    private static final CreativeModeTab UNREALIZED_TAB = new ModTabs("unrealized", ModItems.AQUAMARINE);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    private static final RegistryObject<Block> DREAM_BED = block("todo_dream_bed", () -> new BedBlock(DyeColor.ORANGE, BlockBehaviour.Properties.copy(Blocks.ORANGE_BED)));
    private static final RegistryObject<Item> DREAM_BED_ITEM = blockItem(DREAM_BED);

    private static RegistryObject<Block> block(String name, Supplier<? extends Block> block) {
        return BLOCKS.register(name, block);
    }

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getNamespace(), () -> new BlockItem(block.get(), new Item.Properties().tab(UNREALIZED_TAB)));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }

    // =================================================================================================================

    public static CreativeModeTab tab() {
        return UNREALIZED_TAB;
    }

    public static Block dreamBed() {
        return DREAM_BED.get();
    }

    public static Item dreamBedItem() {
        return DREAM_BED_ITEM.get();
    }
}
