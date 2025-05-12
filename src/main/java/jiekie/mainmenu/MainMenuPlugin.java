package jiekie.mainmenu;

import jiekie.mainmenu.command.MainMenuCommand;
import jiekie.mainmenu.event.InventoryEvent;
import jiekie.mainmenu.util.PacketNames;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainMenuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // command
        getCommand("메인메뉴").setExecutor(new MainMenuCommand());

        // event
        getServer().getPluginManager().registerEvents(new InventoryEvent(this), this);

        // packet
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, PacketNames.OPEN_RULE);

        getLogger().info("세피아 필름 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    @Override
    public void onDisable() {}
}
