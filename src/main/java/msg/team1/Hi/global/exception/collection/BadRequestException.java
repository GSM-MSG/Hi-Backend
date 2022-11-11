package msg.team1.Hi.global.exception.collection;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
