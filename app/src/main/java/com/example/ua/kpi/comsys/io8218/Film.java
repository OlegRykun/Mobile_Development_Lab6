package com.example.ua.kpi.comsys.io8218;

public class Film {
    private String filmName;
    private String filmYear;
//    private String filmGenre;
//    private String filmDirector;
//    private String filmWriter;
//    private String filmActors;
//    private String filmCountry;
//    private String filmLanguage;
//    private String filmProduction;
//    private String filmRelease;
//    private String filmRuntime;
//    private String filmAwards;
//    private String filmRating;
//    private String filmPlot;
    private String filmType;
    private String filmPoster;

    public Film(String filmName, String filmYear, String filmGenre, String filmDirector, String filmWriter,
                String filmActors, String filmCountry, String filmLanguage, String filmProduction,
                String filmRelease, String filmRuntime, String filmAwards, String filmRating, String filmPlot, String filmType, String filmPoster) {
        this.filmName = filmName;
        this.filmYear = filmYear;
//        this.filmGenre = filmGenre;
//        this.filmDirector = filmDirector;
//        this.filmWriter = filmWriter;
//        this.filmActors = filmActors;
//        this.filmCountry = filmCountry;
//        this.filmLanguage = filmLanguage;
//        this.filmProduction = filmProduction;
//        this.filmRelease = filmRelease;
//        this.filmRuntime = filmRuntime;
//        this.filmAwards = filmAwards;
//        this.filmRating = filmRating;
//        this.filmPlot = filmPlot;
        this.filmType = filmType;
        this.filmPoster = filmPoster;
    }

    public Film() {

    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmYear() {
        return filmYear;
    }

//    public String getFilmGenre() {
//        return filmGenre;
//    }
//
//    public String getFilmDirector() {
//        return filmDirector;
//    }
//
//    public String getFilmWriter() {
//        return filmWriter;
//    }
//
//    public String getFilmActors() {
//        return filmActors;
//    }
//
//    public String getFilmCountry() {
//        return filmCountry;
//    }
//
//    public String getFilmLanguage() {
//        return filmLanguage;
//    }
//
//    public String getFilmProduction() {
//        return filmProduction;
//    }
//
//    public String getFilmRelease() {
//        return filmRelease;
//    }
//
//    public String getFilmRuntime() {
//        return filmRuntime;
//    }
//
//    public String getFilmAwards() {
//        return filmAwards;
//    }
//
//    public String getFilmRating() {
//        return filmRating;
//    }
//
//    public String getFilmPlot() {
//        return filmPlot;
//    }
//
    public String getFilmType() {
        return filmType;
    }
//
    public String getFilmPoster() {
        return filmPoster;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmYear(String filmYear) {
        this.filmYear = filmYear;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public void setFilmPoster(String filmPoster) {
        this.filmPoster = filmPoster;
    }
}
