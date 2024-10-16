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
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(2, List.of(
            new Artifact("Axe", ArtifactType.WEAPON, 10, 0, 0),
            new Artifact("Chainmail", ArtifactType.ARMOR, 0, 10, 0),
            new Artifact("Horned Helmet", ArtifactType.HELM, 0, 0, 10)
        ));
        //Level 3
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(3, List.of(
            new Artifact("Greatsword", ArtifactType.WEAPON, 15, 0, 0),
            new Artifact("Plate Armor", ArtifactType.ARMOR, 0, 15, 0),
            new Artifact("Full Helm", ArtifactType.HELM, 0, 0, 15)
        ));
        //Level 4
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(4, List.of(
            new Artifact("Excalibur", ArtifactType.WEAPON, 20, 0, 0),
            new Artifact("Dragon Armor", ArtifactType.ARMOR, 0, 20, 0),
            new Artifact("Dragon Helm", ArtifactType.HELM, 0, 0, 20)
        ));
        //Level 5
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(5, List.of(
            new Artifact("Mjolnir", ArtifactType.WEAPON, 25, 0, 0),
            new Artifact("God Armor", ArtifactType.ARMOR, 0, 25, 0),
            new Artifact("God Helm", ArtifactType.HELM, 0, 0, 25)
        ));
        //Level 6
        artifactsMapByLevel.get(HeroClass.WARRIOR).put(6, List.of(
            new Artifact("Sword of the Gods", ArtifactType.WEAPON, 30, 0, 0),
            new Artifact("Armor of the Gods", ArtifactType.ARMOR, 0, 30, 0),
            new Artifact("Helm of the Gods", ArtifactType.HELM, 0, 0, 30)
        ));

    //ROGUE
        //Level 1
        artifactsMapByLevel.get(HeroClass.ROGUE).put(1, List.of(
            new Artifact("Dagger", ArtifactType.WEAPON, 5, 0, 0),
            new Artifact("Cloak", ArtifactType.ARMOR, 0, 5, 0),
            new Artifact("Hood", ArtifactType.HELM, 0, 0, 5)
        ));
        //Level 2
        artifactsMapByLevel.get(HeroClass.ROGUE).put(2, List.of(
            new Artifact("Shortsword", ArtifactType.WEAPON, 10, 0, 0),
            new Artifact("Leather Armor", ArtifactType.ARMOR, 0, 10, 0),
            new Artifact("Leather Hood", ArtifactType.HELM, 0, 0, 10)
        ));
        //Level 3
        artifactsMapByLevel.get(HeroClass.ROGUE).put(3, List.of(
            new Artifact("Rapier", ArtifactType.WEAPON, 15, 0, 0),
            new Artifact("Chainmail", ArtifactType.ARMOR, 0, 15, 0),
            new Artifact("Horned Helmet", ArtifactType.HELM, 0, 0, 15)
        ));
        //Level 4
        artifactsMapByLevel.get(HeroClass.ROGUE).put(4, List.of(
            new Artifact("Katana", ArtifactType.WEAPON, 20, 0, 0),
            new Artifact("Plate Armor", ArtifactType.ARMOR, 0, 20, 0),
            new Artifact("Full Helm", ArtifactType.HELM, 0, 0, 20)
        ));
        //Level 5
        artifactsMapByLevel.get(HeroClass.ROGUE).put(5, List.of(
            new Artifact("Kusarigama", ArtifactType.WEAPON, 25, 0, 0),
            new Artifact("Dragon Armor", ArtifactType.ARMOR, 0, 25, 0),
            new Artifact("Dragon Helm", ArtifactType.HELM, 0, 0, 25)
        ));
        //level 6
        artifactsMapByLevel.get(HeroClass.ROGUE).put(6, List.of(
            new Artifact("Ninjato", ArtifactType.WEAPON, 30, 0, 0),
            new Artifact("Armor of the Gods", ArtifactType.ARMOR, 0, 30, 0),
            new Artifact("Helm of the Gods", ArtifactType.HELM, 0, 0, 30)
        ));
    
    //PALADIN
        //Level 1
        artifactsMapByLevel.get(HeroClass.PALADIN).put(1, List.of(
            new Artifact("Mace", ArtifactType.WEAPON, 5, 0, 0),
            new Artifact("Plate", ArtifactType.ARMOR, 0, 5, 0),
            new Artifact("Helm", ArtifactType.HELM, 0, 0, 5)
        ));
        //Level 2
        artifactsMapByLevel.get(HeroClass.PALADIN).put(2, List.of(
            new Artifact("Morningstar", ArtifactType.WEAPON, 10, 0, 0),
            new Artifact("Chainmail", ArtifactType.ARMOR, 0, 10, 0),
            new Artifact("Horned Helmet", ArtifactType.HELM, 0, 0, 10)
        ));
        //Level 3
        artifactsMapByLevel.get(HeroClass.PALADIN).put(3, List.of(
            new Artifact("Warhammer", ArtifactType.WEAPON, 15, 0, 0),
            new Artifact("Plate Armor", ArtifactType.ARMOR, 0, 15, 0),
            new Artifact("Full Helm", ArtifactType.HELM, 0, 0, 15)
        ));
        //Level 4
        artifactsMapByLevel.get(HeroClass.PALADIN).put(4, List.of(
            new Artifact("Holy Avenger", ArtifactType.WEAPON, 20, 0, 0),
            new Artifact("Dragon Armor", ArtifactType.ARMOR, 0, 20, 0),
            new Artifact("Dragon Helm", ArtifactType.HELM, 0, 0, 20)
        ));
        //Level 5
        artifactsMapByLevel.get(HeroClass.PALADIN).put(5, List.of(
            new Artifact("Excalibur", ArtifactType.WEAPON, 25, 0, 0),
            new Artifact("God Armor", ArtifactType.ARMOR, 0, 25, 0),
            new Artifact("God Helm", ArtifactType.HELM, 0, 0, 25)
        ));
        //Level 6
        artifactsMapByLevel.get(HeroClass.PALADIN).put(6, List.of(
            new Artifact("Sword of the Gods", ArtifactType.WEAPON, 30, 0, 0),
            new Artifact("Armor of the Gods", ArtifactType.ARMOR, 0, 30, 0),
            new Artifact("Helm of the Gods", ArtifactType.HELM, 0, 0, 30)
        ));
    }

    public List<Artifact> getArtifacts(HeroClass heroClass, int level) {
        return artifactsMapByLevel.get(heroClass).get(level);
    }
}
