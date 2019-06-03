package com.example.chatclient;

import static com.example.chatclient.constant.DataContstants.PST;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatclientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatclientApplication.class, args);
  }

  @PostConstruct
  public void init() {
    // Setting Spring Boot SetTimeZone
    TimeZone.setDefault(TimeZone.getTimeZone(PST));
  }
}
