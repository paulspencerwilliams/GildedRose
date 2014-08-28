public class ItemProcessor {
    protected Item item;

    public ItemProcessor(Item item) {
        this.item = item;
    }

    void process() {
        changeQuantity();
        reduceSellIn();
    }

    protected void changeQuantity() {
        if (isExpired())
        {
            if (item.getQuality() > 0)
            {
                item.setQuality(item.getQuality() - 2);
            }
        }
        else
        {
            if (item.getQuality() > 0)
            {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }

    protected void reduceSellIn() {
        item.setSellIn(item.getSellIn() -1);
    }

    protected boolean isUnderMaxQuality()
    {
        return item.getQuality() < 50;
    }

    protected boolean isExpired() {
        return item.getSellIn() <= 0;
    }
}
