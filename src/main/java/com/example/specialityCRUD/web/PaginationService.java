package com.example.specialityCRUD.web;


import com.example.specialityCRUD.Specialty;
import com.example.specialityCRUD.repository.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class PaginationService {
    private PaginationDao paginationDao;
    @Autowired
    public PaginationService(PaginationDao paginationDao){
        this.paginationDao = paginationDao;
    }

    public Iterable<Specialty> findJsonDataByCondition(String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        if (direction.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specialty> data = paginationDao.findAll(pageable);
        ArrayList<Specialty> list = new ArrayList<>();
        for (Iterator<Specialty> it = data.iterator(); it.hasNext(); ) {
            Specialty specialty = it.next();
            list.add(specialty);
        }
        return list;
    }
}