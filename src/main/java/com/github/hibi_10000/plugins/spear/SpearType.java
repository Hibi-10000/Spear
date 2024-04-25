package com.github.hibi_10000.plugins.spear;

import org.bukkit.Material;

public enum SpearType {
    REGULAR  (  "regular_spear",           "Spear", Material.STICK    ),
    FIRE     (     "fire_spear",      "Fire Spear", Material.BLAZE_ROD),
    EXPLOSIVE("explosive_spear", "Explosive Spear", Material.STICK    ),
    ZEUS     (     "zeus_spear",      "Zeus Spear", Material.BLAZE_ROD),
    TELEPORT ( "teleport_spear",  "Teleport Spear", Material.STICK    ),
    MOB      (      "mob_spear",       "Mob Spear", Material.BONE     ),
    ;

    private final String name;
    private final String displayName;
    private final Material material;

    SpearType(String name, String displayName, Material material) {
        this.name = name;
        this.displayName = displayName;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }
}
