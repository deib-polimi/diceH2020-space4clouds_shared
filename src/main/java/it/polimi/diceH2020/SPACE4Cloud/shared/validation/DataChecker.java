/**
 * Copyright 2015 deib-polimi
 * Contact: deib-polimi <michele.ciavotta@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package it.polimi.diceH2020.SPACE4Cloud.shared.validation;

import java.io.IOException;
import java.net.URL;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class DataChecker {

	private static final String SCHEMA = "/static/schema.json";
	private static URL schemaUrl;
	private static DataChecker checker;
	private static Locator locator;

	private DataChecker(Locator locator) throws Exception {
		if (locator == null)
			DataChecker.locator = new StandaloneLocator();
		else
			DataChecker.locator = locator;
		schemaUrl = DataChecker.locator.resolve(getClass().getResource(SCHEMA));

	}

	public static DataChecker getInstance() throws Exception {
		return ((checker == null) ? new DataChecker(null) : checker);
	}

	public static DataChecker getInstance(Locator locator) throws Exception {
		return ((checker == null) ? new DataChecker(locator) : checker);
	}

	public boolean isValid(URL path) {
		try {
			URL jsonPath = locator.resolve(path);
			return ValidationUtils.isJsonValid(schemaUrl, jsonPath);
		} catch (ProcessingException | IOException e) {
			return false;
		}
	}
}
