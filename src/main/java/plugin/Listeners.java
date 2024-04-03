// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// Import libraries.
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.barny1094875.utilitiesog.UtilitiesOG;
import net.trueog.diamondbankog.DiamondBankOG;
import net.trueog.diamondbankog.PostgreSQL.BalanceType;

// Hook into Bukkit's Listener.
public class Listeners implements Listener {

	// Perform this plugin's action before other plugins actions.
	@EventHandler(priority = EventPriority.HIGHEST)
	// When a block is broken, do this...
	public void onBlockBreak(BlockBreakEvent event) {

		// Open a spectator GUI for the player who broke the block.
		// new SpectatorGui(TemplateOG.getPlugin(), event.getPlayer()).open(true);

		UUID playerUUID = event.getPlayer().getUniqueId();
		BalanceType balanceType = BalanceType.ALL;
		DiamondBankOG.getPlayerBalance(event.getPlayer().getUniqueId(), BalanceType.ALL);
		CompletableFuture<Double> balanceFuture = TemplateOG.getDiamondBankPlugin().getPlayerBalance(playerUUID, balanceType);

		balanceFuture.thenAccept(balance -> {
			Utils.templateOGPlaceholderMessage(event.getPlayer(), "Your current bank balance is: " + balance);
		}).exceptionally(throwable -> {
			// Handle any errors that might occur while fetching the balance
			TemplateOG.getPlugin().getLogger().info("Error getting player balance: " + throwable.getMessage());
			return null;
		});

	}

}