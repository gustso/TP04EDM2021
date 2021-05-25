package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.ProductoService;

@Service
@Qualifier("impprodmysql")
public class ProductoServiceImpMySQL implements ProductoService{

	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO iProductoDAO;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		iProductoDAO.save(unProducto);
	}

	@Override
	public void modificarProducto(Producto productoAModificar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarProducto(Producto productoAEliminar) {
		// TODO Auto-generated method stub
		iProductoDAO.delete(productoAEliminar);
	}

	@Override
	public Producto obtenerUnProducto(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return iProductoDAO.findById(id).orElseThrow(()->new Exception("El producto NO existe"));
	}

	@Override
	public ArrayList<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (ArrayList<Producto>) iProductoDAO.findAll();
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

}
