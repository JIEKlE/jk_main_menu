package jiekie.mainmenu.util;

import org.bukkit.command.CommandSender;

public class ChatUtil {
    /* feedback */
    public static final String CALL_OPERATOR = getCheckPrefix() + "운영자를 호출했습니다.";

    /* prefix */
    public static String getCheckPrefix() {
        return "\uA001 ";
    }

    public static String getWarnPrefix() {
        return "\uA003 ";
    }

    /* validate */
    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void showMessage(CommandSender sender, String message) {
        sender.sendMessage(message);
    }
}
