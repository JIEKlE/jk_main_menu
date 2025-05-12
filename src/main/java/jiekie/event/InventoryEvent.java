package jiekie.event;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import jiekie.MainMenuPlugin;
import jiekie.api.NicknameAPI;
import jiekie.model.MenuInventoryHolder;
import jiekie.model.PlayerNameData;
import jiekie.util.ChatUtil;
import jiekie.util.PacketNames;
import jiekie.util.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryEvent implements Listener {
    private final MainMenuPlugin plugin;

    public InventoryEvent(MainMenuPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        onMainMenuInventoryClick(e);
    }

    private void onMainMenuInventoryClick(InventoryClickEvent e) {
        HumanEntity humanEntity = e.getWhoClicked();
        if(!(humanEntity instanceof Player player)) return;

        Inventory inventory = e.getClickedInventory();
        if(inventory == null) return;
        if(!(inventory.getHolder() instanceof MenuInventoryHolder)) return;

        e.setCancelled(true);

        int slot = e.getSlot();
        // teleport to spawn
        if((slot >= 0 && slot <= 3) || (slot >= 9 && slot <= 12))
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "텔레포트 이동 스폰_권한 " + player.getName());

        // 도감
        if((slot >= 4 && slot <= 7) || (slot >= 13 && slot <= 16))
            player.performCommand("도감");

        // show rule
        if((slot >= 36 && slot <= 39) || (slot >= 45 && slot <= 48))
            showRule(player);

        // open trash bin
        if(slot == 44)
            player.performCommand("쓰레기통");

        // call operator
        if(slot == 53)
            callOperator(player);
    }

    private void showRule(Player player) {
        ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
        player.sendPluginMessage(plugin, PacketNames.OPEN_RULE, byteArrayDataOutput.toByteArray());

        SoundUtil.playButtonClick(player);
    }

    private void callOperator(Player player) {
        PlayerNameData playerNameData = NicknameAPI.getInstance().getPlayerNameData(player.getUniqueId());
        String playerName = player.getName();
        if(playerNameData != null)
            playerName = playerNameData.getNickname() + "(" + playerName + ")";
        String message = ChatColor.YELLOW + playerName + "님" + ChatColor.RESET + "이 운영자를 호출했습니다.";

        for(Player operator : Bukkit.getOnlinePlayers()) {
            if(!operator.isOp()) continue;
            operator.sendTitle("\uA004", message, 10, 100, 10);
            SoundUtil.playServiceBell(operator);
        }

        ChatUtil.showMessage(player, ChatUtil.CALL_OPERATOR);
        SoundUtil.playNoteBlockBell(player);
    }
}
