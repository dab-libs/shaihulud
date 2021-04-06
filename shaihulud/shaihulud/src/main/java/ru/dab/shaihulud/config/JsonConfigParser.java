package ru.dab.shaihulud.config;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Reader;
import java.util.Map;

class JsonConfigParser implements ConfigParser {
  @Override
  public @NotNull Map<String, Object> parse(@NotNull Reader configReader)
      throws ConfigException {
    try {
      return new JSONObject(new JSONTokener(configReader)).toMap();
    }
    catch (JSONException e) {
      throw new ConfigException(e.getMessage(), e);
    }
  }

}
