package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controle.MedicoProcessa;
import controle.RecepcaoProcessa;
import modelo.Medico;
import modelo.Recepcao;

public class TelaMedico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel id, nome, data, horario, medico, diagnostico, recomendacao, rotulos;
	private JTextField tfnome, tfdata, tfhorario, tfmedico, tfdiagnostico, tfrecomendacao, tfId;
	private JScrollPane rolagem;
	private JTextArea verResultados;
	private JButton create, read, update, delete;
	private int autoId = MedicoProcessa.pets.get(MedicoProcessa.pets.size()-1).getId() + 1;

	public TelaMedico() {
		setTitle("Consultas");
		setBounds(100, 100, 800, 600);
		painel = new JPanel();
		painel.setBackground(new Color(0, 255, 127));
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		id = new JLabel("Id:");
		id.setBounds(20, 20, 120, 30);
		painel.add(id);
		nome = new JLabel("nome:");
		nome.setBounds(20, 20, 120, 30);
		painel.add(nome);
		data = new JLabel("data:");
		data.setBounds(20, 55, 120, 30);
		painel.add(data);
		horario = new JLabel("horario:");
		horario.setBounds(20, 90, 120, 30);
		painel.add(horario);
		medico = new JLabel("medico:");
		medico.setBounds(20, 125, 120, 30);
		painel.add(medico);

		diagnostico = new JLabel("diagnostico:");
		diagnostico.setBounds(20, 165, 120, 30);
		painel.add(diagnostico);

		recomendacao = new JLabel("recomendacao:");
		recomendacao.setBounds(20, 205, 120, 30);
		painel.add(recomendacao);

		rotulos = new JLabel("agenda:");
		rotulos.setBounds(20, 310, 500, 30);
		painel.add(rotulos);
		
		tfId = new JTextField(String.format("%d", autoId));
		tfId.setEditable(false);
		tfId.setBounds(140, 25, 140, 30);
		painel.add(tfId);

		tfnome = new JTextField();
		tfnome.setBounds(140, 25, 255, 30);
		painel.add(tfnome);

		tfdata = new JTextField();
		tfdata.setBounds(140, 60, 80, 30);
		painel.add(tfdata);

		tfhorario = new JTextField();
		tfhorario.setBounds(140, 92, 80, 30);
		painel.add(tfhorario);

		tfmedico = new JTextField();
		tfmedico.setBounds(140, 125, 255, 30);
		painel.add(tfmedico);

		tfdiagnostico = new JTextField();
		tfdiagnostico.setBounds(140, 165, 255, 30);
		painel.add(tfdiagnostico);

		tfrecomendacao = new JTextField();
		tfrecomendacao.setBounds(140, 205, 255, 30);
		painel.add(tfrecomendacao);

		verResultados = new JTextArea();
		verResultados.setEditable(false);
		verResultados.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		preencherAreaDeTexto();
		rolagem = new JScrollPane(verResultados);
		rolagem.setBounds(20, 340, 740, 200);
		painel.add(rolagem);

		create = new JButton("Cadastrar");
		read = new JButton("Buscar");
		update = new JButton("Atualizar");
		delete = new JButton("Excluir");

		create.setBounds(405, 20, 110, 30);
		read.setBounds(405, 60, 110, 30);
		update.setBounds(405, 100, 110, 30);
		delete.setBounds(405, 140, 110, 30);

		update.setEnabled(false);
		delete.setEnabled(false);

		painel.add(create);
		painel.add(read);
		painel.add(update);
		painel.add(delete);

		create.addActionListener(this);
		read.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
	}
		private void cadastrar() {

				MedicoProcessa.pets.add(new Medico(autoId, tfnome.getSelectedItem().toString(), tfdata.getText(),
						tfhorario.getText(), peso, tfNascimento.getText()));
				autoId++;
				preencherTabela();
				limparCampos();
				MedicoProcessa.salvar();
				JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
			}

		private void buscar() {
			String entrada = JOptionPane.showInputDialog(this, "Digite o Id do animal:");

			boolean isNumeric = true;
			if (entrada != null && !entrada.equals("")) {
				for (int i = 0; i < entrada.length(); i++) {
					if (!Character.isDigit(entrada.charAt(i))) {
						isNumeric = false;
					}
				}
			} else {
				isNumeric = false;
			}
			if (isNumeric) {
				int id = Integer.parseInt(entrada);
				Medico medico = new Medico(id);
				if (MedicoProcessa.pets.contains(medico)) {
					int indice = MedicoProcessa.pets.indexOf(medico);
					tfId.setText(MedicoProcessa.pets.get(indice).getId("s"));
					tfnome.setSelectedIndex(obterIndiceEspecie(MedicoProcessa.pets.get(indice).getnome()));
					tfdata.setText(MedicoProcessa.pets.get(indice).getNomePet());
					tfhorario.setText(MedicoProcessa.pets.get(indice).getRaca());
					tfPeso.setText(MedicoProcessa.pets.get(indice).getPeso("s"));
					tfNascimento.setText(MedicoProcessa.pets.get(indice).getNascimento("s"));
					create.setEnabled(false);
					update.setEnabled(true);
					delete.setEnabled(true);
					MedicoProcessa.salvar();
				} else {
					JOptionPane.showMessageDialog(this, "Pet n�o encontrado");
				}
			}

		}

		private void alterar() {
			int id = Integer.parseInt(tfId.getText());
			Medico medico = new Medico(id);
			int indice = MedicoProcessa.pets.indexOf(medico);
			if (tfdata.getText().length() != 0 && tfhorario.getText().length() != 0 && tfPeso.getText().length() != 0
					&& tfNascimento.getText().length() != 0 && tfNomeDono.getText().length() != 0
					&& tfTelefone.getText().length() != 0) {

				MedicoProcessa.pets.set(indice, new Pet(id, cb.getSelectedItem().toString(), tfdata.getText(),
						tfhorario.getText(), peso, tfNascimento.getText(), tfNomeDono.getText(), tfTelefone.getText()));
				preencherTabela();
				limparCampos();
			} else {
				JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
			}
			create.setEnabled(true);
			update.setEnabled(false);
			delete.setEnabled(false);
			tfId.setText(String.format("%d", autoId));
			MedicoProcessa.salvar();
		}
		
		private void excluir() {
			int id = Integer.parseInt(tfId.getText());
			Medico medico = new Medico(id);
			int indice = MedicoProcessa.pets.indexOf(medico);
			MedicoProcessa.pets.remove(indice);
			preencherTabela();
			limparCampos();
			create.setEnabled(true);
			update.setEnabled(false);
			delete.setEnabled(false);
			tfId.setText(String.format("%d", autoId));
			MedicoProcessa.salvar();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				cadastrar();
			}
			if (e.getSource() == read) {
				buscar();
			}
			if (e.getSource() == update) {
				alterar();
			}
			if (e.getSource() == delete) {
				excluir();
			}
		}
	}
