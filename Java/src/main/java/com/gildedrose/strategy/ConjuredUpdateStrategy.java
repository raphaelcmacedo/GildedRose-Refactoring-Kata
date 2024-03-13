package com.gildedrose.strategy;

import com.gildedrose.ImprovedItem;

public class ConjuredUpdateStrategy implements UpdateStrategy {

    @Override
    public void updateQuality(ImprovedItem item) {
        if (item.getSellIn() < 0) {
            item.setQuality(item.getQuality() - 4);
        }else{
            item.setQuality(item.getQuality() - 2);
        }
    }
}
