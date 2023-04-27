package com.apress.spring6recipes.shop;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Cashier {

	private final String fileName;
	private final String path;

	private BufferedWriter writer;

	public Cashier(String fileName, String path) {
		this.fileName = fileName;
		this.path = path;
	}

	public void openFile() throws IOException {
		var options = new OpenOption[] {
						StandardOpenOption.CREATE,
						StandardOpenOption.APPEND };
		var file = Path.of(path, fileName + ".txt");
		Files.createDirectories(file.getParent());
		writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, options);
	}

	public void checkout(ShoppingCart cart) throws IOException {
		writer.write(LocalDateTime.now() + "\t" + cart.getItems() + "\r\n");
		writer.flush();
	}

	public void closeFile() throws IOException {
		writer.close();
	}
}
