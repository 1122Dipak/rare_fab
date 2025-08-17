package com.example;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RareDropsTweaker {

    public static void init(List<DropRule> rules) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (DropRule rule : rules) {
                // Identifier now requires namespace + path
                Identifier entityId = Identifier.of(rule.entityId.contains(":") ? rule.entityId : "minecraft:" + rule.entityId);

                EntityType<?> entityType = Registries.ENTITY_TYPE.getOrEmpty(entityId).orElse(null);
                if (entityType == null) continue;

                Identifier expectedLootTable = EntityType.getLootTableId(entityType);

                if (!expectedLootTable.equals(id)) continue;

                Identifier itemId = Identifier.of(rule.itemId.contains(":") ? rule.itemId : "minecraft:" + rule.itemId);
                Item item = Registries.ITEM.getOrEmpty(itemId).orElse(null);
                if (item == null) continue;

                List<LootCondition> conditions = new ArrayList<>();
                // Updated API: use RandomChanceWithLootingLootCondition
                conditions.add(RandomChanceWithLootingLootCondition.builder(rule.baseChance)
                        .withLootingMultiplier(rule.lootingBonusPerLevel)
                        .build());

                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(item).conditionally(conditions.get(0)));

                tableBuilder.pool(pool);
            }
        });
    }

    public static class DropRule {
        public String entityId;
        public String itemId;
        public float baseChance;
        public float lootingBonusPerLevel;
    }
}
