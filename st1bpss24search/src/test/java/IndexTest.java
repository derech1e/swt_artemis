import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class IndexTest {

    private Index i;
    private Resource res;
    private Resource res2;
    private String[] wishes;

    @BeforeEach
    public void setUp(){
        i = new Index();
        PlainTextCollector coll = new PlainTextCollector();
        res = new Resource("name", "path", new ResourceType("desc", coll));
        res2 = new Resource("name", "path", new ResourceType("desc", coll));
        wishes = "are exam good hope in luck prepared this We well wish you".split(" ");
    }

    @Test
    public void testAddNullArgument() {
        try {
            i.add(null);
            Assertions.fail("Index.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testGetResourcesNullArgument() {
        try {
            i.getResources(null);
            Assertions.fail("Index.getResources() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAdd() {
        Field f = null;
        try {
            f = i.getClass().getDeclaredField("index");
        } catch (NoSuchFieldException e) {
            Assertions.fail("The class Index should contain a Map with the name \"index\"");
        }
        f.setAccessible(true);

        Map<String, List<Resource>> indexMap = null;

        try {
            indexMap = castToMap(f.get(i));
        } catch(IllegalAccessException e) {
            Assertions.fail("The class Index should contain a Map with the name \"index\"");
        } catch(ClassCastException e) {
            Assertions.fail("The attribute \"index\" in the class Index should be of type Map<String, List<Resource>>");
        }

        i.add(res);
        Assertions.assertTrue(indexMap.size() == wishes.length, "Index.add() should add each String exactly once!");
        for(List<Resource> resourceList : indexMap.values()) {
            Assertions.assertTrue(resourceList.equals(Arrays.asList(res)),
                    "Index.add() should pair every String with a List of all resources which contain the String!");
        }

        i.add(res2);
        Assertions.assertTrue(indexMap.size() == wishes.length, "Index.add() should add each String exactly once!");
        for(List<Resource> resourceList : indexMap.values()) {
            Assertions.assertTrue(resourceList.equals(Arrays.asList(res, res2)),
                    "Index.add() should pair every String with a List of all resources which contain the String!");
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, List<Resource>> castToMap(Object o) {
        return (Map<String, List<Resource>>) o;
    }

    @Test
    public void testGet() {
        i.add(res);
        i.add(res2);

        for(String word : wishes) {
            Assertions.assertTrue(i.getResources(word).equals(Arrays.asList(res, res2)),
                    "Index.get() should return a List of all Resources that contain the argument!");
        }

        Assertions.assertTrue(i.getResources("not a Keyword").equals(Collections.emptyList()), "Index.get() should return an empty List if the argument is in no Resource!");
    }
}
