package lab.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Message say(String message) {
        System.out.println(message);
        return new Message(message);
    }
}
