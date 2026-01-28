package org.krittaphato3.mcping.model;

import com.dslplatform.json.CompiledJson;
import lombok.Value;

@Value
@CompiledJson
public class Version {
    String name;
    int protocol;
}