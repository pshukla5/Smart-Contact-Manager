package com.scm.scm2_0.Helper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    @Autowired
    HttpSession session;

    public void removeMessage() {

        try {

            // HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");

        } catch (Exception e) {

            System.out.println("Error in removing message from session: " + e);
        }
    }
}
