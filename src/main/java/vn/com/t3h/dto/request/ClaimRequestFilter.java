package vn.com.t3h.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClaimRequestFilter {

    private String claimCode;
    private String fromDate;
    private String toDate;
    private String statusCode;

    private LocalDate fromDateQuery;
    private LocalDate toDateQuery;


}
