package com.codenjoy.blog.repository;

import com.codenjoy.blog.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

    List<Log> findAllByPage(String page);

    void deleteAllByPage(String page);
}