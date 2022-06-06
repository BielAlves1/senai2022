package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controle.UsuarioProcessa;
import controle.RecepcaoProcessa;

public class TelaLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel painel;
	private JLabel rotulo1, rotulo2;
	private JTextField tfEmail;
	private JPasswordField senha;
	private JButton Login;

	TelaLogin() {

		setTitle("Tela de Login");
		setBounds(400, 200, 420, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painel = new JPanel(); 
		painel.setBackground(new Color(176,224,230));
		setContentPane(painel);
		setLayout(null);

		rotulo1 = new JLabel("Email:");
		rotulo1.setBounds(20, 70, 100, 20);
		tfEmail = new JTextField();
		tfEmail.setBounds(120, 70, 200, 30);
		rotulo2 = new JLabel("Senha:");
		rotulo2.setBounds(20, 110, 100, 20);
		senha = new JPasswordField();
		senha.setEchoChar('*');
		senha.setBounds(120, 110, 200, 30);
		Login = new JButton("Login");
		Login.setBounds(120, 150, 200, 30);
		
		Login.addActionListener(this);

		painel.add(rotulo1);
		painel.add(tfEmail);
		painel.add(rotulo2);
		painel.add(senha);
		painel.add(Login);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Login) {
			if (tfEmail.getText().length() > 0 && new String(senha.getPassword()).length() > 0) {
				int indice = UsuarioProcessa.checarLogin(tfEmail.getText());
				if (indice != -1) {
						this.dispose();
						TelaMenu tl = new TelaMenu();
						tl.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(this, "Acesso negado");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Usu�rio n�o encontrado");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Preencha o login e a senha");
			}
		}

	public static void main(String[] args) {
		UsuarioProcessa.abrir();
		RecepcaoProcessa.abrir();
		TelaLogin login = new TelaLogin();
		login.setVisible(true);
	}
}