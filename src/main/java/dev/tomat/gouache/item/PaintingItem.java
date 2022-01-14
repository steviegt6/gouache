package dev.tomat.gouache.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class PaintingItem extends DecorationItem {
    private final PaintingMotive motive;

    public PaintingItem(PaintingMotive motive) {
        super(EntityType.PAINTING, new Item.Settings().group(ItemGroup.DECORATIONS));

        this.motive = motive;
    }

    public PaintingMotive getMotive() {
        return motive;
    }
}
