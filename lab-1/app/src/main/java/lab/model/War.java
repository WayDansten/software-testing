package lab.model;

import java.util.ArrayList;
import java.util.List;

import lab.model.exception.InsufficientParticipantsException;
import lombok.Getter;

@Getter
public class War {
    private List<Creature> participants;
    private boolean isOngoing;

    public War() {
        this.participants = new ArrayList<>();
        this.isOngoing = false;
    }

    public void addParticipant(Creature creature) {
        participants.add(creature);
    }

    public void removeParticipant(Creature creature) {
        participants.remove(creature);
    }

    public void start() throws InsufficientParticipantsException {
        if (participants.size() < 2) {
            throw new InsufficientParticipantsException();
        }

        isOngoing = true;
    }

    public void end() {
        isOngoing = false;
        participants.clear();
    }
}
