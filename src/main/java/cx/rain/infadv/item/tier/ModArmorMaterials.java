package cx.rain.infadv.item.tier;

import cx.rain.infadv.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    SILVER("silver", 20, new int[] {2, 4, 3, 2}, 16, SoundEvents.ARMOR_EQUIP_IRON, 0.5f, 0, () -> Ingredient.of(ModItems.SILVER_INGOT.get())),
    MITHRIL("mithril", 17, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 1.5f, 0.1f, () -> Ingredient.of(ModItems.MITHRIL_INGOT.get())),
    ;

    public static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    private final String materialName;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentEnhance;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final float armorKnockbackResistance;
    private final LazyLoadedValue<Ingredient> repairItem;

    private ModArmorMaterials(String name, int durabilityModifier, int[] protectionPerSlot, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredients) {
        materialName = name;
        durabilityMultiplier = durabilityModifier;
        slotProtections = protectionPerSlot;
        enchantmentEnhance = enchantmentValue;
        equipSound = sound;
        armorToughness = toughness;
        armorKnockbackResistance = knockbackResistance;
        repairItem = new LazyLoadedValue(repairIngredients);
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return slotProtections[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return enchantmentEnhance;
    }

    public SoundEvent getEquipSound() {
        return equipSound;
    }

    public Ingredient getRepairIngredient() {
        return repairItem.get();
    }

    public String getName() {
        return materialName;
    }

    public float getToughness() {
        return armorToughness;
    }

    public float getKnockbackResistance() {
        return armorKnockbackResistance;
    }
}
