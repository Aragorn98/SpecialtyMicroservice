package com.example.specialityCRUD.repository;

import com.example.specialityCRUD.Specialty;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaginationDao extends PagingAndSortingRepository<Specialty, Integer> {

}