package lab.model;

import lab.model.exception.WormholeClosedException;
import lab.model.exception.WormholeStatusException;
import lombok.Getter;

public class Wormhole {
    @Getter
    private boolean isOpen;

    public Wormhole() {
        this.isOpen = true;
    }

    public void open() throws WormholeStatusException {
        if (isOpen) {
            throw new WormholeStatusException(isOpen);
        }
        isOpen = true;
    }

    public void close() throws WormholeStatusException {
        if (!isOpen) {
            throw new WormholeStatusException(isOpen);
        }
        isOpen = false;
    }

    public void sendMessage(Message message, Galaxy galaxy) throws WormholeClosedException {
        if (!isOpen) {
            throw new WormholeClosedException();
        }

        galaxy.receiveMessage(message);
    }
}
