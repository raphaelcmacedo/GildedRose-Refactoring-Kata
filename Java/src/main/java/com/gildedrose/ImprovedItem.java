package com.gildedrose;


public class ImprovedItem {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    private final Item item;

    public ImprovedItem(Item item) {
        this.item = item;
    }
    public String getName() {
        return item.name;
    }
    public int getSellIn() {
        return item.sellIn;
    }
    public int getQuality() {
        return item.quality;
    }
    public void setName(String name) {
        this.item.name = name;
    }
    public void setSellIn(int sellIn) {
        this.item.sellIn = sellIn;
    }

    public void setQuality(int quality) {
        if(quality < MIN_QUALITY){
            quality = MIN_QUALITY;
        }
        if(quality > MAX_QUALITY){
            quality = MAX_QUALITY;
        }
        this.item.quality = quality;
    }

    public void updateSellIn(){
        this.setSellIn(this.getSellIn() - 1);
    }

}
