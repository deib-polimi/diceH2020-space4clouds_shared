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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputData.old;

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

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobMLProfilesMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PrivateCloudParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVMJobClassKey;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfigurationsMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.settings.CloudType;
import it.polimi.diceH2020.SPACE4Cloud.shared.settings.Scenario;
import it.polimi.diceH2020.SPACE4Cloud.shared.settings.Technology;
import lombok.Data;

@Data
public class InstanceData_old {
	private String id;

	private int gamma;

	private String provider;
	
	private Optional<Scenario> scenario;

	private List<JobClass_old> lstClass;

	@JsonDeserialize(keyUsing = TypeVMJobClassDeserializer_old.class, keyAs = TypeVMJobClassKey.class, contentAs = Profile_old.class)
	private Map<TypeVMJobClassKey, Profile_old> mapProfiles;

	@JsonInclude(Include.NON_EMPTY)
	private Optional<PrivateCloudParameters> privateCloudParameters;
	
	@JsonInclude(Include.NON_EMPTY)
	private Optional<VMConfigurationsMap> mapVMConfigurations; //@JsonUnwrapped 
	
	@JsonInclude(Include.NON_EMPTY)
	private Optional<Map<String, List<TypeVM>>> mapTypeVMs;
	
	@JsonInclude(Include.NON_EMPTY)
	private JobMLProfilesMap mapJobMLProfiles;
	
	//only for tests
	public InstanceData_old(Technology technology, String id, int gamma, String provider, List<JobClass_old> classes, Map<String, List<TypeVM>> types, Map<TypeVMJobClassKey, Profile_old> profiles) {
		this.id = id;
		this.setGamma(gamma);
		this.setProvider(provider);
		this.scenario = Optional.of(new Scenario(technology, CloudType.PUBLIC, true, null, null));
		lstClass = classes;
		mapTypeVMs = Optional.of(types);
		mapProfiles = profiles;
	}

	public InstanceData_old() {
		this.scenario = Optional.of(new Scenario(Technology.SPARK, CloudType.PUBLIC, true, null, null));
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
		return lstClass.stream().map(JobClass_old::getId).collect(toList());
	}

	public List<Double> getJob_penalty() {
		return lstClass.stream().map(JobClass_old::getJob_penalty).collect(toList());
	}

	public List<Double> getD(List<JobClass_old> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getD());
	}

	public Double getD(JobClass_old jobClass) {
		List<JobClass_old> lst = new ArrayList<>();
		lst.add(jobClass);
		return getD(lst).get(0);
	}

	public List<Integer> getNM(List<TypeVMJobClassKey> lstKeys) {
		return lstKeys.stream().map(k -> mapProfiles.get(k).getNm()).collect(toList());
	}

	public List<Integer> getNR(List<TypeVMJobClassKey> lstKeys) {
		return lstKeys.stream().map(k -> mapProfiles.get(k).getNr()).collect(toList());
	}

	public List<Integer> getHUp(List<JobClass_old> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHup());
	}

	public List<Integer> getHLow(List<JobClass_old> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHlow());
	}

	public List<JobClass_old> getListJobs(List<TypeVMJobClassKey> lstTypeJobClass) {
		List<JobClass_old> lst = new ArrayList<>();
		for (JobClass_old job : lstClass)
			for (TypeVMJobClassKey key : lstTypeJobClass)
				if (job.getId().equals(key.getJob()))
					lst.add(job);

		return lst;
	}

	private Stream<Optional<JobClass_old>> getStreamJobClasses(Stream<String> strmJobID) {
		return strmJobID.map(j -> lstClass.stream().filter(job -> job.getId().equals(j)).findAny());
	}

	private <R> List<R> getFromMapper(List<JobClass_old> lstTypeJobClass,
			Function<Optional<JobClass_old>, ? extends R> mapper) {
		Stream<String> strmJobID = lstTypeJobClass.stream().map(JobClass_old::getId);
		Stream<Optional<JobClass_old>> strmJob = getStreamJobClasses(strmJobID);
		Stream<Optional<JobClass_old>> strm = strmJob.filter(Optional::isPresent);
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
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSh1max()).collect(toList());
	}

	public List<Double> getSHtypavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getShtypavg()).collect(toList());
	}

	public List<Double> getSHtypmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getShtypmax()).collect(toList());
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

	public List<TypeVM> getLstTypeVM(JobClass_old jobClass) {
		return mapTypeVMs.get().get(jobClass.getId());
	}

	public Profile_old getProfile(JobClass_old jobClass, TypeVM tVM) {
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
