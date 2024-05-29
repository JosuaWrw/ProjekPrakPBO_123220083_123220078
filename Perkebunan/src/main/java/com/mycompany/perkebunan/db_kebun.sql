-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Bulan Mei 2024 pada 07.15
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kebun`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `alokasigudang`
--

CREATE TABLE `alokasigudang` (
  `id` double NOT NULL,
  `waktu_masuk` date NOT NULL,
  `jumlah_masuk_kg` double NOT NULL,
  `id_buah` double NOT NULL,
  `id_gudang` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `alokasigudang`
--

INSERT INTO `alokasigudang` (`id`, `waktu_masuk`, `jumlah_masuk_kg`, `id_buah`, `id_gudang`) VALUES
(1, '2025-11-17', 100, 2, 2),
(4, '2022-12-13', 500, 4, 3),
(5, '2024-11-18', 50, 1, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `buah`
--

CREATE TABLE `buah` (
  `id_buah` double NOT NULL,
  `nama_buah` varchar(50) NOT NULL,
  `berat_buah` double NOT NULL,
  `tgl_tanam` date NOT NULL,
  `tgl_panen` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `buah`
--

INSERT INTO `buah` (`id_buah`, `nama_buah`, `berat_buah`, `tgl_tanam`, `tgl_panen`) VALUES
(1, 'Pisang', 120, '2024-05-08', '2024-05-16'),
(2, 'mangga', 120, '2024-05-16', '2024-11-16'),
(4, 'ubi mangga', 1000, '0022-12-12', '0022-12-12'),
(6, 'anggur', 150, '0024-12-03', '0024-12-04');

-- --------------------------------------------------------

--
-- Struktur dari tabel `gudang`
--

CREATE TABLE `gudang` (
  `id_gudang` double NOT NULL,
  `nama_gudang` varchar(50) NOT NULL,
  `kapasitas` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `gudang`
--

INSERT INTO `gudang` (`id_gudang`, `nama_gudang`, `kapasitas`) VALUES
(1, 'madiun semarang', 1000),
(2, 'semarang', 1300),
(3, 'klambi', 10000),
(4, 'jogja', 1500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `nama_user` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`nama_user`, `username`, `password`) VALUES
('budi', 'budi', 'budi'),
('ayam', 'ayam', 'ayam'),
('qwe', 'qwe', 'qwe'),
('qwe', 'qwe', 'qwe'),
('qwe', 'qwe', 'qwe'),
('josua', 'jw', '010504'),
('josua', 'jw', '010504');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `alokasigudang`
--
ALTER TABLE `alokasigudang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hubungan_buah` (`id_gudang`),
  ADD KEY `hubungan_gudang` (`id_buah`);

--
-- Indeks untuk tabel `buah`
--
ALTER TABLE `buah`
  ADD PRIMARY KEY (`id_buah`);

--
-- Indeks untuk tabel `gudang`
--
ALTER TABLE `gudang`
  ADD PRIMARY KEY (`id_gudang`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `alokasigudang`
--
ALTER TABLE `alokasigudang`
  MODIFY `id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `buah`
--
ALTER TABLE `buah`
  MODIFY `id_buah` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `gudang`
--
ALTER TABLE `gudang`
  MODIFY `id_gudang` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `alokasigudang`
--
ALTER TABLE `alokasigudang`
  ADD CONSTRAINT `hubungan_buah` FOREIGN KEY (`id_gudang`) REFERENCES `gudang` (`id_gudang`),
  ADD CONSTRAINT `hubungan_gudang` FOREIGN KEY (`id_buah`) REFERENCES `buah` (`id_buah`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
