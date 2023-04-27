package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.service.MemberService;
import com.apress.spring6recipes.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/members")
public class RestMemberController {

	private final MemberService memberService;
	private final TaskExecutor taskExecutor;

	public RestMemberController(MemberService memberService, TaskExecutor taskExecutor) {
		this.memberService = memberService;
		this.taskExecutor = taskExecutor;
	}

	@GetMapping
	public ResponseBodyEmitter getRestMembers() {
		var emitter = new SseEmitter();
		taskExecutor.execute(() -> {
			var members = memberService.findAll();
			try {
				for (var member : members) {
					var data = SseEmitter.event()
									.id(String.valueOf(member.hashCode()))
									.data(member);
					emitter.send(data);
					Utils.sleep(Duration.ofMillis(50));
				}
				emitter.complete();
			} catch (IOException ex) {
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}

	@GetMapping("/{memberid}")
	public ResponseEntity<Member> getMember(@PathVariable("memberid") long memberID) {
		return memberService.findById(memberID)
						.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Member> newMember(@Valid @RequestBody Member newMember) {
		return ResponseEntity.ok(memberService.save(newMember));
	}
}
