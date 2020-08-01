package us.siriusteam.springboot.datajpa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;
import us.siriusteam.springboot.datajpa.app.models.entity.Factura;
import us.siriusteam.springboot.datajpa.app.models.service.IClienteService;

import java.util.Map;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
    private IClienteService clienteService;

    public FacturaController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable("clienteId") Long clienteId, Map<String, Object> model,
                        RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(clienteId);
        if (cliente == null) {
            flash.addFlashAttribute("error", "No se encontro el cliente con el id: '" + clienteId +"' en la base de "
                    + "datos");
            return "redirect:/listar";
        }
        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", "Crear factura");

        return "factura/form";
    }
}
