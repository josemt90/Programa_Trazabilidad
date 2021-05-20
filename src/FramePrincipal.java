import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class FramePrincipal extends JFrame {

	
	private JButton botonLogin,botonBuscar;
	public JComboBox combSeccion, combCliente;
	private JLabel label;
	private JMenuBar menuBar;
	private JTextArea textArea;	
	private JScrollPane panelBarras;
	private JPanel panel;
	private JButton btnInsertar;
	private JLabel label2;
	private JTextField CodCircuito;
	EnlazaBD enlazaBD = new EnlazaBD();
		
		

  public FramePrincipal() {
		
		
		setBounds(100, 100, 711,549);
		setLocationRelativeTo(null);
		setTitle("Trazabilidad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		label = new JLabel("   SELECCIONAR:  ");
		menuBar.add(label);
		
		combSeccion = new JComboBox();       //INTRODUCIMOS EN EL COMBOBOX LAS DIFERENTES SECCIONES
		
		combSeccion.addItem("Seccion...");		
		combSeccion.addItem("SMD");
		combSeccion.addItem("THT");
		combSeccion.addItem("Test");
		combSeccion.addItem("Barnizado");
		combSeccion.addItem("Embalaje");
		menuBar.add(combSeccion);
		
		
		
		combCliente = new JComboBox();
		combCliente.setEditable(false);
		combCliente.addItem("Cliente...");
		menuBar.add(combCliente);
		
		addWindowListener(new ControlaClientes(this));   // AGREGAMOS EL LISTENER PARA QUE CUANDO SE ABRA LA VENTANA AÑADA LOS CLIENTES EN EL COMBOBOX CLIENTES
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(9);
		menuBar.add(horizontalStrut_1);
		
		label2 = new JLabel("C\u00F3digo Circuito:   ");
		menuBar.add(label2);
		
		CodCircuito = new JTextField();
		menuBar.add(CodCircuito);
		CodCircuito.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(95);
		menuBar.add(horizontalStrut);
		
		botonBuscar = new JButton("BUSCAR");
		menuBar.add(botonBuscar);
		botonBuscar.setBackground(new Color(0, 204, 204));
		botonBuscar.setForeground(Color.BLACK);
		botonBuscar.addActionListener(new ActionListener() {  //CON EL BOTON ACEPTAR CERRAREMOS ESTE PANEL
			public void actionPerformed(ActionEvent e) {
								
				FrameBuscar frameBuscar = new FrameBuscar();
				frameBuscar.setVisible(true);							
			}
		});
		
		botonLogin = new JButton("LOGIN");
		menuBar.add(botonLogin);
		botonLogin.setBackground(new Color(135, 206, 235));
		botonLogin.setForeground(new Color(0, 0, 0));
		
		abrirLogin abrirLogin = new abrirLogin();
		botonLogin.addActionListener(abrirLogin);
		
		
		textArea = new JTextArea();
		panelBarras = new JScrollPane(textArea);             //AÑADIMOS EL TEXTAREA EN EL JSCROLLPANE PARA QUE TENGA BARRA DE DESPLAZAMIENTO
		getContentPane().add(panelBarras, BorderLayout.CENTER);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		
		btnInsertar = new JButton("INSERTAR");
		insertarCampos insertaCampo = new insertarCampos();
		btnInsertar.addActionListener(insertaCampo);
		btnInsertar.setEnabled(false);	
		panel.add(btnInsertar);
		
						
					
	}
	
	public class abrirLogin implements ActionListener {
		
		FrameLogeo frameLogeo;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			frameLogeo = new FrameLogeo();
			frameLogeo.setVisible(true);	
			btnInsertar.setEnabled(true);     // EL BOTON INSERTAR ESTARA ANULADO HASTA QUE SE CLIQUE EL BOTON LOGIN
									
		}	
	}
	
	
   public class insertarCampos implements ActionListener{
	   
	private String  muestraSeccion,muestraCliente,codCircuito,usuarioA;   
	private Connection miConexion;
	private Statement stmt;   
	private String query;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		 usuarioA = FrameLogeo.usuario.getNombreUsuario();         // ESTOS SON LAS VARIABLES QUE ALMANECARAN CADA DATO DE QUE QUEREMOS INTRODUCIR EN EL TEXTAREA Y EN LA TABLA PRODUCCION
		 codCircuito = CodCircuito.getText();
		 muestraSeccion = (String) combSeccion.getSelectedItem();
		 muestraCliente = (String) combCliente.getSelectedItem();
		 
		 // AÑADIMOS LOS DATOS EN EL TEXT AREA 
		
		textArea.append("Usuario: " + usuarioA + ", Codigo de circuito: " + codCircuito + ", Sección: " + muestraSeccion + ", Cliente: " +  muestraCliente);
		textArea.append("\n");
		
		CodCircuito.setText(""); // CADA VEZ QUE INSERTEMOS UN DATO EN EL TEXT AREA EL TEXTFIELD DONDE INTRODUCIMOS EL CODIGO DEL CIRCUITO SE VACIARA
		
		
		//----------------- INTRODUCIMOS LOS DATOS TAMBIEN EN LA TABLA PRODUCCION PARA QUE QUEDEN ALMACENADOS
		
		miConexion = enlazaBD.dameConexion(); 
		
		try {
			
			stmt = miConexion.createStatement();
			
			query = "INSERT INTO PRODUCCION (CODIGOCIRCUITO,NOMBRECLIENTE,DEPARTAMENTO,NOMBREUSUARIO) values('"+codCircuito+"','"+muestraCliente+"','"+muestraSeccion+"','"+usuarioA+"')";
			
			stmt.executeUpdate(query);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
	}
	   
   }
	
}
			


