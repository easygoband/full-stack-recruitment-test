package com.fernando.zssn.presentation.contract;

import com.fernando.zssn.presentation.ViewModel;
import org.springframework.http.ResponseEntity;
import com.fernando.zssn.presentation.JsonFormatHandler;

public interface IViewModel {
    ResponseEntity<JsonFormatHandler> getResponse();
}
