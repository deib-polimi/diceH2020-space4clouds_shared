/*
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
public class ClassParameters {

    private double D;
    private double U = 0.7;
    private int Hup;
    private int Hlow;
    private double penalty = 0.0;
    private double think;
    private double m;
    private double v;

    public boolean validate() {
        return D > 0. && Hup >= Hlow && Hlow >= 0 && penalty >= 0. && think >= 0.
                && m > 0. && v > 0. && U >= 0. && U <= 1.;
    }
}
