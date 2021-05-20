import java.awt.event.*;

public class ControlaClientes extends WindowAdapter {
	
	private CargaCliente cargaCliente = new CargaCliente();
	
	private FramePrincipal marco;
	
	
	public ControlaClientes(FramePrincipal marco) {  // LE PASAMOS POR PARAMETRO EL MARCO DONDE ESTA EL COMBOBOX
		
		this.marco=marco;		
	}
	
	
	public void windowOpened(WindowEvent e) {
		
		cargaCliente.ejecutaClientes();
		
		try {
			
			while(cargaCliente.rsCliente.next()) {
				
				marco.combCliente.addItem(cargaCliente.rsCliente.getString(2));  //SE AÑADEN TODOS LOS NOMBRES DE LOS CLIENTES QUE HAY EN LA TABLA CLIENTE
			}
			
		}catch(Exception a){
			
			a.printStackTrace();
			
		}
		
	}

}
