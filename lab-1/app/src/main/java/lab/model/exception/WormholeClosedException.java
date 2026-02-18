package lab.model.exception;

public class WormholeClosedException extends Exception {
    public WormholeClosedException() {
        super("Cannot send an object through a closed wormhole");
    }
}
