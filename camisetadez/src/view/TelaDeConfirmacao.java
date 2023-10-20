package view;

import date.User;
import date.UserAttribute;
import logic.HomeFuncoes;

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
    public static void main(String[] args) {

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

    }

    /**
     * Create the frame.
     */
    public TelaDeConfirmacao() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 775, 446);
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
        panel.setBounds(39, 126, 685, 182);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("Tem certeza que deseja excluir o(s) amiguinho(s)?");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));

        textField = new JTextField();
        textField.setColumns(10);

        passwordField = new JPasswordField();

        JLabel lblNomeUsuario = new JLabel("Confirmar Usuario");
        lblNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(96)
                                                .addComponent(lblNewLabel))
                                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(passwordField, Alignment.TRAILING)
                                                        .addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))))
                                .addContainerGap(96, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(5)
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
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
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnOk.setBounds(575, 330, 149, 58);
        contentPane.add(btnOk);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnVoltar.setBounds(39, 330, 149, 58);
        contentPane.add(btnVoltar);

        /*btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //lista jogadores
                List<User> naoExcluidos = excluirSelecionados();

                //lista ranking
                List<UserAttribute> ranking = HomeFuncoes.lerNomeEAtributo("ranking.txt");
                List<UserAttribute> rankingNaoExcluidos = excluirAtributos(ranking);
                //lista com senhas
                List<UserAttribute> senhas = HomeFuncoes.lerNomeEAtributo("jogadores.txt");
                List<UserAttribute> senhasNaoExcluidas = excluirAtributos(senhas);


                //salvar a lista dos não excluidos nos respectivos arquivos txt
                salvarNumeroPosicao(naoExcluidos,"posicao.txt");
                salvarAtributos(rankingNaoExcluidos, "ranking.txt");
                salvarAtributos(senhasNaoExcluidas, "jogadores.txt");
            }
        });
    */


    }
}
