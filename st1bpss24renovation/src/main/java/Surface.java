import java.util.HashMap;
import java.util.Map;

public class Surface extends RenovationObject {

    private double length;
    private double width;
    private Material selectedMaterial;

    public Surface(double length, double width) {
        if(length <= 0 || width <= 0) throw new IllegalArgumentException();
        this.length = length;
        this.width = width;
    }

    public void setMaterial(Material material) {
        if(material == null) throw new NullPointerException();
        this.selectedMaterial = material;
    }

    public double getArea() {
        return length * width;
    }

    public double getLength() {
        return this.length;
    }

    public double getWidth() {
        return this.width;
    }

    @Override
    public double getPrice() {
        return selectedMaterial.getPriceOfASurface(this);
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if(materials == null || selectedMaterial == null) throw new NullPointerException();

        Map<String, Integer> updatedMaterials = new HashMap<>(materials);
        if(updatedMaterials.containsKey(null) || updatedMaterials.containsValue(null)) throw new NullPointerException();

        int newMaterialCount = materials.getOrDefault(selectedMaterial.getName(), 0) + selectedMaterial.getMaterialRequirements(this);
        updatedMaterials.put(selectedMaterial.getName(), newMaterialCount);

        return updatedMaterials;

    }
}