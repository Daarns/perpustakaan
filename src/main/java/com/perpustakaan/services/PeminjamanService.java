package com.perpustakaan.services;

import com.perpustakaan.model.Peminjaman;
import com.perpustakaan.model.Buku;
import com.perpustakaan.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perpustakaan.repository.PeminjamanRepository;
import com.perpustakaan.repository.BukuRepository;
import com.perpustakaan.repository.MahasiswaRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    public Peminjaman pinjamBuku(Long mahasiswaId, Long bukuId) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(mahasiswaId)
                .orElseThrow(() -> new RuntimeException("Mahasiswa Tidak Ditemukan"));

        Buku buku = bukuRepository.findById(bukuId).orElseThrow(() -> new RuntimeException("Buku tidak Ditemukan"));

        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        List<Peminjaman> peminjamanBulanIni = peminjamanRepository
                .findByMahasiswaIdAndTanggalPeminjamanBetween(mahasiswaId, startOfMonth, endOfMonth);

        if (peminjamanBulanIni.size() >= 10) {
            throw new RuntimeException("Mahasiswa ini sudah mencapai batas maksimal peminjaman bulan ini (10 buku).");
        }
        if (buku.getJumlah() <= 0) {
            throw new RuntimeException("Buku tidak Tersedia");
        }

        Peminjaman peminjaman = new Peminjaman();
        peminjaman.setMahasiswa(mahasiswa);
        peminjaman.setBuku(buku);
        peminjaman.setTanggal_peminjaman(LocalDate.now());
        peminjaman.setTanggal_batas_pengembalian(LocalDate.now().plusDays(30));

        buku.setJumlah(buku.getJumlah() - 1);
        bukuRepository.save(buku);

        return peminjamanRepository.save(peminjaman);
    }

    public Peminjaman kembalikanBuku(Long peminjamanId) {
        Peminjaman peminjaman = peminjamanRepository.findById(peminjamanId)
                .orElseThrow(() -> new RuntimeException("Peminjaman Tidak Ditemukan"));

        LocalDate now = LocalDate.now();
        LocalDate batasPengembalian = peminjaman.getTanggal_batas_pengembalian();
        long daysLate = ChronoUnit.DAYS.between(batasPengembalian, now);

        int denda = 0;
        if (daysLate > 0) {
            if (daysLate <= 2) {
                denda = (int) daysLate * 1000;
            } else {
                int additionalDays = (int) daysLate - 2;
                int additionalFine = ((additionalDays + 1) / 2) * 1000;
                denda = 2000 + additionalFine;
            }
        }

        peminjaman.setTanggal_pengembalian(now);
        peminjaman.setDenda(denda);

        Buku buku = peminjaman.getBuku();
        buku.setJumlah(buku.getJumlah() + 1);
        bukuRepository.save(buku);

        return peminjamanRepository.save(peminjaman);
    }

}
