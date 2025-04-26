package latconsole;

import javax.swing.*;

public class pertemuan3 extends JFrame {

    public pertemuan3() {
        initComponents();
    }

    private void initComponents() {
        // Panel utama
        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(null);

        // Panel data pekerjaan (hijau)
        JPanel panelData = new JPanel();
        panelData.setLayout(null);
        panelData.setBackground(new java.awt.Color(153, 255, 204));
        panelData.setBounds(10, 10, 370, 150);

        JLabel labelJudul = new JLabel("Data Pekerjaan");
        labelJudul.setBounds(10, 10, 150, 20);
        panelData.add(labelJudul);

        JLabel labelKode = new JLabel("Kode Pekerjaan");
        labelKode.setBounds(10, 40, 100, 20);
        panelData.add(labelKode);

        JTextField txtKode = new JTextField();
        txtKode.setBounds(120, 40, 100, 20);
        panelData.add(txtKode);

        JButton btnLihat = new JButton("Lihat");
        btnLihat.setBounds(230, 40, 80, 20);
        panelData.add(btnLihat);

        JLabel labelNama = new JLabel("Nama Pekerjaan");
        labelNama.setBounds(10, 70, 100, 20);
        panelData.add(labelNama);

        JTextField txtNama = new JTextField();
        txtNama.setBounds(120, 70, 190, 20);
        panelData.add(txtNama);

        JLabel labelTugas = new JLabel("Jumlah Tugas");
        labelTugas.setBounds(10, 100, 100, 20);
        panelData.add(labelTugas);

        JComboBox<String> comboTugas = new JComboBox<>(new String[]{"Item 1", "Item 2", "Item 3"});
        comboTugas.setBounds(120, 100, 100, 20);
        panelData.add(comboTugas);

        // Panel tombol aksi (merah muda)
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(null);
        panelTombol.setBackground(new java.awt.Color(255, 204, 204));
        panelTombol.setBounds(10, 170, 370, 60);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(40, 15, 80, 30);
        panelTombol.add(btnSimpan);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(140, 15, 80, 30);
        panelTombol.add(btnHapus);

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setBounds(240, 15, 80, 30);
        panelTombol.add(btnTutup);

        // Tambahkan panel ke panel utama
        panelUtama.add(panelData);
        panelUtama.add(panelTombol);

        // Konfigurasi JFrame
        this.setTitle("Master Data Pekerjaan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(410, 280);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panelUtama);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new pertemuan3().setVisible(true);
        });
    }
}
