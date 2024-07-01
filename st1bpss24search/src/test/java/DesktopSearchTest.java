import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

 
public class DesktopSearchTest {
    private DesktopSearch deskSearch;
    private Resource res1;
    private Resource res2;
    private Resource res3;
    private Resource res4;
    private ResourceType resType1;
    private ResourceType resType2;
    private ResourceType resType3;
    private static final Set<String> keywords = new HashSet<>(
            Arrays.asList("are exam good hope in luck prepared this We well wish you".split(" ")));

    @BeforeEach
    public void setUp() {
        deskSearch = new DesktopSearch();
        resType1 = new ResourceType("txt-file", new PlainTextCollector());
        resType2 = new ResourceType("txt-file", new PlainTextCollector());
        resType3 = new ResourceType("unknown", new DefaultCollector());
        res1 = new Resource("res1.txt", "C:\\test", resType1);
        res2 = new Resource("res2.txt", "C:\\test", resType2);
        res3 = new Resource("res3.xyz", "C:\\test", resType3);
        res4 = new Resource("res3.xyz", "C:\\test2", resType3);
    }

    @Test
    public void testRegisterTypeNullArgument() {
        try {
            deskSearch.registerType(null, resType1);
            fail(
                    "DesktopSearch.registerType() should throw a NullPointerException if the extension argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            deskSearch.registerType("txt", null);
            fail("DesktopSearch.registerType() should throw a NullPointerException if the type argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testUnregisterTypeNullArgument() {
        try {
            deskSearch.unregisterType(null);
            fail("DesktopSearch.unregisterType() should throw a NullPointerException if the extension argument is null!");
        } catch (NullPointerException e) {
        }
    }

    // TODO: enable this test after summer term 2023
    @Test
    @Disabled
    public void testGetTypeNullArgument() {
        try {
            deskSearch.getType(null);
            fail("DesktopSearch.getType() should should throw a NullPointerException if the extension argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testRegisterType() {
        assertNull(deskSearch.getType("txt"), "DesktopSearch.getType() should return null if the file extension is not registered!");

        deskSearch.registerType("txt", resType1);
        assertEquals(resType1, deskSearch.getType("txt"), "DesktopSearch.getType() should return the correct resource type!");
        deskSearch.registerType("txt", resType2);
        assertEquals(resType2, deskSearch.getType("txt"), "DesktopSearch.getType() should return the correct resource type!");
        deskSearch.unregisterType("txt");
        assertNull(deskSearch.getType("txt"), "DesktopSearch.getType() should return null after an extension has been unregistered!");
    }

    @Test
    public void testProcessRequestDefaultCollector() {
        List<Resource> result = deskSearch.processRequest("txt");
        assertTrue(result != null && result.size() == 0,
                "DesktopSearch().processRequest should return an empty List if there is no resource on the index!");

        deskSearch.registerResource(res3);
        result = deskSearch.processRequest("res3.xyz");
        assertTrue(result.contains(res3) && result.size() == 1,
                "DesktopSearch.processRequest() should return the correct resources to the specified search term!");

        deskSearch.registerResource(res4);
        result = deskSearch.processRequest("res3.xyz");
        assertTrue(result.contains(res3) && result.contains(res4) && result.size() == 2,
                "DesktopSearch.processRequest() should return the correct resources to the specified search term!");
    }

    @Test
    public void testProcessRequestPlainTextCollector() {
        deskSearch.registerResource(res1);
        deskSearch.registerResource(res2);
        for (String keyword : keywords) {
            List<Resource> result = deskSearch.processRequest(keyword);
            assertTrue(
                    result != null && result.contains(res1) && result.contains(res2)
                            && result.size() == 2,
                    "DesktopSearch.processRequest() should return the correct resources to the search term!");
        }
    }
}
