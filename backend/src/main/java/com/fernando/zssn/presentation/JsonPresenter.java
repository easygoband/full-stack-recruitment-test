package com.fernando.zssn.presentation;

import com.fernando.zssn.presentation.contract.IViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JsonPresenter {
    public IViewModel successResponse(String message) {
        return new ViewModel(
                null,
                HttpStatus.OK,
                message,
                null
        );
    }

    public IViewModel fetchResourceResponse(Object data) {
        return new ViewModel(
            data,
            HttpStatus.OK,
            "",
            null
        );
    }

    public IViewModel collectionResponse(Object data, Long totalResults) {
        return new ViewModel(
                data,
                HttpStatus.OK,
                "",
                totalResults
        );
    }

    public IViewModel createdResponse(Object data) {
        return new ViewModel(
                data,
                HttpStatus.CREATED,
                "",
                null
        );
    }

    public IViewModel notFoundResponse(String message) {
        return new ViewModel(
                null,
                HttpStatus.NOT_FOUND,
                message,
                null
        );
    }

    public IViewModel serverErrorResponse() {
        return new ViewModel();
    }

    public IViewModel badRequestResponse() {
        return new ViewModel();
    }
}
