import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class pertemuan4 extends JFrame {

    JTextField tfNIM, tfNama, tfNilai;
    JButton btnTabel;
    JTable tabelMahasiswa;
    DefaultTableModel model;

    public pertemuan4() {
        initComponents();
    }

    private void initComponents() {
        // Frame config
        setTitle("Data Mahasiswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Label dan Text Field
        JLabel lblNIM = new JLabel("N I M");
        lblNIM.setBounds(30, 30, 100, 20);
        add(lblNIM);

        tfNIM = new JTextField();
        tfNIM.setBounds(150, 30, 200, 20);
        add(tfNIM);

        JLabel lblNama = new JLabel("Nama Mahasiswa");
        lblNama.setBounds(30, 60, 120, 20);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(150, 60, 200, 20);
        add(tfNama);

        JLabel lblNilai = new JLabel("Nilai");
        lblNilai.setBounds(30, 90, 100, 20);
        add(lblNilai);

        tfNilai = new JTextField();
        tfNilai.setBounds(150, 90, 200, 20);
        add(tfNilai);

        // Tombol
        btnTabel = new JButton("TABEL");
        btnTabel.setBounds(370, 30, 80, 30);
        add(btnTabel);

        // Tabel
        String[] kolom = {"NIM", "Nama Mahasiswa", "Nilai"};
        model = new DefaultTableModel(null, kolom);
        tabelMahasiswa = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabelMahasiswa);
        scrollPane.setBounds(30, 130, 420, 200);
        add(scrollPane);

        // Aksi tombol
        btnTabel.addActionListener(e -> {
            String nim = tfNIM.getText().trim();
            String nama = tfNama.getText().trim();
            String nilai = tfNilai.getText().trim();

            if (!nim.isEmpty() && !nama.isEmpty() && !nilai.isEmpty()) {
                model.addRow(new Object[]{nim, nama, nilai});
                tfNIM.setText("");
                tfNama.setText("");
                tfNilai.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new pertemuan4().setVisible(true);
        });
    }
}
