public class BackstagePassProcessor extends ItemProcessor {
    public BackstagePassProcessor(Item item) {
        super(item);
    }

    @Override
    protected void changeQuantity ()
    {
        if (isExpired())
        {
            item.setQuality(0);
        }
        else
        {
            if (isUnderMaxQuality()) {
                item.setQuality(item.getQuality() + 1);
            }
            if (item.getSellIn() < 6) {
                if (isUnderMaxQuality()) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
            if (item.getSellIn() < 11) {
                if (isUnderMaxQuality()) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
        }
    }

}
