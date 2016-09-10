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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import lombok.Data;

@Data
public class PublicCloudParameters {
	
	private double eta;
	private int R;
	
	public PublicCloudParameters(int R){
		this.R = R;
		this.eta = 0;
	}
	
	public PublicCloudParameters(){
		this.R = 0;
		this.eta = 0;
	}

	public boolean validate(){
		return (eta>=0 && R>=0);
	}
}
