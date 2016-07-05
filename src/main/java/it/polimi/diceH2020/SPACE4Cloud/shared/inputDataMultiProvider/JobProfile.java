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
public class JobProfile {

	private int cM;
	private int cR;
	private double Mavg;
	private double Mmax;
	private double Ravg;
	private double Rmax;
	private double SH1max;
	private double SHtypavg;
	private double SHtypmax;
	private int NM;
	private int NR;

	public boolean validate() {
		return (cM > 0 && cR > 0 && Mavg > 0 && Mmax > 0 && Ravg > 0 && Rmax > 0 && SH1max >= 0 && SHtypavg >= 0 && SHtypmax >= 0 && NM > 0 && NR > 0);
	}
}
