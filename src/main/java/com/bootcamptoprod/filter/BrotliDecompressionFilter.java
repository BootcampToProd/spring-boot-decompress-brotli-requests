package com.bootcamptoprod.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(1)
@Component
public class BrotliDecompressionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String contentEncoding = request.getHeader("Content-Encoding");

        if (contentEncoding != null && contentEncoding.toLowerCase().contains("br")) {
            HttpServletRequest wrappedRequest = new BrotliWrappedRequest(request);
            filterChain.doFilter(wrappedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
