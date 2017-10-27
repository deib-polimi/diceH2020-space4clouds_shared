/*
 * Copyright 2017 Marco Lattuada
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
package it.polimi.diceH2020.SPACE4Cloud.shared.settings;

import lombok.Getter;

/**
 * Class describing an application scenario
 */
@Getter
public class Scenario {
   ///The type of target cloud (public or private)
   private final CloudType cloudType;

   ///The type of model used by MILP solver
   private final AMPLModel model;

   ///The target technology
   private final Technology technology;

   ///In case of public cloud if LTC VM are available
   private final boolean LTC;

   /**
    * Empty constructor used for testing
    */
   public Scenario() {
        this.technology = Technology.SPARK; 
        this.cloudType = CloudType.PUBLIC;
        this.model = AMPLModel.KNAPSACK ;
        this.LTC = true;
   }

   /**
    * Constructor
    * @param technology is the target technlogy
    * @param cloudType is the type of the cloud (public or private)
    * @param LTC specifies if LTC are available (public case)
    */
   public Scenario(Technology technology, CloudType cloudType, boolean LTC) {
        this.technology = technology; 
        this.cloudType = cloudType;
        this.model = AMPLModel.KNAPSACK ;
        this.LTC = LTC;
    }

}
