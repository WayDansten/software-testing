package lab.model;

import java.util.List;
import java.util.Optional;

import lab.model.exception.InsufficientParticipantsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Creature {
    private String name;
    private float hostility;

    public Creature(String name, float hostility) {
        this.name = name;
        this.hostility = hostility;
    }

    public Optional<War> rollInitiative(List<Creature> enemies) {
        float totalHostility = hostility;
        for (Creature enemy: enemies) {
            totalHostility = totalHostility + enemy.getHostility();
        }

        if (totalHostility >= 10) {
            War war = new War();

            war.addParticipant(this);
            for (Creature enemy : enemies) {
                war.addParticipant(enemy);
            }

            try {
                war.start();
                return Optional.of(war);
            } catch (InsufficientParticipantsException e) {
                System.out.println(e.getMessage());
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}
