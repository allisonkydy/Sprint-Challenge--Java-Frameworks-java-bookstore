package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController
{
  @Autowired
  private BookService bookService;

  // GET /books/books - returns a JSON object list of all the books, their sections, and their authors.
  @GetMapping(value = "/books/books",
              produces = {"application/json"})
  public ResponseEntity<?> listAllBooks()
  {
    return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
  }

  // PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN) but does NOT have to assign authors to the books.
  @PutMapping(value = "/data/books/{id}",
              consumes = {"application/json"})
  public ResponseEntity<?> updateBookInfo(@Valid
                                          @RequestBody
                                              Book book,
                                          @PathVariable
                                              long id)
  {
    bookService.update(book, id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // POST /data/books/{bookid}/authors/{authorid} - assigns a book already in the system (bookid) to an author already in the system (authorid)
  @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
  public ResponseEntity<?> addBookToAuthor(@PathVariable long bookid,
                                           @PathVariable long authorid)
  {
    bookService.addBookToAuthor(bookid, authorid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // DELETE /data/books/{id} - deletes a book and the book author combinations - but does not delete the author records.
  @DeleteMapping(value = "/data/books/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable long id)
  {
    bookService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
