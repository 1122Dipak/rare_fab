package com.example;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RareDropsTweaker {

    public static void init(List<DropRule> rules) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (DropRule rule : rules) {
                // Ensure proper Identifier
                Identifier entityId = Identifier.of(rule.entityId.contains(":") ? rule.entityId : "minecraft:" + rule.entityId);
                EntityType<?> entityType = Registries.ENTITY_TYPE.getOrEmpty(entityId).orElse(null);
                if (entityType == null) continue;

                // Get default loot table for entity
                Identifier expectedLootTable = EntityType.getLootTableId(entityType);
                if (!expectedLootTable.equals(id)) continue;

                Identifier itemId = Identifier.of(rule.itemId.contains(":") ? rule.itemId : "minecraft:" + rule.itemId);
                Item item = Registries.ITEM.getOrEmpty(itemId).orElse(null);
                if (item == null) continue;

                // Base chance condition
                List<LootCondition> conditions = new ArrayList<>();
                conditions.add(RandomChanceLootCondition.builder(rule.baseChance).build());

                // Build loot pool
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(item)
                                .conditionally(conditions.get(0))
                                .apply(LootingEnchantLootFunction.builder(rule.lootingBonusPerLevel)));

                tableBuilder.pool(pool);
            }
        });
    }

    // Simple class for JSON/config rules
    public static class DropRule {
        public String entityId;           // e.g., "zombie"
        public String itemId;             // e.g., "iron_ingot"
        public float baseChance;          // 0.0–1.0
        public float lootingBonusPerLevel; // Extra chance per Looting level
    }
}
