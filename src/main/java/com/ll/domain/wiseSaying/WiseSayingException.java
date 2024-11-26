package com.ll.domain.wiseSaying;

class WiseSayingException extends RuntimeException {
    WiseSayingException(String msg) {
        super(msg);
    }
    WiseSayingException(Exception ex) {
        super(ex);
    }
}