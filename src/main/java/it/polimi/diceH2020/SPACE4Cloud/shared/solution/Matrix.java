/*
Copyright 2016 Jacopo Rigoli
Copyright 2016 Eugenio Gianniti

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package it.polimi.diceH2020.SPACE4Cloud.shared.solution;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfigurationsMap;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Matrix {

    private Map<String, SolutionPerJob[]> matrix;

    private Map<Integer, String> mapForNotFailedRows;

    public Matrix() {
        matrix = new ConcurrentHashMap<>();
        mapForNotFailedRows = new HashMap<>();
    }

    public void put(String key, SolutionPerJob[] value ) {
        matrix.put(key, value);
    }

    public List<SolutionPerJob> getAllSolutions() {
        return matrix.values().stream().flatMap(Arrays::stream).collect(Collectors.toList());
    }

    public String getIdentifier() {
        String id = matrix.entrySet().iterator().next().getValue()[0].getParentID();
        String classIDs = "";
        String deadlines = "";
        String concurrency = "";

        for(Entry<String, SolutionPerJob[]> spjs : matrix.entrySet()) {
            SolutionPerJob spj = spjs.getValue()[0];
            classIDs += spj.getId();
            deadlines += "D" + spj.getJob().getD() + " ";
            concurrency += "H" + getHlow(spjs.getKey()) + "-" + getHup(spjs.getKey()) + " ";
        }

        return id + classIDs + deadlines + concurrency;
    }

    public SolutionPerJob[] get(String key) {
        return matrix.get(key);
    }

    public Set<Map.Entry<String, SolutionPerJob[]>> entrySet() {
        return matrix.entrySet();
    }

    public Map<String, SolutionPerJob[]> get() {
        return matrix;
    }

    public Iterable<Integer> getAllH(String row) {
        return Arrays.stream(matrix.get(row)).map(SolutionPerJob::getNumberUsers).collect(Collectors.toList());
    }

    public int getHlow(String row) {
        return matrix.get(row)[0].getJob().getHlow();
    }

    public int getHup(String row) {
        int pos = matrix.get(row).length;
        return matrix.get(row)[pos-1].getJob().getHup();
    }

    public Iterable<Double> getAllCost(String row) {
        restoreInitialHup();
        return Arrays.stream(matrix.get(row)).map(SolutionPerJob::getCost).collect(Collectors.toList());
    }

    public Iterable<Double> getAllPenalty(String row) {
        restoreInitialHup();
        return Arrays.stream(matrix.get(row)).map(spj ->
                (spj.getJob().getHup() - spj.getNumberUsers()) * spj.getJob().getPenalty())
                .collect(Collectors.toList());
    }

    // TODO Why is this called restoreInitialHup if it also modifies HLow and cost?
    // TODO Why don't you save these values the first time you get the data? What guarantees that these values are the initial ones?
    // TODO Anyhow, why should you search the whole Matrix over and over again if they are the initial values?
    private void restoreInitialHup() {
        for (SolutionPerJob[] jobs : matrix.values()) {
            OptionalInt maybeHUp = Arrays.stream(jobs).mapToInt(spj -> spj.getJob().getHup()).max();
            OptionalInt maybeHLow = Arrays.stream(jobs).mapToInt(spj -> spj.getJob().getHlow()).min();
            int spjHUp = maybeHUp.orElse(0);
            int spjHLow = maybeHLow.orElse(spjHUp);
            for (SolutionPerJob spj : jobs) {
                ClassParameters job = spj.getJob();
                job.setHup(spjHUp);
                job.setHlow(spjHLow);
                spj.setCost();
            }
        }
    }

    private List<String> getAllSelectedVMid(String row) {
        return Arrays.stream(matrix.get(row)).map(SolutionPerJob::getTypeVMselected)
                .map(TypeVM::getId).collect(Collectors.toList());
    }

    public List<Double> getAllMtilde(String row, VMConfigurationsMap vmConfigurations) {
        return getAllSelectedVMid(row).stream().map(id ->
                vmConfigurations.getMapVMConfigurations().get(id).getMemory()).collect(Collectors.toList());
    }

    public List<Double> getAllVtilde(String row, VMConfigurationsMap vmConfigurations) {
        return getAllSelectedVMid(row).stream().map(id ->
                vmConfigurations.getMapVMConfigurations().get(id).getCore()).collect(Collectors.toList());
    }

    /**
     *
     * @param row class ID
     * @param concurrencyLevel H of the given column
     * @return SolutionPerJob
     */
    public SolutionPerJob getCell(String row, int concurrencyLevel) {
        return Arrays.stream(matrix.get(row)).filter(s ->
                s.getNumberUsers() == concurrencyLevel).findFirst().get();
    }

    public String getID(String row) {
        return Arrays.stream(matrix.get(row)).findFirst().map(SolutionPerJob::getId).get();
    }

    public List<Integer> getAllNu(String row) {
        return Arrays.stream(matrix.get(row)).map(spj ->
                spj.getNumReservedVM() + spj.getNumOnDemandVM() + spj.getNumSpotVM()).collect(Collectors.toList());
    }

    public int getNumRows() {
        return matrix.size();
    }

    /**
     * negative cells
     */
    public Matrix removeFailedSimulations() throws MatrixHugeHoleException {
        Matrix matrixWithHoles = new Matrix();
        for (Map.Entry<String, SolutionPerJob[]> matrixRow : matrix.entrySet()) {
            final int count = (int) Arrays.stream(matrixRow.getValue()).map(SolutionPerJob::getNumberVM)
                    .filter(v -> v > 0).count();
            if (count != 0) {
                SolutionPerJob[] rowWithHoles = Arrays.stream(matrixRow.getValue())
                        .filter(spj -> spj.getNumberVM() > 0).collect(Collectors.toList())
                        .toArray(new SolutionPerJob[count]);
                matrixWithHoles.put(matrixRow.getKey(), rowWithHoles);
            } else {
                throw new MatrixHugeHoleException("All Simulations of Matrix row " +
                        matrix.get(matrixRow.getValue()[0].getId()).toString() + ", have failed!");
            }
        }
        return matrixWithHoles;
    }

    public int getNumCells() {
        return matrix.values().stream().mapToInt(v -> v.length).sum();
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Optimality Matrix(for solution");
        builder.append(matrix.entrySet().iterator().next().getValue()[0].getParentID());
        builder.append("):\n");
        matrix.forEach((k, v) -> {
            builder.append(v[0].getId());
            builder.append("\t| ");
            for (SolutionPerJob cell: v) {
                builder.append(" H:");
                builder.append(cell.getNumberUsers());
                builder.append(",nVM:");
                builder.append(cell.getNumberVM());
                builder.append(cell.getFeasible() ? ",F  \t|" : ",I  \t|");
            }
            builder.append("\n   \t| ");
            for (SolutionPerJob cell: v) {
                builder.append(" dur: ");
                builder.append(cell.getDuration().intValue());
                builder.append("  \t|");
            }
            builder.append('\n');
        });
        return builder.toString();
    }

    public String getNotFailedRow(Integer key) {
        return mapForNotFailedRows.get(key);
    }

    public int numNotFailedRows() {
        return mapForNotFailedRows.size();
    }

    public void addNotFailedRow(Integer key, String value) {
        mapForNotFailedRows.put(key, value);
    }

}
