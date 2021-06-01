package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class ProductoController {
	private static final Log GUSTAVO = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	@Qualifier("impprodmysql")
	ProductoService iProductoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("producto");
	}
	
	@PostMapping(value="/producto/guardar", consumes = "multipart/form-data")
	public String guardarNuevoProducto(@RequestParam("file") MultipartFile file, @ModelAttribute("unProducto") Producto nuevoProducto, Model model) throws IOException {
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		nuevoProducto.setImagen(base64);
		iProductoService.guardarProducto(nuevoProducto);
		//mostrar el listado de producto luego de la carga de un producto		
		GUSTAVO.error("solo de prueba");
		return "redirect:/producto/mostrar";
	}
	
	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		//model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("redirect:/producto");
	}
	
	@GetMapping("/producto/vender")
	public String cargarProductoVender(Model model) {		
		GUSTAVO.error("solo de prueba: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("venta");
	}
	
	@GetMapping("/producto/comprar/{id}")	
	public String editarClienteCompra(Model model, @PathVariable(name="id") int id) throws Exception {
		Producto productoSeleccionado = new Producto();		
		try {		
			productoSeleccionado = iProductoService.obtenerUnProducto(id);		
			model.addAttribute("productoSeleccionado",productoSeleccionado);
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "venta-modal";
	}
}
