package com.example.demo01.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo01.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
    @Query("SELECT * FROM USER WHERE search_name = :search_name")
    Optional<User> findBySerchName(@Param("search_name") String search_name);

    @Query("SELECT user_id FROM USER ORDER BY user_id desc limit 1")
    String findUserIdMax();

    @Query("SELECT mail FROM USER WHERE mail = :mail")
    Optional<String> findMail(@Param("mail") String mail);

    @Query("SELECT search_name FROM USER WHERE search_name = :search_name")
    Optional<String> findSearchName(@Param("search_name") String search_name);

    @Modifying
    @Query("INSERT INTO user VALUES(:user_id, :user_name, :pass, :mail, :search_name)")
    void insertUser(
        @Param("user_id") String user_id,
        @Param("user_name") String user_name,
        @Param("pass") String pass,
        @Param("mail") String mail,
        @Param("search_name") String search_name
    );
}
