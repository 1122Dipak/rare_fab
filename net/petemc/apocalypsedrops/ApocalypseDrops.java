package net.petemc.apocalypsedrops;

import net.fabricmc.api.ModInitializer;
import net.petemc.apocalypsedrops.config.ApocalypseDropsConfig;
import net.petemc.apocalypsedrops.util.ApocalypseDropsLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApocalypseDrops implements ModInitializer {
   public static final String MOD_ID = "apocalypsedrops";
   public static final Logger LOGGER = LoggerFactory.getLogger("apocalypsedrops");

   public void onInitialize() {
      LOGGER.info("Initializing Apocalypse Drops Mod");
      ApocalypseDropsConfig.init();
      ApocalypseDropsLootTableModifiers.modifyLootTables();
   }
}
