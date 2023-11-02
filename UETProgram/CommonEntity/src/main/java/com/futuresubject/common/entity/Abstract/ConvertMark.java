package com.futuresubject.common.entity.Abstract;

public abstract class ConvertMark {

    public static Double MarkToGPA (Double mark) {
        double t;
        if ( Double.compare(mark, 4d) < 0 ) {
            t = 0d;
        } else if (Double.compare(mark, 5d) < 0) {
            t = 1.0d;

        } else if (Double.compare(mark,5.5d) < 0) {
            t =1.5d;

        } else if (Double.compare(mark,6.5d) < 0) {
            t = 2.0d;

        } else if (Double.compare(mark,7.0d) < 0) {
            t=2.5d;

        } else if (Double.compare(mark,8.0d) < 0) {
            t =3.0d;

        } else if (Double.compare(mark,8.5d) < 0) {
            t=3.5d;

        } else if (Double.compare(mark,9.0d) < 0) {
            t=3.7d;
        } else {
            t=4.0d;
        }
        return t;
    };
    public static String MarkToLetterGPA (Double mark) {
        String t=null;
        if ( Double.compare(mark, 4d) < 0 ) {
            t = "F";
        } else if (Double.compare(mark, 5d) < 0) {
            t = "D";

        } else if (Double.compare(mark,5.5d) < 0) {
            t = "D+";

        } else if (Double.compare(mark,6.5d) < 0) {
            t = "C";

        } else if (Double.compare(mark,7.0d) < 0) {
            t="C+";

        } else if (Double.compare(mark,8.0d) < 0) {
            t ="B";

        } else if (Double.compare(mark,8.5d) < 0) {
            t="B+";

        } else if (Double.compare(mark,9.0d) < 0) {
            t="A";
        } else {
            t="A+";
        }
        return t;
    };
}
