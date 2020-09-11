/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.word;

/**
 *
 * @author UNO
 */
public class Word {

    private String acronym;
    private String word_full;

    public Word() {
    }

    public Word(String acronym, String word_full) {
        this.acronym = acronym;
        this.word_full = word_full;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getWord_full() {
        return word_full;
    }

    public void setWord_full(String word_full) {
        this.word_full = word_full;
    }
}
