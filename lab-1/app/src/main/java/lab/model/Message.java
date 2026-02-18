package lab.model;

import lombok.Getter;

@Getter
public class Message {
    private final String content;

    public Message(String content) {
        this.content = content;
    }
}
