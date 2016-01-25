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

import lombok.Data;

@Data
public class SolutionPerJob {

	private double alfa;
	private double beta;
	private boolean changed;
	private double cost;
	private double deltaBar;
	private boolean feasible;
	private int idxVmTypeSelected;
	private int numberContainers;
	private int numberUsers;
	private int numberVM;
	private int numCores;

	private int numOnDemandVM;
	private int numReservedVM;
	private int numSpotVM;

	private int pos;
	private double rhoBar;
	private double sigmaBar;

	private double duration;

	private String typeVMselected;

	private Double eta = null;
	private int R;
	private double D;

	public double getAlfa() {
		return alfa;
	}

	public double getBeta() {
		return beta;
	}

	public double getCost() {
		return cost;
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

	public int getNumCores() {
		return numCores;
	}

	public int getPos() {
		return pos;
	}

	public String getTypeVMselected() {
		return typeVMselected;
	}

	public boolean isChanged() {
		return changed;
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

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
		if (eta != null) {
			this.numSpotVM = (int) Math.floor(eta * this.numberVM);
			this.numReservedVM = (int) Math.min(R, (this.numberVM - numSpotVM));
			this.numOnDemandVM = Math.max(0, this.numberVM - numSpotVM - numReservedVM);
		}
	}

	public void setNumCores(int numCores) {
		this.numCores = numCores;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setTypeVMselected(String typeVMselected) {
		this.typeVMselected = typeVMselected;
	}

}
