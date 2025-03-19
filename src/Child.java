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

    void completeTask(int taskID) {
        Task task = null;

        for (Task task1: Main.notCompletedTasks) {
            if (task1.taskId == taskID) {
                task = task1;
            }else continue;
        }

        if (task == null){
            System.out.println("Task not found");
            return;
        }

        Main.CompletedTasks.add(task);
        Main.notCompletedTasks.remove(task);
        task.status = 1;

        this.childPoints += task.reward;
        this.experience += task.experience;
        updateLevel();
    }

    void updateLevel(){
        if (0 <= this.experience && this.experience < 40){
            this.childLevel = 1;
        }else if (40 <= this.experience && this.experience < 60){
            this.childLevel = 2;
        }else if (60 <= this.experience && this.experience < 80){
            this.childLevel = 3;
        }else if (80 <= this.experience){
            this.childLevel = 4;
        }
    }
}
