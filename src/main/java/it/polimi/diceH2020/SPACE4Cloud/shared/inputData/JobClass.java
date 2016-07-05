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

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class JobClass {

	private String id;
	private double D;
	private int Hup;
	private int Hlow;
	private double job_penalty;
	private double think;
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private double m;  //Optional, for retrocompatibility
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private double v;	 //Optional, for retrocompatibility

	public boolean validate(){
		return (D > 0 && Hup >= Hlow && Hup >0 && job_penalty >=0 && think>=0 && m > 0 && v > 0);		
	}
	
//	public double getM(){
//		return m.get();
//	}
//	
//	public double getV(){
//		return v.get();
//	}
}
