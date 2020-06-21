-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 21, 2020 at 07:12 AM
-- Server version: 5.0.67
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `stambuk` varchar(15) NOT NULL,
  `namaAnggota` varchar(50) NOT NULL,
  `tanggalLahir` varchar(20) NOT NULL,
  `jenisKelamin` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `kelas` varchar(2) NOT NULL,
  PRIMARY KEY  (`stambuk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`stambuk`, `namaAnggota`, `tanggalLahir`, `jenisKelamin`, `alamat`, `kelas`) VALUES
('13020180122', 'Amnah', '23-02-2000', 'Perempuan', 'BTP', 'B2'),
('13020180125', 'Yulianti', '12-05-2000', 'PEREMPUAN', 'Enrekang', 'B2');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `kodeBuku` varchar(5) NOT NULL,
  `judulBuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunTerbit` varchar(4) NOT NULL,
  `hargaBuku` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kodeBuku`, `judulBuku`, `pengarang`, `penerbit`, `tahunTerbit`, `hargaBuku`) VALUES
('KB001', 'Dunia Sophie', 'Among', 'Gramedia', '2014', 76000),
('KB002', 'KAO', 'Asas', 'Dewr', '2011', 35000),
('KB003', 'PBO', 'OOP', 'Gramedia', '2001', 40000);

-- --------------------------------------------------------

--
-- Table structure for table `detailpinjam`
--

CREATE TABLE IF NOT EXISTS `detailpinjam` (
  `noPeminjaman` varchar(5) NOT NULL,
  `kodeBuku` varchar(20) NOT NULL,
  `jumlahPinjam` int(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailpinjam`
--


-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `konfirmasipassword` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`nama`, `username`, `password`, `konfirmasipassword`) VALUES
('Amnah Kurnia', 'amnah', '12345', '12345'),
('aslam', 'aaa', '123456', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE IF NOT EXISTS `peminjaman` (
  `noPeminjaman` varchar(5) NOT NULL,
  `stambuk` varchar(15) NOT NULL,
  `namaAnggota` varchar(50) NOT NULL,
  `tanggalPinjam` date NOT NULL,
  `totalPinjam` varchar(3) NOT NULL,
  PRIMARY KEY  (`noPeminjaman`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`noPeminjaman`, `stambuk`, `namaAnggota`, `tanggalPinjam`, `totalPinjam`) VALUES
('PNJ01', '13020180122', 'Amnah', '2020-06-21', '3'),
('PNJ02', '13020180125', 'Yulianti', '2020-06-21', '2');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE IF NOT EXISTS `pengembalian` (
  `noPengembalian` varchar(5) NOT NULL,
  `stambuk` varchar(15) NOT NULL,
  `tanggalPinjam` date NOT NULL,
  `tanggalKembali` date NOT NULL,
  `noPeminjaman` varchar(5) NOT NULL,
  `keterlambatan` int(5) NOT NULL,
  `denda` int(15) NOT NULL,
  PRIMARY KEY  (`noPengembalian`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`noPengembalian`, `stambuk`, `tanggalPinjam`, `tanggalKembali`, `noPeminjaman`, `keterlambatan`, `denda`) VALUES
('PNG01', '13020180122', '2020-06-21', '2020-06-24', 'PNJ01', 3, 0),
('PNG02', '13020180125', '2020-06-21', '2020-06-27', 'PNJ02', 6, 2000);
