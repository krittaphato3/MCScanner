package org.krittaphato3.mcping.model;

import com.dslplatform.json.CompiledJson;
import lombok.Value;

import java.util.List;

@Value
@CompiledJson
public class Players {
    int max;
    int online;
    List<Player> sample;
}