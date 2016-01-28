package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceDataGenerator {

	// public InstanceDataOld applDataTest() {
	// int Gamma = 240; // num cores cluster
	// int[][] cM = { { 4, 8 }, { 4, 8 } };
	// int[][] cR = { { 4, 8 }, { 4, 8 } };
	// int[] nM = { 65, 65 };
	// int[] nR = { 15, 5 };
	// double[] mmax = { 9.128, 17.541 }; // maximum time to execute a single
	// // map
	// double[] mavg = { 4.103, 8.235 };
	// double[] rmax = { 0.592, 0.499 };
	// double[] ravg = { 0.327, 0.297 };
	// double[] sH1max = { 0, 0 };
	// double[] sHtypmax = { 7.942, 20.141 };
	// double[] sHtypavg = { 4.831, 14.721 };
	//
	// return new InstanceDataOld(Gamma, typeVm, provider, id_job, think, cM,
	// cR, eta, hUp, hLow, nM, nR, mmax, rmax,
	// mavg, ravg, d, sH1max, sHtypmax, sHtypavg, job_penalty, r);
	// }

	public static InstanceData build() {
		List<JobClass> lstClasses = lstJobClasses();
		List<TypeVM> lstTypeVMs = lstTypeVMs();
		Map<Integer,TypeVM> mapTypes = new HashMap<Integer, TypeVM>();
		for (JobClass job : lstClasses) {
			for (TypeVM typeVM : lstTypeVMs) {
				mapTypes.put(job.getId(), typeVM);
			}
		}

		Profile p1 = buildP1();
		Profile p2 = buildP2();
		Profile p3 = buildP1();
		Profile p4 = buildP2();

		Map<TypeVMJobClassKey, Profile> map = new HashMap<TypeVMJobClassKey, Profile>();
		TypeVMJobClassKey key1 = new TypeVMJobClassKey(lstClasses.get(0).getId(), lstTypeVMs.get(0).getId());
		map.put(key1, p1);
		TypeVMJobClassKey key2 = new TypeVMJobClassKey(lstClasses.get(0).getId(), lstTypeVMs.get(1).getId());
		map.put(key2, p2);
		TypeVMJobClassKey key3 = new TypeVMJobClassKey(lstClasses.get(1).getId(), lstTypeVMs.get(0).getId());
		map.put(key3, p3);
		TypeVMJobClassKey key4 = new TypeVMJobClassKey(lstClasses.get(1).getId(), lstTypeVMs.get(1).getId());
		map.put(key4, p4);

		return new InstanceData(240, lstClasses, mapTypes, map);

	}

	private static Profile buildP1() {
		Profile p = new Profile();
		p.setCM(4);
		p.setCR(4);
		p.setMavg(4.103);
		p.setMmax(9.128);
		p.setNM(65);
		p.setNR(15);
		p.setRavg(0.327);
		p.setRmax(0.592);
		p.setSH1max(0);
		p.setSHtypmax(7.942);
		p.setSHtypavg(4.831);
		return p;
	}

	private static Profile buildP2() {
		Profile p = new Profile();
		p.setCM(8);
		p.setCR(8);
		p.setMavg(8.235);
		p.setMmax(17.541);
		p.setNM(65);
		p.setNR(5);
		p.setRavg(0.297);
		p.setRmax(0.499);
		p.setSH1max(0);
		p.setSHtypmax(20.141);
		p.setSHtypavg(14.721);
		return p;
	}

	private static List<JobClass> lstJobClasses() {
		List<JobClass> lst = new ArrayList<>();
		JobClass class1 = new JobClass();
		class1.setD(180);
		class1.setHlow(5);
		class1.setHup(10);
		class1.setId(1);
		class1.setJob_penalty(25.0);
		class1.setThink(10);
		lst.add(class1);
		JobClass class2 = new JobClass();
		class2.setD(150);
		class2.setHlow(5);
		class2.setHup(10);
		class2.setId(2);
		class2.setJob_penalty(14.99);
		class2.setThink(5);
		lst.add(class2);
		return lst;
	}

	private static List<TypeVM> lstTypeVMs() {
		List<TypeVM> lst = new ArrayList<>();
		TypeVM t1 = new TypeVM();
		t1.setId("T1");
		t1.setProvider("Amazon");
		t1.setEta(0.1);
		t1.setR(30);
		lst.add(t1);
		TypeVM t2 = new TypeVM();
		t2.setId("T2");
		t2.setProvider("Amazon");
		t2.setEta(0.3);
		t2.setR(25);
		lst.add(t2);
		return lst;

	}

}
