import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Form Input Data Karyawan - Java Swing Application
 * Dibuat untuk GitHub Codespaces
 */
public class Main extends JFrame {
    private JTextField txtNIK;
    private JTextField txtNama;
    private JTextArea txtAlamat;
    private JTextField txtTempatLahir;
    private JSpinner spinnerTanggalLahir;
    private JComboBox<String> comboJenisKelamin;
    private JTextField txtTelepon;
    private JComboBox<String> comboStatusPernikahan;
    private JTextField txtAlamat1;
    private JTextField txtAlamat2;
    private JButton btnSimpan;
    private JButton btnReset;
    private JTextArea txtOutput;

    public Main() {
        initComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void initComponents() {
        setTitle("Form Input Data Karyawan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        // Initialize components
        txtNIK = new JTextField(20);
        txtNama = new JTextField(20);
        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        
        txtTempatLahir = new JTextField(20);
        
        // Date spinner
        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        
        // Combo boxes
        comboJenisKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        comboStatusPernikahan = new JComboBox<>(new String[]{"Belum Menikah", "Menikah", "Cerai"});
        
        txtTelepon = new JTextField(20);
        txtAlamat1 = new JTextField(20);
        txtAlamat2 = new JTextField(20);
        
        btnSimpan = new JButton("Simpan");
        btnReset = new JButton("Reset");
        
        txtOutput = new JTextArea(10, 40);
        txtOutput.setEditable(false);
        txtOutput.setBackground(Color.LIGHT_GRAY);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // NIK
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("NIK:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNIK, gbc);
        
        // Nama
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNama, gbc);
        
        // Alamat
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JScrollPane(txtAlamat), gbc);
        
        // Tempat Lahir
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Tempat Lahir:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtTempatLahir, gbc);
        
        // Tanggal Lahir
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Tanggal Lahir:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(spinnerTanggalLahir, gbc);
        
        // Jenis Kelamin
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(comboJenisKelamin, gbc);
        
        // Telepon
        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Telepon:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtTelepon, gbc);
        
        // Status Pernikahan
        gbc.gridx = 0; gbc.gridy = 7;
        mainPanel.add(new JLabel("Status Pernikahan:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(comboStatusPernikahan, gbc);
        
        // Alamat 1
        gbc.gridx = 0; gbc.gridy = 8;
        mainPanel.add(new JLabel("Alamat 1:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtAlamat1, gbc);
        
        // Alamat 2
        gbc.gridx = 0; gbc.gridy = 9;
        mainPanel.add(new JLabel("Alamat 2:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtAlamat2, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnReset);
        
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);
    }

    private void setupEventHandlers() {
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }
    
    private void simpanData() {
        // Validasi input
        if (txtNIK.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIK dan Nama harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Format tanggal
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
        
        // Tampilkan data di output area
        StringBuilder output = new StringBuilder();
        output.append("=== DATA KARYAWAN ===\n");
        output.append("NIK: ").append(txtNIK.getText()).append("\n");
        output.append("Nama: ").append(txtNama.getText()).append("\n");
        output.append("Alamat: ").append(txtAlamat.getText()).append("\n");
        output.append("Tempat Lahir: ").append(txtTempatLahir.getText()).append("\n");
        output.append("Tanggal Lahir: ").append(sdf.format(tanggalLahir)).append("\n");
        output.append("Jenis Kelamin: ").append(comboJenisKelamin.getSelectedItem()).append("\n");
        output.append("Telepon: ").append(txtTelepon.getText()).append("\n");
        output.append("Status Pernikahan: ").append(comboStatusPernikahan.getSelectedItem()).append("\n");
        output.append("Alamat 1: ").append(txtAlamat1.getText()).append("\n");
        output.append("Alamat 2: ").append(txtAlamat2.getText()).append("\n");
        output.append("========================\n\n");
        
        txtOutput.append(output.toString());
        
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void resetForm() {
        txtNIK.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtTempatLahir.setText("");
        spinnerTanggalLahir.setValue(new Date());
        comboJenisKelamin.setSelectedIndex(0);
        txtTelepon.setText("");
        comboStatusPernikahan.setSelectedIndex(0);
        txtAlamat1.setText("");
        txtAlamat2.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
