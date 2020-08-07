package com.softuni.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "You can not change your own role!")
    public class ChangeYourOwnRoleException extends RuntimeException {
        private int statusCode;

        public ChangeYourOwnRoleException() {
            this.statusCode = 409;
        }

        public ChangeYourOwnRoleException(String message) {
            super(message);
            this.statusCode = 404;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

