package com.fernando.zssn.presentation.contract;

import org.springframework.http.ResponseEntity;
import com.fernando.zssn.presentation.JsonFormatHandler;

public interface IViewModel {
    ResponseEntity<JsonFormatHandler> getResponse();
}
