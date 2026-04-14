package com.weaponenchanter;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;

public class EffectListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof LivingEntity)) return;

        Player player = (Player) event.getDamager();
        LivingEntity target = (LivingEntity) event.getEntity();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) return;

        List<String> lore = item.getItemMeta().getLore();
        String lorePlain = ChatColor.stripColor(String.join(" ", lore));

        if (lorePlain.contains("Fire Effect")) {
            target.setFireTicks(100);
            target.getWorld().spawnParticle(Particle.FLAME, target.getLocation(), 30);
            target.getWorld().playSound(target.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 1.0f);
        }

        if (lorePlain.contains("Poison Effect")) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2));
            target.getWorld().spawnParticle(Particle.EFFECT, target.getLocation(), 30);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_SPIDER_HURT, 1.0f, 1.5f);
        }

        if (lorePlain.contains("Lightning Effect")) {
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.damage(4.0);
            target.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation(), 40);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5f, 1.5f);
        }

        if (lorePlain.contains("Wither Effect")) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 2));
            target.getWorld().spawnParticle(Particle.SOUL, target.getLocation(), 25);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_WITHER_HURT, 0.8f, 1.5f);
        }

        if (lorePlain.contains("Blindness Effect")) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
            target.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 0));
            target.getWorld().spawnParticle(Particle.SMOKE, target.getLocation(), 40);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5f, 2.0f);
        }

        if (lorePlain.contains("Slowness Effect")) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 120, 4));
            target.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 120, 2));
            target.getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation(), 35);
            target.getWorld().playSound(target.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0.5f);
        }

        if (lorePlain.contains("Life Steal Effect")) {
            double healAmount = event.getDamage() * 0.4;
            if (player.getHealth() + healAmount > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            } else {
                player.setHealth(player.getHealth() + healAmount);
            }
            player.getWorld().spawnParticle(Particle.HEART, target.getLocation(), 8);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0f, 1.2f);
        }

        if (lorePlain.contains("Explosion Effect")) {
            target.getWorld().createExplosion(target.getLocation(), 2.5f, false, false);
            target.getWorld().spawnParticle(Particle.EXPLOSION, target.getLocation(), 15);
        }

        if (lorePlain.contains("Freeze Effect")) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 140, 10));
            target.setFreezeTicks(200);
            target.getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation(), 50);
            target.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, target.getLocation(), 30);
            target.getWorld().playSound(target.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0.3f);
        }

        if (lorePlain.contains("Mega Knockback")) {
            Vector direction = target.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
            direction.multiply(3.0).setY(1.5);
            target.setVelocity(direction);
            target.getWorld().spawnParticle(Particle.CLOUD, target.getLocation(), 30);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 0.8f);
        }
    }
}
