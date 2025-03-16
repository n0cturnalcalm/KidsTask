import java.time.LocalDateTime;
public class Task {
    static int idCounter = 0;
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

    public Task(String title, String description, boolean type, LocalDateTime date_time, int reward, int experience, int rating, int status,User givenBy){
        if (type == false) {
            taskId = idCounter;
            taskTitle = title;
            taskDescription = description;
            taskType = type;
            taskDateTime = date_time;
            this.reward = reward;
            this.experience = experience;
            this.rating = rating;
            this.status = status;
            this.givenBy = givenBy;
            idCounter++;
        }else if (type == true){
            taskId = idCounter;
            taskTitle = title;
            taskDescription = description;
            taskType = type;
            taskDateTime = date_time;
            this.reward = reward;
            this.experience = experience;
            this.rating = rating;
            this.status = status;
            this.givenBy = givenBy;
            idCounter++;
        }
    }

    void getPoints() {}
    void getRewards() {}
}
