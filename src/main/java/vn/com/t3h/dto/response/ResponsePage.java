package vn.com.t3h.dto.response;

import lombok.Data;
import vn.com.t3h.dto.ClaimDTO;

import java.util.List;

@Data
public class ResponsePage<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;

//    private List<ClaimDTO> content;
    private T content;

}
