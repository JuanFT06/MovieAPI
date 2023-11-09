/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digis01.JFigueroa_MovieAPI.entities;

import java.util.List;

/**
 *
 * @author digis
 */
public class Result {
    private List<Movie> results;

    public Result() {
    }

    public Result(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    
    
    
}
