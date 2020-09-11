/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.question;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class Question implements Serializable{
    private int id;
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String correct_answer;
    
    public Question() {
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", question=" + question + ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + '}';
    }
}
