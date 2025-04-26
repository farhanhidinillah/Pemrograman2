import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class InputData extends JFrame {
    JTextField tfNIM, tfNama, tfKelas, tfJurusan;
    JButton btnSimpan;

    public InputData() {
        setTitle("Input Data Mahasiswa");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNIM = new JLabel("NIM:");
        lblNIM.setBounds(20, 20, 100, 25);
        add(lblNIM);

        tfNIM = new JTextField();
        tfNIM.setBounds(120, 20, 180, 25);
        add(tfNIM);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(20, 60, 100, 25);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(120, 60, 180, 25);
        add(tfNama);

        JLabel lblKelas = new JLabel("Kelas:");
        lblKelas.setBounds(20, 100, 100, 25);
        add(lblKelas);

        tfKelas = new JTextField();
        tfKelas.setBounds(120, 100, 180, 25);
        add(tfKelas);

        JLabel lblJurusan = new JLabel("Jurusan:");
        lblJurusan.setBounds(20, 140, 100, 25);
        add(lblJurusan);

        tfJurusan = new JTextField();
        tfJurusan.setBounds(120, 140, 180, 25);
        add(tfJurusan);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(120, 180, 100, 30);
        add(btnSimpan);

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        setVisible(true);
    }

    private void simpanData() {
        try {
            Connection conn = koneksi.getKoneksi();
            String sql = "INSERT INTO mahasiswa (nim, nama, kelas, jurusan) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tfNIM.getText());
            pst.setString(2, tfNama.getText());
            pst.setString(3, tfKelas.getText());
            pst.setString(4, tfJurusan.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            tfNIM.setText("");
            tfNama.setText("");
            tfKelas.setText("");
            tfJurusan.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new InputData();
    }
}
