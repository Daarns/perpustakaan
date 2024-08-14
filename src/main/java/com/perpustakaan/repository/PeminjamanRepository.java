package com.perpustakaan.repository;

import com.perpustakaan.model.Peminjaman;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    List<Peminjaman> findByMahasiswaIdAndTanggalPeminjamanBetween(
            @Param("mahasiswaId") Long mahasiswaId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
