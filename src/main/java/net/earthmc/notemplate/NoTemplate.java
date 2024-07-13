package net.earthmc.notemplate;

import net.earthmc.notemplate.listener.PrepareSmithingListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

import static java.util.Map.entry;

public final class NoTemplate extends JavaPlugin {

    public static final Map<Material, Material> DIAMOND_NETHERITE_MAP = Map.ofEntries(
            entry(Material.DIAMOND_HELMET, Material.NETHERITE_HELMET),
            entry(Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE),
            entry(Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS),
            entry(Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS),
            entry(Material.DIAMOND_SWORD, Material.NETHERITE_SWORD),
            entry(Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE),
            entry(Material.DIAMOND_AXE, Material.NETHERITE_AXE),
            entry(Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL),
            entry(Material.DIAMOND_HOE, Material.NETHERITE_HOE)
    );

    @Override
    public void onEnable() {
        addRecipes();

        registerListeners(
                new PrepareSmithingListener()
        );
    }

    private void addRecipes() {
        for (Map.Entry<Material, Material> entry : DIAMOND_NETHERITE_MAP.entrySet()) {
            Material base = entry.getKey();
            Material result = entry.getValue();

            NamespacedKey key = NamespacedKey.fromString(result.getKey().getKey(), this);
            if (key == null) return;

            SmithingTransformRecipe recipe = new SmithingTransformRecipe(
                    key,
                    new ItemStack(result),
                    new RecipeChoice.MaterialChoice(Material.AIR),
                    new RecipeChoice.MaterialChoice(base),
                    new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
            );
            Bukkit.addRecipe(recipe);
        }
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
