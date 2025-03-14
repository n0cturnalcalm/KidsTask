public class Child extends User{
    String childName;
    int childPoints;
    int experience;
    int childLevel;
    int childID;
    Parent childOf;
    Teacher studentOf;

    public Child(String name, int id, Parent parent, Teacher teacher){
        childName = name;
        childPoints = 0;
        childLevel = 1;
        experience = 0;
        childID = id;
        childOf = parent;
        studentOf = teacher;
    }

    void requestWish(String wishType) {}
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
        }for (Task task: Main.RejectedTasks) {
            if (task == null) {
                System.out.println("there is no rejected task");
            }
            System.out.println("Rej: " + task.taskTitle);
        }
    }
    void listWishes() {}
}
