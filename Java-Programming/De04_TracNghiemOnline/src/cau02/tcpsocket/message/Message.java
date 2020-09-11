/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.message;

import cau02.tcpsocket.question.Question;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author UNO
 */
public class Message implements Serializable{
    ArrayList<Question> questions;
    private ArrayList<String> answer;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
