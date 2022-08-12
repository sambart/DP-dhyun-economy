package com.dhyun.dsshop.events;

import com.darksoldier1404.dppc.api.essentials.MoneyAPI;
import com.dhyun.dsshop.SimpleShop;
import com.dhyun.dsshop.functions.BoardFunction;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
//import com.dhyun.

@SuppressWarnings("all")
public class BoardEvent implements Listener {
    private final SimpleShop plugin = SimpleShop.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.RED + "온앤오프 마을");
        plugin.boards.put(player.getUniqueId(), board);
        BoardFunction.updateBoard(board);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FastBoard board = plugin.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    @EventHandler
    public void onEconomyEvent(EconomyEvent event) {
    }
}
