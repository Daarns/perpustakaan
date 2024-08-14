package com.perpustakaan.repository;

import com.perpustakaan.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BukuRepository extends JpaRepository<Buku, Long> {
}
