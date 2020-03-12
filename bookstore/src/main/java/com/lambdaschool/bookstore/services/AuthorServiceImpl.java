package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
  @Autowired
  private AuthorRepository authorrepos;

  @Override
  public ArrayList<Author> findAll(Pageable pageable)
  {
    ArrayList<Author> list = new ArrayList<>();
    authorrepos.findAll(pageable).iterator().forEachRemaining(list::add);
    return list;
  }
}
