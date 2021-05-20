import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.DropMode;
import java.awt.Insets;

public class FrameBuscar extends JFrame {

	private JPanel contentPane;
	private JTextField introCod;
	private JLabel introduccir;
	private JMenuBar menuBar;
	private JTextArea textArea;
	private JScrollPane panelBarras2;
	private EnlazaBD enlazaBD = new EnlazaBD();
	
	public FrameBuscar() {
		
		setBounds(100, 100, 475, 491);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 0, 0, 0));
		setJMenuBar(menuBar);
		
		introduccir = new JLabel("   Introduce c\u00F3digo del circuito: ");
		menuBar.add(introduccir);
		
		introCod = new JTextField();
		menuBar.add(introCod);
		introCod.setHorizontalAlignment(SwingConstants.LEFT);
		introCod.setColumns(20);
		
		JButton btnMostrar = new JButton("MOSTRAR");		
		MostrarProd mostrarProd = new MostrarProd();		
		btnMostrar.addActionListener(mostrarProd);	
		menuBar.add(btnMostrar);
		
		Component horizontalStrut = Box.createHorizontalStrut(54);
		menuBar.add(horizontalStrut);
					
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		panelBarras2 = new JScrollPane(textArea);               //AÑADIMOS EL TEXTAREA EN EL JSCROLLPANE PARA QUE TENGA BARRA DE DESPLAZAMIENTO
		contentPane.add(panelBarras2, BorderLayout.CENTER);    
	}
	
	public class MostrarProd implements ActionListener{
		
		private Connection miConexion;
		private Statement stmtBusc;
		private ResultSet miResultset;

		@Override
		public void actionPerformed(ActionEvent e) {    // MUESTRA EN EL TEXTAREA TODOS DATOS INTRODUCIDOS QUE TENGAN EL NUM QUE INTRODUCIMOS EN EL TEXTFIELD
					
			// TODO Auto-generated method stub
			
			miConexion = enlazaBD.dameConexion();   // OBTENEMOS LA CONEXION 
			
			try {
											
				stmtBusc = miConexion.createStatement();
				
				miResultset= stmtBusc.executeQuery("SELECT * FROM PRODUCCION WHERE CODIGOCIRCUITO='"+introCod.getText()+"'");
				
				while(miResultset.next()) {    
					
				
					
					textArea.append("Codigo: " + miResultset.getString("CODIGOCIRCUITO")+ ", Cliente: " + miResultset.getString("NOMBRECLIENTE")
					+ ", Departamento: "+ miResultset.getString("DEPARTAMENTO")+ ", Empleado: " + miResultset.getString("NOMBREUSUARIO") );
					
					textArea.append("\n");
			
				}	
				
				miResultset.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	
}
