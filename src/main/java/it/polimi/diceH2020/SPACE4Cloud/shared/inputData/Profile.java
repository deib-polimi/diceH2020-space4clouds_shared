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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputData;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
public class Profile {
	
	@JsonUnwrapped private Map<String,Double> profileMap;
	
	public Profile(){
		profileMap = new HashMap<String,Double>();
	}
	public Profile(Map<String,Double> profileMap){
		this.profileMap = profileMap;
	}
	
	public boolean validate() {
		if(profileMap == null) return false;
		if(profileMap.isEmpty()) return false;
		if(!profileMap.containsKey("h") || !profileMap.containsKey("x")) return false;
		
		return true;
	}
	
	@JsonIgnore
	public void put(String key, double value){
		profileMap.put(key, value);
	}
	
	@JsonIgnore
	public double get(String key){
		return profileMap.get(key);
		
	}
	
}
