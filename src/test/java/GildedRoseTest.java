import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;


public class GildedRoseTest {

    @Test
    public void qualityReducesByOne ()
    {
        assertThat(updateQuality("anItem", 2, 1).getQuality(), equalTo(0));
    }

    @Test
    public void sellInIsDecrementedByOne ()
    {
        assertThat(updateQuality("anItem", 2, 1).getSellIn(), equalTo(1));
    }

    @Test
    public void qualityReducesByTwoOnceExpired ()
    {
        assertThat(updateQuality("anItem", 0, 4).getQuality(), equalTo(2));
    }

    @Test
    public void brieIncreasesInQualityBy1BeforeItExpires ()
    {
        assertThat(updateQuality("Aged Brie", 5, 5).getQuality(), equalTo(6));
    }

    @Test
    public void brieIncreasesInQualityBy2AfterItExpires ()
    {
        assertThat(updateQuality("Aged Brie", 0, 5).getQuality(), equalTo(7));
    }

    @Test
    public void qualityIsLimitedTo50 ()
    {
        assertThat(updateQuality("Aged Brie", 0, 50).getQuality(), equalTo(50));
    }

    @Test
    public void SulfurasDontReduce()
    {
        assertThat(updateQuality("Sulfuras, Hand of Ragnaros", 0, 5).getQuality(), equalTo(5));
    }

    @Test
    public void SulfurasDontHaveSellIn()
    {
        assertThat(updateQuality("Sulfuras, Hand of Ragnaros", 5, 5).getQuality(), equalTo(5));
    }

    @Test
    public void BackstagePassesIncreaseBy1Until10DaysToGo ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 12, 5).getQuality(), equalTo(6));
    }

    @Test
    public void BackstagePassesIncreaseBy2Until10DaysToGo ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 10, 5).getQuality(), equalTo(7));
    }

    @Test
    public void BackstagePassesIncreaseBy3Until5DaysToGo ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 5, 5).getQuality(), equalTo(8));
    }

    @Test
    public void BackstagePassesIncreaseUntilSellIn ()
    {
        assertThat(updateQuality("Backstage passes to a TAFKAL80ETC concert", 0, 5).getQuality(), equalTo(0));
    }

    @Test
    public void conjuredItemsDegradeTwiceAsFastAsNormalItemsBeforeExpiry ()
    {
        assertThat(updateQuality("Conjured Mana Cake", 2, 2).getQuality(), equalTo(0));
    }

    @Test
    public void conjuredItemsDegradeTwiceAsFastAsNormalItemsAfterExpiry ()
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
