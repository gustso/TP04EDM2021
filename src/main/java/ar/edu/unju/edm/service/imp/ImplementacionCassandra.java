package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("implementacionCassandra")
public class ImplementacionCassandra implements IClienteService{

	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		//metodo para guardar el Cliente
		//Cassandra
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(int dni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente encontrarUnClienteId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
