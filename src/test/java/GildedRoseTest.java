import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;


public class GildedRoseTest {

    @Test
    public void degradesByOne ()
    {
        assertThat(updateQuality("anItem", 2, 1).getQuality(), equalTo(0));
    }

    @Test
    public void degradesByTwoAfterExpiry ()
    {
        assertThat(updateQuality("anItem", 0, 4).getQuality(), equalTo(2));
    }

    @Test
    public void decrementsSellInDateByOne ()
    {
        assertThat(updateQuality("anItem", 2, 1).getSellIn(), equalTo(1));
    }

    @Test
    public void upgradesByOneForAgingItemsBeforeExpiry()
    {
        assertThat(updateQuality("Aged Brie", 5, 5).getQuality(), equalTo(6));
    }

    @Test
    public void upgradesByTwoForAgingItemsBeforeExpiry()
    {
        assertThat(updateQuality("Aged Brie", 0, 5).getQuality(), equalTo(7));
    }

    @Test
    public void qualityIsLimitedTo50 ()
    {
        assertThat(updateQuality("Aged Brie", 0, 50).getQuality(), equalTo(50));
    }

    @Test
    public void degradesByZeroForLegendaryItems()
    {
        assertThat(updateQuality("Sulfuras, Hand of Ragnaros", 0, 5).getQuality(), equalTo(5));
    }

    @Test
    public void decrementsSellInDateByZeroForLegendaryItems()
    {
        assertThat(updateQuality("Sulfuras, Hand of Ragnaros", 5, 5).getQuality(), equalTo(5));
    }

    @Test
    public void upgradesBy1ForBackstagePassesWithMoreThan10DaysBeforeExpiry ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 12, 5).getQuality(), equalTo(6));
    }

    @Test
    public void upgradesBy2ForBackstagePassesWithBetween10And5DaysBeforeExpiry()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 10, 5).getQuality(), equalTo(7));
    }

    @Test
    public void upgradesBy3ForBackstagePassesWithLessThan5DaysBeforeExpiry ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 5, 5).getQuality(), equalTo(8));
    }

    @Test
    public void losesAllQualityForBackstagePassesAfterExpiry ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 0, 5).getQuality(), equalTo(0));
    }

    @Test
    public void degradesByTwoForConjuredItemsBeforeExpiry()
    {
        assertThat(updateQuality("Conjured Mana Cake", 2, 2).getQuality(), equalTo(0));
    }

    @Test
    public void degradesBy4ForConjuredItemsBeforeExpiry ()
    {
        assertThat(updateQuality("Conjured Mana Cake", 0, 4).getQuality(), equalTo(0));
    }

    private Item updateQuality(String name, int sellIn, int quality) {
        ArrayList<Item> items = new ArrayList<Item>();
        Item simpleItem = new Item(name, sellIn, quality);
        items.add(simpleItem);
        new GildedRose(items).updateQuality();
        return simpleItem;
    }
}
