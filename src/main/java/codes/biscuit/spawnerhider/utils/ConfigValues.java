package codes.biscuit.spawnerhider.utils;

import codes.biscuit.spawnerhider.SpawnerHider;

public class ConfigValues {

    private SpawnerHider main;

    public ConfigValues(SpawnerHider main) {
        this.main = main;
    }

    public boolean hideEntity() {
        return main.getConfig().getBoolean("hide-entity");
    }

    public boolean hideParticles() {
        return main.getConfig().getBoolean("hide-particles");
    }

    public String getNoPermissionMessage() {
        return Utils.color(main.getConfig().getString("messages.no-permission"));
    }
}
