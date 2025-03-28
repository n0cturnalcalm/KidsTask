import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    void approveWish(String wishID, int wishPoints) { // without level restriction
        Wish wish = null;
        for (Wish wish1: Main.requestedWishes) {
            if (wish1.wishId.equals(wishID)) {
                wish = wish1;
            }else continue;
        }

        if (wish == null){
            System.out.println("Wish not found");
            return;
        }
        wish.wishPoints = wishPoints;
        Main.approvedWishes.add(wish);
        Main.requestedWishes.remove(wish);
        wish.status = 1;
    }

    void approveWish(String wishID, int levelRestriction, int wishPoints) { // with level restriction
        Wish wish = null;
        for (Wish wish1: Main.requestedWishes) {
            if (wish1.wishId.equals(wishID)) {
                wish = wish1;
            }else continue;
        }

        if (wish == null){
            System.out.println("Wish not found");
            return;
        }
        wish.wishPoints = wishPoints;
        Main.approvedWishes.add(wish);
        Main.requestedWishes.remove(wish);
        wish.status = 1;
        wish.levelRestriction = levelRestriction;
    }

    void rejectWish(String wishID) {
        Wish wish = null;
        for (Wish wish1: Main.requestedWishes) {
            if (wish1.wishId.equals(wishID)) {
                wish = wish1;
            }else continue;
        }

        if (wish == null){
            System.out.println("Wish not found");
            return;
        }

        Main.requestedWishes.remove(wish);
        wish.status = -1;
    }

    void listTasks() {
        for (Task task : Main.notCompletedTasks) {
            if (task == null) {
                System.out.println("all tasks are completed");
                break;
            }
            if (task.taskDateTime.isBefore(LocalDateTime.now())){
                System.out.println("FAILURE!! Task has expired. Passed Task Id and Title: " + task.taskId + task.taskTitle);
                Main.PassedTasks.add(task);
                Main.notCompletedTasks.remove(task);
            }else {
                System.out.println("NotComp: " + task.taskTitle);
            }

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

    void listTasksFiltered(char filter) {
        System.out.println("Filtered Tasks (" + filter + "):");

        for (Task task : Main.notCompletedTasks) {
            if (task == null) continue;

            LocalDateTime taskDate = task.taskDateTime;

            if (filter == 'D' && taskDate.toLocalDate().isEqual(LocalDateTime.now().toLocalDate())) {
                System.out.println("NotComp: " + task.taskTitle + " | " + taskDate);
            }
            else if (filter == 'W' && taskDate.isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))) {
                System.out.println("NotComp: " + task.taskTitle + " | " + taskDate);
            }
        }
    }

    void listWishes() {
        for (Wish wish : Main.requestedWishes) {
            if (wish == null) {
                System.out.println("there is no wish");
                break;
            }

            if (wish.wishDateTime.isBefore(LocalDateTime.now())) {
                System.out.println("FAILURE!! Wish has expired. Passed Wish Id and Title: " + wish.wishId + wish.wishTitle);
                Main.requestedWishes.remove(wish);
            } else {
                System.out.println("Req Wish: " + wish.wishTitle);
            }

        }for (Wish wish: Main.approvedWishes) {
            if (wish == null) {
                System.out.println("there is no approved wish");
                break;
            }

            if (wish.wishDateTime.isBefore(LocalDateTime.now())) {
                System.out.println("FAILURE!! Wish has expired. Passed Wish Id and Title: " + wish.wishId + wish.wishTitle);
                Main.approvedWishes.remove(wish);
            } else {
                System.out.println("Appr Wish: " + wish.wishTitle + "Lvl Res: " + wish.levelRestriction);
            }
        }
    }

    void giveExtraPoints(Child child, int amount) {
        child.childPoints += amount;
        child.updateLevel();
    }

    void addTask(int id, String title, String description, boolean type, LocalDateTime date_time, int reward, int rating, int status) {
        Task task = new Task(id, title, description, type, date_time, reward, status, this);
        Main.notCompletedTasks.add(task);

        try (FileWriter writer = new FileWriter("E:\\Uni\\KidsTask\\src\\Tasks.txt")) {
            writer.write("TASK#" + task.taskId + "#" + task.taskTitle + "#" + task.taskDescription + "#" + task.taskType + "#" + task.taskDateTime + "#" + task.taskPoints + "#" + task.rating + "#" + task.status + "#" + this + "\n");
        }catch (Exception e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }

    void approveTask(int taskID, int rating) {
        Task task = null;

        for (Task task1: Main.CompletedTasks) {
            if (task1.taskId == taskID) {
                task = task1;
            }else continue;
        }

        if (task == null){
            System.out.println("Task not found");
            return;
        }
        if(task.givenBy instanceof Parent){
            Main.ApprovedTasks.add(task);
            Main.CompletedTasks.remove(task);
            task.status = 2;
            Main.billy.taskCount++;
            task.rating = rating;
        }else {
            System.out.println("You can't approve this task");
        }
    }

    void updateTasksFile() {
        try (FileWriter writer = new FileWriter("E:\\Uni\\KidsTask\\src\\Tasks.txt")) {
            for (Task task : Main.notCompletedTasks) {
                writer.write("TASK#" + task.taskId + "#" + task.taskTitle + "#" + task.taskDescription + "#" + task.taskType + "#" + task.taskDateTime + "#" + task.taskPoints + "#" + task.rating + "#" + task.status + "#" + task.givenBy + "\n");
            }
            for (Task task : Main.CompletedTasks) {
                writer.write("TASK#" + task.taskId + "#" + task.taskTitle + "#" + task.taskDescription + "#" + task.taskType + "#" + task.taskDateTime + "#" + task.taskPoints + "#" + task.rating + "#" + task.status + "#" + task.givenBy + "\n");
            }
            for (Task task : Main.ApprovedTasks) {
                writer.write("TASK#" + task.taskId + "#" + task.taskTitle + "#" + task.taskDescription + "#" + task.taskType + "#" + task.taskDateTime + "#" + task.taskPoints + "#" + task.rating + "#" + task.status + "#" + task.givenBy + "\n");
            }
            for (Task task : Main.PassedTasks) {
                writer.write("TASK#" + task.taskId + "#" + task.taskTitle + "#" + task.taskDescription + "#" + task.taskType + "#" + task.taskDateTime + "#" + task.taskPoints + "#" + task.rating + "#" + task.status + "#" + task.givenBy + "\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }

    void updateWishesFile() {
        try (FileWriter writer = new FileWriter("E:\\Uni\\KidsTask\\src\\WishList.txt")) {
            for (Wish wish : Main.requestedWishes) {
                writer.write("WISH#" + wish.wishId + "#" + wish.wishTitle + "#" + wish.wishDescription + "#" + wish.wishType + "#" + wish.wishDateTime + "#" + wish.wishPoints + "\n");
            }
            for (Wish wish : Main.approvedWishes) {
                writer.write("WISH#" + wish.wishId + "#" + wish.wishTitle + "#" + wish.wishDescription + "#" + wish.wishType + "#" + wish.wishDateTime + "#" + wish.wishPoints + "\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }
}
