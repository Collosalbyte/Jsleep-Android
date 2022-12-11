package WinstonJSleep.JS.model;

public class Invoice extends Serializable{

    public int buyerId;

    public int renterId;


    public enum RoomRating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }


    public enum PaymentStatus{
        FAILED,
        WAITING,
        SUCCESS;
    }


    public PaymentStatus status = PaymentStatus.WAITING;

    public RoomRating rating = RoomRating.NONE;
}
