package com.lambdaschool.bookstore.repositories;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{

}
