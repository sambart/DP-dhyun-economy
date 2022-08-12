package com.dhyun.dsshop.events;

import com.dhyun.dsshop.constants.EconomyType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;


public final class EconomyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private EconomyType eType;
    private BigDecimal useMoney;
    private ItemStack item;


    public EconomyEvent(Player p, EconomyType type, BigDecimal useMoney, ItemStack item) {
        this.player = p;
        this.eType = type;
        this.useMoney = useMoney;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public EconomyType getType() {
        return eType;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public EconomyType geteType() {
        return eType;
    }

    public ItemStack getItem() {
        return item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
