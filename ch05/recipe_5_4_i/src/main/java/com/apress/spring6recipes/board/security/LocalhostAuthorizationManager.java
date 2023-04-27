package com.apress.spring6recipes.board.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.function.Supplier;

public class LocalhostAuthorizationManager<T> implements AuthorizationManager<T> {

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, T object) {
		var auth = authentication.get();
		var granted = false;
		if (auth.getDetails() instanceof WebAuthenticationDetails details) {
			String address = details.getRemoteAddress();
			granted = address.equals("127.0.0.1") || address.equals("0:0:0:0:0:0:0:1");
		}
		return new AuthorizationDecision(granted);
	}
}
