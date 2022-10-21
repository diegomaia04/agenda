package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor
	 */
	public Agenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setTitle("Agenda de contatos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/pesquisar.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(285, 11, 15, 14);
		contentPane.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(308, 8, 64, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(8, 11, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(50, 8, 199, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setBounds(8, 58, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtFone = new JTextField();
		txtFone.setBounds(50, 58, 118, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(8, 118, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(50, 115, 290, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setToolTipText("Adicionar contato");
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/Add.png")));
		btnCreate.setBounds(215, 158, 64, 64);
		contentPane.add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnDelete.setIcon(new ImageIcon(Agenda.class.getResource("/img/Delete.png")));
		btnDelete.setToolTipText("Adicionar contato");
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(370, 158, 64, 64);
		contentPane.add(btnDelete);

		btnRead = new JButton("");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnRead.setIcon(new ImageIcon(Agenda.class.getResource("/img/Procurar.png")));
		btnRead.setToolTipText("Adicionar contato");
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		btnRead.setBounds(193, 49, 56, 55);
		contentPane.add(btnRead);

		lblStatus = new JLabel("New label");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(8, 158, 48, 48);
		contentPane.add(lblStatus);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnRead);

		// Uso da biblioteca do atxy2k
		RestrictedTextField nome = new RestrictedTextField(txtNome);
		nome.setLimit(50);
		nome.setOnlyText(true);
		nome.setAcceptSpace(true);
		RestrictedTextField fone = new RestrictedTextField(txtFone);
		fone.setLimit(15);
		fone.setOnlyNums(true);
		fone.setAcceptSpace(true);
		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(50);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Agenda.class.getResource("/img/update.png")));
		btnUpdate.setToolTipText("Adicionar contato");
		btnUpdate.setEnabled(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(308, 158, 64, 64);
		contentPane.add(btnUpdate);

		btnDelete_1 = new JButton("");
		btnDelete_1.setIcon(new ImageIcon(Agenda.class.getResource("/img/Delete.png")));
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();

			}
		});
		btnDelete_1.setToolTipText("Adicionar contato");
		btnDelete_1.setEnabled(false);
		btnDelete_1.setContentAreaFilled(false);
		btnDelete_1.setBorderPainted(false);
		btnDelete_1.setBounds(264, 56, 46, 42);
		contentPane.add(btnDelete_1);
		email.setLimit(50);

	} // fim do construtor

	// criar um objeto para acessar o meteodo conectar() da classe DAO
	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnRead;
	private JButton btnUpdate;
	private JButton btnDelete_1;

	/**
	 * Meteodo responsavel por verificar o status da conexao com o banco de dados
	 */
	private void status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe conection (JBBC) para estabelecer a conexao
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dbon.png")));
			}
			// Nunca esequcer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do status

	/**
	 * Meteodo respnsavel pela pesquisa(selct) de um coontato
	 */

	private void pesquisarContato() {
		// validação
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtNome.requestFocus();
		} else {

			// System.out.println("teste");
			// iniciar com a instrução sql
			// ? e um parametro a ser substituido
			String read = "select * from contatos where nome = ?";
			try {
				// Estabelecer a conexão("abrir a porta da geladeria")
				Connection con = dao.conectar();
				// Preparar o codigo sql para execução
				PreparedStatement pst = con.prepareStatement(read);
				// a linha baixo substitui o ? pelo conteudo da caixa de texto txtNome, o 1 faz
				// referencia a interrogação
				pst.setString(1, txtNome.getText());
				// Obter os dados do contato 9resultado)
				ResultSet rs = pst.executeQuery();
				// verificar se existe um contato cadastrado
				// rs.next() significa ter um contato correspondente ao nome pesquisado
				if (rs.next()) {
					// Setar as caixa de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtFone.setText(rs.getString(3));
					txtEmail.setText(rs.getString(4));
					// habilitar botoes e alterar e excluir
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnDelete_1.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato Inexistente");
					// setar campos e botoes
					txtFone.setText(null);
					txtEmail.setText(null);
					txtNome.requestFocus();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(false);
				}
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Mteodo responsavel pelo cadastro
	 */
	void adicionarContato() {
		// validação de campos obrigadotria
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome");
			txtNome.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Fone");
			txtFone.requestFocus();
		} else {

			// System.out.println("teste adicionar ");
			String creat = "insert into contatos (nome,fone,email) values(?,?,?)";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// Preparar a querry(substituição)
				PreparedStatement pst = con.prepareStatement(creat);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato adicionado!");
					limpar();
				}

				// encerrar a conexao
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * meteodo responsavel pela edição do contato
	 */
	private void alterarContato() {
		// System.out.println("teste");

		// validação
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome");
			txtNome.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Fone");
			txtFone.requestFocus();
		} else {
			// logica principal
			String update = "update contatos set nome = ?, fone = ?, email = ? where id = ?";

			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a querry (instrução sql)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());
				// executar a query e confimar inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do contato atualizados com sucesso");
					limpar();
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void excluirContato() {
		// System.out.println("teste de botao excluir);
		// validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desde contato ?", "Excluir Contato",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from contatos where id = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// execuatr o comandosql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso");
					limpar();
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Meteodo Usado para limpar os campos da agenda e setar os botoes
	 */
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}
}// fim do codigo
