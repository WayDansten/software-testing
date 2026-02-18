package lab.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Galaxy {
    private String name;

    public Galaxy(String name) {
        this.name = name;
    }

    public void receiveMessage(Message message) {
        System.out.println(message.getContent());
    }
}
