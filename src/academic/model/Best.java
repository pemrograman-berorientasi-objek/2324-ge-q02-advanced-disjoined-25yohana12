package academic.model;

public class Best {
    private String year;
    private String bestGradeOdd;
    private String highestGradeEven;
    
    public Best(String year, String bestGradeOdd, String highestGradeEven) {
        this.year = year;
        this.bestGradeOdd = bestGradeOdd;
        this.highestGradeEven = highestGradeEven;
    }

    public String getYear() {
        return year;
    }

    public String getbestGradeOdd() {
        return bestGradeOdd;
    }

    public String getHighestGradeEven() {
        return highestGradeEven;
    }
}
 