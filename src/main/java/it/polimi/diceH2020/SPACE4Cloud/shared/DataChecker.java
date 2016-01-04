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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class DataChecker {

	private static final String SCHEMA = "src/test/resources/static/schema.json";

	
	private static File schema;
	private static DataChecker checker;

	private DataChecker() throws Exception {
		System.out.println(System.getProperty("java.class.path"));
		
		schema = new File(SCHEMA);
		if (!schema.exists()) {
			throw new Exception();
		}
		
	}

	public static DataChecker getInstance() throws Exception {
		return ((checker == null) ? new DataChecker() : checker);
	}

	public boolean isValid(String path){
		try {
			
			File jsonFile = new File(path);
			
			String schema = readFile(SCHEMA, Charset.defaultCharset());
			String json = readFile(path, Charset.defaultCharset());
			return ValidationUtils.isJsonValid(schema,json);
		} catch (ProcessingException | IOException e) {
			return false;
			//e.printStackTrace();
		}
	}
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	
}
