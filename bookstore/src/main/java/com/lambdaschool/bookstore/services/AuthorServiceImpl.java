package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
  @Autowired
  private AuthorRepository authorrepos;


}
