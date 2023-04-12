package io.github.bankapi.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class TransactionFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter: ", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Starting Transaction :{} {}", req.getMethod(), req.getRequestURI());
        chain.doFilter(request, response);
        log.info("Committing Transaction for req :{} {}", req.getMethod(), req.getRequestURI());

    }

    @Override
    public void destroy() {
        log.warn("Destructing filter :{}", this);
    }
}
