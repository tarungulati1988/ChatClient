package com.example.chatclient.init;

import static com.example.chatclient.constant.DataContstants.YYYY_MM_DD_T;
import com.example.chatclient.dal.MessageDal;
import com.example.chatclient.dal.UserDal;
import com.example.chatclient.entity.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat(YYYY_MM_DD_T);
  @Autowired
  private UserDal userDal;
  @Autowired
  private MessageDal messageDal;

//  @Autowired
//  public DataInit(UserDal userDal) {
//    this.userDal = userDal;
//  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Long count = userDal.count();
    if (count == 0) {
      User user1 = new User();
      user1.firstname = "John";
      user1.lastname = "Wick";
      user1.username = "johnwick";

      User user2 = new User();
      user2.firstname = "Gayle";
      user2.lastname = "Lackman";
      user2.username = "glackman";

      userDal.save(user1);
      userDal.save(user2);
    }
  }

}
