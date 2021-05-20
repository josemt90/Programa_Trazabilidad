import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CargaCliente {
	
	private EnlazaBD enlazaBD;
	public ResultSet rsCliente;
	private Connection accesoBD;
	private  Cliente cliente;
	 private Statement STcliente;
	
	public CargaCliente() {
		
		enlazaBD = new EnlazaBD();
		

}
public String ejecutaClientes() {             // ESTE METODO GUARDA LOS NOMBRES DE LA TABLA CLIENTE EN LA CLASE CLIENTE
		
		accesoBD = enlazaBD.dameConexion(); 	
        cliente = null;
		
		try {
			
			STcliente = accesoBD.createStatement();
			
			rsCliente = STcliente.executeQuery("SELECT * FROM CLIENTE");
			
			while(rsCliente.next()) {
				
				cliente = new Cliente();
				
				cliente.setNombreCliente(rsCliente.getString(2));  // GUARDAMOS EN LA CLASE CLIENTE EL NOMBRE DEL CLIENTE QUE OBTENEMOS DE LA TABLA
				
				return cliente.getNombreCliente();
								
			}
			
			rsCliente.close();
			
		}catch(Exception e) {
						
		}
		
		return cliente.getNombreCliente();
	}
}