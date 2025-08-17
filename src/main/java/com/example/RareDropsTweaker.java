package com.dipak.raredrops;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RareDropsTweaker implements ModInitializer {
    public static final String MOD_ID = "rare_drops_tweaker";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Config CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = Config.load();

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) return; // don't touch datapack tables

            // Loop rules and match their entity's loot table id
            for (DropRule rule : CONFIG.rules) {
                EntityType<?> entityType = Registries.ENTITY_TYPE.getOrEmpty(new Identifier(rule.entityId)).orElse(null);
                if (entityType == null) continue;
                Identifier expectedLootTable = entityType.getLootTableId();
                if (!expectedLootTable.equals(key)) continue;

                Item item = Registries.ITEM.getOrEmpty(new Identifier(rule.itemId)).orElse(null);
                if (item == null) continue;

                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(rule.rolls))
                        .with(ItemEntry.builder(item));

                // Conditions
                List<LootCondition> conditions = new ArrayList<>();
                if (rule.onlyWhenKilledByPlayer) conditions.add(KilledByPlayerLootCondition.builder().build());

                if (rule.lootingBonusPerLevel > 0f) {
                    conditions.add(RandomChanceWithEnchantedBonusLootCondition.builder(rule.baseChance, rule.lootingBonusPerLevel).build());
                } else {
                    conditions.add(RandomChanceLootCondition.builder(rule.baseChance).build());
                }

                for (LootCondition cond : conditions) pool.conditionally(cond);

                tableBuilder.pool(pool);
            }
        });
    }

    // ---------------- CONFIG ----------------
    public static class Config {
        public List<DropRule> rules = new ArrayList<>();

        public static Config load() {
            Path cfgPath = getConfigPath();
            try {
                if (Files.notExists(cfgPath)) {
                    Config def = defaults();
                    Files.createDirectories(cfgPath.getParent());
                    try (Writer w = Files.newBufferedWriter(cfgPath)) { GSON.toJson(def, w); }
                    return def;
                }
                try (Reader r = Files.newBufferedReader(cfgPath)) {
                    Type t = new TypeToken<Config>(){}.getType();
                    Config c = GSON.fromJson(r, t);
                    if (c == null) return defaults();
                    return c;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return defaults();
            }
        }

        private static Config defaults() {
            Config c = new Config();
            // Zombies: vanilla rare drops but configurable
            c.rules.add(new DropRule("minecraft:zombie", id(Items.IRON_INGOT), 0.025f, 0.01f, 1, true));
            c.rules.add(new DropRule("minecraft:zombie", id(Items.CARROT),      0.025f, 0.01f, 1, true));
            c.rules.add(new DropRule("minecraft:zombie", id(Items.POTATO),      0.025f, 0.01f, 1, true));
            // Drowned: copper ingot
            c.rules.add(new DropRule("minecraft:drowned", id(Items.COPPER_INGOT), 0.05f, 0.02f, 1, true));
            // Wither skeleton: skull (default vanilla ~2.5%)
            c.rules.add(new DropRule("minecraft:wither_skeleton", id(Items.WITHER_SKELETON_SKULL), 0.025f, 0.01f, 1, true));
            return c;
        }

        private static String id(Item item) {
            return Objects.requireNonNull(Registries.ITEM.getId(item)).toString();
        }
    }

    public static class DropRule {
        /** e.g. "minecraft:zombie" */
        public String entityId;
        /** e.g. "minecraft:iron_ingot" */
        public String itemId;
        /** Base chance 0.0–1.0 */
        public float baseChance;
        /** Added chance per Looting level (set to 0 to ignore looting) */
        public float lootingBonusPerLevel;
        /** Number of rolls for this entry (usually 1) */
        public int rolls;
        /** Require player kill (true matches vanilla behavior for rares) */
        public boolean onlyWhenKilledByPlayer;

        public DropRule() {}

        public DropRule(String entityId, String itemId, float baseChance, float lootingBonusPerLevel, int rolls, boolean onlyWhenKilledByPlayer) {
            this.entityId = entityId;
            this.itemId = itemId;
            this.baseChance = baseChance;
            this.lootingBonusPerLevel = lootingBonusPerLevel;
            this.rolls = rolls;
            this.onlyWhenKilledByPlayer = onlyWhenKilledByPlayer;
        }
    }

    private static Path getConfigPath() {
        String gameDir = System.getProperty("fabric.gameDirectory");
        Path base = (gameDir != null) ? Path.of(gameDir) : Path.of(".");
        return base.resolve("config").resolve("rare_drops_tweaker.json");
    }
}