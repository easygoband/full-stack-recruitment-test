package com.fernando.zssn.presentation;

import com.fernando.zssn.presentation.contract.IViewModel;
import org.springframework.http.HttpStatus;

public class JsonPresenter {
    public IViewModel successResponse() {
        return new ViewModel();
    }

    public IViewModel createdResponse(Object data) {
        return new ViewModel(
                data,
                HttpStatus.CREATED
        );
    }

    public IViewModel notFoundResponse() {
        return new ViewModel();
    }

    public IViewModel serverErrorResponse() {
        return new ViewModel();
    }

    public IViewModel badRequestResponse() {
        return new ViewModel();
    }
}
