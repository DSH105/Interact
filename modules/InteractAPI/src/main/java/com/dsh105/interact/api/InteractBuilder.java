package com.dsh105.interact.api;

import java.util.Map;

public interface InteractBuilder<B extends InteractBuilder<B>> {

    B from(Map<String, Object> args);
}