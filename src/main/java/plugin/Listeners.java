package plugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {

        // Make sure to never run any other Bukkit functions in runTaskAsynchronously()
        // (for example accessing players'
        // inventories)
        // runTaskAsynchronously() is needed in this case since getTotalShards() calls a
        // database which can be
        // too slow to run on the main thread
        /*
         * Bukkit.getScheduler().runTaskAsynchronously(TemplateOG.getPlugin(), () -> {
         * 
         * final int totalShards; try {
         * 
         * totalShards =
         * TemplateOG.diamondBankAPI().getTotalShards(event.getPlayer().getUniqueId());
         * 
         * } catch (DiamondBankException.EconomyDisabledException e) {
         * 
         * UtilitiesOG.trueogMessage(event.getPlayer(),
         * "<red>The economy is disabled."); return;
         * 
         * } catch (DiamondBankException.TransactionsLockedException e) {
         * 
         * UtilitiesOG.trueogMessage(event.getPlayer(),
         * "<red>Transactions are currently locked for you."); return;
         * 
         * } catch (DiamondBankException.DatabaseException e) {
         * 
         * UtilitiesOG.trueogMessage(event.getPlayer(),
         * "<red>Something went wrong with the database."); return;
         * 
         * } // If you don't care about the specific exceptions you can also do the //
         * following: // try { // totalShards = //
         * TemplateOG.diamondBankAPI().getTotalShards(event.getPlayer().getUniqueId());
         * // } catch (DiamondBankException e) { //
         * UtilitiesOG.trueogMessage(event.getPlayer(), "<red>Something went wrong.");
         * // }
         * 
         * // Send a message to the player with their balance.
         * UtilitiesOG.trueogMessage(event.getPlayer(), "&BYour balance is: &e" +
         * totalShards + "&B Diamond Shards.");
         * UtilitiesOG.logToConsole("[Template-OG]", "The player: " + event.getPlayer()
         * + "'s <aqua>balance</aqua> is: " + totalShards + "&B Diamond Shards");
         * 
         * });
         */

        // save(TemplateOG.config(), Bukkit.getOfflinePlayer("TheMonsterEric"));

    }

    public static void save(FileConfiguration config, OfflinePlayer player) {

        config.set("players." + player.getUniqueId(), player);
        TemplateOG.getPlugin().saveConfig();

    }

}