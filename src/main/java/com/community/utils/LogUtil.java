package com.community.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogUtil {
    public static final Logger log = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String format, Object... param) {
        log.info(format, param);
    }

    public static void error(String format, Object... param) {
        log.error(format, param);
    }

    public static void warn(String format, Object... param) {
        log.warn(format, param);
    }

    public static void debug(String format, Object... param) {
        log.debug(format, param);
    }

    public static void trace(String format, Object... param) {
        log.trace(format, param);
    }
}
