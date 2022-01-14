package dev.tomat.gouache.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.GlowItemFrameEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class PaintingItem extends DecorationItem {
    private final PaintingMotive motive;

    public PaintingItem(PaintingMotive motive) {
        super(EntityType.PAINTING, new Item.Settings().group(ItemGroup.DECORATIONS));

        this.motive = motive;
    }

    public PaintingMotive getMotive() {
        return motive;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        AbstractDecorationEntity abstractDecorationEntity;
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();

        if (playerEntity != null && !this.canPlaceOn(playerEntity, direction, itemStack, blockPos2)) {
            return ActionResult.FAIL;
        }

        World world = context.getWorld();

        abstractDecorationEntity = new PaintingEntity(world, blockPos2, direction, getMotive());

        NbtCompound nbtCompound = itemStack.getNbt();

        if (nbtCompound != null) {
            EntityType.loadFromEntityNbt(world, playerEntity, abstractDecorationEntity, nbtCompound);
        }

        if (abstractDecorationEntity.canStayAttached()) {
            if (!world.isClient) {
                abstractDecorationEntity.onPlace();
                world.emitGameEvent(playerEntity, GameEvent.ENTITY_PLACE, blockPos);
                world.spawnEntity(abstractDecorationEntity);
            }

            itemStack.decrement(1);

            return ActionResult.success(world.isClient);
        }
        
        return ActionResult.CONSUME;
    }
}
