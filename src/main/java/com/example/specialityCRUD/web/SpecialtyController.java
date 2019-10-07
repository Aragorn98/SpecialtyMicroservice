package com.example.specialityCRUD.web;


import com.example.specialityCRUD.Specialty;
import com.example.specialityCRUD.repository.PaginationDao;
import com.example.specialityCRUD.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//end::baseClass[]
//tag::baseClass[]

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    private SpecialtyRepository specialtyRepository;
    private PaginationDao paginationDao;


    @Autowired
    public SpecialtyController(SpecialtyRepository specialtyRepository, PaginationDao paginationDao) {
        this.specialtyRepository = specialtyRepository;
        this.paginationDao = paginationDao;
    }

    @GetMapping("/ps")
    public Iterable<Specialty> getAllSpecialties(@RequestParam String orderBy, @RequestParam String direction,
                                                 @RequestParam int page, @RequestParam int size) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, direction, page, size);


    }

    @GetMapping("/psOrderBy")
    public Iterable<Specialty> getAllSpecialties(@RequestParam String orderBy) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, "ASC",0,5);
    }

    @GetMapping
    public Iterable<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @GetMapping("/find")
    public Iterable<Specialty> findSpecialties(@RequestParam String searchText) {
        return specialtyRepository.findSpecialtyByName(searchText);
    }

    @PostMapping("/specialty/add")
    public Specialty saveSpecialty(@RequestBody @Valid Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @PutMapping("/specialty/update/{id}")
    public void updateSpecialty(@PathVariable long id, @RequestBody Specialty specialty) {
        if (specialty.getId() != id) {
            throw new IllegalStateException("Given specialty's ID doesn't match the ID in the path.");
        }
        specialtyRepository.save(specialty);
    }

    @DeleteMapping("/specialty/delete/{id}")
    public void deleteSpecialty(@PathVariable long id) {
        specialtyRepository.deleteById(id);
    }

}