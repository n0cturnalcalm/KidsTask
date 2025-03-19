import java.time.LocalDateTime;
public class Task {
    int taskId;
    String taskTitle;
    String taskDescription;
    boolean taskType; // false -> mission task  | true -> event task
    LocalDateTime taskDateTime;
    int reward;
    int experience;
    int rating;
    int status; // 0 given, 1 completed, 2 approved, -1 rejected
    User givenBy;

    public Task(int id, String title, String description, boolean type, LocalDateTime date_time, int reward, int experience, int rating, int status,User givenBy){
        if (type == false) {
            taskId = id;
            taskTitle = title;
            taskDescription = description;
            taskType = type;
            taskDateTime = date_time;
            this.reward = reward;
            this.experience = experience;
            this.rating = rating;
            this.status = status;
            this.givenBy = givenBy;
        }else if (type == true){
            taskId = id;
            taskTitle = title;
            taskDescription = description;
            taskType = type;
            taskDateTime = date_time;
            this.reward = reward;
            this.experience = experience;
            this.rating = rating;
            this.status = status;
            this.givenBy = givenBy;
        }
    }

    void getPoints() {}
    void getRewards() {}

    public int getId() {
        return taskId;
    }
}
