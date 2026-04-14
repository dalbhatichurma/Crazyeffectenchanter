package com.weaponenchanter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EffectCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.GOLD + "=== Weapon Effects ===");
            player.sendMessage(ChatColor.YELLOW + "/we fire      - Adds fire effect");
            player.sendMessage(ChatColor.YELLOW + "/we poison    - Adds poison effect");
            player.sendMessage(ChatColor.YELLOW + "/we lightning - Adds lightning effect");
            player.sendMessage(ChatColor.YELLOW + "/we wither    - Adds wither effect");
            player.sendMessage(ChatColor.YELLOW + "/we blind     - Adds blindness effect");
            player.sendMessage(ChatColor.YELLOW + "/we slow      - Adds slowness effect");
            player.sendMessage(ChatColor.YELLOW + "/we lifesteal - Adds life steal effect");
            player.sendMessage(ChatColor.YELLOW + "/we explode   - Adds explosion effect");
            player.sendMessage(ChatColor.YELLOW + "/we freeze    - Adds freeze effect");
            player.sendMessage(ChatColor.YELLOW + "/we knockback - Adds mega knockback");
            player.sendMessage(ChatColor.GRAY + "Hold a weapon and use the command!");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        
        if (item == null || item.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "You must be holding a weapon!");
            return true;
        }

        if (!isWeapon(item.getType())) {
            player.sendMessage(ChatColor.RED + "This item is not a weapon!");
            return true;
        }

        String effect = args[0].toLowerCase();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

        switch (effect) {
            case "fire":
                lore.add(ChatColor.GOLD + "🔥 Fire Effect");
                player.sendMessage(ChatColor.GREEN + "Added Fire effect to your weapon!");
                break;
            case "poison":
                lore.add(ChatColor.DARK_GREEN + "☠ Poison Effect");
                player.sendMessage(ChatColor.GREEN + "Added Poison effect to your weapon!");
                break;
            case "lightning":
                lore.add(ChatColor.AQUA + "⚡ Lightning Effect");
                player.sendMessage(ChatColor.GREEN + "Added Lightning effect to your weapon!");
                break;
            case "wither":
                lore.add(ChatColor.DARK_GRAY + "💀 Wither Effect");
                player.sendMessage(ChatColor.GREEN + "Added Wither effect to your weapon!");
                break;
            case "blind":
                lore.add(ChatColor.BLACK + "👁 Blindness Effect");
                player.sendMessage(ChatColor.GREEN + "Added Blindness effect to your weapon!");
                break;
            case "slow":
                lore.add(ChatColor.BLUE + "❄ Slowness Effect");
                player.sendMessage(ChatColor.GREEN + "Added Slowness effect to your weapon!");
                break;
            case "lifesteal":
                lore.add(ChatColor.RED + "♥ Life Steal Effect");
                player.sendMessage(ChatColor.GREEN + "Added Life Steal effect to your weapon!");
                break;
            case "explode":
                lore.add(ChatColor.RED + "💥 Explosion Effect");
                player.sendMessage(ChatColor.GREEN + "Added Explosion effect to your weapon!");
                break;
            case "freeze":
                lore.add(ChatColor.AQUA + "🧊 Freeze Effect");
                player.sendMessage(ChatColor.GREEN + "Added Freeze effect to your weapon!");
                break;
            case "knockback":
                lore.add(ChatColor.WHITE + "💨 Mega Knockback");
                player.sendMessage(ChatColor.GREEN + "Added Mega Knockback to your weapon!");
                break;
            default:
                player.sendMessage(ChatColor.RED + "Unknown effect! Use /we for a list.");
                return true;
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }

    private boolean isWeapon(Material material) {
        return material.name().endsWith("_SWORD") || 
               material.name().endsWith("_AXE") || 
               material.name().endsWith("_PICKAXE") ||
               material.name().endsWith("_SHOVEL") ||
               material.name().endsWith("_HOE") ||
               material == Material.TRIDENT ||
               material == Material.MACE ||
               material == Material.BOW ||
               material == Material.CROSSBOW;
    }
}
