import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Task> notCompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> CompletedTasks = new ArrayList<Task>();
    public static ArrayList<Task> ApprovedTasks = new ArrayList<Task>();
    public static ArrayList<Task> RejectedTasks = new ArrayList<Task>();

    public static Parent anna = new Parent();
    public static Teacher jack =new Teacher();
    public static Child billy = new Child("Billy", 1, anna, jack);

    public static void main(String[] args) {
        notCompletedTasks.add(new Task(1, "görev1", "açıklama", false, LocalDateTime.of(2026,9,29, 12,0), 70, 120, 0, 0, jack));
        notCompletedTasks.add(new Task(2, "görev2", "açıklama", false, LocalDateTime.of(2026,9,29, 12,0), 70, 120, 0, 0, jack));
        notCompletedTasks.add(new Task(3, "görev3", "açıklama", false, LocalDateTime.of(2026,9,29, 12,0), 70, 120, 0, 0, jack));

        // Store references to the tasks before modifying the list
        Task task1 = notCompletedTasks.get(0);
        Task task2 = notCompletedTasks.get(1);
        Task task3 = notCompletedTasks.get(2);

        billy.completeTask(task1);
        billy.completeTask(task2);
        billy.completeTask(task3);

        jack.approveTask(CompletedTasks.get(0), 5);
        jack.rejectTask(CompletedTasks.get(1));
        billy.listTasks();
    }
}