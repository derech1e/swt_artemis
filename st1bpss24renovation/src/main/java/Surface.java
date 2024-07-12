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
        return 0;
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if(materials == null || selectedMaterial == null) throw new NullPointerException();
        return null;
    }
}