package cx.rain.infadv.enchantment;

import cx.rain.infadv.InfAdv;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv Enchantments.");
        ENCHANTMENTS.register(bus);
    }

    public static final RegistryObject<Enchantment> UNFAILING = ENCHANTMENTS.register("unfailing", () -> new UnfailingEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<Enchantment> SONIC_BOOM = ENCHANTMENTS.register("sonic_boom", () -> new SonicBoomEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<Enchantment> GUN_THWACK = ENCHANTMENTS.register("gun_thwack", () -> new GunThwackEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<Enchantment> HEAVILY_DAMAGED = ENCHANTMENTS.register("heavily_damaged", () -> new HeavilyDamagedEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<Enchantment> SNIPER_BLINDLY = ENCHANTMENTS.register("sniper_blindly", () -> new SniperBlindlyEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<Enchantment> STABLE_SHOOTING = ENCHANTMENTS.register("stable_shooting", () -> new StableShootingEnchantment(Enchantment.Rarity.RARE));

}
