package lab.model;

import lombok.Getter;
import lombok.Setter;

public class Galaxy {
    @Getter
    @Setter
    private String name;

    @Getter
    private Message lastReceivedMessage;

    public Galaxy(String name) {
        this.name = name;
    }

    public void receiveMessage(Message message) {
        lastReceivedMessage = message;
    }
}
