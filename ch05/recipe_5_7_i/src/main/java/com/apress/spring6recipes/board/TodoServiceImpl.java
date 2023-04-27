package com.apress.spring6recipes.board;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.acls.domain.BasePermission.DELETE;
import static org.springframework.security.acls.domain.BasePermission.READ;
import static org.springframework.security.acls.domain.BasePermission.WRITE;

@Service
@Transactional
class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;
	private final MutableAclService mutableAclService;

	TodoServiceImpl(TodoRepository todoRepository, MutableAclService mutableAclService) {
		this.todoRepository = todoRepository;
		this.mutableAclService = mutableAclService;
	}

	@Override
	@PreAuthorize("hasAuthority('USER')")
	@PostFilter("hasAnyAuthority('ADMIN') or hasPermission(filterObject, 'read')")
	public List<Todo> listTodos() {
		return todoRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('USER')")
	public void save(Todo todo) {

		this.todoRepository.save(todo);

		var oid = new ObjectIdentityImpl(Todo.class, todo.getId());
		var acl = mutableAclService.createAcl(oid);
		var principalSid = new PrincipalSid(todo.getOwner());
		var authoritySid = new GrantedAuthoritySid("ADMIN");

		acl.insertAce(0, READ, principalSid, true);
		acl.insertAce(1, WRITE, principalSid, true);
		acl.insertAce(2, DELETE, principalSid, true);

		acl.insertAce(3, READ, authoritySid, true);
		acl.insertAce(4, WRITE, authoritySid, true);
		acl.insertAce(5, DELETE, authoritySid, true);
	}

	@Override
	@PreAuthorize("hasPermission(#id, 'com.apress.springrecipes.board.Todo', 'write')")
	public void complete(long id) {
		findById(id)
						.ifPresent((todo) -> {
							todo.setCompleted(true);
							todoRepository.save(todo);
						});
	}

	@Override
	@PreAuthorize("hasPermission(#id, 'com.apress.springrecipes.board.Todo', 'delete')")
	public void remove(long id) {
		todoRepository.remove(id);

		var oid = new ObjectIdentityImpl(Todo.class, id);
		mutableAclService.deleteAcl(oid, false);
	}

	@Override
	@PostFilter("hasPermission(filterObject, 'read')")
	public Optional<Todo> findById(long id) {
		return todoRepository.findOne(id);
	}
}
