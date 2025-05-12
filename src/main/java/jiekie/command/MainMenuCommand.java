package jiekie.command;

import jiekie.model.MenuInventoryHolder;
import jiekie.util.ChatUtil;
import jiekie.util.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        openMainMenu(player);
        return true;
    }

    private void openMainMenu(Player player) {
        String chestName = ":offset_-16::main_menu:";
        int size = 54;

        MenuInventoryHolder holder = new MenuInventoryHolder("main_menu");
        Inventory inventory = Bukkit.createInventory(holder, size, chestName);
        player.openInventory(inventory);
        SoundUtil.playNoteBlockBell(player);
    }
}
