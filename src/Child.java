import java.time.LocalDateTime;

public class Child extends User{
    String childName;
    int childPoints;
    int experience;
    int childLevel;
    Parent childOf;
    Teacher studentOf;

    public Child(String name, int id){
        childName = name;
        childPoints = 0;
        experience = 0;
        childLevel = 1;
    }

    void requestWish(String title, String description, boolean type, LocalDateTime date_time, int price, int levelRestriction) {
        Wish wish = new Wish(title, description, type, date_time, price, levelRestriction);
        Main.requestedWishes.add(wish);
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

    void addTeacher(Teacher teacher){
        this.studentOf = teacher;
    }

    void addParent(Parent parent){
        this.childOf = parent;
    }

    void completeTask(Task task) {
        Main.CompletedTasks.add(task);
        Main.notCompletedTasks.remove(task);
        task.status = 1;

        this.childPoints += task.reward;
        this.experience += task.experience;
        if (this.experience >= 100) {
            this.childLevel += 1;
            this.experience = 0;
        }
    }
}
