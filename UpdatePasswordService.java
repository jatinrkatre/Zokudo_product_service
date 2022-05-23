package com.cards.zokudo.services;

import com.cards.zokudo.request.UpdatePasswordRequest;
import com.cards.zokudo.response.ApiResponse;

public interface UpdatePasswordService {

    ApiResponse<Object> execute(UpdatePasswordRequest updatePasswordRequest,String programUrl);
}
