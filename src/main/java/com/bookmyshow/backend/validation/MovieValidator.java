package com.bookmyshow.backend.validation;

import com.bookmyshow.backend.config.Constants;
import com.bookmyshow.backend.model.Movie;
import com.bookmyshow.backend.utility.BackendUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieValidator {
    public static final List<String> langList = Arrays.asList(Constants.Languages.Hindi, Constants.Languages.English);
    public static final List<String> genreList = Arrays.asList(Constants.Genre.DRAMA, Constants.Genre.ACTION, Constants.Genre.COMEDY, Constants.Genre.DOCUMENTARY, Constants.Genre.ROMANCE);
    public static final List<String> certificateList = Arrays.asList(Constants.Certificate.A, Constants.Certificate.U, Constants.Certificate.UA);

    public static List<String> validateMovie(Movie movie) {
        List<String> result = new ArrayList<>();
        boolean nameValid = validateMovieName(movie.getName());
        if(!nameValid) {
            result.add("invalid name");
        }
        boolean durationValid = validateMovieDuration(movie.getDuration());
        if(!durationValid) {
            result.add("invalid duration");
        }
        boolean directorValid = validateDirector(movie.getDirector());
        if(!directorValid) {
            result.add("invalid director");
        }
        boolean actorsValid = validateActors(movie.getActors());
        if(!actorsValid) {
            result.add("invalid actors");
        }
        boolean summaryValid = validateSummary(movie.getSummary());
        if(!summaryValid) {
            result.add("invalid summary");
        }
        boolean langValid = validateLang(movie.getLang());
        if(!langValid) {
            result.add("invalid lang");
        }
        boolean genreValid = validateGenre(movie.getGenre());
        if(!genreValid) {
            result.add("invalid genre");
        }
        boolean certificateValid = validateCertificate(movie.getCertificate());
        if(!certificateValid) {
            result.add("invalid certificate");
        }
        return result;
    }

    private static boolean validateCertificate(String certificate) {
        if(!BackendUtil.isEmpty(certificate) && certificate.length()<=12
                && (certificateList.contains(certificate.toUpperCase()))) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateGenre(String genre) {
        if(!BackendUtil.isEmpty(genre) && genre.length()<=255
                && (genreList.contains(genre.toLowerCase()))) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean validateLang(String lang) {
        if(!BackendUtil.isEmpty(lang) && lang.length()<=255
                && (langList.contains(lang.toLowerCase()))) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateSummary(String summary) {
        if(!BackendUtil.isEmpty(summary) && summary.length()<=255) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateActors(String actors) {
        if(!BackendUtil.isEmpty(actors) && actors.length()<=255) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateDirector(String director) {
        if(!BackendUtil.isEmpty(director) && director.length()<=255) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateMovieDuration(String duration) {
        if(!BackendUtil.isEmpty(duration) && Integer.parseInt(duration)<=99999) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateMovieName(String name) {
        if(!BackendUtil.isEmpty(name) && name.length()<=255) {
            return true;
        } else {
            return false;
        }
    }
}
