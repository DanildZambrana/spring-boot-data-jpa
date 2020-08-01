package us.siriusteam.springboot.datajpa.app.controllers;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.siriusteam.springboot.datajpa.app.models.entity.Cliente;
import us.siriusteam.springboot.datajpa.app.models.service.IClienteService;
import us.siriusteam.springboot.datajpa.app.models.service.IUploadFileService;
import us.siriusteam.springboot.datajpa.app.util.paginator.PageRender;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    private IClienteService clienteService;
    private IUploadFileService uploadFileService;
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private static final String UPLOADS_FOLDER = "uploads";

    public ClienteController(IClienteService clienteService,
                             IUploadFileService uploadFileService) {
        this.clienteService = clienteService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping(value = "/uploads/{fileName:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable(value = "fileName") String fileName) {
        Path pathFoto = Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
        logger.info("pathFoto: " + pathFoto);

        Resource resource = null;
        try {
            resource = uploadFileService.load(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +
                        "\"")
                .body(resource);
    }

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente cliente = clienteService.findOne(id);

        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos!");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente " + cliente.getNombre() + " " + cliente.getApellido());

        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@NotNull Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        //Pageable pageRequest = new PageRequest(page, size);
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);

        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();

        String text = "crear";
        model.put("button", text);
        model.put("titulo", "Formulario cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
                          @RequestParam("file") MultipartFile file, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            String botonTexto = (cliente.getId() != null) ? "Editar Cliente" : "Crear Cliente";
            model.addAttribute("button", botonTexto);
            return "form";
        }

        if (!file.isEmpty()) {
            logger.info(cliente.toString());

            if (cliente.getId() != null
                    && cliente.getId() > 0
                    && cliente.getFoto() != null
                    && cliente.getFoto().length() > 0) {
                uploadFileService.delete(cliente.getFoto());
            }

            try {
                flash.addFlashAttribute("info", "Has subido correctamente '" + file.getOriginalFilename() + "'");
                cliente.setFoto(uploadFileService.copy(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logger.info(cliente.toString());
        String message = (cliente.getId() != null) ? "Cliente editado con exito!" : "Cliente creado con exito!";
        clienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("success", message);
        return "redirect:listar";
    }


    @RequestMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente cliente;

        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "No se encontro un cliente con el id" + id + "!");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID debe ser mayor a 0!");
            return "redirect:/listar";
        }

        String text = "editar";
        model.put("button", text);
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);

            clienteService.delete(id);
            flash.addFlashAttribute("succes", "Cliente con el id " + id + " eliminado con exito!");


            if (uploadFileService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada correctamente!");
            } else {
                flash.addFlashAttribute("error", "Foto " + cliente.getFoto() + " no se puede eliminar!");
            }

        }
        return "redirect:/listar";
    }
}
