public class Teacher extends User{
    String teacherName;

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
    void giveExtraPoints(Child child) {}
    void addTask() {}
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
    void rejectTask(Task task) {
        if (task.givenBy instanceof Teacher){
            Main.RejectedTasks.add(task);
            Main.CompletedTasks.remove(task);
            task.status = -1;

            task.rating = 0;
        }else {
            System.out.println("You can't reject this task");
        }
    }
    void rateTask() {}
}
