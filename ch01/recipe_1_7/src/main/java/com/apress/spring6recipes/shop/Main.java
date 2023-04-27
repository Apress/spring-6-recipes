package com.apress.spring6recipes.shop;

import com.apress.spring6recipes.shop.config.ShopConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Locale;

public class Main {

	private static final String MSG = "The I18N message for %s is: %s%n";

	public static void main(String[] args) {

		var cfg = ShopConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var alert = context.getMessage("alert.checkout", null, Locale.US);
			var alert_inventory = context.getMessage("alert.inventory.checkout",
					new Object[] { "[DVD-RW 3.0]", LocalDateTime.now() }, Locale.US);

			System.out.printf(MSG, "alert.checkout", alert);
			System.out.printf(MSG, "alert.inventory.checkout", alert_inventory);
		}
	}
}
