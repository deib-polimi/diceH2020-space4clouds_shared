package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.util.List;
import java.util.stream.Collectors;

public class matrix<T> {

	private  List<List<T>> matrix;// = new ArrayList<List<T>>();

	public T get(int row, int col) {
		return matrix.get(row).get(col);
	}
	
	public void set(List<List<T>> mat){
		matrix = mat;
	}
	
	public void set(int rowNum, List<T> row){
		matrix.add(rowNum, row);
	}
	

	@Override
	public String toString() {
		return matrix.stream().map(row -> mapperString(row)).collect(Collectors.joining("\n"));
	}

	private String mapperString(List<T> row) {
		return row.stream().map(T::toString).collect(Collectors.joining(" ", "| ", " |"));
	}
}
