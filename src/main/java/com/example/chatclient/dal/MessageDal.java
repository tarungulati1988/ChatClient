package com.example.chatclient.dal;

import com.example.chatclient.entity.Message;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDal extends CrudRepository<Message, Long> {
  //  List<Message> findByReceiver(@Param("receiver") String receiver);
  List<Message> findByReceiverAndAndExpirationDateAfter(@Param("receiver") String receiver, @Param("expirationDate") Date expirationDate);

//  Message findById(@Param("id") Long id);
}
