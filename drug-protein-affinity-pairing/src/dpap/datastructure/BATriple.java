package dpap.datastructure;

import java.text.DecimalFormat;

/**
 * Created by sofia on 2/8/14.
 */

/**
 * Data structure to represent <protein, drug, binding affinity value> triple.
 *
 * @author sofia
 */
public class BATriple implements Comparable<BATriple> {

    private String proteinName;
    private String drugName;
    private double baValue;

    public BATriple() {}

    public BATriple(String proteinName, String drugName, double baValue) {
        this.proteinName = proteinName;
        this.drugName = drugName;
        this.baValue = baValue;
    }

    @Override
    // establish sorting order by descending order of BA value and lexicographical order of protein and drug names 
    public int compareTo(BATriple other) {
        if (this.baValue > other.baValue) {
            return -1;
        } else if (this.baValue < other.baValue) {
            return 1;
        } else {
            if (this.proteinName.toLowerCase().compareTo(other.proteinName.toLowerCase()) != 0) {
                return this.proteinName.toLowerCase().compareTo(other.proteinName.toLowerCase());
            } else {
                return this.drugName.toLowerCase().compareTo(other.drugName.toLowerCase());
            }
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("00.000");
        return ("(" + df.format(baValue) + ": " + proteinName + ", " + drugName + ")");
    }

    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getBaValue() {
        return baValue;
    }

    public void setBaValue(double baValue) {
        this.baValue = baValue;
    }

}
