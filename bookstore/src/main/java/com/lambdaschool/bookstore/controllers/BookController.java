package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController
{
  @Autowired
  private BookService bookService;

  @ApiOperation(value = "list all books",
                response = Book.class,
                responseContainer = "List")
  @ApiImplicitParams({
                         @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                           value = "Results page you want to retrieve (1..N)"),
                         @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                           value = "Number of records per page."),
                         @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                           value = "Sorting criteria in the format: property(,asc|desc). " +
                                               "Default sort order is ascending. " +
                                               "Multiple sort criteria are supported.")})
  // GET /books/books - returns a JSON object list of all the books, their sections, and their authors.
  @GetMapping(value = "/books/books",
              produces = {"application/json"})
  public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 3)
                                              Pageable pageable)
  {
    return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
  }

  @ApiOperation(value = "update book info using book id")
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Book Updated"), @ApiResponse(code = 404,
                                                                                 message = "Book Not Found",
                                                                                 response = ErrorDetail.class)})
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

  @ApiOperation(value = "add an existing book to an existing author using ids")
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Book Added to Author"), @ApiResponse(code = 404,
                                                                              message = "Book/Author Not Found",
                                                                              response = ErrorDetail.class)})
  // POST /data/books/{bookid}/authors/{authorid} - assigns a book already in the system (bookid) to an author already in the system (authorid)
  @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
  public ResponseEntity<?> addBookToAuthor(@PathVariable long bookid,
                                           @PathVariable long authorid)
  {
    bookService.addBookToAuthor(bookid, authorid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ApiOperation(value = "delete a book using book id")
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Book Deleted"), @ApiResponse(code = 404,
                                                                              message = "Book Not Found",
                                                                              response = ErrorDetail.class)})
  // DELETE /data/books/{id} - deletes a book and the book author combinations - but does not delete the author records.
  @DeleteMapping(value = "/data/books/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable long id)
  {
    bookService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
