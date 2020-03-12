package com.lambdaschool.bookstore.repositories;

import com.lambdaschool.bookstore.models.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>
{
}
