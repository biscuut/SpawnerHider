package codes.biscuit.spawnerhider.listeners;

import codes.biscuit.spawnerhider.SpawnerHider;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import org.bukkit.plugin.Plugin;

public class PacketListener extends PacketAdapter {

    private SpawnerHider main;

    public PacketListener(Plugin plugin, PacketType... types) {
        super(plugin, types);
        this.main = (SpawnerHider)plugin;
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        PacketContainer packet = e.getPacket();
        if (packet.getIntegers().read(0) == 1) { // If the action is setting the data of a mob spawner
            NbtCompound nbt = (NbtCompound)packet.getNbtModifier().read(0);
            if (main.getConfigValues().hideEntity()) {
                nbt.put("EntityId", "null"); // You can't just cancel the packet- the client will "assume" what was spawned. Alternatively, null renders an empty spawner on the client.
            }
            if (main.getConfigValues().hideParticles()) {
                nbt.put("RequiredPlayerRange", (short) 0); // This will trick the client into thinking you are out of range and will not show the flame particles.
            }
        }
    }
}
