package com.softuni.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Part not found!")
    public class PartNotFoundException extends RuntimeException {
        private int statusCode;

        public PartNotFoundException() {
            this.statusCode = 404;
        }

        public PartNotFoundException(String message) {
            super(message);
            this.statusCode = 404;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

