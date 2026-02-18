package lab.model.exception;

public class InsufficientParticipantsException extends Exception {
    public InsufficientParticipantsException() {
        super("Cannot start a war that has less than 2 participants");
    }
}
