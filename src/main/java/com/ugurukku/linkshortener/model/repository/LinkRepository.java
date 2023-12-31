package com.ugurukku.linkshortener.model.repository;

import com.ugurukku.linkshortener.model.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link,Integer> {

    @Query("select l from links l where l.shortLink = :shortLink and l.isActive = true")
    Optional<Link> findByShortLink(@Param("shortLink") String shortLink);

}