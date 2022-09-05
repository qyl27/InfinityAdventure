package cx.rain.infadv.block;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.base.OreBlockBase;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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

    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new OreBlockBase(3, 3));
    public static final RegistryObject<Block> MITHRIL_ORE = BLOCKS.register("mithril_ore", () -> new OreBlockBase(3, 4));
    public static final RegistryObject<Block> ADAMANTINE_ORE = BLOCKS.register("adamantine_ore", () -> new OreBlockBase(3, 6));
    public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", () -> new OreBlockBase(3, 3));
    public static final RegistryObject<Block> AQUAMARINE_ORE = BLOCKS.register("aquamarine_ore", () -> new OreBlockBase(3, 2));

}
