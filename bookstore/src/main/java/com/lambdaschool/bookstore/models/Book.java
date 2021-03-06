package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long bookid;

  @Column(nullable = false)
  private String booktitle;

  @Column(nullable = false,
          unique = true)
  private String ISBN;

  private Integer copy;

  @ManyToOne
  @JoinColumn(name = "sectionid")
  @JsonIgnoreProperties("books")
  private Section section;

  @OneToMany(mappedBy = "book",
             cascade = CascadeType.ALL)
  @JsonIgnoreProperties("book")
  private List<Wrote> wrote = new ArrayList<>();

  public Book()
  {
  }

  public Book(String booktitle, String ISBN, Integer copy)
  {
    this.booktitle = booktitle;
    this.ISBN = ISBN;
    this.copy = copy;
  }

  public long getBookid()
  {
    return bookid;
  }

  public void setBookid(long bookid)
  {
    this.bookid = bookid;
  }

  public String getBooktitle()
  {
    return booktitle;
  }

  public void setBooktitle(String booktitle)
  {
    this.booktitle = booktitle;
  }

  public String getISBN()
  {
    return ISBN;
  }

  public void setISBN(String ISBN)
  {
    this.ISBN = ISBN;
  }

  public Integer getCopy()
  {
    return copy;
  }

  public void setCopy(Integer copy)
  {
    this.copy = copy;
  }

  public Section getSection()
  {
    return section;
  }

  public void setSection(Section section)
  {
    this.section = section;
  }

  public List<Wrote> getWrote()
  {
    return wrote;
  }

  public void setWrote(List<Wrote> wrote)
  {
    this.wrote = wrote;
  }

  @Override
  public String toString()
  {
    return "Book{" + "bookid=" + bookid + ", booktitle='" + booktitle + '\'' + ", ISBN='" + ISBN + '\'' + ", copy=" + copy + ", section=" + section + ", wrote=" + wrote + '}';
  }
}
