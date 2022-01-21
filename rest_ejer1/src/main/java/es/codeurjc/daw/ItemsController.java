package es.codeurjc.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemRepository repository;
	
	@PostConstruct
	public void init() {
		repository.save(new Item("leche", false)); 
		repository.save(new Item("pan", false)); 
		repository.save(new Item("pescado", false)); 
		repository.save(new Item("fruta", true)); 
		repository.save(new Item("cereales", false)); 
		repository.save(new Item("pasta", false)); 
		repository.save(new Item("queso", false)); 
		repository.save(new Item("jamón", false)); 
	}
	
	@GetMapping("/")
	public Page<Item> items(Pageable page) {
		return repository.findAll(page);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Item nuevoItem(@RequestBody Item item) {

		Item itemActualizado = repository.save(item);

		return itemActualizado;
	}

	@PutMapping("/{id}")
	public Item actualizaItem(@PathVariable long id, @RequestBody Item itemActualizado) {

		Item item = repository.findById(id).get();
		item.setChecked(itemActualizado.getChecked());
		item.setDescription(itemActualizado.getDescription());
		
		itemActualizado = repository.save(item);
		
		return itemActualizado;
	}

	@GetMapping("/{id}")
	public Item getItem(@PathVariable long id) {

		Item item = repository.findById(id).get();
		return item;
		
	}

	@DeleteMapping("/{id}")
	public void borraItem(@PathVariable long id) {

		repository.findById(id).get();
		repository.deleteById(id);

	}

}
