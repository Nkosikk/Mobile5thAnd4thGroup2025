package Pages;

/* OOP Project structure
CampusEventManagement/
├ Main.java
├ User.java
├ Student.java
├ Staff.java
├ Event.java
└ EventManager.java

How it works: Staff create and manage events. Students register for events, join waitlists when events are full, and receive automatic promotions when spaces become available.
This is the parent class for all users. It demonstrates inheritance because Student and Staff extend User.*/

public abstract class User {
    protected String userId;
    protected String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return userId + " - " + name + " (" + getRole() + ")";
    }
}

//Students can register, cancel registrations, and view their own registration status.
public class Student extends User {

    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRole() {
        return "Student";
    }
}

// Staff users have access to create, manage and view event management functions.

public class Staff extends User {

    public Staff(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRole() {
        return "Staff";
    }
}
//This class models a campus event. It maintains two separate states: registered participants and a waitlist.

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Event {

    private int eventId;
    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String location;
    private int maxParticipants;
    private boolean cancelled;

    private ArrayList<Student> registeredParticipants;
    private Queue<Student> waitlist;

    public Event(int eventId, String eventName, LocalDate eventDate,
                 LocalTime eventTime, String location, int maxParticipants) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.maxParticipants = maxParticipants;

        registeredParticipants = new ArrayList<>();
        waitlist = new LinkedList<>();
        cancelled = false;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public ArrayList<Student> getRegisteredParticipants() {
        return registeredParticipants;
    }

    public Queue<Student> getWaitlist() {
        return waitlist;
    }

    public void updateDetails(String name, LocalTime time, String location) {
        this.eventName = name;
        this.eventTime = time;
        this.location = location;
    }

    public void cancelEvent() {
        cancelled = true;
    }

    public boolean isFull() {
        return registeredParticipants.size() >= maxParticipants;
    }

    public boolean isRegistered(Student student) {
        return registeredParticipants.stream()
                .anyMatch(s -> s.getUserId().equals(student.getUserId()));
    }

    public boolean isWaitlisted(Student student) {
        return waitlist.stream()
                .anyMatch(s -> s.getUserId().equals(student.getUserId()));
    }

    public String registerStudent(Student student) {

        if (cancelled) {
            return "Registration failed. This event has been cancelled.";
        }

        if (isRegistered(student)) {
            return "You are already registered for this event.";
        }

        if (isWaitlisted(student)) {
            return "You are already on the waitlist.";
        }

        if (!isFull()) {
            registeredParticipants.add(student);
            return "Registration successful. You are registered for the event.";
        }

        waitlist.add(student);
        return "Event is full. You have been added to the waitlist.";
    }

    public String cancelRegistration(Student student) {

        boolean removed = registeredParticipants.removeIf(
                s -> s.getUserId().equals(student.getUserId()));

        if (removed) {
            return "Registration cancelled successfully.";
        }

        boolean removedFromWaitlist = waitlist.removeIf(
                s -> s.getUserId().equals(student.getUserId()));

        if (removedFromWaitlist) {
            return "You have been removed from the waitlist.";
        }

        return "You are not registered or waitlisted for this event.";
    }

    public Student promoteNextStudent() {

        if (!isFull() && !waitlist.isEmpty()) {
            Student student = waitlist.poll();
            registeredParticipants.add(student);
            return student;
        }

        return null;
    }

    public String getRegistrationStatus(Student student) {

        if (isRegistered(student)) {
            return "Registered";
        }

        if (isWaitlisted(student)) {
            return "Waitlisted";
        }

        return "Not Registered";
    }

    public String getDetails() {

        return "\nEvent ID: " + eventId +
                "\nEvent Name: " + eventName +
                "\nDate: " + eventDate +
                "\nTime: " + eventTime +
                "\nLocation: " + location +
                "\nMaximum Participants: " + maxParticipants +
                "\nRegistered Participants: " + registeredParticipants.size() +
                "\nWaitlist Count: " + waitlist.size() +
                "\nStatus: " + (cancelled ? "Cancelled" : "Available");
    }

    public String getRegisteredNames() {

        if (registeredParticipants.isEmpty()) {
            return "No registered participants.";
        }

        StringBuilder result = new StringBuilder();

        for (Student student : registeredParticipants) {
            result.append(student.getUserId())
                    .append(" - ")
                    .append(student.getName())
                    .append("\n");
        }

        return result.toString();
    }

    public String getWaitlistNames() {

        if (waitlist.isEmpty()) {
            return "No students on the waitlist.";
        }

        StringBuilder result = new StringBuilder();

        for (Student student : waitlist) {
            result.append(student.getUserId())
                    .append(" - ")
                    .append(student.getName())
                    .append("\n");
        }

        return result.toString();
    }
}

/*This is the main class. It manages events, users, registration, searching, sorting, and file persistence.*/
import java.io.*;
        import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EventManager {

    private ArrayList<Event> events = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();

    private final String EVENT_FILE = "events.txt";
    private final String REGISTRATION_FILE = "registrations.txt";

    private final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");

    public EventManager() {
        loadData();
    }

    // ---------------- USER MANAGEMENT ----------------

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User findUser(String userId) {
        return users.get(userId);
    }

    // ---------------- EVENT MANAGEMENT ----------------

    public boolean eventExists(int eventId) {

        return events.stream()
                .anyMatch(e -> e.getEventId() == eventId);
    }

    public Event findEvent(int eventId) {

        return events.stream()
                .filter(e -> e.getEventId() == eventId)
                .findFirst()
                .orElse(null);
    }

    public void createEvent(Scanner scanner) {

        try {
            System.out.print("Enter Event ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (eventExists(id)) {
                System.out.println("Error: Event ID already exists.");
                return;
            }

            System.out.print("Enter Event Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Event Date (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);

            System.out.print("Enter Event Time (HH:mm): ");
            LocalTime time = LocalTime.parse(scanner.nextLine(), TIME_FORMAT);

            System.out.print("Enter Location: ");
            String location = scanner.nextLine();

            System.out.print("Enter Maximum Participants: ");
            int max = Integer.parseInt(scanner.nextLine());

            if (name.trim().isEmpty() || location.trim().isEmpty()) {
                System.out.println("Error: Text fields cannot be empty.");
                return;
            }

            if (max <= 0) {
                System.out.println("Error: Maximum participants must be positive.");
                return;
            }

            Event event = new Event(id, name, date, time, location, max);
            events.add(event);

            saveData();

            System.out.println("Event created successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date or time format.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }




    public void updateEvent(Scanner scanner) {

        try {
            System.out.print("Enter Event ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Event event = findEvent(id);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            System.out.print("Enter new Event Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Event Time (HH:mm): ");
            LocalTime time = LocalTime.parse(scanner.nextLine(), TIME_FORMAT);

            System.out.print("Enter new Location: ");
            String location = scanner.nextLine();

            if (name.trim().isEmpty() || location.trim().isEmpty()) {
                System.out.println("Error: Text fields cannot be empty.");
                return;
            }

            event.updateDetails(name, time, location);

            saveData();

            System.out.println("Event updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Event ID.");

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid time format.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cancelEvent(Scanner scanner) {

        try {
            System.out.print("Enter Event ID to cancel: ");
            int id = Integer.parseInt(scanner.nextLine());

            Event event = findEvent(id);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            event.cancelEvent();
            saveData();

            System.out.println("Event cancelled successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Event ID.");
        }
    }

    // ---------------- VIEW EVENTS ----------------

    public void viewAllEvents() {

        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (Event event : events) {
            System.out.println(event.getDetails());
            System.out.println("----------------------------");
        }
    }

    public void searchEvents(Scanner scanner) {

        System.out.print("Enter event name or date to search: ");
        String search = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Event event : events) {

            if (event.getEventName().toLowerCase().contains(search)
                    || event.getEventDate().format(DATE_FORMAT).contains(search)) {

                System.out.println(event.getDetails());
                System.out.println("----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching events found.");
        }
    }

    public void sortEventsByName() {

        events.sort(Comparator.comparing(Event::getEventName));
        viewAllEvents();
    }

    public void sortEventsByDate() {

        events.sort(Comparator.comparing(Event::getEventDate));
        viewAllEvents();
    }

    // ---------------- STUDENT REGISTRATION ----------------

    public void registerStudent(Scanner scanner, Student student) {

        try {
            System.out.print("Enter Event ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Event event = findEvent(id);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            String result = event.registerStudent(student);

            System.out.println(result);

            saveData();

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Event ID.");
        }
    }

    public void cancelRegistration(Scanner scanner, Student student) {

        try {
            System.out.print("Enter Event ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Event event = findEvent(id);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            boolean wasRegistered = event.isRegistered(student);

            String result = event.cancelRegistration(student);

            System.out.println(result);

            if (wasRegistered) {
                promoteFromWaitlist(event);
            }

            saveData();

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Event ID.");
        }
    }

    public void viewRegistrationStatus(Student student) {

        boolean found = false;

        for (Event event : events) {

            String status = event.getRegistrationStatus(student);

            if (!status.equals("Not Registered")) {

                System.out.println(
                        "Event: " + event.getEventName() +
                                " | Status: " + status
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("You have no registrations.");
        }
    }

    // ---------------- BACKGROUND PROMOTION ----------------

    public void promoteFromWaitlist(Event event) {

        Thread promotionThread = new Thread(() -> {

            try {
                Thread.sleep(1000);

                Student promoted = event.promoteNextStudent();

                if (promoted != null) {

                    System.out.println(
                            "\nRegistration cancelled. Student "
                                    + promoted.getUserId()
                                    + " has been promoted from the waitlist to the event."
                    );
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        promotionThread.start();
    }

    // ---------------- STAFF VIEW ----------------

    public void viewParticipants(Scanner scanner) {

        try {
            System.out.print("Enter Event ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Event event = findEvent(id);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            System.out.println("\nREGISTERED PARTICIPANTS:");
            System.out.println(event.getRegisteredNames());

            System.out.println("WAITLIST:");
            System.out.println(event.getWaitlistNames());

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Event ID.");
        }
    }

    // ---------------- FILE PERSISTENCE ----------------

    public void saveData() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(EVENT_FILE))) {

            for (Event event : events) {

                writer.println(
                        event.getEventId() + "|" +
                                event.getEventName() + "|" +
                                event.getEventDate() + "|" +
                                event.getEventTime() + "|" +
                                event.getLocation() + "|" +
                                event.getMaxParticipants() + "|" +
                                event.isCancelled()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(REGISTRATION_FILE))) {

            for (Event event : events) {

                for (Student student : event.getRegisteredParticipants()) {

                    writer.println(
                            event.getEventId() + "|" +
                                    student.getUserId() + "|REGISTERED"
                    );
                }

                for (Student student : event.getWaitlist()) {

                    writer.println(
                            event.getEventId() + "|" +
                                    student.getUserId() + "|WAITLISTED"
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving registrations: " + e.getMessage());
        }
    }

    public void loadData() {

        File file = new File(EVENT_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(EVENT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                LocalDate date = LocalDate.parse(data[2]);
                LocalTime time = LocalTime.parse(data[3]);
                String location = data[4];
                int max = Integer.parseInt(data[5]);

                Event event = new Event(id, name, date, time, location, max);

                if (Boolean.parseBoolean(data[6])) {
                    event.cancelEvent();
                }

                events.add(event);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading events: " + e.getMessage());
        }

        loadRegistrations();
    }

    private void loadRegistrations() {

        File file = new File(REGISTRATION_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRATION_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                int eventId = Integer.parseInt(data[0]);
                String userId = data[1];
                String status = data[2];

                Event event = findEvent(eventId);
                User user = findUser(userId);

                if (event != null && user instanceof Student) {

                    Student student = (Student) user;

                    if (status.equals("REGISTERED")) {
                        event.getRegisteredParticipants().add(student);
                    } else {
                        event.getWaitlist().add(student);
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading registrations: " + e.getMessage());
        }
    }
}
/*This is the entry point of the application. It prompts the user to select a role and restricts access to the appropriate menu.*/
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EventManager manager = new EventManager();

        // Sample users
        Student student1 = new Student("S102", "Sanele");
        Student student2 = new Student("S103", "Thabo");
        Staff staff1 = new Staff("ST001", "Admin");

        manager.addUser(student1);
        manager.addUser(student2);
        manager.addUser(staff1);

        System.out.println("====================================");
        System.out.println(" CAMPUS EVENT MANAGEMENT SYSTEM");
        System.out.println("====================================");

        boolean running = true;

        while (running) {

            System.out.println("\nSELECT ROLE");
            System.out.println("1. Student");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    studentMenu(scanner, manager, student1);
                    break;

                case "2":
                    staffMenu(scanner, manager);
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    // ---------------- STUDENT MENU ----------------

    private static void studentMenu(
            Scanner scanner,
            EventManager manager,
            Student student) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. View Available Events");
            System.out.println("2. Search Events");
            System.out.println("3. Register for Event");
            System.out.println("4. Cancel Registration");
            System.out.println("5. View Registration Status");
            System.out.println("6. Sort Events by Name");
            System.out.println("7. Sort Events by Date");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    manager.viewAllEvents();
                    break;

                case "2":
                    manager.searchEvents(scanner);
                    break;

                case "3":
                    manager.registerStudent(scanner, student);
                    break;

                case "4":
                    manager.cancelRegistration(scanner, student);
                    break;

                case "5":
                    manager.viewRegistrationStatus(student);
                    break;

                case "6":
                    manager.sortEventsByName();
                    break;

                case "7":
                    manager.sortEventsByDate();
                    break;

                case "8":
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ---------------- STAFF MENU ----------------

    private static void staffMenu(
            Scanner scanner,
            EventManager manager) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. Create Event");
            System.out.println("2. Update Event");
            System.out.println("3. Cancel Event");
            System.out.println("4. View All Events");
            System.out.println("5. View Participants and Waitlist");
            System.out.println("6. Search Events");
            System.out.println("7. Sort Events by Name");
            System.out.println("8. Sort Events by Date");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    manager.createEvent(scanner);
                    break;

                case "2":
                    manager.updateEvent(scanner);
                    break;

                case "3":
                    manager.cancelEvent(scanner);
                    break;

                case "4":
                    manager.viewAllEvents();
                    break;

                case "5":
                    manager.viewParticipants(scanner);
                    break;

                case "6":
                    manager.searchEvents(scanner);
                    break;

                case "7":
                    manager.sortEventsByName();
                    break;

                case "8":
                    manager.sortEventsByDate();
                    break;

                case "9":
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

