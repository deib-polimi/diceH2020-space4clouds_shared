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
import javax.persistence.*;
import it.polimi.diceH2020.SPACE4Cloud.shared.settings.CloudType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class describing an application scenario
 */
@Getter
@Entity
public class Scenario {
   @Id @GeneratedValue
   @Column(name = "id")
   @JsonIgnore
   private int id;

   ///The type of target cloud (public or private)
   @Column(name = "cloud_type")
   @Enumerated(EnumType.STRING)
   private final CloudType cloudType;

   ///The target technology
   @Column(name = "technology")
   private final Technology technology;

   ///In case of public cloud if complex pricing model is used 
   @Column(name = "use_complex_pricing_model")
   private final Boolean useComplexPricingModel;

   ///In case of private cloud if there is admission control for submitted jobs
   @Column(name = "admission_control")
   private final Boolean admissionControl;

   /**
    * Empty constructor used for testing
    */
   public Scenario() {
      this.technology = Technology.SPARK; 
      this.cloudType = CloudType.PUBLIC;
      this.useComplexPricingModel = true;
      this.admissionControl = null;
   }

   /**
    * Constructor
    * @param technology is the target technlogy
    * @param cloudType is the type of the cloud (public or private)
    * @param useComplexPricingModel is true if complex pricing model has to be used 
    */
   public Scenario(Technology technology, CloudType cloudType, Boolean useComplexPricingModel, Boolean admissionControl) throws RuntimeException {
      this.technology = technology; 
      this.cloudType = cloudType;
      this.useComplexPricingModel = useComplexPricingModel; 
      this.admissionControl = admissionControl;
      if(cloudType == CloudType.PUBLIC) {
         if(useComplexPricingModel == null) {
            throw new RuntimeException("Usage of complex pricing model not set for public case");
         }
      } else if(useComplexPricingModel != null) {
         throw new RuntimeException("Usage of complex princing model not set for private case");
      }
      if(cloudType == CloudType.PRIVATE && technology == Technology.STORM) {
         throw new RuntimeException("Private cloud cannot be selected for STORM");
      }
   }

   /**
    * Create Scenario starting from its string representation
    * @param stringRepresentation is the string representation created by CreateStringRepresentation
    */
   static public Scenario generateScenario(String stringRepresentation) {
      String[] tokens = stringRepresentation.split("_", -1);
      if(tokens.length != 4) {
         throw new RuntimeException("Unrecognized scenario string representation: " + stringRepresentation + " Number of tokens is " + tokens.length);
      }
      final Technology technology = Technology.valueOf(tokens[0]);
      final CloudType cloudType = CloudType.valueOf(tokens[1]);
      final Boolean useComplexPricingModel = !tokens[2].isEmpty() ? Boolean.valueOf(tokens[2]) : null;
      final Boolean admissionControl = !tokens[3].isEmpty() ? Boolean.valueOf(tokens[3]) : null;
      return new Scenario(technology, cloudType, useComplexPricingModel, admissionControl);
   }

   /**
    * Generate a string representation of this scenario to be used as argument of WS
    */
   @JsonIgnore
      public String getStringRepresentation() {
         return technology.name() + "_" + cloudType.name() + "_" + (useComplexPricingModel != null ? useComplexPricingModel.toString() : "") + "_" +  (admissionControl != null ? admissionControl.toString() : "");
   }

   /**
    * Return a text description of the scenario
    */
   @JsonIgnore
   public String getDescription() {
      String ret_value = "";
      ret_value += technology.name();
      ret_value += " - " + cloudType.name();
      ret_value += (useComplexPricingModel != null) ? (useComplexPricingModel ? " with Complex Pricing Model" : " without Complex Pricing Model") : "";
      ret_value += (admissionControl != null) ? (admissionControl ? " with Admission Control" : " without Admission Control") : "";
      return ret_value;
   }

   /**
    * Return a short text description of the scenario
    */
   @JsonIgnore
   public String getShortDescription() {
      String ret_value = "";
      ret_value += technology.name();
      ret_value += " - " + (cloudType == CloudType.PUBLIC ? "Pub." : "Pr.");
      ret_value += (useComplexPricingModel != null) ? (useComplexPricingModel ? " with Complex Pricing Model" : "") : "";
      ret_value += (admissionControl != null) ? (admissionControl ? " AC" : "") : "";
      return ret_value;
   }
}
