package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.JobClass;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.Profile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.SolutionPerJob;

public class SolutionPerJobGenerator {

	public static SolutionPerJob build(int num){
		SolutionPerJob sol = new SolutionPerJob();
		TypeVM t1 = TypeVMGenerator.build(1);
	//	TypeVM t2 = TypeVMGenerator.build(2);
		switch (num) {
		case 1:
			
			sol.setTypeVMselected(t1);
			JobClass job1 = JobClassGenerator.build(1);
			sol.setJob(job1);	
			sol.setAlfa(1250.0);
			sol.setBeta(125.0);
			sol.setChanged(false);
			sol.setCost(8.5);
			sol.setDeltaBar(2.1);
			sol.setDuration(180.0);
			sol.setFeasible(false);
			sol.setNumberContainers(10);
			sol.setNumberUsers(10);
			sol.setNumCores(1);
			sol.setNumberVM(10);
			sol.setNumOnDemandVM(0);
			sol.setNumReservedVM(9);
			sol.setNumSpotVM(1);
			sol.setPos(0);			
			sol.setRhoBar(0.9);
			sol.setSigmaBar(0.4);
			Profile prof1 = ProfileGenerator.build(1);
			sol.setProfile(prof1);
			break;
		case 2:
	        JobClass job2 = JobClassGenerator.build(2);
	        sol.setJob(job2);
			sol.setTypeVMselected(t1);
			sol.setAlfa(749.5);
	        sol.setBeta(74.95);
	        sol.setChanged(false);
	        sol.setCost(10.3);
	        sol.setDeltaBar(2.1);
	        sol.setFeasible(false);
	        sol.setNumberContainers(12);
	        sol.setNumberUsers(10);
	        sol.setNumCores(1);
	        sol.setNumberVM(12);
	        sol.setNumOnDemandVM(0);
	        sol.setNumReservedVM(11);
	        sol.setNumSpotVM(1);
	        sol.setPos(1);
	        sol.setRhoBar(0.9);
	        sol.setSigmaBar(0.4);
	        sol.setDuration(150.0);
	        Profile prof2 = ProfileGenerator.build(1);
	        sol.setProfile(prof2);

		default:
			break;
		}
		return sol;
	}
}
