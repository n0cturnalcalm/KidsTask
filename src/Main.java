import java.util.ArrayList;
import java.time.LocalDateTime;

public class Main {
    public static ArrayList<Task> notCompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> CompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> ApprovedTasks = new ArrayList<Task>();

    public static ArrayList<Wish> requestedWishes;
    public static ArrayList<Wish> approvedWishes;

    public static Parent anna = new Parent("Anna");
    public static Teacher jack =new Teacher("Jack");
    public static Child billy = new Child("Billy", 1);

    static {
        anna.addChild(billy);
        jack.addStudent(billy);
        billy.addParent(anna);
        billy.addTeacher(jack);
    }

    public static void main(String[] args) {
        // Initialize ArrayLists that aren't initialized yet
        requestedWishes = new ArrayList<>();
        approvedWishes = new ArrayList<>();

        System.out.println("===== TESTING TASK MANAGEMENT =====");

        // Create tasks with due dates (LocalDateTime)
        notCompletedTasks.add(new Task("Matematik Ödev", "5 problem çöz", false,
                LocalDateTime.of(2023, 12, 15, 18, 0), 50, 80, 0, 0, jack));
        notCompletedTasks.add(new Task("İngilizce Makale", "300 kelimelik makale yaz", false,
                LocalDateTime.of(2023, 12, 20, 23, 59), 60, 90, 0, 0, jack));
        notCompletedTasks.add(new Task("Okul Gezisi", "Müze gezisi", true,
                LocalDateTime.of(2023, 12, 25, 9, 30), 70, 100, 0, 0, jack));

        System.out.println("Billy'nin başlangıç puanı: " + billy.childPoints);
        System.out.println("Billy'nin başlangıç deneyimi: " + billy.experience);
        System.out.println("Billy'nin başlangıç seviyesi: " + billy.childLevel);

        // Keep references to tasks
        Task task1 = notCompletedTasks.get(0);
        Task task2 = notCompletedTasks.get(1);
        Task task3 = notCompletedTasks.get(2);

        System.out.println("\nTasks before completion:");
        billy.listTasks();

        // Complete tasks
        System.out.println("\nBilly completing tasks...");
        billy.completeTask(task1);
        System.out.println("Task1 completed - Billy's points: " + billy.childPoints +
                ", experience: " + billy.experience + ", level: " + billy.childLevel);

        billy.completeTask(task2);
        System.out.println("Task2 completed - Billy's points: " + billy.childPoints +
                ", experience: " + billy.experience + ", level: " + billy.childLevel);

        billy.completeTask(task3);
        System.out.println("Task3 completed - Billy's points: " + billy.childPoints +
                ", experience: " + billy.experience + ", level: " + billy.childLevel);

        System.out.println("\nTasks after completion:");
        billy.listTasks();

        // Approve and reject tasks
        System.out.println("\nJack approving/rejecting tasks...");
        jack.approveTask(CompletedTasks.get(0), 5);
        jack.approveTask(CompletedTasks.get(0), 4); // This will fail as the task is already approved

        // Parent trying to approve a task from teacher (should fail)
        System.out.println("\nParent attempting to approve teacher's task:");
        anna.approveTask(CompletedTasks.get(0), 3);

        System.out.println("\nTasks after approval/rejection:");
        billy.listTasks();

        // Test wish functionality
        System.out.println("\n===== TESTING WISH MANAGEMENT =====");
        billy.requestWish("Yeni Oyuncak", "Robot oyuncak", false, LocalDateTime.of(2023, 12, 31, 0, 0), 150, 2);
        billy.requestWish("Hayvanat Bahçesi", "Hayvanat bahçesine gitmek", true, LocalDateTime.of(2024, 1, 15, 10, 0), 200, 3);

        System.out.println("Wishes before approval:");
        billy.listWishes();

        anna.approveWish(requestedWishes.get(0));

        System.out.println("\nWishes after approval:");
        billy.listWishes();

        // Test extra points
        System.out.println("\n===== TESTING EXTRA POINTS =====");
        System.out.println("Billy's points before: " + billy.childPoints);
        anna.giveExtraPoints(billy, 25);
        System.out.println("Billy's points after parent bonus: " + billy.childPoints);
        jack.giveExtraPoints(billy, 15);
        System.out.println("Billy's points after teacher bonus: " + billy.childPoints);

        // Test adding new tasks through Parent and Teacher
        System.out.println("\n===== TESTING ADDING NEW TASKS =====");
        anna.addTask("Oda Temizliği", "Odanı temizle", false,
                LocalDateTime.of(2024, 1, 5, 18, 0), 30, 40, 0, 0);
        jack.addTask("Proje Ödevi", "Bilim projesi", false,
                LocalDateTime.of(2024, 1, 10, 23, 59), 80, 110, 0, 0);

        System.out.println("Tasks after adding new ones:");
        billy.listTasks();
    }
}