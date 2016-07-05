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

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PrivateCloudParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfigurationsMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.settings.Scenarios;
import lombok.Data;

@Data
public class InstanceData {
	private String id;

	private int gamma;

	private String provider;
	
	private Optional<Scenarios> scenario;

	private List<JobClass> lstClass;

	@JsonDeserialize(keyUsing = TypeVMJobClassDeserializer.class, keyAs = TypeVMJobClassKey.class, contentAs = Profile.class)
	private Map<TypeVMJobClassKey, Profile> mapProfiles;

	@JsonInclude(Include.NON_EMPTY)
	private Optional<PrivateCloudParameters> privateCloudParameters;
	
	@JsonInclude(Include.NON_EMPTY)
	private Optional<VMConfigurationsMap> mapVMConfigurations; //@JsonUnwrapped 
	
	@JsonInclude(Include.NON_EMPTY)
	private Optional<Map<String, List<TypeVM>>> mapTypeVMs;
	
	//only for tests
	public InstanceData(String id, int gamma, String provider, List<JobClass> classes, Map<String, List<TypeVM>> types,
			Map<TypeVMJobClassKey, Profile> profiles) {
		this.id = id;
		this.setGamma(gamma);
		this.setProvider(provider);
		this.scenario = Optional.of(Scenarios.PublicPeakWorkload);
		lstClass = classes;
		mapTypeVMs = Optional.of(types);
		mapProfiles = profiles;
	}

	public InstanceData() {
		this.scenario = Optional.of(Scenarios.PublicPeakWorkload);
		this.privateCloudParameters = Optional.empty();
		this.mapVMConfigurations = Optional.empty();
		this.mapTypeVMs = Optional.empty();
	}

	@JsonIgnore
	public int getNumberJobs() {
		return lstClass.size();
	}

	public int getNumberTypeVM(Integer idClass) {
		return mapTypeVMs.get().get(idClass).size();
	}

	public List<String> getId_job() {
		return lstClass.stream().map(JobClass::getId).collect(toList());
	}

	public List<Double> getJob_penalty() {
		return lstClass.stream().map(JobClass::getJob_penalty).collect(toList());
	}

	public List<Double> getD(List<JobClass> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getD());
	}

	public Double getD(JobClass jobClass) {
		List<JobClass> lst = new ArrayList<>();
		lst.add(jobClass);
		return getD(lst).get(0);
	}

	public List<Integer> getNM(List<TypeVMJobClassKey> lstKeys) {
		return lstKeys.stream().map(k -> mapProfiles.get(k).getNM()).collect(toList());
	}

	public List<Integer> getNR(List<TypeVMJobClassKey> lstKeys) {
		return lstKeys.stream().map(k -> mapProfiles.get(k).getNR()).collect(toList());
	}

	public List<Integer> getHUp(List<JobClass> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHup());
	}

	public List<Integer> getHLow(List<JobClass> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHlow());
	}

	public List<JobClass> getListJobs(List<TypeVMJobClassKey> lstTypeJobClass) {
		List<JobClass> lst = new ArrayList<>();
		for (JobClass job : lstClass)
			for (TypeVMJobClassKey key : lstTypeJobClass)
				if (job.getId().equals(key.getJob()))
					lst.add(job);

		return lst;
	}

	private Stream<Optional<JobClass>> getStreamJobClasses(Stream<String> strmJobID) {
		return strmJobID.map(j -> lstClass.stream().filter(job -> job.getId().equals(j)).findAny());
	}

	private <R> List<R> getFromMapper(List<JobClass> lstTypeJobClass,
			Function<Optional<JobClass>, ? extends R> mapper) {
		Stream<String> strmJobID = lstTypeJobClass.stream().map(JobClass::getId);
		Stream<Optional<JobClass>> strmJob = getStreamJobClasses(strmJobID);
		Stream<Optional<JobClass>> strm = strmJob.filter(Optional::isPresent);
		return strm.map(mapper).collect(toList());
	}

	public List<Double> getMmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getMmax()).collect(toList());
	}

	public List<Double> getMavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getMavg()).collect(toList());
	}

	public List<Double> getRmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getRmax()).collect(toList());
	}

	public List<Double> getRavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getRavg()).collect(toList());
	}

	public List<Double> getSH1max(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSH1max()).collect(toList());
	}

	public List<Double> getSHtypavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSHtypavg()).collect(toList());
	}

	public List<Double> getSHtypmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSHtypmax()).collect(toList());
	}

	public List<Integer> getR(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapperTypeVM(lstTypeJobClass, opt -> opt.get().getR());
	}

	public Integer getR(TypeVMJobClassKey key) {
		List<TypeVMJobClassKey> lst = new ArrayList<>();
		lst.add(key);
		return getR(lst).get(0);
	}

	public List<Double> getEta(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapperTypeVM(lstTypeJobClass, opt -> opt.get().getEta());
	}

	public Double getEta(TypeVMJobClassKey key) {
		List<TypeVMJobClassKey> lst = new ArrayList<>();
		lst.add(key);
		return getEta(lst).get(0);
	}

	private <R> List<R> getFromMapperTypeVM(List<TypeVMJobClassKey> lstTypeJobClass,
			Function<Optional<TypeVM>, ? extends R> mapper) {
		Stream<Optional<TypeVM>> strm = lstTypeJobClass.stream()
				.map(k -> mapTypeVMs.get().get(k.getJob()).stream().filter(tVM -> tVM.getId() == k.getTypeVM()).findAny())
				.filter(Optional::isPresent);
		return strm.map(mapper).collect(toList());
	}

	public List<TypeVM> getLstTypeVM(JobClass jobClass) {
		return mapTypeVMs.get().get(jobClass.getId());
	}

	public Profile getProfile(JobClass jobClass, TypeVM tVM) {
		TypeVMJobClassKey key = new TypeVMJobClassKey();
		key.setJob(jobClass.getId());
		key.setTypeVM(tVM.getId());
		return mapProfiles.get(key);
	}

	public int getGamma() {
		return gamma;
	}

	public void setGamma(int gamma) {
		this.gamma = gamma;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
}
