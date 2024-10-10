package com.mobyeoldol.starcast.notice.application;

import com.mobyeoldol.starcast.notice.presentation.response.NoticeResponse;

public interface NoticeService {
    public NoticeResponse changeNotice(String profileUid, boolean agree);
}
