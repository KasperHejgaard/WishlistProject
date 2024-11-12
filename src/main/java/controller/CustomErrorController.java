package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class CustomErrorController implements ErrorController {

        @GetMapping("/error")
        public String handleError(HttpServletRequest request) {
            String errorPage = "error"; // default

            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

            if (status != null) {
                Integer statusCode = Integer.valueOf(status.toString());

                if (statusCode == HttpStatus.NOT_FOUND.value()) {
                    // handle HTTP 404 Not Found error
                    errorPage = "error/404";

                } else {
                    errorPage = "error";
                }
            }

            return errorPage;
        }

        public String getErrorPath() {
            return "/error";
        }

    }
