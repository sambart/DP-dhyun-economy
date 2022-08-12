package com.dhyun.dsshop.functions;

import com.darksoldier1404.dppc.api.essentials.MoneyAPI;
import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import com.dhyun.dsshop.SimpleShop;
import com.google.common.collect.Lists;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.*;
import com.earth2me.essentials.Essentials;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

@SuppressWarnings("all")
public class BoardFunction {
    private static final SimpleShop plugin = SimpleShop.getInstance();
    private static final String prefix = plugin.prefix;
    private static final DLang lang = plugin.lang;
    private final static Essentials ess = plugin.ess;
    private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static Scoreboard board = manager.getNewScoreboard();

    private static final String MAIN_BOARD_NAME = "온앤오프 보드";

    public static void InitAllBoard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            InitPlayerBoard(online);
        }
    }

    public static void updateBoard(FastBoard board) {
        board.updateLines(
                "",
                "§0플레이어: §8" + getServer().getOnlinePlayers().size(),
                "",
                "§0킬: §8" + board.getPlayer().getStatistic(Statistic.MOB_KILLS),
                "",
                "§0현금: §8" + MoneyAPI.getMoney(board.getPlayer()).toString()
        );
    }

    public static void InitPlayerBoard(Player player){
        Objective objective = CreateDummyBoard(MAIN_BOARD_NAME);
        player.setScoreboard(board);
        Score balance = objective.getScore(ChatColor.GREEN + "현금:");
        int playerBal = MoneyAPI.getMoney(player).intValue();
        balance.setScore(playerBal);
    }

    private static Objective CreateDummyBoard(String boardName) {
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(boardName);
        return objective;
    }

    public static void AddBoard(CommandSender p, String name){

    }

    public static String getColoredText(String[] args, int line) {
        StringBuilder s = new StringBuilder();
        args = Arrays.copyOfRange(args, line, args.length);
        Iterator<String> i = Arrays.stream(args).iterator();
        while (i.hasNext()) {
            s.append(i.next()).append(" ");
        }
        // delete last space
        if (s.charAt(s.length() - 1) == ' ') {
            s.deleteCharAt(s.length() - 1);
        }
        return ChatColor.translateAlternateColorCodes('&', s.toString());
    }

    public static List<YamlConfiguration> getData(String path) {
        File file = new File(plugin.getDataFolder() + "/" + path);
        if (!file.exists()) {
            return null;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return null;
        }
        List<YamlConfiguration> list = Lists.newArrayList();
        for (File f : files) {
            YamlConfiguration data = YamlConfiguration
                    .loadConfiguration(new File(plugin.getDataFolder() + "/" + path, f.getName()));
            list.add(data);
        }
        return list;
    }

}
