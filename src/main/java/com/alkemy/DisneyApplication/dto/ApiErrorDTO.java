
package com.alkemy.DisneyApplication.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorDTO {
    
    private String message;
    private List<String> details;
    private HttpStatus status;
    private Date timestamp;
    
    
    
}
