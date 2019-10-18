package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wrote",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"bookid", "authorid"})})
public class Wrote extends Auditable implements Serializable
{
  private static final long serialVersionUID = 3L;

  @Id
  @ManyToOne
  @JoinColumn(name = "bookid")
  @JsonIgnoreProperties("wrote")
  private Book book;

  @Id
  @ManyToOne
  @JoinColumn(name = "authorid")
  @JsonIgnoreProperties("wrote")
  private Author author;
}
