/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.word;

import java.io.Serializable;

/**
 *
 * @author UNO
 */
public class Word {

    private String word_eng;
    private String word_vie;
    private String meaning;

    public Word() {
    }

    public Word(String word_eng, String word_vie, String meaning) {
        this.word_eng = word_eng;
        this.word_vie = word_vie;
        this.meaning = meaning;
    }

    public String getWord_eng() {
        return word_eng;
    }

    public void setWord_eng(String word_eng) {
        this.word_eng = word_eng;
    }

    public String getWord_vie() {
        return word_vie;
    }

    public void setWord_vie(String word_vie) {
        this.word_vie = word_vie;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "Word{" + "word_eng=" + word_eng + ", word_vie=" + word_vie + ", meaning=" + meaning + '}';
    }
}
