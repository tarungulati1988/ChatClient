package com.example.chatclient.entity;

import java.io.Serializable;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class AssignedSequenceGenerator extends SequenceStyleGenerator {

  public Serializable generate(SessionImplementor session, Object obj) {
    if (obj instanceof Identifiable) {
      Identifiable identifiable = (Identifiable) obj;
      Long id = (Long) identifiable.getId();
      if (id != null && id > 0) {
        return id;
      }
    }
    return super.generate(session, obj);
  }
}