package net.petemc.apocalypsedrops.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.class_1299;
import net.minecraft.class_141;
import net.minecraft.class_1802;
import net.minecraft.class_219;
import net.minecraft.class_44;
import net.minecraft.class_55;
import net.minecraft.class_5662;
import net.minecraft.class_77;
import net.minecraft.class_55.class_56;
import net.petemc.apocalypsedrops.config.ApocalypseDropsConfig;

public class ApocalypseDropsLootTableModifiers {
   public static void modifyLootTables() {
      LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
         if (class_1299.field_6051.method_16351().isPresent() && class_1299.field_6051.method_16351().get() == key && ApocalypseDropsConfig.INSTANCE.enableZombieDrops || class_1299.field_6071.method_16351().isPresent() && class_1299.field_6071.method_16351().get() == key && ApocalypseDropsConfig.INSTANCE.enableHuskDrops) {
            class_56 poolBuilderNetherWart;
            if (ApocalypseDropsConfig.INSTANCE.enderPearlDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.enderPearlDropChance)).method_351(class_77.method_411(class_1802.field_8634)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.boneDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.boneDropChance)).method_351(class_77.method_411(class_1802.field_8606)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.gunpowderDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.gunpowderDropChance)).method_351(class_77.method_411(class_1802.field_8054)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.stringDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.stringDropChance)).method_351(class_77.method_411(class_1802.field_8276)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.spiderEyeDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.spiderEyeDropChance)).method_351(class_77.method_411(class_1802.field_8680)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.phantomMembraneDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.phantomMembraneDropChance)).method_351(class_77.method_411(class_1802.field_8614)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.potatoDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.potatoDropChance)).method_351(class_77.method_411(class_1802.field_8567)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.carrotDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.carrotDropChance)).method_351(class_77.method_411(class_1802.field_8179)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.blazeRodDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.blazeRodDropChance)).method_351(class_77.method_411(class_1802.field_8894)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.netherWartDropChance > 0.0D) {
               poolBuilderNetherWart = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float)ApocalypseDropsConfig.INSTANCE.netherWartDropChance)).method_351(class_77.method_411(class_1802.field_8790)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }

            if (ApocalypseDropsConfig.INSTANCE.ironIngotDropChance > 0.0D) {
               poolBuilderIronIngot = class_55.method_347().method_352(class_44.method_32448(1.0F)).method_356(class_219.method_932((float) ApocalypseDropsConfig.INSTANCE.ironIngotDropChance)).method_351(class_77.method_411(class_1802.field_8643)).apply(class_141.method_621(class_5662.method_32462(1.0F, 1.0F)).method_515());
               tableBuilder.method_336(poolBuilderNetherWart);
            }
         }

      });
   }
}
