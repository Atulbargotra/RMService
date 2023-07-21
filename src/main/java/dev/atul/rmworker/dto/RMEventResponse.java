package dev.atul.rmworker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RMEventResponse {
    private String requestId;
    private String status;
    private String errorMessage;
}
