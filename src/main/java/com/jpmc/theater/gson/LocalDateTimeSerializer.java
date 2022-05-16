package com.jpmc.theater.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * A {@link JsonSerializer} instance to serialize {@link LocalDateTime}
 */
public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    @Override public JsonElement serialize(LocalDateTime localDateTime, Type type,
		    JsonSerializationContext jsonSerializationContext) {
	return new JsonPrimitive(localDateTime.toString());
    }
}
