import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    private List<Item> items = null;

    public GildedRose(ArrayList<Item> items) {
        this.items = items;
    }

	public static void main(String[] args) {
		
        System.out.println("OMGHAI!");


        ArrayList<Item> defaultItems= new ArrayList<Item>();
        defaultItems.add(new Item(DEXTERITY_VEST, 10, 20));
        defaultItems.add(new Item(AGED_BRIE, 2, 0));
        defaultItems.add(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
        defaultItems.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
        defaultItems.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, 15, 20));
        defaultItems.add(new Item(CONJURED_MANA_CAKE, 3, 6));
        GildedRose gildedRose = new GildedRose(defaultItems);

        gildedRose.updateQuality();
    }


	
    public void updateQuality()
    {
        for (Item item : items) {
            if (degradesNormally(item)) {
                if (hasValue(item) && notLegendary(item)) {
                    degrade(item);
                }
            } else {
                if (underMaxQuality(item)) {
                    increment(item);

                    if (isBackstagePass(item)) {
                        incrementBackstagePass(item);
                    }
                }
            }

            if (notLegendary(item)) {
                decrementSellIn(item);
            }

            if (expired(item)) {
                if (isBrie(item)) {
                    if (underMaxQuality(item)) {
                        increment(item);
                    }
                } else {
                    if (!isBackstagePass(item)) {
                        if (hasValue(item) && notLegendary(item)) {
                            degrade(item);
                        }
                    } else {
                        item.setQuality(0);
                    }
                }
            }
        }
    }

    private void increment(Item item) {
        item.setQuality(item.getQuality() + 1);
    }

    private boolean underMaxQuality(Item item) {
        return item.getQuality() < 50;
    }

    private boolean isBrie(Item item) {
        return AGED_BRIE.equals(item.getName());
    }

    private void incrementBackstagePass(Item item) {
        if (item.getSellIn() < 11) {
            if (underMaxQuality(item)) {
                increment(item);
            }
        }

        if (item.getSellIn() < 6) {
            if (underMaxQuality(item)) {
                increment(item);
            }
        }
    }

    private boolean isBackstagePass(Item item) {
        return BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT.equals(item.getName());
    }

    private boolean expired(Item item) {
        return item.getSellIn() < 0;
    }

    private void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void degrade(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private boolean hasValue(Item item) {
        return item.getQuality() > 0;
    }

    private boolean notLegendary(Item item) {
        return !SULFURAS_HAND_OF_RAGNAROS.equals(item.getName());
    }

    private boolean degradesNormally(Item item) {
        return (!AGED_BRIE.equals(item.getName())) && !isBackstagePass(item);
    }

}