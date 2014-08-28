public class AgingProcessor extends ItemProcessor {
    public AgingProcessor(Item item) {
        super(item);
    }

    @Override
    protected void changeQuantity ()
    {
        if (isUnderMaxQuality())
        {
            if (isExpired())
            {
                item.setQuality(item.getQuality() + 2);
            }
            else
            {
                item.setQuality(item.getQuality() + 1);
            }

        }
    }
}
