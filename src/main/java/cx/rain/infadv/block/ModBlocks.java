package cx.rain.infadv.block;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.base.OreBase;
import cx.rain.infadv.block.base.OreDropExperienceBase;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    protected static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv blocks.");

        BLOCKS.register(bus);
    }

    public static DeferredRegister<Block> getRegistry() {
        return BLOCKS;
    }

    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new OreDropExperienceBase(3, 3, UniformInt.of(0, 2)));
    public static final RegistryObject<Block> MITHRIL_ORE = BLOCKS.register("mithril_ore", () -> new OreBase(3, 4));
    public static final RegistryObject<Block> DEEPSLATE_MITHRIL_ORE = BLOCKS.register("deepslate_mithril_ore", () -> new OreBase(4.5f, 4));
    public static final RegistryObject<Block> ADAMANTINE_ORE = BLOCKS.register("adamantine_ore", () -> new OreBase(3, 6));
    public static final RegistryObject<Block> DEEPSLATE_ADAMANTINE_ORE = BLOCKS.register("deepslate_adamantine_ore", () -> new OreBase(4.5f, 6));
    public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", () -> new OreDropExperienceBase(3, 3, UniformInt.of(1, 3)));
    public static final RegistryObject<Block> AQUAMARINE_ORE = BLOCKS.register("aquamarine_ore", () -> new OreDropExperienceBase(3, 2, UniformInt.of(2, 5)));

}
