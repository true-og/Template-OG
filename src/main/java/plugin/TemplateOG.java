package plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.miniplaceholders.api.Expansion;
import me.barny1094875.utilitiesog.UtilitiesOG;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.trueog.diamondbankog.DiamondBankOG;

public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;
    private static DiamondBankOG diamondBankPlugin = (DiamondBankOG) Bukkit.getPluginManager().getPlugin("DiamondBank-OG");
    private static UtilitiesOG utilitiesPlugin = (UtilitiesOG) Bukkit.getPluginManager().getPlugin("UtilitiesOG");
	public void onEnable() {

		plugin = this;

		getServer().getPluginManager().registerEvents(new Listeners(), this);
		registerExpansion();

	}

	public static DiamondBankOG getDiamondBankPlugin() {
		return diamondBankPlugin;
	}

	public static UtilitiesOG getUtilitiesPlugin() {
		return utilitiesPlugin;
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