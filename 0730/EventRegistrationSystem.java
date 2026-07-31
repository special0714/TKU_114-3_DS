import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class EventRegistrationSystem {

    private final List<Registration> allRegistrations = new ArrayList<>();
    private final Queue<Registration> waitingQueue = new ArrayDeque<>();
    private final Deque<Registration> cancelledStack = new ArrayDeque<>();
    private final Set<String> registeredIds = new HashSet<>();
    private final int capacity;
    private boolean isSortedById = false;

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(String id, String name, String phone) {
        if (id == null || name == null) return false;

        if (registeredIds.contains(id)) {
            System.out.println("Error: Duplicate ID " + id);
            return false;
        }

        Registration reg = new Registration(id, name, phone);
        registeredIds.add(id);

        if (allRegistrations.size() < capacity) {
            allRegistrations.add(reg);
            isSortedById = false;
            System.out.println("Success: Registered - " + reg);
        } else {
            waitingQueue.offer(reg);
            System.out.println("Capacity full. Placed in waiting queue - " + reg);
        }
        return true;
    }

    public boolean cancelRegistration(String id) {
        if (id == null || !registeredIds.contains(id)) {
            System.out.println("Error: Registration ID not found - " + id);
            return false;
        }

        Registration target = null;
        for (Registration reg : allRegistrations) {
            if (reg.getId().equals(id)) {
                target = reg;
                break;
            }
        }

        if (target != null) {
            allRegistrations.remove(target);
            registeredIds.remove(id);
            cancelledStack.push(target);
            System.out.println("Cancelled registration: " + target);

            if (!waitingQueue.isEmpty()) {
                Registration promoted = waitingQueue.poll();
                allRegistrations.add(promoted);
                isSortedById = false;
                System.out.println("Promoted from waiting queue: " + promoted);
            } else {
                System.out.println("Waiting queue is empty. No promotion made.");
            }
            return true;
        }

        Registration inQueueTarget = null;
        for (Registration reg : waitingQueue) {
            if (reg.getId().equals(id)) {
                inQueueTarget = reg;
                break;
            }
        }

        if (inQueueTarget != null) {
            waitingQueue.remove(inQueueTarget);
            registeredIds.remove(id);
            cancelledStack.push(inQueueTarget);
            System.out.println("Cancelled waiting queue registration: " + inQueueTarget);
            return true;
        }

        return false;
    }

    public boolean undoLastCancellation() {
        if (cancelledStack.isEmpty()) {
            System.out.println("Error: No cancellation record to undo.");
            return false;
        }

        Registration restored = cancelledStack.pop();

        if (allRegistrations.size() < capacity) {
            allRegistrations.add(restored);
            registeredIds.add(restored.getId());
            isSortedById = false;
            System.out.println("Restored to main list: " + restored);
        } else {
            Registration demoted = allRegistrations.remove(allRegistrations.size() - 1);
            ((ArrayDeque<Registration>) waitingQueue).addFirst(demoted);
            allRegistrations.add(restored);
            registeredIds.add(restored.getId());
            isSortedById = false;
            System.out.println("Restored to main list: " + restored + " (Demoted last entry " + demoted.getId() + " to waiting queue)");
        }
        return true;
    }

    public void sortById() {
        if (allRegistrations.isEmpty()) return;
        RegistrationAlgorithms.mergeSortById(allRegistrations, 0, allRegistrations.size() - 1);
        isSortedById = true;
    }

    public Registration searchById(String id) {
        if (!isSortedById) {
            sortById();
        }
        return RegistrationAlgorithms.binarySearchById(allRegistrations, id);
    }

    public List<Registration> searchByName(String name) {
        List<Registration> result = new ArrayList<>();
        result.addAll(RegistrationAlgorithms.sequentialSearchByName(allRegistrations, name));
        for (Registration reg : waitingQueue) {
            if (reg.getName().equalsIgnoreCase(name)) {
                result.add(reg);
            }
        }
        return result;
    }

    public void displayStatus() {
        System.out.println("=== Main Registrations (" + allRegistrations.size() + "/" + capacity + ") ===");
        for (Registration r : allRegistrations) {
            System.out.println(r);
        }
        System.out.println("=== Waiting Queue (" + waitingQueue.size() + ") ===");
        for (Registration r : waitingQueue) {
            System.out.println(r);
        }
        System.out.println("=== Cancelled Stack (" + cancelledStack.size() + ") ===");
        for (Registration r : cancelledStack) {
            System.out.println(r);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EventRegistrationSystem system = new EventRegistrationSystem(3);

        system.register("R003", "Alice", "0911-111-111");
        system.register("R001", "Bob", "0922-222-222");
        system.register("R002", "Alice", "0933-333-333");

        system.register("R001", "Charlie", "0944-444-444");

        system.register("R005", "David", "0955-555-555");
        system.register("R004", "Eve", "0966-666-666");

        system.displayStatus();

        system.sortById();
        System.out.println("Sorted main list by ID.");
        system.displayStatus();

        System.out.println("Binary Search R002: " + system.searchById("R002"));
        System.out.println("Binary Search R099: " + system.searchById("R099"));
        System.out.println();

        System.out.println("Sequential Search 'Alice':");
        List<Registration> aliceList = system.searchByName("Alice");
        for (Registration r : aliceList) {
            System.out.println("Found: " + r);
        }
        System.out.println();

        system.cancelRegistration("R099");
        system.cancelRegistration("R001");
        system.displayStatus();

        system.undoLastCancellation();
        system.displayStatus();

        system.cancelRegistration("R003");
        system.cancelRegistration("R002");
        system.cancelRegistration("R005");
        system.displayStatus();

        system.cancelRegistration("R004");
        system.displayStatus();

        system.undoLastCancellation();
        system.undoLastCancellation();
        system.undoLastCancellation();

        system.undoLastCancellation();
        system.undoLastCancellation();
    }
}