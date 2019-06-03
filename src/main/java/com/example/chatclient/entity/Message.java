package com.example.chatclient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "MESSAGE")
@DynamicUpdate
public class Message implements Identifiable<Long> {

  @Id
  @GenericGenerator(
      name = "assigned-sequence",
      strategy = "com.example.chatclient.entity.AssignedSequenceGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "MESSAGE_SEQ")}
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assigned-sequence")
  public Long id;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  @JsonBackReference
  public User user;

  @NotEmpty
  public String text;

  @NotEmpty
  public String receiver;

  @CreationTimestamp
  @Column(updatable = false)
  public Date creationDate;

  @UpdateTimestamp
  public Date lastModificationDate;

  public Date expirationDate;

  @Override
  public Long getId() {
    return id;
  }
}
