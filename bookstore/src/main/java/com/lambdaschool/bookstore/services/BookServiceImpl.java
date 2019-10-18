package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceFoundException;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repositories.AuthorRepository;
import com.lambdaschool.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
  @Autowired
  private BookRepository bookrepos;

  @Autowired
  private AuthorRepository authorrepos;

  @Override
  public ArrayList<Book> findAll()
  {
    ArrayList<Book> list = new ArrayList<>();
    bookrepos.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Transactional
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

  @Transactional
  @Override
  public void addBookToAuthor(long bookid, long authorid)
  {
    bookrepos.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book id " + bookid + " not found"));
    authorrepos.findById(authorid).orElseThrow(() -> new ResourceNotFoundException("Author id " + authorid + " not found"));

    if (bookrepos.checkWroteCombo(bookid, authorid)
        .getCount() <= 0)
    {
      bookrepos.insertWrote(bookid, authorid);
    } else
    {
      throw new ResourceFoundException("Book and Author Combination Already Exists");
    }
  }

  @Transactional
  @Override
  public void delete(long id)
  {
    bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found"));

    bookrepos.deleteById(id);
  }
}
