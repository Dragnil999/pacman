package controllers;

public class ScoreData {
    private static Integer score;

    public static Integer getScore() {
        return score;
    }

    public static void setScore(Integer score) {
        ScoreData.score = score;
    }
}
