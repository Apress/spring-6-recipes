package com.apress.spring6recipes.todo;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

public class WebFluxInitializer extends AbstractReactiveWebInitializer {

	@Override
	protected Class<?>[] getConfigClasses() {
		return new Class<?>[] { WebFluxConfiguration.class };
	}

}
