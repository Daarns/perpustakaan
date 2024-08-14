package com.perpustakaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.perpustakaan.model.Mahasiswa;
import com.perpustakaan.repository.MahasiswaRepository;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @GetMapping
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    @PostMapping
    public Mahasiswa createMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

}
