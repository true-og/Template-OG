// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.

package plugin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.miniplaceholders.api.Expansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.trueog.diamondbankog.DiamondBankOG;
import net.trueog.diamondbankog.PostgreSQL.BalanceType;

//Extending this class is standard bukkit boilerplate for any plugin, or else the server software won't load the classes.
public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;

	// What to do when the plugin is run by the server.
	public void onEnable() {

		// Register the plugin instance.
		plugin = this;

		// Register the event.
		getServer().getPluginManager().registerEvents(new Listeners(), this);

		// Register the placeholder.
		registerExpansion();

	}

	public static TemplateOG getPlugin() {

		// Pass instance of main to other classes.
		return plugin;

	}

	public static void registerExpansion() {
		Expansion.Builder builder = Expansion.builder("diamondbank_og_balance");
		builder.filter(Player.class);

		builder.audiencePlaceholder("name", (audience, ctx, queue) -> {
			final Player player = (Player) audience;
			Utils.templateOGPlaceholderMessage(player, "&BYour balance is: " + checkPlayerBalance(player));
			TextComponent nameHandler = LegacyComponentSerializer.legacyAmpersand().deserialize(player.getName());
			return Tag.selfClosingInserting(nameHandler);
		}).globalPlaceholder("tps", (ctx, queue) -> Tag.selfClosingInserting(Component.text(Bukkit.getTPS()[0]))).build();

		Expansion expansion = builder.build();
		expansion.register();

	}

	public static long checkPlayerBalance(Player player) {
		DiamondBankOG diamondBankPlugin = new DiamondBankOG();

		// Using a CompletableFuture to bridge the asynchronous result with a long return
		CompletableFuture<Long> balanceFuture = new CompletableFuture<>();

		diamondBankPlugin.getPlayerBalance(player.getUniqueId(), BalanceType.ALL)
		.thenAccept(balance -> {
			balanceFuture.complete(balance.getBankBalance().longValue());
     }).exceptionally(error -> {
         // Return null if failed.
         return null;
     });

		try {
			// May block until the balance is available
			return balanceFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

}
