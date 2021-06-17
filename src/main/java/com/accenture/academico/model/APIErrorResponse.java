package com.accenture.academico.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIErrorResponse {

    private HttpStatus status;
    private String error_code;
    private String message;
    private String detail;
    private LocalDateTime timeStamp;

    // getter and setters
    //Builder
    public static final class APIErrorResponseBuilder {
        private HttpStatus status;
        private String error_code;
        private String message;
        private String detail;
        private LocalDateTime timeStamp;

        public APIErrorResponseBuilder() {
        }

        public static APIErrorResponseBuilder anAPIErrorResponse() {
            return new APIErrorResponseBuilder();
        }

        public APIErrorResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public APIErrorResponseBuilder withError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public APIErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public APIErrorResponseBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public APIErrorResponseBuilder atTime(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }
        public APIErrorResponse build() {
            APIErrorResponse apiErrorResponse = new APIErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.error_code = this.error_code;
            apiErrorResponse.detail = this.detail;
            apiErrorResponse.message = this.message;
            apiErrorResponse.timeStamp = this.timeStamp;
            return apiErrorResponse;
        }
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}