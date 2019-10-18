package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.services.AuthorService;
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

  // GET /authors/authors - returns a JSON object list of all the authors, their books, and the book's section.
  @GetMapping(value = "/authors/authors",
              produces = {"application/json"})
  public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 3) Pageable pageable)
  {
    return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
  }
}
