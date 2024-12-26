package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.request.ClaimRequestFilter;
import vn.com.t3h.dto.response.ResponsePage;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.mapper.ClaimMapper;
import vn.com.t3h.repository.ClaimRepository;
import vn.com.t3h.service.IClaimService;
import vn.com.t3h.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimServiceImpl implements IClaimService {

    // Khai báo claim repository
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimMapper claimMapper;

    public List findAllWithSort(){
        List<ClaimEntity> claims = claimRepository.findAll(Sort.by("amount").descending());
        return claims;
    }

    // pageSize, pageIndex là thông số truyền xuống từ controller
    public Page getAllWithPage(int pageIndex, int pageSize){
        // tạo ra pageable kèm pageSize, pageIndex, và cách sắp xếp
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("claimDate").descending());
        Page<ClaimEntity> claims = claimRepository.findAllByAmount(1000.0, pageable);
        return claims;
    }

    @Override
    public ResponsePage<List<ClaimDTO>> getAllClaim(ClaimRequestFilter filter,Pageable pageable) {

        // Viết hàm xử lý tại đây
        // convert string data to LocalDate
        filter.setFromDateQuery(DateUtils.strToDate(filter.getFromDate()));
        filter.setToDateQuery(DateUtils.strToDate(filter.getToDate()));

        // query data base
        Page<ClaimEntity> page = claimRepository.findByFilter(filter,pageable);

        List<ClaimEntity> claimEntities = page.getContent();

        // convert entity to dto
        List<ClaimDTO> claimDTOS = claimMapper.toDtos(claimEntities);

        // set data to response
        ResponsePage<List<ClaimDTO>> responsePage = new ResponsePage<>();
        responsePage.setContent(claimDTOS);
        responsePage.setPageNumber(pageable.getPageNumber());
        responsePage.setPageSize(pageable.getPageSize());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setTotalElements(page.getTotalElements());
        return responsePage;
    }
}
