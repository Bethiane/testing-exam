package rw.ac.rca.termOneExam.utils;

import org.springframework.http.HttpStatus;

public class CustomExceptions extends RuntimeException {

        private HttpStatus httpStatus;

        public CustomExceptions(String message, HttpStatus httpStatus) {
            super(message);
            this.httpStatus = httpStatus;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }
    }

