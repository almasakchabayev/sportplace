package com.epam.aa.sportplace.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Action> actions;

    public static Action getAction(HttpServletRequest request) {
        if (actions == null) initActionFactory();
        return actions.get(request.getMethod() + request.getRequestURI());
    }

    private static void initActionFactory() {
        actions = new HashMap<>();
        actions.put("POST/customer-register", new CustomerRegisterPostAction());
        actions.put("GET/customer-register", new Action() {
            @Override
            public String execute(HttpServletRequest request, HttpServletResponse response) {
                return "/customer-register";
            }
        });
    }
}
