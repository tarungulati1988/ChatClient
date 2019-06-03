package com.example.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHelper {

  public static <T> T getData(String fileName, Class<T> clazz) throws IOException {
    Path path = Paths.get("testData/" + fileName);
    try {
      return new ObjectMapper().readValue(path.toFile(), clazz);
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

}
