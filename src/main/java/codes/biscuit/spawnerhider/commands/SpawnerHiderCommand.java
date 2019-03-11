package codes.biscuit.spawnerhider.commands;

import codes.biscuit.spawnerhider.SpawnerHider;
import codes.biscuit.spawnerhider.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class SpawnerHiderCommand implements TabExecutor {

    private SpawnerHider main;

    public SpawnerHiderCommand(SpawnerHider main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("reload");
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("spawnerhider.reload") || sender.hasPermission("spawnerhider.admin")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    main.reloadConfig();
                    sender.sendMessage(Utils.color("&aSuccessfully reloaded the config. Most values have been instantly updated."));
                } else {
                    sender.sendMessage(Utils.color("&cInvalid argument!"));
                }
            } else {
                sender.sendMessage(Utils.color("&7&m--------------&7[&4&l SpawnerHider &7]&7&m--------------"));
                sender.sendMessage(Utils.color("&4● /sh reload &7- Reload the config"));
                sender.sendMessage(Utils.color("&7&ov" + main.getDescription().getVersion() + " by Biscut"));
                sender.sendMessage(Utils.color("&7&m-------------------------------------------"));
            }
        } else {
            sender.sendMessage(main.getConfigValues().getNoPermissionMessage());
        }
        return false;
    }
}
