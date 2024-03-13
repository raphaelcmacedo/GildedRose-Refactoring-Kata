package com.gildedrose;

import com.gildedrose.strategy.UpdateStrategyContext;

import java.util.Arrays;
import java.util.stream.Stream;

class GildedRose {

    private static final String[] LEGENDARY_ITEMS = {"Sulfuras, Hand of Ragnaros"};

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean canBeSold(ImprovedItem item){
        boolean legendary = Arrays.stream(LEGENDARY_ITEMS)
            .anyMatch(name -> name.equals(item.getName()));

        return !legendary;//Easy to add more rules in the future if necessary
    }

    public void updateQuality() {
        Stream<Item> itemStream = Arrays.stream(items);
        itemStream
            .map(ImprovedItem::new)
            .filter(this::canBeSold)
            .forEach(item -> {
                item.updateSellIn();
                UpdateStrategyContext.updateQuality(item);
            });


        //Keeping the old cold commented as reference
        //Normally I would remove it
        /*for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }*/
    }
}
