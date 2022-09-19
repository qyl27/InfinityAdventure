package cx.rain.infadv.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class ModTabs extends CreativeModeTab {
    public static final CreativeModeTab ORES = new ModTabs("infadv_ores", ModItems.RUBY);

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
