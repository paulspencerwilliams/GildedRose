public class ConjuredItemProcessor extends ItemProcessor {
    public ConjuredItemProcessor(Item item) {
        super(item);
    }

    @Override
    public void changeQuantity () {
        if (isExpired())
        {
            if (item.getQuality() > 0)
            {
                item.setQuality(item.getQuality() - 4);
            }
        }
        else
        {
            if (item.getQuality() > 0)
            {
                item.setQuality(item.getQuality() - 2);
            }
        }
    }
}
