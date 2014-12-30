package com.dsh105.interact.api;

public enum InteractType {
    INVENTORY(Inventory.Builder.class),;

    // TODO: implement these
    /*ANVIL,
    COMMAND_BLOCK,
    BOSS_BAR,
    WORKBENCH,
    EXPERIENCE,
    MAP,
    MERCHANT
    */

    private Class<? extends InteractBuilder> builderClass;

    InteractType(Class<? extends InteractBuilder> builderClass) {
        this.builderClass = builderClass;
    }

    protected Class<? extends InteractBuilder> getBuilderClass() {
        return builderClass;
    }

    protected InteractType getByClass(Class<? extends InteractBuilder> builderClass) {
        for (InteractType type : values()) {
            if (this.builderClass.equals(builderClass)) {
                return type;
            }
        }
        return null;
    }
}