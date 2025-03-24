package com.zetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    /**
     * Skenario Unit Testing by @MFarhanZ1 // M. Farhan Aulia Pratama
     * List skenario pengujian yang dilakukan:
        - [TEST: 01] Menguji Movement Pacman Berhasil Bergerak ke Atas
        - [TEST: 02] Menguji Movement Pacman Berhasil Bergerak ke Bawah
        - [TEST: 03] Menguji Movement Pacman Berhasil Bergerak ke Kiri
        - [TEST: 04] Menguji Movement Pacman Berhasil Bergerak ke Kanan
        - [TEST: 05] Menguji apakah berhasil Pacman Nyawa-nya Berkurang ketika mati pertama kali
        - [TEST: 06] Menguji apakah game terhenti saat pacman mati dengan kondisi nyawa habis
        - [TEST: 07] Menguji apakah setelah semua dot dimakan, score berhasil bertambah
        - [TEST: 08] Menguji apakah setelah semua dot dimakan, lanjut ke level berikutnya ditandai dengan jumlah ghost bertambah 
     */

    private Board board;

    @BeforeEach
    public void setUp() {
        this.board = new Board();
    }

    @Test
    @DisplayName("[TEST: 04] Menguji Movement Pacman Berhasil Bergerak ke Kanan")
    public void testMovePacmanKeKanan() {
        System.out.println("[TEST: 04] Menguji Movement Pacman Berhasil Bergerak ke Kanan");

        // Menginisialisasi variabel dan memindahkan Pacman
        board.initVariables();
        board.initLevel();

        // Simulasikan pergerakan Pacman ke kanan
        board.setReq_dx(1);
        board.setReq_dy(0);
        board.movePacman();

        // Tambahkan assertion agar hasil tes dapat dicek
        assertEquals(7 * 24 + 6, board.getPacman_x(), "Koordinat x Pacman tidak bertambah 6, yang menandakan tidak gerak ke kanan");
        assertEquals(11 * 24, board.getPacman_y(), "Koordinat y Pacman tidak tetap, malah berubah");
    }

    @Test
    @DisplayName("[TEST: 03] Menguji Movement Pacman Berhasil Bergerak ke Kiri")
    public void testMovePacmanKeKiri() {
        System.out.println("[TEST: 03] Menguji Movement Pacman Berhasil Bergerak ke Kiri");

        // Menginisialisasi variabel dan memindahkan Pacman
        board.initVariables();
        board.initLevel();

        // Simulasikan pergerakan Pacman ke kiri
        board.setReq_dx(-1);
        board.setReq_dy(0);
        board.movePacman();

        // Tambahkan assertion agar hasil tes dapat dicek
        assertEquals(7 * 24 - 6, board.getPacman_x(), "Koordinat x Pacman tidak berkurang 6, yang menandakan tidak gerak ke kiri");
        assertEquals(11 * 24, board.getPacman_y(), "Koordinat y Pacman tidak tetap, malah berubah");
    }

    @Test
    @DisplayName("[TEST: 02] Menguji Movement Pacman Berhasil Bergerak ke Bawah")
    public void testMovePacmanKeBawah() {
        System.out.println("[TEST: 02] Menguji Movement Pacman Berhasil Bergerak ke Bawah");

        // Menginisialisasi variabel dan memindahkan Pacman
        board.initVariables();
        board.initLevel();

        // Simulasikan pergerakan Pacman ke bawah
        board.setReq_dx(0);
        board.setReq_dy(-1);
        board.movePacman();

        // Tambahkan assertion agar hasil tes dapat dicek
        assertEquals(7 * 24, board.getPacman_x(), "Koordinat x Pacman tidak tetap, malah berubah");
        assertEquals(11 * 24, board.getPacman_y(), "Koordinat y Pacman tidak berkurang 6, yang menandakan tidak gerak ke bawah");
    }

    @Test
    @DisplayName("[TEST: 01] Menguji Movement Pacman Berhasil Bergerak ke Atas")
    public void testMovePacmanKeAtas() {
        System.out.println("[TEST: 01] Menguji Movement Pacman Berhasil Bergerak ke Atas");

        // Menginisialisasi variabel dan memindahkan Pacman
        board.initVariables();
        board.initLevel();

        // Simulasikan pergerakan Pacman ke atas
        board.setReq_dx(0);
        board.setReq_dy(1);
        board.movePacman();

        // Tambahkan assertion agar hasil tes dapat dicek
        assertEquals(7 * 24, board.getPacman_x(), "Koordinat x Pacman tidak tetap, malah berubah");
        assertEquals(11 * 24 + 6, board.getPacman_y(), "Koordinat y Pacman tidak bertambah 6, yang menandakan tidak gerak ke atas");
    }

    @Test
    @DisplayName("[TEST: 06] Menguji apakah game terhenti saat pacman mati dengan kondisi nyawa habis")
    public void testDeath() {
        System.out.println("[TEST: 06] Menguji apakah game terhenti saat pacman mati dengan kondisi nyawa habis");

        // Menginisialisasi variabel dan mensimulasikan kematian Pacman dengan nyawa habis
        board.initVariables();
        board.initLevel();

        // Memeriksa apakah game berakhir ketika nyawa habis
        board.setPacsLeft(0);
        board.death();
        assertFalse(board.isInGame());
    }

    @Test
    @DisplayName("[TEST: 05] Menguji apakah berhasil Pacman Nyawa-nya Berkurang ketika mati pertama kali")
    public void testNyawaBerkurang() {
        System.out.println("[TEST: 05] Menguji apakah berhasil Pacman Nyawa-nya Berkurang ketika mati pertama kali");

        // Menginisialisasi variabel dan mensimulasikan kematian Pacman pertama kali dan nyawanya berkurang
        board.initVariables();
        board.initLevel();

        int initialPacsLeft = board.getPacsLeft();
        board.death();

        // Memeriksa apakah jumlah nyawa Pacman berkurang
        assertEquals(initialPacsLeft - 1, board.getPacsLeft());
    }

    @Test
    @DisplayName("[TEST: 07] Menguji apakah setelah semua dot dimakan, score berhasil bertambah")
    public void testCheckMaze() {
        System.out.println("[TEST: 07] Menguji apakah setelah semua dot dimakan, score berhasil bertambah");
        
        // Menginisialisasi variabel dan memeriksa maze
        board.initVariables();
        board.initLevel();

        // Simulasikan semua dot telah dimakan
        for (int i = 0; i < board.getScreenData().length; i++) {
            board.getScreenData()[i] = (short) (board.getScreenData()[i] & 15); // Hapus dot
        }

        // Panggil metode checkMaze
        board.checkMaze();

        // Memeriksa apakah score telah bertambah
        assertEquals(50, board.getScore());
    }

    @Test
    @DisplayName("[TEST: 08] Menguji apakah setelah semua dot dimakan, lanjut ke level berikutnya ditandai dengan jumlah ghost bertambah")
    public void testNextLevelGhosts() {
        System.out.println("[TEST: 08] Menguji apakah setelah semua dot dimakan, lanjut ke level berikutnya ditandai dengan jumlah ghost bertambah");

        // Menginisialisasi variabel dan memeriksa maze
        board.initVariables();
        board.initLevel();

        int initialN_GHOSTS = board.getN_GHOSTS(); // Jumlah ghost awal sebelum dimakan semua dot

        // Simulasikan semua dot telah dimakan
        for (int i = 0; i < board.getScreenData().length; i++) {
            board.getScreenData()[i] = (short) (board.getScreenData()[i] & 15); // Hapus dot
        }

        // Panggil metode checkMaze
        board.checkMaze();

        // Memeriksa apakah ghost berhasil bertambah saat semua dot dimakan
        assertEquals(initialN_GHOSTS + 1, board.getN_GHOSTS()); // Jumlah ghost bertambah
    }

}
