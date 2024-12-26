package vn.com.t3h.dto.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserResqestFiter {
    private String UserName;
    private String Code;
    private String fromDate;
    private String toDate;
    private LocalDate fromDateQuery;
    private LocalDate toDateQuery;
}
