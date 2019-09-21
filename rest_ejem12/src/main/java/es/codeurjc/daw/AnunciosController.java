package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AnunciosController {

	@Autowired
	AnunciosService service;
	
	@GetMapping("/")
	public String tablon(Model model) {

		model.addAttribute("anuncios", service.getAnuncios());

		return "tablon";
	}

	@PostMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {
		
		service.postAnuncio(anuncio);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{num}")
	public String verAnuncio(Model model, @PathVariable int num) {

		Anuncio anuncio = service.getAnuncioById((long) num);

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}

}
