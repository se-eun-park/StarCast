package com.mobyeoldol.starcast.business.application;

import com.mobyeoldol.starcast.business.presentation.request.MainRequest;
import com.mobyeoldol.starcast.business.presentation.response.MainResponse;

public interface BusinessService {
    MainResponse getMain(String profileUid, MainRequest request);
}
