package cx.rain.infadv.item;

import cx.rain.infadv.block.ModBlockItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public class ModTabs extends CreativeModeTab {

    public static final CreativeModeTab BUILDING_BLOCKS = new ModTabs("building_blocks", ModBlockItems.DWARF_BRICKS);
    public static final CreativeModeTab FUNCTIONAL_BLOCKS = new ModTabs("functional_blocks", ModBlockItems.BEER_BARREL);
    public static final CreativeModeTab FUNCTIONAL_ITEMS = new ModTabs("functional_items", ModItems.DREAM_PILLOW);
    public static final CreativeModeTab BATTLING = new ModTabs("battling", ModItems.MITHRIL_HELMET);
    public static final CreativeModeTab MISC = new ModTabs("misc", ModItems.DWARF_SPAWN_EGG);
    public static final CreativeModeTab TOOLS = new ModTabs("tools", ModItems.MITHRIL_AXE);
    public static final CreativeModeTab FOODS = new ModTabs("foods", ModItems.AMBROSIA);
    public static final CreativeModeTab ORES = new ModTabs("ores", ModItems.RUBY);

    protected final Supplier<? extends ItemLike> iconItem;

    public ModTabs(String label, Supplier<? extends ItemLike> icon) {
        super("infadv_" + label);
        iconItem = icon;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(iconItem.get());
    }

}