package com.fernando.zssn.presentation;

import com.fernando.zssn.presentation.contract.IViewModel;
import com.fernando.zssn.presentation.JsonFormatHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpRequestWrapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewModel implements IViewModel {

    private Object data;
    private HttpStatus httpStatus;

    @Override
    public ResponseEntity<JsonFormatHandler> getResponse() {

        return new ResponseEntity<>(new JsonFormatHandler(this.getData(), this.getHttpStatus().value()), this.getHttpStatus());
    }

}
