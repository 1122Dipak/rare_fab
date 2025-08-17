package net.petemc.apocalypsedrops.util;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_437;
import net.petemc.apocalypsedrops.config.ApocalypseDropsConfig;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return (parent) -> {
         return (class_437)AutoConfig.getConfigScreen(ApocalypseDropsConfig.class, parent).get();
      };
   }
}
