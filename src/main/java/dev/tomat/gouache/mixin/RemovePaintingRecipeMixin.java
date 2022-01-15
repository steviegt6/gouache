package dev.tomat.gouache.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RemovePaintingRecipeMixin extends JsonDataLoader {
    public RemovePaintingRecipeMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Inject(
            method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
            at = @At("HEAD")
    )
    protected void apply(
            Map<Identifier, JsonElement> recipeMap,
            ResourceManager pResourceManager,
            Profiler pProfiler,
            CallbackInfo callback
    ) {
        recipeMap.remove(new Identifier("painting"));
    }
}
