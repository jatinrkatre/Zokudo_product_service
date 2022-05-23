package com.cards.zokudo.config;

import com.cards.zokudo.exceptions.ForbiddenException;
import com.cards.zokudo.exceptions.UnAuthorizedException;
import com.cards.zokudo.services.UserService;
import com.cards.zokudo.util.UrlMetaData;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;
    private final Client client;
    private final UrlMetaData urlMetaData;


    @Autowired
    public AuthInterceptor(UserService userService, @Qualifier(value = "client") Client client, UrlMetaData urlMetaData) {
        this.userService = userService;
        this.client = client;
        this.urlMetaData = urlMetaData;
    }


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String[] urls = request.getRequestURI().split("/");

        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        MultivaluedMap<String, Object> headerMap = new MultivaluedHashMap<>();
        headerMap.add("program_url", urls[2]);
        headerMap.add("request_url", urls[urls.length - 1]);
        headerMap.add("Authorization", request.getHeader("Authorization"));
        headerMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerMap.add("ipaddress" , remoteAddr);

        Response clientResponse = client.target(urlMetaData.AUTHENTICATE_AND_AUTHORIZE_USER)
                .request()
                .headers(headerMap)
                .get();
        if (clientResponse.getStatus() != 200) {
            String stringResponse = clientResponse.readEntity(String.class);
            JSONObject jsonResponse = new JSONObject(stringResponse);
            String errorMessage = jsonResponse.getString("message") != null ? jsonResponse.getString("message") : "";
            if (clientResponse.getStatus() == 401) new ForbiddenException(errorMessage);
            throw new UnAuthorizedException(errorMessage);
//            return true;
        }
        return true;
    }

    private void authenticateAndAuthorizeUser(HttpServletRequest request) {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("post handle method calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        System.out.println("after completion");
    }



}
