package net.earthmc.notemplate.listener;

import net.earthmc.notemplate.NoTemplate;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

public class PrepareSmithingListener implements Listener {

    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        ItemStack mineral = inventory.getInputMineral();
        if (mineral == null) return;

        if (!mineral.getType().equals(Material.NETHERITE_INGOT)) return;

        ItemStack template = inventory.getInputTemplate();
        if (template != null) return;

        ItemStack equipment = inventory.getInputEquipment();
        if (equipment == null) return;

        Material netheriteEquipment = NoTemplate.DIAMOND_NETHERITE_MAP.get(equipment.getType());
        if (netheriteEquipment == null) return;

        ItemStack netheriteItem = new ItemStack(netheriteEquipment);
        netheriteItem.setItemMeta(equipment.getItemMeta());

        event.setResult(netheriteItem);
    }
}
