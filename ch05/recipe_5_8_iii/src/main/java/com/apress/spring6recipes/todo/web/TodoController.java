package com.apress.spring6recipes.todo.web;

import com.apress.spring6recipes.todo.Todo;
import com.apress.spring6recipes.todo.TodoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public Mono<String> list(Model model) {
		var todos = todoService.listTodos();
		model.addAttribute("todos", todos);
		return Mono.just("todos");
	}

	@GetMapping("/new")
	public Mono<String> create(Model model) {
		model.addAttribute("todo", new Todo());
		return Mono.just("todo-create");
	}

	@PostMapping
	public Mono<String> newTodo(@ModelAttribute @Valid Todo todo, BindingResult errors) {

		if (errors.hasErrors()) {
			return Mono.just("todo-create");
		}
		var owner = "marten@deinum.biz";
		todo.setOwner(owner);
		return todoService.save(todo)
						.then(Mono.just("redirect:/todos"));
	}

	@PutMapping("/{todoId}/completed")
	public Mono<String> complete(@PathVariable("todoId") long todoId) {
		return this.todoService.complete(todoId)
						.then(Mono.just("redirect:/todos"));
	}

	@DeleteMapping("/{todoId}")
	public Mono<String> delete(@PathVariable("todoId") long todoId) {
		return this.todoService.remove(todoId).then(Mono.just("redirect:/todos"));
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// We don't want to bind the id and owner fields as we control them in this
		// controller and service instead.
		binder.setDisallowedFields("id", "owner");
	}
}
