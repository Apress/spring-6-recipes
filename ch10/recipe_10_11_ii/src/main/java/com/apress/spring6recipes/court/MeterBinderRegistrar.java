package com.apress.spring6recipes.court;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import jakarta.annotation.PostConstruct;

@Component
public class MeterBinderRegistrar {

	private final MeterRegistry registry;
	private final ObjectProvider<MeterBinder> binders;

	public MeterBinderRegistrar(MeterRegistry registry,
					ObjectProvider<MeterBinder> binders) {
		this.registry = registry;
		this.binders = binders;
	}

	@PostConstruct
	public void register() {
		this.binders
				.forEach( (b) -> b.bindTo(this.registry));
	}
}
