package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.services.AuthorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController
{
  @Autowired
  private AuthorService authorService;

  @ApiOperation(value = "list all books",
                response = Author.class,
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
  // GET /authors/authors - returns a JSON object list of all the authors, their books, and the book's section.
  @GetMapping(value = "/authors/authors",
              produces = {"application/json"})
  public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 3) Pageable pageable)
  {
    return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
  }
}
