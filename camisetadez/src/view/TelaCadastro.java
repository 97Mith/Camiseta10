package view;

import java.awt.*;
import date.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class TelaCadastro extends JFrame {
    public String nome;

    public String nCamiseta;
    public String posicao;
    private JPanel contentPane;
    private JTextField jogadorNomeField;
    private JTextField nField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastro frame = new TelaCadastro();
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
    public TelaCadastro() {
        setResizable(false);
        setTitle("Cadastro de Jogadores");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 404, 519);
        //setAlwaysOnTop(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCadastroJog = new JLabel("Cadastro Jogador");
        lblCadastroJog.setForeground(new Color(0, 64, 0));
        lblCadastroJog.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblCadastroJog.setBounds(96, 28, 198, 27);
        contentPane.add(lblCadastroJog);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 64, 0), 4));
        panel.setBounds(10, 72, 370, 302);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNomeJog = new JLabel("Jogador");
        lblNomeJog.setForeground(new Color(0, 0, 0));
        lblNomeJog.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNomeJog.setBounds(28, 45, 65, 19);
        panel.add(lblNomeJog);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.BLACK);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblSenha.setBounds(35, 108, 51, 19);
        panel.add(lblSenha);

        JLabel lblCSenha = new JLabel("Conf. Senha");
        lblCSenha.setForeground(Color.BLACK);
        lblCSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCSenha.setBounds(15, 161, 91, 19);
        panel.add(lblCSenha);

        JLabel lblPosicao = new JLabel("Posição");
        lblPosicao.setForeground(Color.BLACK);
        lblPosicao.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPosicao.setBounds(152, 202, 65, 19);
        panel.add(lblPosicao);

        jogadorNomeField = new JTextField();
        jogadorNomeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jogadorNomeField.setBounds(114, 44, 114, 22);
        panel.add(jogadorNomeField);
        jogadorNomeField.setColumns(10);

        ButtonGroup grupoPosicao = new ButtonGroup();

        JRadioButton rdbtnGol = new JRadioButton("Goleiro");
        rdbtnGol.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnGol.setBounds(15, 248, 73, 21);
        panel.add(rdbtnGol);

        JRadioButton rdbtnZagueiro = new JRadioButton("Zagueiro");
        rdbtnZagueiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnZagueiro.setBounds(96, 248, 81, 21);
        panel.add(rdbtnZagueiro);

        JRadioButton rdbtnMeioC = new JRadioButton("Meio C");
        rdbtnMeioC.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnMeioC.setBounds(179, 248, 73, 21);
        panel.add(rdbtnMeioC);

        JRadioButton rdbtnAtacante = new JRadioButton("Atacante");
        rdbtnAtacante.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnAtacante.setBounds(262, 248, 78, 21);
        panel.add(rdbtnAtacante);

        grupoPosicao.add(rdbtnGol);
        grupoPosicao.add(rdbtnZagueiro);
        grupoPosicao.add(rdbtnMeioC);
        grupoPosicao.add(rdbtnAtacante);

        JLabel lblN = new JLabel("N°");
        lblN.setForeground(Color.BLACK);
        lblN.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblN.setBounds(238, 45, 19, 19);
        panel.add(lblN);

        nField = new JTextField(2);
        nField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nField.setColumns(10);
        nField.setBounds(275, 44, 51, 22);
        panel.add(nField);
        ((AbstractDocument) nField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                currentText += text;

                if (currentText.length() <= 2 && currentText.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {

                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        JCheckBox mostrarSenhaCheckbox = new JCheckBox("Ver");
        mostrarSenhaCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 8));
        mostrarSenhaCheckbox.setBounds(321, 107, 43, 21);
        panel.add(mostrarSenhaCheckbox);
        mostrarSenhaCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Quando a caixa de seleção for marcada, mostra a senha
                if (mostrarSenhaCheckbox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Define o caractere de ocultação para vazio
                    //confirmPasswordField.setEchoChar((char) 0);
                } else {
                    // Quando a caixa de seleção for desmarcada, oculte a senha
                    passwordField.setEchoChar('•'); // Defina o caractere de ocultação padrão (geralmente '*')
                    //confirmPasswordField.setEchoChar('*');
                }
            }
        });

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setBounds(114, 106, 201, 22);
        panel.add(passwordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        confirmPasswordField.setBounds(114, 158, 201, 22);
        panel.add(confirmPasswordField);

        JCheckBox mostrarSenhaCheckbox2 = new JCheckBox("Ver");
        mostrarSenhaCheckbox2.setFont(new Font("Tahoma", Font.PLAIN, 8));
        mostrarSenhaCheckbox2.setBounds(321, 159, 43, 21);
        panel.add(mostrarSenhaCheckbox2);
        mostrarSenhaCheckbox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Quando a caixa de seleção for marcada, mostra a senha
                if (mostrarSenhaCheckbox2.isSelected()) {
                    //passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    // Quando a caixa de seleção for desmarcada, oculte a senha
                    //passwordField.setEchoChar('*');
                    confirmPasswordField.setEchoChar('•');
                }
            }
        });
        JButton btnCadastOk = new JButton("Ok");
        btnCadastOk.setBackground(new Color(0, 64, 0));
        btnCadastOk.setForeground(new Color(255, 255, 255));
        btnCadastOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCadastOk.setBounds(142, 394, 238, 78);
        contentPane.add(btnCadastOk);
        btnCadastOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nField.getText().isEmpty() || passwordField.getPassword().length == 0 || confirmPasswordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    if (e.getSource() == btnCadastOk) {
                        //dados jogados para variáveis
                        nome = jogadorNomeField.getText();
                        nCamiseta = nField.getText();

                        //senha
                        char[] senhaChars = passwordField.getPassword();
                        String senha = new String(senhaChars);

                        //confirmação de senha
                        char[] confirmSenhaChars = confirmPasswordField.getPassword();
                        String confirmSenha = new String(confirmSenhaChars);
                        if (verificarNome(nome)) {
                            JOptionPane.showMessageDialog(null, "Nome de usuário já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!senha.equals(confirmSenha)) {
                            JOptionPane.showMessageDialog(null, "As senhas não são iguais", "Erro", JOptionPane.ERROR_MESSAGE);

                        }else if (rdbtnGol.isSelected()) {
                            posicao = "Goleiro";
                            salvarNumeroPosicao(nome, nCamiseta,posicao);
                            salvarUsuarioSenha(nome, senha);
                            limparCampos(); grupoPosicao.clearSelection();
                        } else if (rdbtnZagueiro.isSelected()) {
                            posicao = "Zagueiro";
                            salvarNumeroPosicao(nome, nCamiseta,posicao);
                            salvarUsuarioSenha(nome, senha);
                            limparCampos(); grupoPosicao.clearSelection();
                        } else if (rdbtnMeioC.isSelected()) {
                            posicao = "MeioC";
                            salvarNumeroPosicao(nome, nCamiseta,posicao);
                            salvarUsuarioSenha(nome, senha);
                            limparCampos(); grupoPosicao.clearSelection();
                        } else if (rdbtnAtacante.isSelected()) {
                            posicao = "Atacante";
                            salvarNumeroPosicao(nome, nCamiseta,posicao);
                            salvarUsuarioSenha(nome, senha);
                            limparCampos(); grupoPosicao.clearSelection();
                        }  else {
                            JOptionPane.showMessageDialog(null, "Escolha a posição do jogador", "Erro", JOptionPane.ERROR_MESSAGE);

                        }

                    }
                    //JOptionPane.showMessageDialog(null, "Usuário", "vamo vê", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVoltar.setBackground(new Color(0, 64, 0));
        btnVoltar.setBounds(10, 394, 117, 78);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
    }

    public void salvarUsuarioSenha(String nome, String senha) {

        String caminhoArquivo = "C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\jogadores.txt";


        // logica para salvar os dados do jogador
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, true))) {

            writer.println(nome + "," + senha);
            JOptionPane.showMessageDialog(null, "Jogador registrado com sucesso!", "Boa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void salvarNumeroPosicao(String nome, String numeroC, String posicao){
        String caminhoArquivo = "C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\posicao.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, true))) {
            writer.println(nome + "," + numeroC + "," + posicao);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void limparCampos() {
        jogadorNomeField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        nField.setText("");

    }
    public boolean verificarNome(String nome) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Matheus\\Desktop\\PI2\\Camiseta10\\camisetadez\\src\\date\\jogadores.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 1 && partes[0].equals(nome)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
