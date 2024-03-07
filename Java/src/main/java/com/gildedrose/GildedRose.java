package com.gildedrose;

import java.util.Arrays;
import java.util.stream.Stream;

class GildedRose {

    private static final String AGED_BRIE =  "Aged Brie";
    private static final String SULFURAS =  "Sulfuras";
    private static final String BACKSTAGE_PASSES = "Backstage passes";
    private static final String CONJURED = "Conjured";
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean canBeSold(Item item){
        boolean legendary = (item.name != null && item.name.contains(SULFURAS));

        return !legendary;//Easy to add more rules in the future if necessary
    }
    private void denoteSellIn(Item item){
        item.sellIn--;
    }

    private void denoteQuality(Item item){
        int factor = this.getQualityFactor(item);
        boolean decreasing = factor < 0;

        item.quality += factor;

        if(decreasing && item.quality < MIN_QUALITY){//The Quality of an item is never negative
            item.quality = MIN_QUALITY;
        }

        if(!decreasing && item.quality > MAX_QUALITY){//The Quality of an item is never more than 50
            item.quality = MAX_QUALITY;
        }

    }

    private int getQualityFactor(Item item){
        int factor = -1;

        if(item.name != null && item.name.contains(AGED_BRIE)){//"Aged Brie" actually increases in Quality the older it gets
            return 1;
        }

        if(item.name != null && item.name.contains(BACKSTAGE_PASSES)){//"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
            if(item.sellIn < 0){//Quality drops to 0 after the concert
                return -item.quality;
            }

            //Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
            if (item.sellIn <= 5) {
                return 3;
            }
            if (item.sellIn <= 10) {
                return 2;
            }

            return 1;//Default value for more than 10 days. Not specified clearly in the task (assuming)
        }

        if(item.name != null && item.name.contains(CONJURED)){//"Conjured" items degrade in Quality twice as fast as normal items
            return -2;
        }

        if(item.sellIn <= 0){//Once the sell by date has passed, Quality degrades twice as fast
            return -2;
        }


        return factor;
    }


    public void updateQuality() {

        Stream<Item> itemStream = Arrays.stream(items);
        itemStream
            .filter(this::canBeSold)
            .forEach(item -> {
                this.denoteSellIn(item);
                this.denoteQuality(item);
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
