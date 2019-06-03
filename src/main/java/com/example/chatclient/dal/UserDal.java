package com.example.chatclient.dal;

import com.example.chatclient.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDal extends CrudRepository<User, Long> {
  User findByUsername(@Param("username") String username);
}
