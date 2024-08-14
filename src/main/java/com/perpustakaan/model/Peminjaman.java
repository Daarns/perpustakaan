package com.perpustakaan.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mahasiswa", referencedColumnName = "id")
    private Mahasiswa mahasiswa;

    @ManyToOne
    @JoinColumn(name = "id_buku", referencedColumnName = "id")
    private Buku buku;

    private LocalDate tanggal_peminjaman;

    private LocalDate tanggal_batas_pengembalian;

    private LocalDate tanggal_pengembalian;

    private double denda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public LocalDate getTanggal_peminjaman() {
        return tanggal_peminjaman;
    }

    public void setTanggal_peminjaman(LocalDate tanggal_peminjaman) {
        this.tanggal_peminjaman = tanggal_peminjaman;
    }

    public LocalDate getTanggal_batas_pengembalian() {
        return tanggal_batas_pengembalian;
    }

    public void setTanggal_batas_pengembalian(LocalDate tanggal_batas_pengembalian) {
        this.tanggal_batas_pengembalian = tanggal_batas_pengembalian;
    }

    public LocalDate getTanggal_pengembalian() {
        return tanggal_pengembalian;
    }

    public void setTanggal_pengembalian(LocalDate tanggal_pengembalian) {
        this.tanggal_pengembalian = tanggal_pengembalian;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }
}
