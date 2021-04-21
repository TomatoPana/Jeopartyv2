package com.mdlozano.jeopartyv2;

public class Question {
    int id;
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String rightAnswer;
    String category;
    String score;

    public Question (int id, String question, String answer1, String answer2, String answer3, String answer4, String rightAnswer, String category, String score){
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
        this.category = category;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getScore() {
        return score;
    }
}
