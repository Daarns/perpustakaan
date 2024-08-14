package com.perpustakaan.controller;

import com.perpustakaan.model.Peminjaman;
import com.perpustakaan.services.PeminjamanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/peminjaman")
public class PeminjamanController {
    @Autowired
    private PeminjamanService peminjamanService;

    @PostMapping("/pinjam")
    public Peminjaman pinjamBuku(@RequestParam Long mahasiswaId, @RequestParam Long bukuId) {
        return peminjamanService.pinjamBuku(mahasiswaId, bukuId);
    }

    @PostMapping("/kembali")
    public Peminjaman kembalikanBuku(@RequestParam Long peminjamanId) {
        return peminjamanService.kembalikanBuku(peminjamanId);
    }

}
