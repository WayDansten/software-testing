package lab.model;

import java.util.ArrayList;
import java.util.List;

import lab.model.exception.WormholeStatusException;
import lombok.Getter;

public class DimensionalWeave {
    private static DimensionalWeave instance;

    @Getter
    private List<Wormhole> wormholes;

    private DimensionalWeave() {
        this.wormholes = new ArrayList<>();
    }

    public static DimensionalWeave getInstance() {
        if (instance == null) {
            instance = new DimensionalWeave();
        }
        
        return instance;
    }

    public Wormhole openWormhole() {
        Wormhole wormhole = new Wormhole();
        wormholes.add(wormhole);
        return wormhole;
    }

    public void closeWormhole(Wormhole wormhole) {
        try {
            wormhole.close();
            wormholes.remove(wormhole);
        } catch (WormholeStatusException e) {}
    }
}
