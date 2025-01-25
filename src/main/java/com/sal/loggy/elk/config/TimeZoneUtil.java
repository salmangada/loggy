package com.sal.loggy.elk.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.TimeZone;

@Slf4j
@Component
public class TimeZoneUtil {

    private static final String TIMEZONE_HEADER = "x-timezone";

    public String getTimeZoneFromRequest(HttpServletRequest request) {
        return request.getHeader(TIMEZONE_HEADER);
    }

    public void setTimeZoneForRequest(HttpServletRequest request) {
        String timezone = getTimeZoneFromRequest(request);
        if (StringUtils.hasText(timezone)) {
            setTimezone(timezone);
        }
    }

    private void setTimezone(String timezoneString) {
        try {
            TimeZone timeZone = TimeZone.getTimeZone(timezoneString);
            LocaleContextHolder.setTimeZone(timeZone);
        } catch (Exception e) {
            log.error("Exception occurred while setting timezone for request", e);
        }
    }

}
