import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FrameLogeo extends JFrame {

	
	private JPanel contentPane;
	private  JTextField textField; 
    private JLabel labelNombre;    
    private JButton btnaceptar, btnValidar;   
    EnlazaBD enlaza = new EnlazaBD();
    static Usuario usuario = new Usuario(); // PONEMOS STATICO EL OBJETO USUARIO PARA LUEGO PODER USARLO EN LA CLASE DISEÑO_PRINCIPAL
    mostrarNombre muestra;
   
    
	public FrameLogeo() {
		
		setBounds(500, 300, 402, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduce codigo  usuario:");
		lblNewLabel.setBounds(10, 26, 148, 23);
		contentPane.add(lblNewLabel);
		
		btnaceptar = new JButton("Aceptar");
		btnaceptar.setEnabled(false);
		
		btnaceptar.addActionListener(new ActionListener() {  //CON EL BOTON ACEPTAR CERRAREMOS ESTE PANEL
			public void actionPerformed(ActionEvent e) {
								
				setVisible(false);
															
			}
		});
		
		btnaceptar.setBounds(143, 148, 89, 23);
		contentPane.add(btnaceptar);
		
		textField = new JTextField();
		textField.setBounds(159, 27, 86, 20);     // ESTE TEXTFIELD ES EL QUE INTRODUCIREMOS EL CODIGO DE USUARIO
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("");
		
		btnValidar = new JButton("Validar");
		btnValidar.setBounds(255, 26, 89, 23);        
		contentPane.add(btnValidar);
		
		
		labelNombre = new JLabel();
		labelNombre.setText("");
		labelNombre.setBounds(93, 93, 203, 14);
		contentPane.add(labelNombre);
		
		muestra = new mostrarNombre();  // INSTANCIAMOS LA CLASE MOSTRARNOMBRE 
		btnValidar.addActionListener(muestra);        // PONEMOS A LA ESCUCHA BTNVALIDIDAR
		
		
	}
	public class mostrarNombre implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {  // ESTE LISTENER GUARDA LOS DATOS DEL USUARIO QUE HEMOS INTRODUCICO EN EL TEXTFIELD
			// TODO Auto-generated method stub
									
			usuario = enlaza.verificaLogin(textField);
						
						
			if(usuario.getNombreUsuario()== "") {        // SI NO COINCIDE CON NINGUN CODIGO DE USUARIO ENTONCES EL NOMBRE DE USUARIO ESTARÁ VACIO 
				
				labelNombre.setText("Usuario: No encontrado " ); // SI ESTA VACIO PONDRA USUARIO NO ENCONTRADO HASTA QUE SE INTRODUCZCA UN CODIGO QUE EXISTA EN LA TABLA
				
			}else {
										
			labelNombre.setText("Usuario:     " + usuario.getNombreUsuario() +" " + usuario.getApellido1());  // SI EXISTE EN LA TABLA EN EL PANEL SALDRA EL NOMBRE Y EL APELLIDO
			btnaceptar.setEnabled(true);
			
					
			}
			
		} 		
	}
}


		
		

	

