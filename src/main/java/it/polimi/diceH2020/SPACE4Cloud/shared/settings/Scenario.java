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

   ///The type of model used by MILP solver
   @Column(name = "model")
   private final AMPLModel model;

   ///The target technology
   @Column(name = "technology")
   private final Technology technology;

   ///In case of public cloud if LTC VM are available
   @Column(name = "long_term_commitment")
   private final Boolean longTermCommitment;

   ///In case of private cloud is private assignment is considered
   @Column(name = "physical_assignment")
   private final Boolean physicalAssignment;

   ///In case of private cloud if there is admission control for submitted jobs
   @Column(name = "admission_control")
   private final Boolean admissionControl;

   /**
    * Empty constructor used for testing
    */
   public Scenario() {
      this.technology = Technology.SPARK; 
      this.cloudType = CloudType.PUBLIC;
      this.model = AMPLModel.KNAPSACK ;
      this.longTermCommitment = true;
      this.physicalAssignment = null;
      this.admissionControl = null;
   }

   /**
    * Constructor
    * @param technology is the target technlogy
    * @param cloudType is the type of the cloud (public or private)
    * @param longTermCommitment specifies if LTC are available (significant only for public case)
    * @param physicalAssignment specifies if physical assignment has to be considered (significant only for private case)
    */
   public Scenario(Technology technology, CloudType cloudType, Boolean longTermCommitment, Boolean physicalAssignment, Boolean admissionControl) throws RuntimeException {
      this.technology = technology; 
      this.cloudType = cloudType;
      this.model = AMPLModel.KNAPSACK ;
      this.longTermCommitment = longTermCommitment;
      this.physicalAssignment = physicalAssignment;
      this.admissionControl = admissionControl;
      if(cloudType == CloudType.PUBLIC) {
         if(longTermCommitment == null) {
            throw new RuntimeException("Presence of longTermCommitment not set for public case");
         }
      } else if(longTermCommitment != null) {
         throw new RuntimeException("Presence of LTC set for private case");
      }
      if(cloudType == CloudType.PRIVATE && technology == Technology.STORM) {
         throw new RuntimeException("Private cloud cannot be selected for STORM");
      }
      if(cloudType == CloudType.PRIVATE && technology != Technology.STORM) {
         if(physicalAssignment == null) {
            throw new RuntimeException("Physical assignment not set for private case");
         }
      } else if(physicalAssignment != null) {
         throw new RuntimeException("Physical assignment set for public  or storm case");
      }
      if(cloudType == CloudType.PRIVATE && physicalAssignment == true && admissionControl == false) {
         throw new RuntimeException("Physical assignment and no admission control set for private case");
      }
   }

   /**
    * Create Scenario starting from its string representation
    * @param stringRepresentation is the string representation created by CreateStringRepresentation
    */
   static public Scenario generateScenario(String stringRepresentation) {
      String[] tokens = stringRepresentation.split("_", -1);
      if(tokens.length != 5) {
         throw new RuntimeException("Unrecognized scenario string representation: " + stringRepresentation + " Number of tokens is " + tokens.length);
      }
      final Technology technology = Technology.valueOf(tokens[0]);
      final CloudType cloudType = CloudType.valueOf(tokens[1]);
      final Boolean longTermCommitment = !tokens[2].isEmpty() ? Boolean.valueOf(tokens[2]) : null;
      final Boolean physicalAssignment = !tokens[3].isEmpty() ? Boolean.valueOf(tokens[3]) : null;
      final Boolean admissionControl = !tokens[4].isEmpty() ? Boolean.valueOf(tokens[4]) : null;
      return new Scenario(technology, cloudType, longTermCommitment, physicalAssignment, admissionControl);
   }

   /**
    * Generate a string representation of this scenario to be used as argument of WS
    */
   @JsonIgnore
      public String getStringRepresentation() {
         return technology.name() + "_" + cloudType.name() + "_" + (longTermCommitment != null ? longTermCommitment.toString() : "") + "_" + (physicalAssignment != null ? physicalAssignment.toString() : "") + "_" + (admissionControl != null ? admissionControl.toString() : "");
   }

   /**
    * Return a text description of the scenario
    */
   @JsonIgnore
   public String getDescription() {
      String ret_value = "";
      ret_value += technology.name();
      ret_value += " - " + cloudType.name();
      ret_value += (longTermCommitment != null) ? (longTermCommitment ? " with LTC" : " without LTC") : "";
      ret_value += (admissionControl != null) ? (admissionControl ? " with Admission Control" : " without Admission Control") : "";
      ret_value += (physicalAssignment != null && physicalAssignment) ? " with Physical Assignment" : "";
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
      ret_value += (longTermCommitment != null) ? (longTermCommitment ? " LTC" : "") : "";
      ret_value += (admissionControl != null) ? (admissionControl ? " AC" : "") : "";
      ret_value += (physicalAssignment != null && physicalAssignment) ? " PA" : "";
      return ret_value;
   }
}
