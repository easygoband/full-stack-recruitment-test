package com.fernando.zssn.presentation;

import com.fernando.zssn.presentation.contract.IViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewModel implements IViewModel {

    private Object data;
    private HttpStatus httpStatus;
    private String message;
    private Long totalResults;

    @Override
    public ResponseEntity<JsonFormatHandler> getResponse() {

        return new ResponseEntity<>(new JsonFormatHandler(this.getData(), this.getHttpStatus().value(), this.message, this.totalResults), this.getHttpStatus());
    }

}
