package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.JobClass;

public class JobClassGenerator {
	public static JobClass build(int num) {
		JobClass job = new JobClass();
		switch (num) {
		case 1:
			job.setId(1);
			job.setD(180.0);
			job.setHlow(5);
			job.setHup(10);
			job.setThink(10.0);
			job.setJob_penalty(25.0);
			break;
		case 2:
			job.setId(2);
			job.setD(150.0);
			job.setHlow(5);
			job.setHup(10);
			job.setThink(5.0);
			job.setJob_penalty(14.99);
		default:
			break;
		}
		return job;
	}
}
