package com.apress.spring6recipes.board.web;

import com.apress.spring6recipes.board.Todo;
import com.apress.spring6recipes.board.TodoService;
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

@Controller
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public String list(Model model) {
		var todos = todoService.listTodos();
		model.addAttribute("todos", todos);
		return "todos";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("todo", new Todo());
		return "todo-create";
	}

	@PostMapping
	public String newTodo(@ModelAttribute @Valid Todo todo, BindingResult errors) {

		if (errors.hasErrors()) {
			return "todo-create";
		}
		var owner = "marten@deinum.biz";
		todo.setOwner(owner);
		todoService.save(todo);
		return "redirect:/todos";
	}

	@PutMapping("/{todoId}/completed")
	public String complete(@PathVariable("todoId") long todoId) {
		this.todoService.complete(todoId);
		return "redirect:/todos";
	}

	@DeleteMapping("/{todoId}")
	public String delete(@PathVariable("todoId") long todoId) {
		this.todoService.remove(todoId);
		return "redirect:/todos";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// We don't want to bind the id and owner fields as we control them in this
		// controller and service instead.
		binder.setDisallowedFields("id", "owner");
	}
}
