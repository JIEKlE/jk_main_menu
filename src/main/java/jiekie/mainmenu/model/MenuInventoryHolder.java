package jiekie.mainmenu.model;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public record MenuInventoryHolder(String name) implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}
