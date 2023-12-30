package com.ugurukku.linkshortener.model.repository;

import com.ugurukku.linkshortener.model.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Integer> {

}