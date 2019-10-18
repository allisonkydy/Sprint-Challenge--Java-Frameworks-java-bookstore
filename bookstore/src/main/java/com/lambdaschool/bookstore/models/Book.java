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

  private int copy;

  @ManyToOne
  @JoinColumn(name = "sectionid")
  @JsonIgnoreProperties("books")
  private Section section;

  @ManyToMany
  @JoinTable(name = "wrote",
             joinColumns = {@JoinColumn(name = "bookid")},
             inverseJoinColumns = {@JoinColumn(name = "authorid")})
  @JsonIgnoreProperties("books")
  private List<Author> authors = new ArrayList<>();

  public Book()
  {
  }

  public Book(String booktitle, String ISBN, int copy)
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

  public int getCopy()
  {
    return copy;
  }

  public void setCopy(int copy)
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

  public List<Author> getAuthors()
  {
    return authors;
  }

  public void setAuthors(List<Author> authors)
  {
    this.authors = authors;
  }

  @Override
  public String toString()
  {
    return "Book{" + "bookid=" + bookid + ", booktitle='" + booktitle + '\'' + ", ISBN='" + ISBN + '\'' + ", copy=" + copy + ", section=" + section + ", authors=" + authors + '}';
  }
}
