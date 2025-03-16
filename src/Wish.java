import java.time.LocalDateTime;
public class Wish {
    static int idCounter = 0;
    int wishId;
    String wishTitle;
    String wishDescription;
    boolean wishType; // false -> product wish  | true -> activity wish
    LocalDateTime wishDateTime;
    int price;
    int levelRestriction;
    int status;// 0 requested, 1 approved, -1 rejected

    public Wish(String title, String description, boolean type, LocalDateTime date_time, int price, int levelRestriction){
        if (type == false) {
            wishId = idCounter;
            wishTitle = title;
            wishDescription = description;
            wishType = type;
            wishDateTime = date_time;
            this.price = price;
            this.levelRestriction = levelRestriction;
            this.status = 0;
            idCounter++;
        }else if (type == true){
            wishId = idCounter;
            wishTitle = title;
            wishDescription = description;
            wishType = type;
            wishDateTime = date_time;
            this.price = price;
            this.levelRestriction = levelRestriction;
            this.status = 0;
            idCounter++;
        }
    }

    void takePrice() {}
}
