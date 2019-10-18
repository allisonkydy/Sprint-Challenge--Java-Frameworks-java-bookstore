package com.lambdaschool.bookstore.services;


import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Wrote;
import com.lambdaschool.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
  @Autowired
  private BookRepository bookrepos;

  @Override
  public ArrayList<Book> findAll()
  {
    ArrayList<Book> list = new ArrayList<>();
    bookrepos.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public Book update(Book book, long id)
  {
    Book currentBook = bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found"));

    if (book.getBooktitle() != null)
    {
      currentBook.setBooktitle(book.getBooktitle());
    }

    if (book.getISBN() != null)
    {
      currentBook.setISBN(book.getISBN());
    }

    if (book.getCopy() != null)
    {
      currentBook.setCopy(book.getCopy());
    }

    return bookrepos.save(currentBook);
  }

  @Override
  public void addBookToAuthor(long bookid, long authorid)
  {

  }

  @Override
  public void delete(long id)
  {
    bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found"));

    bookrepos.deleteById(id);
  }
}
