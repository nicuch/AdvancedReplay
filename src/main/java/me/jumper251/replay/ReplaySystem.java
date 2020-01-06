package me.jumper251.replay;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


import me.jumper251.replay.filesystem.ConfigManager;
import me.jumper251.replay.filesystem.saving.DatabaseReplaySaver;
import me.jumper251.replay.filesystem.saving.DefaultReplaySaver;
import me.jumper251.replay.filesystem.saving.ReplaySaver;
import me.jumper251.replay.replaysystem.Replay;
import me.jumper251.replay.utils.Metrics;
import me.jumper251.replay.utils.ReplayManager;
import me.jumper251.replay.utils.Updater;


public class ReplaySystem extends JavaPlugin {
    public static Updater updater;
    public static Metrics metrics;

    //the best way is not to use UTF-8 in Java classes
    //is not wrong, but is bad practice
    public final static String PREFIX = ChatColor.translateAlternateColorCodes('&', "&8[&3Replay&8] &r&7");


    @Override
    public void onDisable() {
        for (Replay replay : ReplayManager.activeReplays.values()) {
            if (replay.isRecording()) {
                replay.getRecorder().stop(ConfigManager.SAVE_STOP);
            }
        }
    }

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        this.getLogger().info("Loading Replay v" + getDescription().getVersion() + " by " + getDescription().getAuthors().get(0));
        ReplayManager.register();
        ConfigManager.loadConfigs();
        ReplaySaver.register(ConfigManager.USE_DATABASE ? new DatabaseReplaySaver() : new DefaultReplaySaver());
        updater = new Updater();
        metrics = new Metrics(this);
        this.getLogger().info("Finished (" + (System.currentTimeMillis() - start) + "ms)");
    }

    //A better instance!
    public static ReplaySystem getInstance() {
        return (ReplaySystem) Bukkit.getPluginManager().getPlugin("AdvancedReplay");
    }
}
