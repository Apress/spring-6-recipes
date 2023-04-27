package com.apress.spring6recipes.shop;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Cashier {

	private final String filename;
	private final String path;

	private BufferedWriter writer;

	public Cashier(String filename, String path) {
		this.filename = filename;
		this.path = path;
	}

	@PostConstruct
	public void openFile() throws IOException {

		var checkoutPath = Paths.get(path, filename + ".txt");
		if (Files.notExists(checkoutPath.getParent())) {
			Files.createDirectories(checkoutPath.getParent());
		}
		this.writer = Files.newBufferedWriter(checkoutPath, StandardCharsets.UTF_8,
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	}

	public void checkout(ShoppingCart cart) throws IOException {
		writer.write(LocalDateTime.now() + "\t" + cart.getItems() + "\r\n");
		writer.flush();
	}

	@PreDestroy
	public void closeFile() throws IOException {
		writer.close();
	}
}
