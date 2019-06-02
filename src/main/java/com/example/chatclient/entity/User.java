package com.example.chatclient.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "USER")
@DynamicUpdate
public class User implements Identifiable<Long> {

  @Id
  @GenericGenerator(
      name = "assigned-sequence",
      strategy = "com.example.chatclient.entity.AssignedSequenceGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "USER_SEQ")}
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assigned-sequence")
  public Long userid;

  @NotEmpty
  public String username;

  @NotEmpty
  public String firstname;

  @NotEmpty
  public String lastname;

  @CreationTimestamp
  @Column(updatable = false)
  public Date creationDate;

  @UpdateTimestamp
  public Date lastModificationDate;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Message> messages;

  @Override
  public Long getId() {
    return userid;
  }
}
