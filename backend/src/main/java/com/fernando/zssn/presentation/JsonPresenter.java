package com.fernando.zssn.presentation;

import com.fernando.zssn.presentation.contract.IViewModel;
import org.springframework.http.HttpStatus;

public class JsonPresenter {
    public IViewModel successResponse(String message) {
        return new ViewModel(
                null,
                HttpStatus.OK,
                message
        );
    }

    public IViewModel createdResponse(Object data) {
        return new ViewModel(
                data,
                HttpStatus.CREATED,
                ""
        );
    }

    public IViewModel notFoundResponse(String message) {
        return new ViewModel(
                null,
                HttpStatus.NOT_FOUND,
                message
        );
    }

    public IViewModel serverErrorResponse() {
        return new ViewModel();
    }

    public IViewModel badRequestResponse() {
        return new ViewModel();
    }
}
