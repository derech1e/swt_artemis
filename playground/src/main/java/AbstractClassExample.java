import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractClassExample<T> {
    public List<? extends Collection<String>> test = new ArrayList<>(); // Takes only objects that inherit from Collection<String>
    public abstract void method1();
    public void method2() { /* Body required */ }
}
