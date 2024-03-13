package com.gildedrose.strategy;

import com.gildedrose.ImprovedItem;

import java.util.HashMap;
import java.util.Map;

public class UpdateStrategyContext {

    private final static UpdateStrategy  DEFAULT_STRATEGY = new DefaultUpdateStrategy();
    private final static Map<String, UpdateStrategy> SPECIAL_STRATEGIES = new HashMap<>();
    private static final String AGED_BRIE =  "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    static {
        SPECIAL_STRATEGIES.put(AGED_BRIE, new AgedBrieUpdateStrategy());
        SPECIAL_STRATEGIES.put(BACKSTAGE_PASSES, new BackstagePassesUpdateStrategy());
        SPECIAL_STRATEGIES.put(CONJURED, new ConjuredUpdateStrategy());
    }

    public static void updateQuality(ImprovedItem item){
        UpdateStrategy selectedStrategy = SPECIAL_STRATEGIES.getOrDefault(item.getName(), DEFAULT_STRATEGY);
        selectedStrategy.updateQuality(item);
    }
}
