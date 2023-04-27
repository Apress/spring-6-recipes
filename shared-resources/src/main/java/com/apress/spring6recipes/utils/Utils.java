/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apress.spring6recipes.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Utility class to include some helper methods.
 *
 * @author Marten Deinum
 */
public final class Utils {

	private Utils() {
	}

	/**
	 * Let the current thread wait for @{code millis}.
	 * @param millis time to sleep in milliseconds.
	 */
	public static void sleep(long millis) {
		sleep(Duration.ofMillis(millis));
	}

	public static void sleep(Duration duration) {
		try {
			Thread.sleep(duration);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void sleep(long duration, TimeUnit unit) {
		sleep(Duration.of(duration, unit.toChronoUnit()));
	}

	public static void quitOnEnterKey() {
		System.out.println("Press [ENTER] to stop.");
		try {
			System.in.read();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}
}
