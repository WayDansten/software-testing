package lab.model.exception;

public class WormholeStatusException extends Exception {
    public WormholeStatusException(boolean wormholeStatus) {
        super(String.format("Cannot set wormhole 'isOpened' status to %b when it's already %b",
                wormholeStatus,
                wormholeStatus));
    }
}
