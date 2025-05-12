package jiekie.mainmenu.util;

import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class SoundUtil {
    public static void playNoteBlockBell(Player player) {
        player.playSound(player.getLocation(), "minecraft:block.note_block.bell", SoundCategory.MASTER, 0.5f, 1.0f);
    }

    public static void playButtonClick(Player player) {
        player.playSound(player.getLocation(), "minecraft:ui.button.click", SoundCategory.MASTER, 0.5f, 1.0f);
    }

    public static void playServiceBell(Player player) {
        player.playSound(player.getLocation(), "sepia_film_sounds:sound_effect.service_bell", SoundCategory.MASTER, 0.4f, 1.0f);
    }
}
