import javax.swing.*;
import java.awt.event.*;

public class pertemuan5 extends JFrame {
    JTextField tfNIM, tfNama, tfNilai;
    JButton btnTambah;

    public pertemuan5() {
        setTitle("Input Mahasiswa");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNIM = new JLabel("NIM:");
        lblNIM.setBounds(30, 30, 100, 20);
        add(lblNIM);

        tfNIM = new JTextField();
        tfNIM.setBounds(150, 30, 200, 25);
        add(tfNIM);

        JLabel lblNama = new JLabel("Nama Mahasiswa:");
        lblNama.setBounds(30, 70, 120, 20);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(150, 70, 200, 25);
        add(tfNama);

        JLabel lblNilai = new JLabel("Nilai:");
        lblNilai.setBounds(30, 110, 100, 20);
        add(lblNilai);

        tfNilai = new JTextField();
        tfNilai.setBounds(150, 110, 200, 25);
        add(tfNilai);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(100, 160, 180, 30);
        add(btnTambah);

        // Aksi tombol tambah
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = tfNIM.getText();
                String nama = tfNama.getText();
                String nilai = tfNilai.getText();

                // Validasi sederhana
                if (nim.isEmpty() || nama.isEmpty() || nilai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Data Berhasil Ditambahkan:\n" +
                        "NIM: " + nim + "\nNama: " + nama + "\nNilai: " + nilai);
                    
                    // Kosongkan field
                    tfNIM.setText("");
                    tfNama.setText("");
                    tfNilai.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        new pertemuan5().setVisible(true);
    }
}
