package dev.tomat.gouache.mixin;

import dev.tomat.gouache.registry.Registrar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PaintingEntity.class)
public abstract class PaintingDropReplacementMixin extends AbstractDecorationEntity {
    @Shadow public PaintingMotive motive;

    protected PaintingDropReplacementMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Tomat
     * @reason Overwrite the normally-dropped painting to be in line with our painting items.
     */
    @Overwrite
    public void onBreak(@Nullable Entity entity) {
        if (!world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            return;
        }

        playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0f, 1.0f);

        if (entity instanceof PlayerEntity playerEntity) {
            if (playerEntity.getAbilities().creativeMode) {
                return;
            }
        }

        this.dropItem(Registrar.resovlvePainting(Registry.PAINTING_MOTIVE.getId(motive).toString()));
    }

    /**
     * @author Tomat
     * @reason Overwrite the normally-dropped painting to be in line with our painting items.
     */
    @Overwrite
    public ItemStack getPickBlockStack() {
        return new ItemStack(Registrar.resovlvePainting(Registry.PAINTING_MOTIVE.getId(motive).toString()));
    }
}
