package cx.rain.infadv.item.tier;

import cx.rain.infadv.data.tag.ModTags;
import cx.rain.infadv.item.ModItems;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final ForgeTier SILVER = new ForgeTier(2, 210, 6, 2, 16, ModTags.SILVER_MINEABLE, () -> Ingredient.of(ModItems.SILVER_INGOT.get()));
    public static final ForgeTier MITHRIL = new ForgeTier(2, 520, 6, 3, 25, ModTags.MITHRIL_MINEABLE, () -> Ingredient.of(ModItems.MITHRIL_INGOT.get()));
    public static final ForgeTier MICA = new ForgeTier(2, 450, 6, 2.5f, 12, ModTags.MICA_MINEABLE, () -> Ingredient.of(ModItems.MITHRIL_INGOT.get()));
}
