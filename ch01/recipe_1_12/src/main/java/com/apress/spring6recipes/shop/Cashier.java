package com.apress.spring6recipes.shop;

import org.springframework.beans.factory.BeanNameAware;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Cashier implements BeanNameAware {

	private final String path;
	private String fileName;
	private BufferedWriter writer;

	public Cashier(String path) {
		this.path = path;
	}

	public void openFile() throws IOException {
		var options = new OpenOption[] {
						StandardOpenOption.CREATE,
						StandardOpenOption.APPEND };
		Files.createDirectories(Path.of(path));
		writer = Files.newBufferedWriter(Path.of(path, fileName),
						StandardCharsets.UTF_8,
						options);
	}

	public void checkout(ShoppingCart cart) throws IOException {
		writer.write(LocalDate.now() + "\t" + cart.getItems() + "\r\n");
		writer.flush();
	}

	public void closeFile() throws IOException {
		writer.close();
	}

	@Override
	public void setBeanName(String name) {
		this.fileName = name;
	}
}
