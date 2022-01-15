package dev.tomat.gouache.registry;

import dev.tomat.gouache.item.PaintingItem;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public final class Registrar {
    // public static final PaintingMotive EVE = regP("eve", 32, 16);
    // public static final PaintingMotive BACK = regP("back", 16, 16);

    public static final PaintingItem ALBAN_ITEM = new PaintingItem(PaintingMotive.ALBAN);
    public static final PaintingItem AZTEC_ITEM = new PaintingItem(PaintingMotive.AZTEC);
    public static final PaintingItem AZTEC2_ITEM = new PaintingItem(PaintingMotive.AZTEC2);
    // public static final PaintingItem BACK_ITEM = new PaintingItem(BACK);
    public static final PaintingItem BOMB_ITEM = new PaintingItem(PaintingMotive.BOMB);
    public static final PaintingItem BURNING_SKULL_ITEM = new PaintingItem(PaintingMotive.BURNING_SKULL);
    public static final PaintingItem BUST_ITEM = new PaintingItem(PaintingMotive.BUST);
    public static final PaintingItem COURBET_ITEM = new PaintingItem(PaintingMotive.COURBET);
    public static final PaintingItem CREEBET_ITEM = new PaintingItem(PaintingMotive.CREEBET);
    public static final PaintingItem DONKEY_KONG_ITEM = new PaintingItem(PaintingMotive.DONKEY_KONG);
    // public static final PaintingItem EVE_ITEM = new PaintingItem(EVE);
    public static final PaintingItem FIGHTERS_ITEM = new PaintingItem(PaintingMotive.FIGHTERS);
    public static final PaintingItem GRAHAM_ITEM = new PaintingItem(PaintingMotive.GRAHAM);
    public static final PaintingItem KEBAB_ITEM = new PaintingItem(PaintingMotive.KEBAB);
    public static final PaintingItem MATCH_ITEM = new PaintingItem(PaintingMotive.MATCH);
    public static final PaintingItem PIGSCENE_ITEM = new PaintingItem(PaintingMotive.PIGSCENE);
    public static final PaintingItem PLANT_ITEM = new PaintingItem(PaintingMotive.PLANT);
    public static final PaintingItem POINTER_ITEM = new PaintingItem(PaintingMotive.POINTER);
    public static final PaintingItem POOL_ITEM = new PaintingItem(PaintingMotive.POOL);
    public static final PaintingItem SEA_ITEM = new PaintingItem(PaintingMotive.SEA);
    public static final PaintingItem SKELETON_ITEM = new PaintingItem(PaintingMotive.SKELETON);
    public static final PaintingItem SKULL_AND_ROSES_ITEM = new PaintingItem(PaintingMotive.SKULL_AND_ROSES);
    public static final PaintingItem STAGE_ITEM = new PaintingItem(PaintingMotive.STAGE);
    public static final PaintingItem SUNSET_ITEM = new PaintingItem(PaintingMotive.SUNSET);
    public static final PaintingItem VOID_ITEM = new PaintingItem(PaintingMotive.VOID);
    public static final PaintingItem WANDERER_ITEM = new PaintingItem(PaintingMotive.WANDERER);
    public static final PaintingItem WASTELAND_ITEM = new PaintingItem(PaintingMotive.WASTELAND);
    public static final PaintingItem WITHER_ITEM = new PaintingItem(PaintingMotive.WITHER);

    public static Map<String, PaintingItem> PAINTING_ITEMS = new HashMap<>();

    public static void registerItems() {
        // FYI, this is generated using Regex.
        // public static final PaintingItem (.*?) = new PaintingItem\((.*?)\);
        // quickRegI("$1", $1);
        // So excuse the messiness. :)
        
        quickRegI("ALBAN_ITEM", ALBAN_ITEM);
        quickRegI("AZTEC_ITEM", AZTEC_ITEM);
        quickRegI("AZTEC2_ITEM", AZTEC2_ITEM);
        // quickRegI("BACK_ITEM", BACK_ITEM);
        quickRegI("BOMB_ITEM", BOMB_ITEM);
        quickRegI("BURNING_SKULL_ITEM", BURNING_SKULL_ITEM);
        quickRegI("BUST_ITEM", BUST_ITEM);
        quickRegI("COURBET_ITEM", COURBET_ITEM);
        quickRegI("CREEBET_ITEM", CREEBET_ITEM);
        quickRegI("DONKEY_KONG_ITEM", DONKEY_KONG_ITEM);
        // quickRegI("EVE_ITEM", EVE_ITEM);
        quickRegI("FIGHTERS_ITEM", FIGHTERS_ITEM);
        quickRegI("GRAHAM_ITEM", GRAHAM_ITEM);
        quickRegI("KEBAB_ITEM", KEBAB_ITEM);
        quickRegI("MATCH_ITEM", MATCH_ITEM);
        quickRegI("PIGSCENE_ITEM", PIGSCENE_ITEM);
        quickRegI("PLANT_ITEM", PLANT_ITEM);
        quickRegI("POINTER_ITEM", POINTER_ITEM);
        quickRegI("POOL_ITEM", POOL_ITEM);
        quickRegI("SEA_ITEM", SEA_ITEM);
        quickRegI("SKELETON_ITEM", SKELETON_ITEM);
        quickRegI("SKULL_AND_ROSES_ITEM", SKULL_AND_ROSES_ITEM);
        quickRegI("STAGE_ITEM", STAGE_ITEM);
        quickRegI("SUNSET_ITEM", SUNSET_ITEM);
        quickRegI("VOID_ITEM", VOID_ITEM);
        quickRegI("WANDERER_ITEM", WANDERER_ITEM);
        quickRegI("WASTELAND_ITEM", WASTELAND_ITEM);
        quickRegI("WITHER_ITEM", WITHER_ITEM);
    }

    public static Item resolvePainting(String name) {
        String realName = name + "_item";

        if (PAINTING_ITEMS.containsKey(realName))
            return PAINTING_ITEMS.get(realName);

        return Items.PAINTING;
    }

    private static void quickRegI(String name, PaintingItem item) {
        PAINTING_ITEMS.put("minecraft:" + name.toLowerCase(), item);
        regI(new Identifier("gouache", name.toLowerCase()).toString(), item);
    }

    private static void regI(String id, Item item) {
        Registry.register(Registry.ITEM, id, item);
    }

    private static PaintingMotive regP(String name, int width, int height) {
        return Registry.register(Registry.PAINTING_MOTIVE, name, new PaintingMotive(width, height));
    }
}
