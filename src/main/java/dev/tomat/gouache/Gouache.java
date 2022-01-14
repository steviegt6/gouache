package dev.tomat.gouache;

import dev.tomat.gouache.registry.Registrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class Gouache implements ModInitializer {
    @Override
    public void onInitialize() {
        Registrar.registerItems();

        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (!LootTables.SIMPLE_DUNGEON_CHEST.equals(id))
                return;

            FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();

            for (Item item : Registrar.PAINTING_ITEMS.values()) {
                builder.withEntry(ItemEntry.builder(item).build());
            }

            supplier.withPool(builder.rolls(ConstantLootNumberProvider.create(1)).build());
        }));
    }
}
