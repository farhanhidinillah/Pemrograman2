import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class pertemuan4_2 extends JFrame {
    JTextField tfMeja, tfTotalPesanan, tfPPN, tfBayar, tfKembali, tfPendapatan;
    JTextArea areaStruk;
    JButton btnStruk, btnBayar;
    JTable tabel;
    DefaultTableModel model;

    double totalPendapatan = 0;

    public pertemuan4_2() {
        setTitle("PEMBAYARAN");
        setSize(600, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblMeja = new JLabel("NO. MEJA");
        lblMeja.setBounds(30, 30, 100, 20);
        add(lblMeja);

        tfMeja = new JTextField();
        tfMeja.setBounds(130, 30, 100, 20);
        add(tfMeja);

        JLabel lblTotal = new JLabel("TOTAL PESANAN");
        lblTotal.setBounds(30, 60, 100, 20);
        add(lblTotal);

        tfTotalPesanan = new JTextField();
        tfTotalPesanan.setBounds(130, 60, 100, 20);
        add(tfTotalPesanan);

        JLabel lblPPN = new JLabel("PPN");
        lblPPN.setBounds(30, 90, 100, 20);
        add(lblPPN);

        tfPPN = new JTextField();
        tfPPN.setBounds(130, 90, 100, 20);
        add(tfPPN);

        JLabel lblBayar = new JLabel("UANG PEMBAYARAN");
        lblBayar.setBounds(30, 120, 120, 20);
        add(lblBayar);

        tfBayar = new JTextField();
        tfBayar.setBounds(150, 120, 100, 20);
        add(tfBayar);

        JLabel lblKembali = new JLabel("UANG KEMBALI");
        lblKembali.setBounds(30, 150, 100, 20);
        add(lblKembali);

        tfKembali = new JTextField();
        tfKembali.setBounds(130, 150, 100, 20);
        tfKembali.setEditable(false);
        add(tfKembali);

        areaStruk = new JTextArea();
        JScrollPane scrollStruk = new JScrollPane(areaStruk);
        scrollStruk.setBounds(250, 30, 200, 140);
        add(scrollStruk);

        btnStruk = new JButton("STRUK");
        btnStruk.setBounds(80, 180, 100, 30);
        add(btnStruk);

        btnBayar = new JButton("BAYAR");
        btnBayar.setBounds(250, 180, 100, 30);
        add(btnBayar);

        // TABEL
        model = new DefaultTableModel(new String[]{"No. Meja", "Total", "Bayar", "Kembali"}, 0);
        tabel = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(tabel);
        scrollTable.setBounds(30, 230, 520, 100);
        add(scrollTable);

        JLabel lblPendapatan = new JLabel("TOTAL PENDAPATAN");
        lblPendapatan.setBounds(370, 340, 150, 20);
        add(lblPendapatan);

        tfPendapatan = new JTextField();
        tfPendapatan.setBounds(500, 340, 100, 20);
        tfPendapatan.setEditable(false);
        add(tfPendapatan);

        // Action untuk tombol BAYAR
        btnBayar.addActionListener(e -> {
            try {
                String meja = tfMeja.getText().trim();
                double total = Double.parseDouble(tfTotalPesanan.getText());
                double ppn = Double.parseDouble(tfPPN.getText());
                double bayar = Double.parseDouble(tfBayar.getText());

                double totalAkhir = total + ppn;
                double kembali = bayar - totalAkhir;

                tfKembali.setText(String.format("%.2f", kembali));

                model.addRow(new Object[]{meja, totalAkhir, bayar, kembali});

                totalPendapatan += totalAkhir;
                tfPendapatan.setText(String.format("%.2f", totalPendapatan));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action untuk tombol STRUK
        btnStruk.addActionListener(e -> {
            String struk = """
                    ===== STRUK PEMBAYARAN =====
                    No Meja: %s
                    Total Pesanan: %s
                    PPN: %s
                    ----------------------------
                    Total Bayar: %s
                    Uang Pembayaran: %s
                    Uang Kembali: %s
                    ============================
                    """.formatted(
                    tfMeja.getText(),
                    tfTotalPesanan.getText(),
                    tfPPN.getText(),
                    Double.parseDouble(tfTotalPesanan.getText()) + Double.parseDouble(tfPPN.getText()),
                    tfBayar.getText(),
                    tfKembali.getText()
            );
            areaStruk.setText(struk);
        });
    }

    public static void main(String[] args) {
        new pertemuan4_2().setVisible(true);
    }
}
