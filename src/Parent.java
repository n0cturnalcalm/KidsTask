import java.time.LocalDateTime;
import java.util.ArrayList;
public class Parent extends User{
    String parentName;
    ArrayList<Child> childList = new ArrayList<Child>();

    public Parent(String name){
        parentName = name;
    }

    void addChild(Child child){
        this.childList.add(child);
    }

    void approveWish(Wish wish) {
        Main.approvedWishes.add(wish);
        Main.requestedWishes.remove(wish);
        wish.status = 1;
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

    void listWishes() {
        for (Wish wish : Main.requestedWishes) {
            if (wish == null) {
                System.out.println("there is no wish");
            }
            System.out.println("Wish: " + wish.wishTitle);
        }for (Wish wish: Main.approvedWishes) {
            if (wish == null) {
                System.out.println("there is no approved wish");
            }
            System.out.println("Appr: " + wish.wishTitle);
        }
    }

    void giveExtraPoints(Child child, int amount) {
        child.childPoints += amount;
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
}
