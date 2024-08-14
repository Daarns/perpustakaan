package com.perpustakaan.controller;

import com.perpustakaan.model.Buku;

import com.perpustakaan.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/buku")
public class BukuController {
    @Autowired
    private BukuRepository bukuRepository;

    @GetMapping
    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    @PostMapping
    public Buku createBuku(@RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

}