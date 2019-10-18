package com.lambdaschool.bookstore.services;


import com.lambdaschool.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
  @Autowired
  private BookRepository bookrepos;
}
