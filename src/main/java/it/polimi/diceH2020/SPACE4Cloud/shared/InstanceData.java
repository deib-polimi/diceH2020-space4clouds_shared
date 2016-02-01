package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
public class InstanceData {
	private int gamma;

	private String provider;

	private List<JobClass> lstClass;

	private Map<Integer, List<TypeVM>> mapTypeVMs;


	private Map<TypeVMJobClassKey, Profile> mapProfiles;

	public InstanceData(int gamma, String provider, List<JobClass> classes, Map<Integer, List<TypeVM>> types,
			Map<TypeVMJobClassKey, Profile> profiles) {
		this.gamma = gamma;
		this.provider = provider;
		lstClass = classes;
		mapTypeVMs = types;
		mapProfiles = profiles;
	}

	public Map<TypeVMJobClassKey, Profile> getMapProfile(){
		return mapProfiles;
	}
	
	public void setMapProfile(Map<TypeVMJobClassKey, Profile> mapProfile){
		this.mapProfiles = mapProfile;
	}
	
	public InstanceData() {

	}

	public int getNumberJobs() {
		return lstClass.size();
	}

	public int getNumberTypeVM(Integer idClass) {
		return mapTypeVMs.get(idClass).size();
	}

	public List<Integer> getId_job() {
		return lstClass.stream().map(JobClass::getId).collect(Collectors.toList());
	}

	public List<Double> getJob_penalty() {
		return lstClass.stream().map(JobClass::getJob_penalty).collect(Collectors.toList());
	}

	public List<Double> getD(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getD());
	}
	
	public Double getD(TypeVMJobClassKey key) {
		List<TypeVMJobClassKey> lst = new ArrayList<>();
		lst.add(key);
		return getD(lst).get(0);

	}
	public Double getD(JobClass jobClass){
		TypeVMJobClassKey key = new TypeVMJobClassKey(jobClass.getId(), "");
		return this.getD(key);
	}

	public List<Integer> getNM(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getNM()).collect(Collectors.toList());
	}

	public List<Integer> getNR(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getNR()).collect(Collectors.toList());
	}

	public List<Integer> getHUp(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHup());
	}

	public List<Integer> getHLow(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapper(lstTypeJobClass, opt -> opt.get().getHlow());
	}

	private Stream<Integer> getStreamJobIDs(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(TypeVMJobClassKey::getJob);
	}

	private Stream<Optional<JobClass>> getStreamJobClasses(Stream<Integer> strmJobID) {
		return strmJobID.map(j -> lstClass.stream().filter(job -> job.getId() == j).findAny());
	}

	private <R> List<R> getFromMapper(List<TypeVMJobClassKey> lstTypeJobClass,
			Function<Optional<JobClass>, ? extends R> mapper) {
		Stream<Integer> strmJobID = getStreamJobIDs(lstTypeJobClass);
		Stream<Optional<JobClass>> strmJob = getStreamJobClasses(strmJobID);
		Stream<Optional<JobClass>> strm = strmJob.filter(Optional::isPresent);
		return strm.map(mapper).collect(Collectors.toList());
	}

	public List<Double> getMmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getMmax()).collect(Collectors.toList());
	}

	public List<Double> getMavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getMavg()).collect(Collectors.toList());
	}

	public List<Double> getRmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getRmax()).collect(Collectors.toList());
	}

	public List<Double> getRavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getRavg()).collect(Collectors.toList());
	}

	public List<Double> getSH1max(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSH1max()).collect(Collectors.toList());
	}

	public List<Double> getSHtypavg(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSHtypavg()).collect(Collectors.toList());
	}

	public List<Double> getSHtypmax(List<TypeVMJobClassKey> lstTypeJobClass) {
		return lstTypeJobClass.stream().map(k -> mapProfiles.get(k).getSHtypmax()).collect(Collectors.toList());
	}

	public List<Integer> getR(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapperTypeVM(lstTypeJobClass, opt -> opt.get().getR());
	}

	public Integer getR(TypeVMJobClassKey key){
		List<TypeVMJobClassKey> lst = new ArrayList<>();
		lst.add(key);
		return getR(lst).get(0);
	}
	
	
	public List<Double> getEta(List<TypeVMJobClassKey> lstTypeJobClass) {
		return getFromMapperTypeVM(lstTypeJobClass, opt -> opt.get().getEta());
	}
	
	public Double getEta(TypeVMJobClassKey key){
		List<TypeVMJobClassKey> lst = new ArrayList<>();
		lst.add(key);
		return getEta(lst).get(0);
	}
	

	private <R> List<R> getFromMapperTypeVM(List<TypeVMJobClassKey> lstTypeJobClass,
			Function<Optional<TypeVM>, ? extends R> mapper) {
		Stream<Optional<TypeVM>> strm = lstTypeJobClass.stream()
				.map(k -> mapTypeVMs.get(k.getJob()).stream().filter(tVM -> tVM.getId() == k.getTypeVM()).findAny())
				.filter(Optional::isPresent);
		return strm.map(mapper).collect(Collectors.toList());
	}

	public List<TypeVM> getLstTypeVM(JobClass jobClass) {
		return mapTypeVMs.get(jobClass.getId());
	}
	
	

	public Profile getProfile(JobClass jobClass, TypeVM tVM) {
		return mapProfiles.get(new TypeVMJobClassKey(jobClass.getId(), tVM.getId()));
	}
}
