package com.apress.spring6recipes.sequence;

/**
 * {@code PrefixGenerator} simple interface, usable as a functional interface as well, to
 * note the contract for a prefix generator.
 *
 * @author Marten Deinum
 */
@FunctionalInterface
public interface PrefixGenerator {

	String getPrefix();

}
