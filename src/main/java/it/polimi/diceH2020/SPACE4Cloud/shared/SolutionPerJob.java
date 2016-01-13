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

public class SolutionPerJob {

	private double alfa;
	private double beta;
	private boolean feasible;
	private boolean changed;
	private int idxVmTypeSelected;
	private int numberContainers;
	private int numberUsers;
	private int numberVM;
	
	private double numSpotVM;
	private double numReservedVM;
	private double numOnDemandVM;
	
	private double cost;
	
	public double getNumSpotVM() {
		return numSpotVM;
	}

	public void setNumSpotVM(double numSpotVM) {
		this.numSpotVM = numSpotVM;
	}

	public double getNumReservedVM() {
		return numReservedVM;
	}

	public void setNumReservedVM(double numReservedVM) {
		this.numReservedVM = numReservedVM;
	}

	public double getNumOnDemandVM() {
		return numOnDemandVM;
	}

	public void setNumOnDemandVM(double numOnDemandVM) {
		this.numOnDemandVM = numOnDemandVM;
	}

	private double simulatedTime;
	private String typeVMselected;
	private int numCores;

	private int pos;

	public double getAlfa() {
		return alfa;
	}

	public double getBeta() {
		return beta;
	}

	public int getIdxVmTypeSelected() {
		return idxVmTypeSelected;
	}

	public int getNumberContainers() {
		return numberContainers;
	}

	public int getNumberUsers() {
		return numberUsers;
	}

	public int getNumberVM() {
		return numberVM;
	}

	public double getSimulatedTime() {
		return simulatedTime;
	}

	public String getTypeVMselected() {
		return typeVMselected;
	}

	public boolean isFeasible() {
		return feasible;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public void setIdxVmTypeSelected(int idxVmTypeSelected) {
		this.idxVmTypeSelected = idxVmTypeSelected;
	}

	public void setNumberContainers(int numberContainers) {
		this.numberContainers = numberContainers;
	}

	public void setNumberUsers(int numberUsers) {
		this.numberUsers = numberUsers;
	}

	public void setNumberVM(int numberVM) {
		this.numberVM = numberVM;
	}

	public void setSimulatedTime(double simulatedTime) {
		this.simulatedTime = simulatedTime;
	}

	public void setTypeVMselected(String typeVMselected) {
		this.typeVMselected = typeVMselected;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getNumCores() {
		return numCores;
	}

	public void setNumCores(int numCores) {
		this.numCores = numCores;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
