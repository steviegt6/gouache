package dev.tomat.gouache;

import dev.tomat.gouache.registry.Registrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class Gouache implements ModInitializer {
    @Override
    public void onInitialize() {
        Registrar.registerItems();

        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.WITHER_ITEM, 0.25f),
                        new PaintingLoot(Registrar.POINTER_ITEM, 0.2f)
                });
            }

            if (LootTables.BURIED_TREASURE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.ALBAN_ITEM, 0.25f),
                        new PaintingLoot(Registrar.CREEBET_ITEM, 0.15f)
                });
            }

            if (LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.ALBAN_ITEM, 0.15f),
                        new PaintingLoot(Registrar.BOMB_ITEM, 0.15f),
                        new PaintingLoot(Registrar.WASTELAND_ITEM, 0.15f),
                        new PaintingLoot(Registrar.MATCH_ITEM, 0.15f)
                });
            }

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.SKULL_AND_ROSES_ITEM,15f),
                        new PaintingLoot(Registrar.STAGE_ITEM, 0.2f),
                        new PaintingLoot(Registrar.BURNING_SKULL_ITEM, 0.05f)
                });
            }

            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.BUST_ITEM, 0.1f),
                        new PaintingLoot(Registrar.VOID_ITEM, 0.1f)
                });
            }

            if (LootTables.IGLOO_CHEST_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.GRAHAM_ITEM, 0.25f)
                });
            }

            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.SUNSET_ITEM, 0.15f),
                        new PaintingLoot(Registrar.STAGE_ITEM, 0.2f),
                        new PaintingLoot(Registrar.FIGHTERS_ITEM, 0.05f)
                });
            }

            if (LootTables.NETHER_BRIDGE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.MATCH_ITEM, 0.15f),
                        new PaintingLoot(Registrar.WITHER_ITEM, 0.5f)
                });
            }

            if (LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.KEBAB_ITEM, 0.15f),
                        new PaintingLoot(Registrar.SUNSET_ITEM, 0.1f),
                        new PaintingLoot(Registrar.STAGE_ITEM, 0.1f)
                });
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.MATCH_ITEM, 0.01f) // lol
                });
            }

            if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.BOMB_ITEM, 0.05f),
                        new PaintingLoot(Registrar.SEA_ITEM, 0.08f)
                });
            }

            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.AZTEC_ITEM, 0.15f),
                        new PaintingLoot(Registrar.AZTEC2_ITEM, 0.15f),
                        new PaintingLoot(Registrar.GRAHAM_ITEM, 0.1f),
                        new PaintingLoot(Registrar.VOID_ITEM, 0.1f),
                        new PaintingLoot(Registrar.PIGSCENE_ITEM, 0.2f)
                });
            }

            if (LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.PLANT_ITEM, 0.15f),
                        new PaintingLoot(Registrar.COURBET_ITEM, 0.25f),
                        new PaintingLoot(Registrar.DONKEY_KONG_ITEM, 0.05f),
                        new PaintingLoot(Registrar.SKELETON_ITEM, 0.05f)
                });
            }

            if (LootTables.VILLAGE_CARTOGRAPHER_CHEST.equals(id)) {
                addPaintingsToPool(supplier, new PaintingLoot[] {
                        new PaintingLoot(Registrar.ALBAN_ITEM, 0.15f),
                        new PaintingLoot(Registrar.KEBAB_ITEM, 0.2f),
                        new PaintingLoot(Registrar.PLANT_ITEM, 0.25f)
                });
            }
        }));
    }

    private static void addPaintingsToPool(FabricLootSupplierBuilder supplier, PaintingLoot[] loot) {
        FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();

        float weight = 1f;

        for (PaintingLoot pLoot : loot) {
            weight -= pLoot.CHANCE;
            builder.withEntry(ItemEntry.builder(pLoot.PAINTING).weight((int) (pLoot.CHANCE * 100f)).build());
        }

        builder.withEntry(ItemEntry.builder(Items.AIR).weight((int)(weight * 100f)).build());

        supplier.withPool(builder.rolls(ConstantLootNumberProvider.create(1)).build());
    }

    public static class PaintingLoot {
        public final Item PAINTING;
        public final float CHANCE;

        public PaintingLoot(Item painting, float chance) {
            PAINTING = painting;
            CHANCE = chance;
        }
    }
}
