public class Factory {

    private ReceivingStock receivingStock;
    private Purchasing purchasing;

    public Factory(Purchasing purchasing, ReceivingStock receivingStock) {
        if (purchasing == null || receivingStock == null) throw new NullPointerException();
        this.purchasing = purchasing;
        this.receivingStock = receivingStock;
    }

    public Purchasing getPurchasing() {
        return this.purchasing;
    }

    public ReceivingStock getReceivingStock() {
        return this.receivingStock;
    }

    public static Part createPart(PartType partType, String id, String name) {
        if (partType == null || id == null || name == null) throw new NullPointerException();
        if (id.isEmpty() || name.isEmpty()) throw new IllegalArgumentException();

        switch (partType) {
            case RESOURCE:
                return new Resource(id, name);
            case COMPONENTS:
                return new Components(id, name);
            default:
                return new SingleComponent(id, name);
        }
    }
}