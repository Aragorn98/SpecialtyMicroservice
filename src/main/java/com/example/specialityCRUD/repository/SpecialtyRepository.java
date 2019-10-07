package com.example.specialityCRUD.repository;


import com.example.specialityCRUD.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query(
            value = "SELECT * FROM specialty u WHERE u.name like CONCAT('%',?1,'%')",
            nativeQuery = true)
    ArrayList<Specialty> findSpecialtyByName(String name);
}