package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class TypeVMJobClassKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int job;
	private String typeVM;
	
	public TypeVMJobClassKey(int jobClass, String typeVM){
		this.job = jobClass;
		this.typeVM = typeVM;
	}
	
//    @Override
//    public boolean equals(Object obj) {
//        if(obj != null && obj instanceof TypeVMJobClassKey) {
//        	TypeVMJobClassKey s = (TypeVMJobClassKey) obj;
//            return job.equals(s.job) && typeVM.equals(s.typeVM);
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return (job.toString() +typeVM.toString()).hashCode();
//    }
//	
}
