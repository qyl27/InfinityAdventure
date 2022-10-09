package cx.rain.infadv.entity;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.renderer.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, InfAdv.MODID);

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv Entities.");
        ENTITY_TYPES.register(bus);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DWARF.get(), DwarfRenderer::new);
        event.registerEntityRenderer(DARK_GHOST.get(), DarkGhostRenderer::new);
        event.registerEntityRenderer(BROODER_QUEEN.get(), BrooderQueenRenderer::new);
        event.registerEntityRenderer(MUSHROOM_FAERY.get(), MushroomFaeryRenderer::new);
        event.registerEntityRenderer(MUSHROOM_LURKER.get(), MushroomLurkerRenderer::new);
        event.registerEntityRenderer(POISON_LURKER.get(), PoisonLurkerRenderer::new);
        event.registerEntityRenderer(POISON_THROAT.get(), PoisonThroatRenderer::new);
        event.registerEntityRenderer(TARANTULA.get(), TarantulaRenderer::new);
        event.registerEntityRenderer(VILE_RAT.get(), VileRatRenderer::new);
        event.registerEntityRenderer(TRAPPER.get(), TrapperRenderer::new);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(DWARF.get(), DwarfEntity.getDefaultAttributes());
        event.put(DARK_GHOST.get(), DarkGhostEntity.getDefaultAttributes());
        event.put(BROODER_QUEEN.get(), BrooderQueenEntity.getDefaultAttributes());
        event.put(MUSHROOM_FAERY.get(), MushroomFaeryEntity.getDefaultAttributes());
        event.put(MUSHROOM_LURKER.get(), MushroomLurkerEntity.getDefaultAttributes());
        event.put(POISON_LURKER.get(), PoisonLurkerEntity.getDefaultAttributes());
        event.put(POISON_THROAT.get(), PoisonThroatEntity.getDefaultAttributes());
        event.put(TARANTULA.get(), TarantulaEntity.getDefaultAttributes());
        event.put(VILE_RAT.get(), VileRatEntity.getDefaultAttributes());
        event.put(TRAPPER.get(), TrapperEntity.getDefaultAttributes());
    }

    public static final RegistryObject<EntityType<DwarfEntity>> DWARF = ENTITY_TYPES.register("dwarf", () -> EntityType.Builder.of(DwarfEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("dwarf"));
    public static final RegistryObject<EntityType<DarkGhostEntity>> DARK_GHOST = ENTITY_TYPES.register("dark_ghost", () -> EntityType.Builder.of(DarkGhostEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("dark_ghost"));
    public static final RegistryObject<EntityType<BrooderQueenEntity>> BROODER_QUEEN = ENTITY_TYPES.register("brooder_queen", () -> EntityType.Builder.of(BrooderQueenEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("brooder_queen"));
    public static final RegistryObject<EntityType<MushroomFaeryEntity>> MUSHROOM_FAERY = ENTITY_TYPES.register("mushroom_faery", () -> EntityType.Builder.of(MushroomFaeryEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("mushroom_faery"));
    public static final RegistryObject<EntityType<MushroomLurkerEntity>> MUSHROOM_LURKER = ENTITY_TYPES.register("mushroom_lurker", () -> EntityType.Builder.of(MushroomLurkerEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("mushroom_lurker"));
    public static final RegistryObject<EntityType<PoisonLurkerEntity>> POISON_LURKER = ENTITY_TYPES.register("poison_lurker", () -> EntityType.Builder.of(PoisonLurkerEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("poison_lurker"));
    public static final RegistryObject<EntityType<PoisonThroatEntity>> POISON_THROAT = ENTITY_TYPES.register("poison_throat", () -> EntityType.Builder.of(PoisonThroatEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("poison_throat"));
    public static final RegistryObject<EntityType<TarantulaEntity>> TARANTULA = ENTITY_TYPES.register("tarantula", () -> EntityType.Builder.of(TarantulaEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("tarantula"));
    public static final RegistryObject<EntityType<VileRatEntity>> VILE_RAT = ENTITY_TYPES.register("vile_rat", () -> EntityType.Builder.of(VileRatEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("vile_rat"));
    public static final RegistryObject<EntityType<TrapperEntity>> TRAPPER = ENTITY_TYPES.register("trapper", () -> EntityType.Builder.of(TrapperEntity::new, MobCategory.MISC).sized(1.25f, 1.5f).build("trapper"));

}
