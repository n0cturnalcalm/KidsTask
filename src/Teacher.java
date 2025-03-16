import java.time.LocalDateTime;
import java.util.ArrayList;

public class Teacher extends User{
    String teacherName;
    ArrayList<Child> studentsList = new ArrayList<Child>();

    public Teacher(String name){
        teacherName = name;
    }

    void addTask(String title, String description, boolean type, LocalDateTime date_time, int reward, int experience, int rating, int status) {
        Task task = new Task(title, description, type, date_time, reward, experience, rating, status, this);
        Main.notCompletedTasks.add(task);
    }

    void approveTask(Task task, int rating) {
        if(task.givenBy instanceof Teacher){
            Main.ApprovedTasks.add(task);
            Main.CompletedTasks.remove(task);
            task.status = 2;
            task.rating = rating;
        }else {
            System.out.println("You can't approve this task");
        }
    }

    void listTasks() {
        for (Task task : Main.notCompletedTasks) {
            if (task == null) {
                System.out.println("all tasks are completed");
            }
            System.out.println("NotComp: " + task.taskTitle);
        }for (Task task: Main.CompletedTasks) {
            if (task == null) {
                System.out.println("there is no completed task");
            }
            System.out.println("Comp: " + task.taskTitle);
        }for (Task task: Main.ApprovedTasks) {
            if (task == null) {
                System.out.println("there is no approved task");
            }
            System.out.println("Appr: " + task.taskTitle);
        }
    }

    void addStudent(Child student){
        this.studentsList.add(student);
    }

    void giveExtraPoints(Child child, int amount) {
        child.childPoints += amount;
    }
}
