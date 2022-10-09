package cx.rain.infadv.item;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.ModEntities;
import cx.rain.infadv.item.tier.ModArmorMaterials;
import cx.rain.infadv.item.tier.ModToolTiers;
import cx.rain.infadv.item.wine.AlcoholFoodItem;
import cx.rain.infadv.item.wine.ModFoodData;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
    public static final RegistryObject<Item> WINE_GLASS = ITEMS.register("wine_glass", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> DREAM_PILLOW = ITEMS.register("dream_pillow", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> QUEEN_FANG = ITEMS.register("queen_fang", () -> new Item(new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<Item> SOUL_FLUTE = ITEMS.register("soul_flute", () -> new Item(new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<Item> SOUL_SALT = ITEMS.register("soul_salt", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> TARANTULA_EGG = ITEMS.register("tarantula_egg", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> EMERALD_COIN = ITEMS.register("emerald_coin", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));
    public static final RegistryObject<Item> DIAMONDS_GOLD_COIN = ITEMS.register("diamond_gold_coin", () -> new Item(new Item.Properties().tab(ModTabs.FUNCTIONAL_ITEMS)));

    public static final RegistryObject<Item> EMERALD_SUNRISE = ITEMS.register("cup_emeraldsunrise", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.EMERALD_SUNRISE), 0.08F, true));
    public static final RegistryObject<Item> DWARF_MALT_LIQUOR = ITEMS.register("cup_dwarfmalt", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.DWARF_MALT_LIQUOR), 0.12F, true));
    public static final RegistryObject<Item> GOLDEN_SCORPION = ITEMS.register("cup_goldenscorpion", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.GOLDEN_SCORPION), 0.40F, true));
    public static final RegistryObject<Item> RUM = ITEMS.register("cup_rum", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.RUM), 0.45F, true));
    public static final RegistryObject<Item> VODKA = ITEMS.register("cup_vodka", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.VODKA), 0.56F, true));
    public static final RegistryObject<Item> WINE = ITEMS.register("cup_wine", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.WINE), 0.15F, true));
    public static final RegistryObject<Item> AMBROSIA = ITEMS.register("cup_ambrosia", () -> new Item(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.AMBROSIA)));

    public static final RegistryObject<Item> MALT_LIQUOR_CHOCOLATE = ITEMS.register("chocolate_dwarfmalt", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.MALT_LIQUOR_CHOCOLATE), 0.03F, false));
    public static final RegistryObject<Item> EMERALD_CHOCOLATE = ITEMS.register("chocolate_emeraldsunrise", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.EMERALD_CHOCOLATE), 0.02F, false));
    public static final RegistryObject<Item> RUM_CHOCOLATE = ITEMS.register("chocolate_rum", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.RUM_CHOCOLATE), 0.11F, false));
    public static final RegistryObject<Item> VODKA_CHOCOLATE = ITEMS.register("chocolate_vodka", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.VODKA_CHOCOLATE), 0.14F, false));
    public static final RegistryObject<Item> WINE_CHOCOLATE = ITEMS.register("chocolate_wine", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.WINE_CHOCOLATE), 0.04F, false));
    public static final RegistryObject<Item> GOLDEN_SCORPION_CHOCOLATE = ITEMS.register("chocolate_goldenscorpion", () -> new AlcoholFoodItem(new Item.Properties().tab(ModTabs.FOODS).food(ModFoodData.GOLDEN_SCORPION_CHOCOLATE), 0.10F, false));

    public static final RegistryObject<SwordItem> SILVER_SWORD = ITEMS.register("silver_sword", () -> new SwordItem(ModToolTiers.SILVER, 3, -2.4F, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<AxeItem> SILVER_AXE = ITEMS.register("silver_axe", () -> new AxeItem(ModToolTiers.SILVER, 6, -3.1f, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<PickaxeItem> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () -> new PickaxeItem(ModToolTiers.SILVER, 1, -2.8F, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<ShovelItem> SILVER_SHOVEL = ITEMS.register("silver_shovel", () -> new ShovelItem(ModToolTiers.SILVER, 1.5F, -3, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<HoeItem> SILVER_HOE = ITEMS.register("silver_hoe", () -> new HoeItem(ModToolTiers.SILVER, -2, -1, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<SwordItem> MICA_SWORD = ITEMS.register("mica_sword", () -> new SwordItem(ModToolTiers.SILVER, 3, -2.4F, new Item.Properties().tab(ModTabs.BATTLING)));
    public static final RegistryObject<AxeItem> MICA_AXE = ITEMS.register("mica_axe", () -> new AxeItem(ModToolTiers.SILVER, 6, -3.1f, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<PickaxeItem> MICA_PICKAXE = ITEMS.register("mica_pickaxe", () -> new PickaxeItem(ModToolTiers.SILVER, 1, -2.8F, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<ShovelItem> MICA_SHOVEL = ITEMS.register("mica_shovel", () -> new ShovelItem(ModToolTiers.SILVER, 1.5F, -3, new Item.Properties().tab(ModTabs.TOOLS)));
    public static final RegistryObject<HoeItem> MICA_HOE = ITEMS.register("mica_hoe", () -> new HoeItem(ModToolTiers.SILVER, -2, -1, new Item.Properties().tab(ModTabs.TOOLS)));
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
    public static final RegistryObject<ForgeSpawnEggItem> DARK_GHOST_SPAWN_EGG = ITEMS.register("dark_ghost_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DARK_GHOST, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> BROODER_QUEEN_SPAWN_EGG = ITEMS.register("brooder_queen_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BROODER_QUEEN, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> MUSHROOM_FAERY_SPAWN_EGG = ITEMS.register("mushroom_faery_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.MUSHROOM_FAERY, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> MUSHROOM_LURKER_SPAWN_EGG = ITEMS.register("mushroom_lurker_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.MUSHROOM_LURKER, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> POISON_LURKER_SPAWN_EGG = ITEMS.register("poison_lurker_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.POISON_LURKER, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> POISON_THROAT_SPAWN_EGG = ITEMS.register("poison_throat_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.POISON_THROAT, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> TARANTULA_SPAWN_EGG = ITEMS.register("tarantula_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TARANTULA, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> VILE_RAT_SPAWN_EGG = ITEMS.register("vile_rat_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.VILE_RAT, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));
    public static final RegistryObject<ForgeSpawnEggItem> TRAPPER_SPAWN_EGG = ITEMS.register("trapper_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TRAPPER, 0x66ccff, 0x0099ff, new Item.Properties().tab(ModTabs.MISC).stacksTo(16)));

    public static final RegistryObject<Item> DWARF_COPPER_COIN = ITEMS.register("dwarf_copper_coin", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));
    public static final RegistryObject<Item> DWARF_SILVER_COIN = ITEMS.register("dwarf_silver_coin", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));
    public static final RegistryObject<Item> DWARF_GOLDEN_COIN = ITEMS.register("dwarf_golden_coin", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));
    public static final RegistryObject<Item> DWARF_DIAMOND_COIN = ITEMS.register("dwarf_diamond_coin", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));
    public static final RegistryObject<Item> DWARF_EMERALD_COIN = ITEMS.register("dwarf_emerald_coin", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));

    // Todo: wine below.
    public static final RegistryObject<Item> CUP = ITEMS.register("cup", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_AMBROSIA = ITEMS.register("cup_ambrosia", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_DWARF_MALT = ITEMS.register("cup_dwarf_malt", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_EMERALD_SUNRISE = ITEMS.register("cup_emerald_sunrise", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_GOLDEN_SCORPION = ITEMS.register("cup_golden_scorpion", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_RUM = ITEMS.register("cup_rum", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_VODKA = ITEMS.register("cup_vodka", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> CUP_WINE = ITEMS.register("cup_wine", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE_DWARF_MALT = ITEMS.register("wine_chocolate_dwarf_malt", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE_EMERALD_SUNRISE = ITEMS.register("wine_chocolate_emerald_sunrise", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE_GOLDEN_SCORPION = ITEMS.register("wine_chocolate_golden_scorpion", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE_RUM = ITEMS.register("wine_chocolate_rum", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE_VODKA = ITEMS.register("wine_chocolate_vodka", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> WINE_CHOCOLATE = ITEMS.register("wine_chocolate", () -> new Item(new Item.Properties().tab(ModTabs.FOODS)));
    public static final RegistryObject<Item> SOUL_SALT = ITEMS.register("soul_salt", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));
    public static final RegistryObject<Item> SOUL_WHISTLE = ITEMS.register("soul_whistle", () -> new Item(new Item.Properties().tab(ModTabs.TOOLS)));   // Todo: function.
    public static final RegistryObject<Item> TARANTULA_EGG = ITEMS.register("tarantula_egg", () -> new Item(new Item.Properties().tab(ModTabs.MISC)));  // Todo: function.
}

