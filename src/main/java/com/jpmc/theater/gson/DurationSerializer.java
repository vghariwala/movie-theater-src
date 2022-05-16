package com.jpmc.theater.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jpmc.theater.util.CommonUtil;

import java.lang.reflect.Type;
import java.time.Duration;

/**
 * A {@link JsonSerializer} instance to serialize {@link Duration}
 */
public class DurationSerializer implements JsonSerializer<Duration> {

    @Override public JsonElement serialize(Duration duration, Type type,
		    JsonSerializationContext jsonSerializationContext) {
	return new JsonPrimitive(CommonUtil.humanReadableFormat(duration));
    }
}
