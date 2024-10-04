package com.jdasilva.Swingy.model.hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtifactManager {
    private Map<HeroClass, Map<Integer, List<Artifact>>> artifactsMapByLevel; 

    public ArtifactManager() {
        artifactsMapByLevel = new HashMap<>();
        initializeArtifacts();
    }
    
    private void initializeArtifacts() {
        artifactsMapByLevel.put(HeroClass.WARRIOR, new HashMap<>());
        artifactsMapByLevel.put(HeroClass.ROGUE, new HashMap<>());
        artifactsMapByLevel.put(HeroClass.PALADIN, new HashMap<>());

    //WARRIOR
        //Level 1
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(1, List.of(
            new Artifact("Sword", ArtifactType.WEAPON, 5, 0, 0),
            new Artifact("Shield", ArtifactType.ARMOR, 0, 5, 0),
            new Artifact("Helmet", ArtifactType.HELM, 0, 0, 5)
        ));
        //Level 2
        //Level 3
        //Level 4

    //ROGUE
        //Level 1
        artifactsMapByLevel.get(HeroClass.ROGUE).put(1, List.of(
            new Artifact("Dagger", ArtifactType.WEAPON, 5, 0, 0),
            new Artifact("Cloak", ArtifactType.ARMOR, 0, 5, 0),
            new Artifact("Hood", ArtifactType.HELM, 0, 0, 5)
        ));
        //Level 2
        //Level 3
        //Level 4
    
    //PALADIN
        //Level 1
        artifactsMapByLevel.get(HeroClass.PALADIN).put(1, List.of(
            new Artifact("Mace", ArtifactType.WEAPON, 5, 0, 0),
            new Artifact("Plate", ArtifactType.ARMOR, 0, 5, 0),
            new Artifact("Helm", ArtifactType.HELM, 0, 0, 5)
        ));
        //Level 2
        //Level 3
        //Level 4
    }

    public List<Artifact> getArtifacts(HeroClass heroClass, int level) {
        return artifactsMapByLevel.get(heroClass).get(level);
    }
}
