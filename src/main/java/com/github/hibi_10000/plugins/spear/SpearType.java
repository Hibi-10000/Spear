package com.github.hibi_10000.plugins.spear;

import com.github.hibi_10000.plugins.spear.type.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum SpearType {
    REGULAR  (  "regular_spear",           "Spear", Material.STICK    , Regular  .class),
    FIRE     (     "fire_spear",      "Fire Spear", Material.BLAZE_ROD, Fire     .class),
    EXPLOSIVE("explosive_spear", "Explosive Spear", Material.STICK    , Explosive.class),
    ZEUS     (     "zeus_spear",      "Zeus Spear", Material.BLAZE_ROD, Zeus     .class),
    TELEPORT ( "teleport_spear",  "Teleport Spear", Material.STICK    , Teleport .class),
    MOB      (      "mob_spear",       "Mob Spear", Material.BONE     , Mob      .class),
    ;

    private final String name;
    private final String displayName;
    private final Material material;
    private final Class<? extends Spear> spearClass;
    private Spear spear;

    SpearType(String name, String displayName, Material material, Class<? extends Spear> spearClass) {
        this.name = name;
        this.displayName = displayName;
        this.material = material;
        this.spearClass = spearClass;
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

    public Spear getSpear() {
        return spear;
    }

    public static SpearType fromName(String name) {
        for (SpearType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    public static SpearType fromNameIgnoreCase(String name) {
        for (SpearType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static List<String> names() {
        List<String> list = new ArrayList<>();
        for (SpearType type : values()) {
            list.add(type.getName());
        }
        return list;
    }

    public static void init(Main instance) {
        for (SpearType type : values()) {
            try {
                type.spear = type.spearClass.getConstructor(Main.class).newInstance(instance);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
