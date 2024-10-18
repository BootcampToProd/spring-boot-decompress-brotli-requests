package com.bootcamptoprod.filter;

import com.aayushatharva.brotli4j.decoder.Decoder;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;

class BrotliWrappedRequest extends HttpServletRequestWrapper {
    private final byte[] decompressedBody;

    public BrotliWrappedRequest(HttpServletRequest request) throws IOException {
        super(request);
        decompressedBody = Decoder.decompress(request.getInputStream().readAllBytes())
                .getDecompressedData();
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {
            private final ByteArrayInputStream inputStream = new ByteArrayInputStream(decompressedBody);

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new UnsupportedOperationException();
            }
        };
    }
}
