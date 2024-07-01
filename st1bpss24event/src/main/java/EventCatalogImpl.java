import java.util.*;

public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {


    @Override
    public boolean addCatalogEntry(Event e, Set<Time> tSet) {
        if(e == null || tSet == null) throw new NullPointerException();

        for (Time time: tSet) {
            if(time == null) throw new NullPointerException();
        }

        if(!containsKey(e)) {
            put(e, tSet);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTimeToEvent(Event e, Time t) {
        if(e == null || t == null) throw new NullPointerException();
        if(containsKey(e) && !get(e).contains(t)) {
            Set<Time> tSet = get(e);
            tSet.add(t);
            put(e, tSet);
            return true;
        }
        return false;
    }

    @Override
    public Set<Event> getAllEvents() {
        return keySet();
    }

    @Override
    public Set<Time> getTimesOfEvent(Event e) {
        if(e == null) throw new NullPointerException();
        if(containsKey(e)) {
            return get(e);
        }
        return null;
    }


    @Override
    public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
        if(category == null) throw new NullPointerException();
        Map<Event, Set<Time>> filteredCatalog = new HashMap<>();

        for(Event e: keySet()) {
            if(e.getCategory() == category) {
                filteredCatalog.put(e, get(e));
            }
        }
        return filteredCatalog;
    }


    @Override
    public Set<Time> deleteEvent(Event e) {
        if(e == null) throw new NullPointerException();
        if(containsKey(e)) {
            return remove(e);
        }
        return null;
    }

    @Override
    public boolean deleteTime(Event e, Time t) {
        if(e == null || t == null) throw new NullPointerException();
        if(containsKey(e)) {
            Set<Time> tSet = getTimesOfEvent(e);
            if(!tSet.contains(t)) {
                return false;
            }
            tSet.remove(t);
            replace(e, tSet);
            return true;
        }
        return false;
    }

}