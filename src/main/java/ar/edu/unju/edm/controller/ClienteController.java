package ar.edu.unju.edm.controller;

import javax.validation.Valid;

//import java.time.LocalDate;
//import java.time.Period;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	
	//@Qualifier("unImp")
	//sobre el List
	
	//sobre el BD MySQL
	@Autowired
	@Qualifier("implementacionmysql")	
	IClienteService clienteService;
	
	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}

	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@Valid @ModelAttribute("unCliente") Cliente nuevoCliente, BindingResult result, Model model) {		
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		if (result.hasErrors()) {
			// si da error el objeto recibido se vuelve a enviar a la vista
			model.addAttribute("unCliente", nuevoCliente);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			return "cliente";
		} else {
			clienteService.guardarCliente(nuevoCliente);		
			LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
			return "redirect:/cliente/mostrar";
		}		
		//llamo a este método para que puedan ver cómo trabajar con fechas
		//trabajarConFechas();		
	}
	
	//public void trabajarConFechas() {
		//algunas cosas con fecha;
		//obtengo tres fechas
		//LocalDate fecha1 = clienteService.obtenerTodosClientes().get(0).getFechaNacimiento();
		//LocalDate fecha2 = LocalDate.now();
		//LocalDate fecha3 = LocalDate.of(2020, 3, 25);
		//calculo el período entre dos de ellas
		//Period periodo = Period.between(fecha1,fecha2);
		//una vez que tengo el período puedo saber sus cantidades en días meses y años
		//int dias = periodo.getDays();		
		//System.out.println("dias: "+dias);
		//revisen la salida en la consola, debería dar la diferencia en días
		//todo esto nos debe hacer revisar la documentación de las clases LocalDate, LocalTime, LocalDateTime y Period
	//}
	
	@GetMapping("/cliente/editar/{nroDocumento}")
	public String editarCliente(Model model, @PathVariable(name="nroDocumento") int dni) throws Exception {		
		try {
			LOGGER.info("METHOD: ingresando editar modificar, antes de traer el Cliente");
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(dni);
			LOGGER.info("METHOD: ingresando editar modificar, traje el Cliente");
			model.addAttribute("unCliente", clienteEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteService.crearCliente());
			model.addAttribute("editMode", "false");
		}				
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());		
		return "cliente";
	}
	
	@GetMapping("/cliente/editarCompra/{id}")	
	public String editarClienteCompra(Model model, @PathVariable(name="id") int id) throws Exception {
		Cliente clienteEncontrado = new Cliente();
		LOGGER.info("METHOD: ingresando el metodo xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		try {		
			clienteEncontrado = clienteService.encontrarUnClienteId(id);		
			model.addAttribute("unClienteDetalle",clienteEncontrado);
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "cliente-modal";
	}
	
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model) {
		//try permite realizar una accion, pero si ocurre un error no se caera el programa
			try {
				clienteService.modificarCliente(clienteModificado);
				model.addAttribute("unCliente", new Cliente());				
				model.addAttribute("editMode", "false");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// pasar las excepciones al html
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unCliente", clienteModificado);			
				model.addAttribute("clientes", clienteService.obtenerTodosClientes());
				model.addAttribute("editMode", "true");
			}		
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return "cliente";
	}
	
	@GetMapping("/cancelar")
	public String cancelar() {
		return "redirect:/cliente/mostrar";
	}
	
	@GetMapping("/cliente/eliminarCliente/{id}")
	public String eliminarCliente(Model model, @PathVariable(name="id") int id) {
		LOGGER.info("METHOD: ingresando el metodo Eliminar");
		try {
			clienteService.eliminarCliente(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/cliente/mostrar";
	}
	
	@GetMapping("/cliente/vender")
	public String cargarProductoVender(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("venta");
	}
}
