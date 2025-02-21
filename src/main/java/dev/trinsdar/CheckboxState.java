package dev.trinsdar;

import net.minecraft.util.StringRepresentable;

public enum CheckboxState implements StringRepresentable {
    EMPTY,
    CHECK,
    X;

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
