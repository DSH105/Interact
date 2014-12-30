package com.dsh105.interact;

import com.dsh105.commodus.container.ItemStackContainer;
import com.dsh105.commodus.container.PlayerContainer;
import com.dsh105.interact.api.*;

import java.util.*;

public abstract class InteractInventory implements Inventory {

    private final Set<PlayerContainer> VIEWERS = new HashSet<>();

    private UUID id;
    private String name;
    private Layout layout;
    private Icon interactIcon;
    private ClickAction clickAction;
    private OpenAction openAction;

    protected InteractInventory(String name, Layout layout, Icon interactIcon, ClickAction clickAction, OpenAction openAction) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.layout = layout;
        this.interactIcon = interactIcon;
        this.clickAction = clickAction;
        this.openAction = openAction;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    @Override
    public Icon getInteractIcon() {
        return interactIcon;
    }

    @Override
    public ClickAction getClickAction() {
        return clickAction;
    }

    @Override
    public OpenAction getOpenAction() {
        return openAction;
    }

    @Override
    public void show(PlayerContainer player) {
        if (player.get() == null) {
            throw new IllegalArgumentException("Provided player is not currently available");
        }
        VIEWERS.add(player);

        if (openAction != null) {
            openAction.onOpen(this, player);
        }
    }

    @Override
    public void close(PlayerContainer player) {
        if (player.get() == null) {
            throw new IllegalArgumentException("Provided player is not currently available");
        }
        VIEWERS.remove(player);
    }

    @Override
    public Inventory.Builder builder() {
        return Interact.builder(this);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.replace(Interact.COLOR_CHAR, '&')); // TODO: color codes are being phased out
        map.put("layout", layout.serialize());
        map.put("interactIcon", interactIcon.serialize());
        
        // TODO: implement a way to save/load these
        map.put("action.click", clickAction.getId());
        map.put("action.open", openAction.getId());
        return map;
    }

    protected void handleClick(PlayerContainer player, int slot) {
        Icon icon = layout.get(slot);
        if (icon != null && clickAction != null) {
            clickAction.onClick(this, player, Interact.position().slot(slot).icon(icon).build());
        }
    }

    protected boolean handleInteract(PlayerContainer player, ItemStackContainer itemStack) {
        if (itemStack != null && this.interactIcon != null) {
            ItemStackContainer interactIcon = ItemStackContainer.of(this.interactIcon.getTypeId(), this.interactIcon.getQuantity());
            if (itemStack.isSimilar(interactIcon)) {
                show(player);
                return true;
            }
        }
        return false;
    }

}