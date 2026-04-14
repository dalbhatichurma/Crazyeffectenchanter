package com.weaponenchanter;

import org.bukkit.plugin.java.JavaPlugin;

public class WeaponEnchanter extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("weaponeffect").setExecutor(new EffectCommand());
        getServer().getPluginManager().registerEvents(new EffectListener(), this);
        getLogger().info("WeaponEnchanter has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WeaponEnchanter has been disabled!");
    }
}
