import java.time.LocalDateTime;
public class Wish {
    String wishId;
    String wishTitle;
    String wishDescription;
    boolean wishType; // false -> product wish  | true -> activity wish
    LocalDateTime wishDateTime = null;
    Integer price = null;
    Integer levelRestriction = null;
    int status;// 0 requested, 1 approved, -1 rejected

    public Wish(String id, String title, String description, boolean type, LocalDateTime date_time){
        if (type == false) {
            wishId = id;
            wishTitle = title;
            wishDescription = description;
            wishType = type;
            this.price = null;
            this.status = 0;
        }else if (type == true){
            wishId = id;
            wishTitle = title;
            wishDescription = description;
            wishType = type;
            wishDateTime = date_time;
            this.price = null;
            this.status = 0;
        }
    }

    void setPrice(int price) {
        this.price = price;
    }
}
