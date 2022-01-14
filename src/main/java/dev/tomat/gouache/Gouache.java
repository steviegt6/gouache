package dev.tomat.gouache;

import dev.tomat.gouache.registry.Registrar;
import net.fabricmc.api.ModInitializer;

public class Gouache implements ModInitializer {
    @Override
    public void onInitialize() {
        Registrar.registerItems();
    }
}
