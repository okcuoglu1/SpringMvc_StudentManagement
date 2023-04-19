package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);
    List<Student> findAll();
    //Student findById(Long id); //eğer aradıgımız idli ogrenci yoksa nullpointer exception atar.
    Optional<Student> findById(Long id); //null yerine boş bir optional objesi döndürür. Böylece hatadan kurtulmus oluruz.
    void delete(Long id);



}
