import java.util.*;

public class StructuredObject extends RenovationObject {

    private Set<RenovationObject> parts;


    public StructuredObject() {
        parts = new HashSet<>();
    }

    public void add(RenovationObject renovationObject) {
        if(renovationObject == null) throw new NullPointerException();
        parts.add(renovationObject);
    }

    @Override
    public double getPrice() {
        double price = 0D;
        for (RenovationObject renovationObject : parts) {
            price += renovationObject.getPrice();
        }
        return price;
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if(materials == null) throw new NullPointerException();
        Map<String, Integer> updatedMaterials = new HashMap<>(materials);
        if(updatedMaterials.containsKey(null) || updatedMaterials.containsValue(null)) throw new NullPointerException();
        for (RenovationObject renovationObject : parts) {
            updatedMaterials = renovationObject.addMaterialRequirements(updatedMaterials);
        }
        return updatedMaterials;
    }
}