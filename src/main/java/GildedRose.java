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
            getItemProcessor(item).process();
        }
    }

    private ItemProcessor getItemProcessor(Item item) {
        if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)) {
            return new BackstagePassProcessor(item);
        } else if (item.getName().equals(AGED_BRIE)) {
            return new AgingProcessor(item);
        } else if (item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            return new LegendaryItemProcessor(item);
        } else if (item.getName().equals(CONJURED_MANA_CAKE)) {
            return new ConjuredItemProcessor(item);
        } else {
            return new ItemProcessor(item);
        }
    }
}