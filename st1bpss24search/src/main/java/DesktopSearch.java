import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DesktopSearch {

    private Map<String, ResourceType> types = new HashMap<>();
    private Index index;

    public DesktopSearch() {
        index = new Index();
    }

    public void registerType(String extension, ResourceType type) {
        if(extension == null || type == null) throw new NullPointerException();
        if(extension.isEmpty()) throw new IllegalArgumentException();
        types.put(extension, type);
    }

    public ResourceType getType(String extension) {
        if(extension == null) throw new NullPointerException();
        if(extension.isEmpty()) throw new IllegalArgumentException();
        return this.types.get(extension);
    }

    public void unregisterType(String extension) {
        if(extension == null) throw new NullPointerException();
        if(extension.isEmpty()) throw new IllegalArgumentException();
        types.remove(extension);
    }

    public void registerResource(Resource res) {
        if(res == null) throw new NullPointerException();
        index.add(res);
    }

    public List<Resource> processRequest(String request) {
        if(request == null) throw new NullPointerException();
        if(request.isEmpty()) throw new IllegalArgumentException();
        List<Resource> result = index.getResources(request);
        if(result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }
}