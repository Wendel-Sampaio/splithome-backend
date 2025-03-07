package com.splithome.application.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestGlobalErrorMessage {
    private HttpStatus status;
    private String message;
}
