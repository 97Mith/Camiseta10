package view;

import date.User;
import logic.FuncoesArquivo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class TelaDeConfirmacao extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaDeConfirmacao frame = new TelaDeConfirmacao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public TelaDeConfirmacao(JButton atualizar) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 775, 446);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAtt = new JLabel("Atenção!");
        lblAtt.setForeground(new Color(0, 64, 0));
        lblAtt.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblAtt.setBounds(286, 10, 189, 93);
        contentPane.add(lblAtt);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 64, 0), 6));
        panel.setBounds(39, 92, 685, 216);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("A seguir é disposto uma janela onde é possivel excluir usuários,");
        lblNewLabel.setForeground(new Color(0, 64, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNomeUsuario = new JLabel("Confirmar Usuario");
        lblNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));

        JLabel lblPorEsseMotivo = new JLabel("por esse motivo, confirme seu usuário e senha");
        lblPorEsseMotivo.setForeground(new Color(0, 64, 0));
        lblPorEsseMotivo.setFont(new Font("Tahoma", Font.PLAIN, 23));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(passwordField, Alignment.TRAILING)
                                                        .addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                                                .addContainerGap(61, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                                .addComponent(lblPorEsseMotivo, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
                                                .addGap(86))
                                        .addComponent(lblNewLabel, Alignment.TRAILING)))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblPorEsseMotivo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                .addGap(29))
        );
        panel.setLayout(gl_panel);

        JButton btnOk = new JButton("Confirmar");
        btnOk.setBackground(new Color(192, 192, 192));
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnOk.setBounds(575, 330, 149, 58);
        contentPane.add(btnOk);

        JButton btnAtu = new JButton("");

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(192, 192, 192));
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnVoltar.setBounds(39, 330, 149, 58);
        contentPane.add(btnVoltar);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> lista = FuncoesArquivo.carregarJogadores("posicao.txt");
                ExcluirJogador excluirJogador = new ExcluirJogador(lista, false, btnAtu);
                excluirJogador.setVisible(true);
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAtu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar.doClick();
                dispose();
            }
        });

    }
}
