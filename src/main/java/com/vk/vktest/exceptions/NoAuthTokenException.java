package com.vk.vktest.exceptions;

import javax.naming.AuthenticationException;

public class NoAuthTokenException extends AuthenticationException {
    public NoAuthTokenException(String msg) {
        super(msg);
    }
}
