package controllers;

public class ScoreData {
    private static Integer score = -1;

    public static Integer getScore() {
        return score;
    }

    public static void setScore(Integer score) {
        ScoreData.score = score;
    }
}
