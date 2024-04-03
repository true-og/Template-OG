package plugin;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.miniplaceholders.api.Expansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;

public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;

	public void onEnable() {

		plugin = this;

		getServer().getPluginManager().registerEvents(new Listeners(), this);
		registerExpansion();

	}

	public static TemplateOG getPlugin() {

		return plugin;

	}

	public void registerExpansion() {

		// Create <diamondbankog_balance> placeholder.
		Expansion expansion = Expansion.builder("diamondbankog").globalPlaceholder("balance", (queue, ctx) -> {
			int value = 500;
			return Tag.selfClosingInserting(Component.text(value));
		}).build();

		expansion.register();

	}

}