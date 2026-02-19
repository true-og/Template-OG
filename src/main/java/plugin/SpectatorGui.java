// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

// Import libraries.
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.trueog.gxui.GUIBase;
import net.trueog.gxui.GUIButton;
import net.trueog.gxui.GUIItem;

public class SpectatorGui extends GUIBase {

    private final Player assignedPlayer;

    public SpectatorGui(JavaPlugin plugin, Player player) {

        super(plugin, player, "&3&lTest GUI", 9, true);

        assignedPlayer = player;

    }

    @Override
    public void setupItems() {

        int curr = 0;
        GUIItem item = null;
        final List<String> name = new ArrayList<>(18);

        // Add a new element to the list for each player.
        Bukkit.getOnlinePlayers().forEach((Player p) -> name.add("&eTeleport to: &d&l" + p.getName()));

        for (Player p : Bukkit.getOnlinePlayers()) {

            item = new GUIItem(Material.PLAYER_HEAD, 1, name.get(curr), p.getName());
            final GUIButton guibutton = new GUIButton() {

                @Override
                public boolean leftClick() {

                    assignedPlayer.closeInventory();
                    assignedPlayer.teleport(p);

                    return true;

                }

                @Override
                public boolean leftClickShift() {

                    return false;

                }

                @Override
                public boolean rightClick() {

                    return false;

                }

                @Override
                public boolean rightClickShift() {

                    return false;

                }

            };

            item.setButton(guibutton);

            addItem(curr, item);
            curr = nextCurr(curr);

        }

    }

    private int nextCurr(int curr) {

        if (curr == 3) {

            return 6;

        } else {

            return (curr += 1);

        }

    }

}