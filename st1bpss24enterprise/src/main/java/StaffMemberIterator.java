import java.util.*;

public class StaffMemberIterator implements EnterpriseNodeIterator<StaffMember> {

    private int iterationIdx = 0;

    private SortedSet<StaffMember> allMembers = new TreeSet<>();
    public StaffMemberIterator(Set<StaffMember> directSubordinates) {
        if(directSubordinates == null) throw new NullPointerException();
        allMembers.addAll(directSubordinates);
    }

    private void findSubordinatesRecursively(StaffMember m) {
        if(m == null) throw new NullPointerException();

        Set<StaffMember> subordinate = m.getDirectSubordinates();

        if(subordinate.isEmpty())
            return;

        for(StaffMember member: subordinate) {
            findSubordinatesRecursively(member);
        }

    }

    @Override
    public boolean hasNext() {
        return iterationIdx < allMembers.size();
    }

    @Override
    public StaffMember next() {
        if(!hasNext()) throw new NoSuchElementException();
        int currentIndex = 0;
        StaffMember result = null;
        for(StaffMember member: allMembers) {
            if(currentIndex == iterationIdx) {
                result = member;
                break;
            }
            currentIndex++;
        }
        iterationIdx++;
        return result;
    }
}