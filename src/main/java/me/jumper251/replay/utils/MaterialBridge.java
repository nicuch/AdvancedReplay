package me.jumper251.replay.utils;

import org.bukkit.Material;

import me.jumper251.replay.utils.VersionUtil.VersionEnum;

public enum MaterialBridge {

    WATCH("CLOCK"),
    WOOD_DOOR("WOODEN_DOOR");

    private String materialName;

    MaterialBridge(String materialName) {
        this.materialName = materialName;
    }

    public Material toMaterial() {
        return Material.valueOf(this.toString());

    }

    public String getMaterialName() {
        return materialName;
    }

    @SuppressWarnings("deprecation")
    public static Material fromID(int id) {
        if (VersionUtil.isCompatible(VersionEnum.V1_13) || VersionUtil.isCompatible(VersionEnum.V1_14) || VersionUtil.isCompatible(VersionEnum.V1_15)) {
            for (Material mat : Material.values()) {
                if (mat.getId() == id) return mat;
            }

            return null;

        }
        return null; //This is so wrong... sorry buddy :(
    }
}
