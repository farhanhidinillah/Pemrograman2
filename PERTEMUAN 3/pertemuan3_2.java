import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class pertemuan3_2 extends JFrame {

    JTable tabelPekerjaan;
    DefaultTableModel model;
    JButton btnPilih, btnTutup, btnTambah;
    JTextField tfKode, tfNama;
    JComboBox<String> cbJumlah;

    public pertemuan3_2() {
        initComponents();
    }

    private void initComponents() {
        // Panel utama
        JPanel panelUtama = new JPanel(null);

        // Panel data (hijau)
        JPanel panelData = new JPanel(null);
        panelData.setBackground(new java.awt.Color(153, 255, 204));
        panelData.setBounds(10, 10, 460, 300);

        JLabel labelJudul = new JLabel("Data Pekerjaan");
        labelJudul.setBounds(10, 10, 200, 20);
        panelData.add(labelJudul);

        // Input kode pekerjaan
        JLabel lblKode = new JLabel("Kode:");
        lblKode.setBounds(10, 40, 80, 20);
        panelData.add(lblKode);

        tfKode = new JTextField();
        tfKode.setBounds(100, 40, 150, 20);
        panelData.add(tfKode);

        // Input nama pekerjaan
        JLabel lblNama = new JLabel("Nama Pekerjaan:");
        lblNama.setBounds(10, 70, 100, 20);
        panelData.add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(120, 70, 150, 20);
        panelData.add(tfNama);

        // Input jumlah tugas
        JLabel lblJumlah = new JLabel("Jumlah Tugas:");
        lblJumlah.setBounds(10, 100, 100, 20);
        panelData.add(lblJumlah);

        cbJumlah = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            cbJumlah.addItem(String.valueOf(i));
        }
        cbJumlah.setBounds(120, 100, 100, 20);
        panelData.add(cbJumlah);

        // Tombol tambah
        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(290, 70, 100, 30);
        panelData.add(btnTambah);

        // Tabel kosong di awal
        String[] kolom = {"Kode", "Nama Pekerjaan", "Jumlah Tugas"};
        model = new DefaultTableModel(null, kolom);
        tabelPekerjaan = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelPekerjaan);
        scrollPane.setBounds(10, 140, 440, 150);
        panelData.add(scrollPane);

        // Panel tombol (merah muda)
        JPanel panelTombol = new JPanel(null);
        panelTombol.setBackground(new java.awt.Color(255, 204, 204));
        panelTombol.setBounds(10, 320, 460, 60);

        btnPilih = new JButton("Pilih");
        btnPilih.setBounds(120, 15, 100, 30);
        panelTombol.add(btnPilih);

        btnTutup = new JButton("Tutup");
        btnTutup.setBounds(240, 15, 100, 30);
        panelTombol.add(btnTutup);

        // Tambahkan panel ke panel utama
        panelUtama.add(panelData);
        panelUtama.add(panelTombol);

        // Frame konfigurasi
        setTitle("Data Pekerjaan");
        setSize(500, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        add(panelUtama);

        // Aksi tombol
        btnTutup.addActionListener(e -> dispose());

        btnTambah.addActionListener(e -> {
            String kode = tfKode.getText().trim();
            String nama = tfNama.getText().trim();
            String jumlah = (String) cbJumlah.getSelectedItem();

            if (!kode.isEmpty() && !nama.isEmpty()) {
                model.addRow(new Object[]{kode, nama, jumlah});
                tfKode.setText("");
                tfNama.setText("");
                cbJumlah.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Kode dan Nama tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new pertemuan3_2().setVisible(true);
        });
    }
}
