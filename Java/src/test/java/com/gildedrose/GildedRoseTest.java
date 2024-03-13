package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItemDegrades() {
        Item[] items = new Item[] { new Item("foo", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = items[0];
        assertEquals("foo", item.name);
        assertEquals(1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void passedItemDegradesTwiceAsFast() {
        Item[] items = new Item[] { new Item("Passed", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item[] items = new Item[] { new Item("foo", 1, 0), new Item("boo", 1, -1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(0, items[1].quality);
    }

    @Test
    void agedBrieIncreasesQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = items[0];
        assertEquals(1, item.sellIn);
        assertEquals(3, item.quality);
    }

    @Test
    void qualityNoMoreThan50() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = items[0];
        assertEquals(1, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void sulfurasCannotBeSold() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = items[0];
        assertEquals(10, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void backstagePassesIncreasesQuality() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(3, items[0].quality);
        assertEquals(4, items[1].quality);

    }

    @Test
    void conjuredItemDegradesTwiceAsFast() {
        Item[] items = new Item[] {
            new Item("Conjured", 10, 10),
            new Item("Conjured", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(8, items[0].quality);
        assertEquals(-1, items[1].sellIn);
        assertEquals(6, items[1].quality);
    }

}
