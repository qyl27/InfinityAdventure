package cx.rain.infadv.item;

import cx.rain.infadv.block.ModBlockItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class ModTabs extends CreativeModeTab {
    public static final CreativeModeTab BUILDING_BLOCKS = new ModTabs("building_blocks", ModBlockItems.DWARF_BRICKS);
    public static final CreativeModeTab FUNCTIONAL_BLOCKS = new ModTabs("functional_blocks", ModBlockItems.BEER_BARREL);
    public static final CreativeModeTab ORES = new ModTabs("ores", ModItems.RUBY);
    public static final CreativeModeTab FUNCTIONAL_ITEMS = new ModTabs("functional_items", ModItems.DREAM_PILLOW);
    public static final CreativeModeTab BATTLING = new ModTabs("battling", ModItems.MITHRIL_HELMET);
    public static final CreativeModeTab TOOLS = new ModTabs("tools", ModItems.DREAM_PILLOW);


    protected Supplier<? extends ItemLike> iconItem;

    public ModTabs(String label, Supplier<? extends ItemLike> icon) {
        super("infadv_" + label);
        iconItem = icon;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(iconItem.get());
    }
}
