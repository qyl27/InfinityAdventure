package cx.rain.infadv.item;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.ModEntities;
import cx.rain.infadv.item.tier.ModArmorMaterials;
import cx.rain.infadv.item.tier.ModToolTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    protected static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv Items.");

        ITEMS.register(bus);
    }

    public static DeferredRegister<Item> getRegistry() {
        return ITEMS;
    }

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> ADAMANTINE_INGOT = ITEMS.register("adamantine_ingot", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> MICA = ITEMS.register("mica", () -> new Item(new Item.Properties().tab(ModTabs.ORES)));
    public static final RegistryObject<Item> DREAM_PILLOW = ITEMS.register("dream_pillow", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));

    public static final RegistryObject<SwordItem> SILVER_SWORD = ITEMS.register("silver_sword", () -> new SwordItem(ModToolTiers.SILVER, 3, -2.4F, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<AxeItem> SILVER_AXE = ITEMS.register("silver_axe", () -> new AxeItem(ModToolTiers.SILVER, 6, -3.1f, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<PickaxeItem> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () -> new PickaxeItem(ModToolTiers.SILVER, 1, -2.8F, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<ShovelItem> SILVER_SHOVEL = ITEMS.register("silver_shovel", () -> new ShovelItem(ModToolTiers.SILVER, 1.5F, -3, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<HoeItem> SILVER_HOE = ITEMS.register("silver_hoe", () -> new HoeItem(ModToolTiers.SILVER, -2, -1, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<ArmorItem> MITHRIL_HELMET = ITEMS.register("mithril_helmet", () -> new ArmorItem(ModArmorMaterials.MITHRIL, EquipmentSlot.HEAD, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<ArmorItem> MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate", () -> new ArmorItem(ModArmorMaterials.MITHRIL, EquipmentSlot.CHEST, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<ArmorItem> MITHRIL_LEGGINGS = ITEMS.register("mithril_leggings", () -> new ArmorItem(ModArmorMaterials.MITHRIL, EquipmentSlot.LEGS, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<ArmorItem> MITHRIL_BOOTS = ITEMS.register("mithril_boots", () -> new ArmorItem(ModArmorMaterials.MITHRIL, EquipmentSlot.FEET, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<SwordItem> MITHRIL_SWORD = ITEMS.register("mithril_sword", () -> new SwordItem(ModToolTiers.MITHRIL, 3, -2.4F, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<AxeItem> MITHRIL_AXE = ITEMS.register("mithril_axe", () -> new AxeItem(ModToolTiers.MITHRIL, 6, -3.1f, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<PickaxeItem> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe", () -> new PickaxeItem(ModToolTiers.MITHRIL, 1, -2.8F, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<ShovelItem> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel", () -> new ShovelItem(ModToolTiers.MITHRIL, 1.5F, -3, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<HoeItem> MITHRIL_HOE = ITEMS.register("mithril_hoe", () -> new HoeItem(ModToolTiers.MITHRIL, -2, -1, new Item.Properties().tab(ModTabs.TOOLS)));

    public static final RegistryObject<ForgeSpawnEggItem> DWARF_SPAWN_EGG = ITEMS.register("dwarf_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DWARF, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
}
