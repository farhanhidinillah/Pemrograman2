import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class AplikasiAntrianPasienSwing {
    private AntrianPasien antrianPasien = new AntrianPasien();
    
    private JFrame frame;
    private JTextField namaField;
    private JTextField usiaField;
    private JComboBox<String> kondisiComboBox;
    private JTable tabelPasien;
    private DefaultTableModel tableModel;
    private JLabel infoLabel;

    public AplikasiAntrianPasienSwing() {
        frame = new JFrame("Manajemen Antrian Pasien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel Judul
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Manajemen Antrian Pasien");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        
        // Panel Form Input
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Tambah Pasien"));
        
        formPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        formPanel.add(namaField);
        
        formPanel.add(new JLabel("Usia:"));
        usiaField = new JTextField();
        formPanel.add(usiaField);
        
        formPanel.add(new JLabel("Kondisi:"));
        kondisiComboBox = new JComboBox<>(new String[]{"Normal", "Darurat", "Kritis"});
        formPanel.add(kondisiComboBox);
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton tambahButton = new JButton("Tambah Pasien");
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTambahPasien();
            }
        });
        
        JButton panggilButton = new JButton("Panggil Pasien Berikutnya");
        panggilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePanggilPasien();
            }
        });
        
        JButton urutUsiaButton = new JButton("Urutkan Berdasarkan Usia");
        urutUsiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUrutkanUsia();
            }
        });
        
        JButton urutPrioritasButton = new JButton("Urutkan Berdasarkan Prioritas");
        urutPrioritasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUrutkanPrioritas();
            }
        });
        
        buttonPanel.add(tambahButton);
        buttonPanel.add(panggilButton);
        buttonPanel.add(urutUsiaButton);
        buttonPanel.add(urutPrioritasButton);
        
        // Panel Informasi
        infoLabel = new JLabel(" ");
        infoLabel.setForeground(Color.RED);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Panel Tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Antrian Pasien"));
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Usia");
        tableModel.addColumn("Kondisi");
        tableModel.addColumn("Prioritas");
        
        tabelPasien = new JTable(tableModel);
        tabelPasien.setPreferredScrollableViewportSize(new Dimension(750, 300));
        tabelPasien.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(tabelPasien);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Menyusun komponen utama
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(infoLabel, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    
    private void handleTambahPasien() {
        try {
            String nama = namaField.getText();
            int usia = Integer.parseInt(usiaField.getText());
            String kondisi = (String) kondisiComboBox.getSelectedItem();

            if (nama.isEmpty()) {
                infoLabel.setText("Nama tidak boleh kosong!");
                return;
            }

            Pasien pasienBaru = new Pasien(nama, usia, kondisi);
            antrianPasien.tambahPasien(pasienBaru);
            refreshTabel();

            namaField.setText("");
            usiaField.setText("");
            infoLabel.setText("Pasien " + nama + " telah ditambahkan ke antrian.");
        } catch (NumberFormatException e) {
            infoLabel.setText("Usia harus berupa angka!");
        }
    }

    private void handlePanggilPasien() {
        Pasien pasien = antrianPasien.panggilPasienBerikutnya();
        if (pasien != null) {
            infoLabel.setText("Memanggil pasien: " + pasien.getNama());
            refreshTabel();
        } else {
            infoLabel.setText("Tidak ada pasien dalam antrian.");
        }
    }

    private void handleUrutkanUsia() {
        antrianPasien.urutkanBerdasarkanUsia();
        refreshTabel();
        infoLabel.setText("Antrian telah diurutkan berdasarkan usia (tertua ke termuda).");
    }

    private void handleUrutkanPrioritas() {
        antrianPasien.urutkanBerdasarkanPrioritas();
        refreshTabel();
        infoLabel.setText("Antrian telah diurutkan berdasarkan prioritas.");
    }

    private void refreshTabel() {
        tableModel.setRowCount(0); // Clear table
        
        for (Pasien pasien : antrianPasien.getDaftarPasien()) {
            tableModel.addRow(new Object[]{
                pasien.getNama(),
                pasien.getUsia(),
                pasien.getKondisi(),
                pasien.getPrioritas()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplikasiAntrianPasienSwing();
            }
        });
    }
}

class Pasien {
    private String nama;
    private int usia;
    private String kondisi;
    private String prioritas;

    public Pasien(String nama, int usia, String kondisi) {
        this.nama = nama;
        this.usia = usia;
        this.kondisi = kondisi;
        this.prioritas = hitungPrioritas(usia, kondisi);
    }

    private String hitungPrioritas(int usia, String kondisi) {
        if (kondisi.equalsIgnoreCase("kritis")) {
            return "Tinggi";
        } else if (usia > 60 || kondisi.equalsIgnoreCase("darurat")) {
            return "Sedang";
        } else {
            return "Rendah";
        }
    }

    public String getNama() {
        return nama;
    }

    public int getUsia() {
        return usia;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getPrioritas() {
        return prioritas;
    }

    @Override
    public String toString() {
        return nama + " (" + usia + " tahun) - " + kondisi + " - Prioritas: " + prioritas;
    }
}

class AntrianPasien {
    private Queue<Pasien> antrian;
    private Queue<Pasien> antrianPrioritas;

    public AntrianPasien() {
        antrian = new LinkedList<>();
        antrianPrioritas = new LinkedList<>();
    }

    public void tambahPasien(Pasien pasien) {
        if (pasien.getPrioritas().equals("Tinggi") || pasien.getPrioritas().equals("Sedang")) {
            antrianPrioritas.add(pasien);
        } else {
            antrian.add(pasien);
        }
    }

    public Pasien panggilPasienBerikutnya() {
        if (!antrianPrioritas.isEmpty()) {
            return antrianPrioritas.poll();
        } else if (!antrian.isEmpty()) {
            return antrian.poll();
        }
        return null;
    }

    public List<Pasien> getDaftarPasien() {
        List<Pasien> semuaPasien = new ArrayList<>();
        semuaPasien.addAll(antrianPrioritas);
        semuaPasien.addAll(antrian);
        return semuaPasien;
    }

    public void urutkanBerdasarkanUsia() {
        List<Pasien> semuaPasien = getDaftarPasien();
        Collections.sort(semuaPasien, Comparator.comparingInt(Pasien::getUsia).reversed());
        
        antrianPrioritas.clear();
        antrian.clear();
        
        for (Pasien pasien : semuaPasien) {
            tambahPasien(pasien);
        }
    }

    public void urutkanBerdasarkanPrioritas() {
        List<Pasien> semuaPasien = getDaftarPasien();
        Collections.sort(semuaPasien, (p1, p2) -> {
            int prioritas1 = getNilaiPrioritas(p1.getPrioritas());
            int prioritas2 = getNilaiPrioritas(p2.getPrioritas());
            return Integer.compare(prioritas2, prioritas1);
        });
        
        antrianPrioritas.clear();
        antrian.clear();
        
        for (Pasien pasien : semuaPasien) {
            tambahPasien(pasien);
        }
    }

    private int getNilaiPrioritas(String prioritas) {
        switch (prioritas) {
            case "Tinggi": return 3;
            case "Sedang": return 2;
            case "Rendah": return 1;
            default: return 0;
        }
    }

    public int getJumlahPasien() {
        return antrian.size() + antrianPrioritas.size();
    }
}