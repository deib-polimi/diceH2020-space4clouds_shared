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
package it.polimi.diceH2020.SPACE4Cloud.shared;

import static org.junit.Assert.*;

import java.io.File;

public class Test {

	@org.junit.Test
	public void test() throws Exception {
		File schema = new File("src/test/resources/static/schema.json");
		assertTrue(schema.exists());
		DataChecker checker = DataChecker.getInstance();
		String jsonPath = "src/test/resources/static/input.json";
		assertTrue(checker.isValid(jsonPath));
	}

}
