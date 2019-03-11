package codes.biscuit.spawnerhider;

import codes.biscuit.spawnerhider.commands.SpawnerHiderCommand;
import codes.biscuit.spawnerhider.listeners.PacketListener;
import codes.biscuit.spawnerhider.utils.ConfigValues;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerHider extends JavaPlugin {

    private ConfigValues configValues = new ConfigValues(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketListener(this, PacketType.Play.Server.TILE_ENTITY_DATA));
        SpawnerHiderCommand command = new SpawnerHiderCommand(this);
        getCommand("spawnerhider").setExecutor(command);
        getCommand("spawnerhider").setTabCompleter(command);
    }

    public ConfigValues getConfigValues() {
        return configValues;
    }
}
