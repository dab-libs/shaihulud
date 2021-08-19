package ru.dab.shaihulud;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class JsonParseTests {
  @Test
  public void testNumber() {
    JSONObject obj = new JSONObject(new JSONTokener("{\"a\":1,\"b\":1.0}"));
    Map<String, Object> map = obj.toMap();
    Assertions.assertTrue(map.get("a") instanceof Integer);
    Assertions.assertTrue(map.get("b") instanceof BigDecimal);
  }
}
