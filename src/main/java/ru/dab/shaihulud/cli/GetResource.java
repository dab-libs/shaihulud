package ru.dab.shaihulud.cli;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GetResource {
  public static void main(String[] args) {
    final String name = args[0];
    final URL url =
        GetResource.class.getClassLoader().getResource(name);
    if (url != null) {
      System.out.println(url.toString());
    }
    else {
      System.out.println(name + " is not found");
      return;
    }
    InputStream inputStream =
        GetResource.class.getClassLoader().getResourceAsStream(name);
    if (inputStream == null) {
      System.out.println(name + " can not be opened");
      return;
    }
    try (
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Scanner scanner = new Scanner(reader).useDelimiter("\\Z")
    ) {
      System.out.println(scanner.next());
    }
    catch (Exception e) {
      System.out.println(name + " can not be read");
    }
  }
}
