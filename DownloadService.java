package com.cards.zokudo.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface DownloadService {

    void downloadProgramList(HttpServletRequest request, HttpServletResponse response, String programUrl, Map<String, String> requestParams);

    void downloadClientList(HttpServletRequest request, HttpServletResponse response, String programUrl, Map<String, String> requestParams);
}
