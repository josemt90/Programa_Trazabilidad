import java.sql.*;

import javax.swing.JTextField;


public class EnlazaBD {
	
	private Connection conexion;
	private Statement miStatement;
	private Usuario usuario = new Usuario();
	private ResultSet resultset;
	
	
	EnlazaBD(){
		            		
    }	
	
			
	public Connection dameConexion() {   // DEVUELVE LA CONEXION
		
		try {
			
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/trazabilidad","root", ""); 
			
			
			}catch(Exception e) {
				
				System.out.println("No conecta");
			}
		
		return conexion;
	}
	
	
	
	public Usuario verificaLogin( JTextField textField) {
		
		try {
			
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/trazabilidad","root", "");
			
			miStatement = conexion.createStatement();
			
			resultset = miStatement.executeQuery("SELECT * FROM usuario");
			
			while(resultset.next()) {
				
				if(resultset.getString("CODIGOUSUARIO").equals(textField.getText())) {   // SE COMPRUEBA SI EL CODIGO COINCIDE CON ALGUNO DE LOS USUARIOS DE LA TABLA 				
									                                                     // Y SI ES ASI GUARDA TODOS LOS DATOS DE LA TABLA DE DICHO USUARIO EN LA CLASE USUARIO
					usuario.setCodigoUsuario(resultset.getString("CODIGOUSUARIO"));
					usuario.setNombreUsuario(resultset.getString("NOMBREUSUARIO"));
					usuario.setApellido1(resultset.getString("APELLIDO1"));				
					usuario.setApellido2(resultset.getString("APELLIDO2"));
										
				}			
			}
			
			resultset.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
			
}	
	


