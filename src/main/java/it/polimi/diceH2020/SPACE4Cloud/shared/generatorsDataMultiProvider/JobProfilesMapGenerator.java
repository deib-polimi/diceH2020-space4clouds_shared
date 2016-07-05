package it.polimi.diceH2020.SPACE4Cloud.shared.generatorsDataMultiProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfilesMap;


public class JobProfilesMapGenerator {
	private static List<String> jobsIDs =  Arrays.asList("23","32","64");
	private static List<String> providers =  Arrays.asList("Amazon","Cineca");
	private static List<String> typesVMs =  Arrays.asList("medium","large");
	
	public static JobProfilesMap build() {
		List<JobProfile> lstTypeVMs = lstTypeVMs();
		
		Map<String,Map<String,Map<String,JobProfile>>> mapJobProfiles = new HashMap<String,Map<String,Map<String,JobProfile>>>();
		
		
		int counter = 0;
		
		Map<String,Map<String,JobProfile>> tmp2 = new HashMap<>();
		Map<String,JobProfile> tmp1 = new HashMap<>();
		for(String i : jobsIDs){
			for(String provider : providers){
				for(String typeVMs : typesVMs){
					tmp1.put(typeVMs, lstTypeVMs.get(counter%2)); //<-- %2 
					counter ++;
				}
				tmp2.put(provider,tmp1);
			}
			mapJobProfiles.put(i,tmp2);
		}
		
		return new JobProfilesMap(mapJobProfiles);
	}
	
	public static String[] getMapFirstEntryKeys(){
		String[] keys = new String[3]; 
		keys[0] = jobsIDs.get(0);
		keys[1] = providers.get(0);
		keys[2] = typesVMs.get(0);
		
		return keys;
	}

	private static List<JobProfile> lstTypeVMs() {
		List<JobProfile> lst = new ArrayList<>();
		JobProfile t1 = JobProfileGenerator.build(1);
		lst.add(t1);
		JobProfile t2 = JobProfileGenerator.build(2);
		lst.add(t2);
		return lst;
	}
}
