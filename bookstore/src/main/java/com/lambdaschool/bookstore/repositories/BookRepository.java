package com.lambdaschool.bookstore.repositories;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
  @Query(value = "SELECT COUNT(*) as count FROM wrote WHERE bookid = :bookid AND authorid = :authorid",
         nativeQuery = true)
  JustTheCount checkWroteCombo(long bookid,
                               long authorid);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO Wrote (bookid, authorid) VALUES (:bookid, :authorid)",
         nativeQuery = true)
  void insertWrote(long bookid,
                   long authorid);
}
