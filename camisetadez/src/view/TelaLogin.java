package view;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TelaLogin extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField passwordField;
    private JCheckBox mostrarSenhaCheckbox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin frame = new TelaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaLogin() {
        //carregar imagem
        JLabel background = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("imgTelaLogin.jpg"));
            ImageIcon icon = new ImageIcon(img);

            background.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setResizable(false);
        setTitle("Entrar - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 555, 570);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0,64,0));

        contentPane.setBorder(new LineBorder(new Color(255, 255, 255), 6));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Camiseta 10");
        lblTitulo.setBounds(113, 10, 315, 81);
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("David CLM", Font.BOLD, 62));
        contentPane.add(lblTitulo);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(255, 255, 255), 6, true));
        panel.setBackground(new Color(0, 128, 0));
        panel.setBounds(121, 143, 315, 319);
        contentPane.add(panel);
        panel.setLayout(null);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtUsuario.setToolTipText("Usuário");
        txtUsuario.setBounds(87, 68, 187, 37);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(87, 151, 167, 37);
        panel.add(passwordField);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setForeground(new Color(255, 255, 255));
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUsuario.setBounds(22, 81, 67, 13);
        panel.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSenha.setBounds(22, 161, 50, 13);
        panel.add(lblSenha);

        JCheckBox chckbxLembrarMe = new JCheckBox("Lembrar-me");
        chckbxLembrarMe.setForeground(new Color(255, 255, 255));
        chckbxLembrarMe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chckbxLembrarMe.setBackground(new Color(0, 128, 0));
        chckbxLembrarMe.setBounds(22, 210, 97, 21);
        panel.add(chckbxLembrarMe);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(0, 64, 0));
        btnEntrar.setForeground(new Color(255, 255, 255));
        btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEntrar.setBounds(32, 237, 242, 48);
        panel.add(btnEntrar);


        JButton btnCadastrar = new JButton("Cadastrar jogador");
        btnCadastrar.setForeground(new Color(0, 255, 255));
        btnCadastrar.setBackground(new Color(0, 128, 0));
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCadastrar.setBounds(125, 211, 149, 21);
        panel.add(btnCadastrar);

        JCheckBox mostrarSenhaCheckBox = new JCheckBox("Ver");
        mostrarSenhaCheckBox.setForeground(new Color(255, 255, 255));
        mostrarSenhaCheckBox.setBackground(new Color(0, 128, 0));
        mostrarSenhaCheckBox.setBounds(260, 159, 49, 21);
        panel.add(mostrarSenhaCheckBox);

        mostrarSenhaCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Quando a caixa de seleção for marcada, mostra a senha
                if (mostrarSenhaCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Define o caractere de ocultação para vazio
                } else {
                    // Quando a caixa de seleção for desmarcada, oculta a senha
                    passwordField.setEchoChar('•');
                }
            }
        });
        //abre a tela de cadastro
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.setVisible(true);
            }
        });
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioDigitado = txtUsuario.getText();
                char[] senhaChars = passwordField.getPassword();
                String senhaDigitada = new String(senhaChars);
                if (verificarCredenciais(usuarioDigitado, senhaDigitada)) {
                    HomeAB telaHome = new HomeAB();
                    telaHome.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(220, 89, 100, 44);
        contentPane.add(lblLogin);
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setFont(new Font("Gentium Basic", Font.PLAIN, 37));
    }

    private boolean verificarCredenciais(String usuario, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader("jogadores.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 2 && partes[0].equals(usuario) && partes[1].equals(senha)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // As credenciais não correspondem
        return false;
    }
}
