package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.request.ClaimRequestFilter;
import vn.com.t3h.dto.response.ResponsePage;

import java.util.List;

public interface IClaimService {

    public ResponsePage<List<ClaimDTO>> getAllClaim(ClaimRequestFilter filter, Pageable pageable);
}
