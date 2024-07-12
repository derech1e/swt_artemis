import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StructuredObject extends RenovationObject {

    private List<RenovationObject> parts = new ArrayList<>();


    public StructuredObject() {

    }

    public void add(RenovationObject renovationObject) {
        if(renovationObject == null) throw new NullPointerException();
        parts.add(renovationObject);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if(materials == null) throw new NullPointerException();
        return null;
    }
}