package lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import lab.model.Creature;
import lab.model.DimensionalWeave;
import lab.model.Galaxy;
import lab.model.Message;
import lab.model.Person;
import lab.model.War;
import lab.model.Wormhole;
import lab.model.exception.InsufficientParticipantsException;
import lab.model.exception.WormholeClosedException;
import lab.model.exception.WormholeStatusException;

class DomainModelTest {
    @Test
    void personTest() {
        List<String> messages = List.of("Hello", "", "    message  ");
        String personName = "John Doe";
        Person person = new Person(personName);

        assertEquals(personName, person.getName());

        for (String message : messages) {
            Message saidMessage = person.say(message);
            assertEquals(message, saidMessage.getContent());
        }
    }

    @Test
    void dimensionalWeaveOpenWormholeTest() {
        DimensionalWeave weave = DimensionalWeave.getInstance();
        Wormhole wormhole = weave.openWormhole();
        assertNotNull(wormhole);
        assertTrue(weave.getWormholes().contains(wormhole));
    }

    @Test
    void dimensionalWeaveCloseWormholeTest() {
        DimensionalWeave weave = DimensionalWeave.getInstance();
        Wormhole wormhole = weave.openWormhole();
        weave.closeWormhole(wormhole);
        assertNotNull(wormhole);
        assertFalse(weave.getWormholes().contains(wormhole));
    }

    @Test
    void wormholeStatusTest() {
        Wormhole wormhole = new Wormhole();

        assertThrows(WormholeStatusException.class, wormhole::open);

        try {
            wormhole.close();
        } catch (WormholeStatusException e) {
            System.out.println(e.getMessage());
        }

        assertThrows(WormholeStatusException.class, wormhole::close);
    }

    @Test
    void wormholeSendMessageToGalaxyTest() {
        String messageContent = "Test message";

        Wormhole wormhole = new Wormhole();
        Message message = new Message(messageContent);

        Galaxy galaxy = new Galaxy("Zyzyx");

        try {
            wormhole.sendMessage(message, galaxy);
        } catch (WormholeClosedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(messageContent, galaxy.getLastReceivedMessage().getContent());
    }

    @Test
    void wormholeSendMessageErrorTest() {
        String messageContent = "Test message";

        Wormhole wormhole = new Wormhole();
        Message message = new Message(messageContent);

        Galaxy galaxy = new Galaxy("Zyzyx");

        try {
            wormhole.close();
        } catch (WormholeStatusException e) {
            System.out.println(e.getMessage());
        }

        assertThrows(WormholeClosedException.class, () -> wormhole.sendMessage(message, galaxy));
    }

    @Test
    void creatureWarStartTest() {
        Creature creature1 = new Creature("Creature 1", 5);
        Creature creature2 = new Creature("Creature 2", 10);

        try {
            Optional<War> warOptional = creature1.rollInitiative(List.of(creature2));
            assertFalse(warOptional.isEmpty());

            War war = warOptional.get();
            assertTrue(war.isOngoing());
        } catch (InsufficientParticipantsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void creatureWarStartFailTest() {
        Creature creature1 = new Creature("Creature 1", 5);
        Creature creature2 = new Creature("Creature 2", 4);

        try {
            Optional<War> warOptional = creature1.rollInitiative(List.of(creature2));
            assertTrue(warOptional.isEmpty());
        } catch (InsufficientParticipantsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void warInsufficientParticipantsTest() {
        Creature creature1 = new Creature("Creature 1", 14);

        assertThrows(InsufficientParticipantsException.class, () -> creature1.rollInitiative(List.of()));
    }
}
