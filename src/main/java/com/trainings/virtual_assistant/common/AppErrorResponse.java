package com.trainings.virtual_assistant.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AppErrorResponse  {

    private String errorMessage;
    private Integer statusCode;


}
