import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Child extends User {
    String childName;
    int childPoints;
    int childLevel;
    Parent childOf;
    Teacher studentOf;
    int taskCount = 0;

    public Child(String name, int id) {
        childName = name;
        childPoints = 0;
        childLevel = 1;
        updateLevel();
    }

    void requestWish(String id, String title, String description, boolean type, LocalDateTime date_time) {
        Wish wish = new Wish(id, title, description, type, date_time);
        Main.requestedWishes.add(wish);
        try {
            FileWriter writer = new FileWriter("E:\\Uni\\KidsTask\\src\\Wishes.txt");
            writer.write("WISH#" + wish.wishId + "#" + wish.wishTitle + "#" + wish.wishDescription + "#" + wish.wishType + "#" + wish.wishDateTime + "#" + wish.wishPoints + "#" + wish.levelRestriction + "#" + wish.status + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred" + e.getMessage());
        }
        System.out.println("Wish requested " + wish.wishTitle);
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
                if (wish.levelRestriction != null) {
                    System.out.println("Appr Wish: " + wish.wishTitle + "Lvl Res: " + wish.levelRestriction);
                } else{
                    System.out.println("Appr Wish: " + wish.wishTitle);
                }
            }
        }
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

    void addTeacher(Teacher teacher) {
        this.studentOf = teacher;
    }

    void addParent(Parent parent) {
        this.childOf = parent;
    }

    void completeTask(int taskID) {
        Task task = null;

        for (Task task1 : Main.notCompletedTasks) {
            if (task1.taskId == taskID) {
                task = task1;
            } else continue;
        }

        if (task == null) {
            System.out.println("Task not found");
            return;
        }

        if (task.taskDateTime.isBefore(LocalDateTime.now())){
            System.out.println("FAILURE!! Task has expired. Passed Task Id and Title: " + task.taskId + task.taskTitle);
            Main.PassedTasks.add(task);
            Main.notCompletedTasks.remove(task);
            task.status = -2;
            return;
        } else {
            Main.CompletedTasks.add(task);
            Main.notCompletedTasks.remove(task);
            task.status = 1;
            this.childPoints += task.taskPoints;
            updateLevel();
            System.out.println("Task completed " + task.taskTitle);
        }

    }

    void updateLevel() {
        if (0 <= this.childPoints && this.childPoints < 40) {
            this.childLevel = 1;
        } else if (40 <= this.childPoints && this.childPoints < 60) {
            this.childLevel = 2;
        } else if (60 <= this.childPoints && this.childPoints < 80) {
            this.childLevel = 3;
        } else if (80 <= this.childPoints) {
            this.childLevel = 4;
        }
    }

    void buyWish(String wishID) {
        Wish wish = null;
        for (Wish wish1: Main.approvedWishes) {
            if (wish1.wishId.equals(wishID)) {
                wish = wish1;
            }else continue;
        }

        if (wish == null){
            System.out.println("Wish not found");
            return;
        }

        if (wish.levelRestriction == null || wish.levelRestriction <= this.childLevel) {
            this.childPoints-=wish.wishPoints;
            System.out.println("Wish bought " + wish.wishTitle);
        } else  {
            System.out.println("You can't buy this wish");
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
