package com.bookmyshow.backend.config;

public class Constants {
    public static class SeatStatus {
        public static final String AVAILABLE = "available";
        public static final String UNAVAILABLE = "unavailable";
        public static final String RESERVED = "reserved";
    }

    public static class VenueType {
        public static final String CINEMA = "cinema";
        public static final String STADIUM = "stadium";
        public static final String THEATRE = "theatre";
    }

    public static class BookingStatus {
        public static final String CONFIRMED = "confirmed";
        public static final String CANCELLED = "cancelled";
        public static final String PENDING = "pending";
    }

    public static class PaymentStatus {
        public static final String SUCCESS = "success";
        public static final String FAILED = "failed";
    }

    public static class Languages {
        public static final String English = "english";
        public static final String Hindi = "hindi";
    }

    public static class Genre {
        public static final String DRAMA = "drama";
        public static final String ACTION = "action";
        public static final String ROMANCE = "romance";
        public static final String COMEDY = "comedy";
        public static final String DOCUMENTARY = "documentary";
    }

    public static class Certificate {
        public static final String U = "U";
        public static final String UA = "UA";
        public static final String A = "A";
    }

}
