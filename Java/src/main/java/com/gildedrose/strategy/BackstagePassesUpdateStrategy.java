package com.gildedrose.strategy;

import com.gildedrose.ImprovedItem;

public class BackstagePassesUpdateStrategy implements UpdateStrategy {

    @Override
    public void updateQuality(ImprovedItem item) {
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        }else{
            int factor = 1;
            if (item.getSellIn() <= 5) {
                factor = 3;
            }else if (item.getSellIn() <= 10) {
                factor = 2;
            }
            item.setQuality(item.getQuality() + factor);
        }
    }
}
