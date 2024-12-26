package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "claim_document")
@Getter
@Setter
public class ClaimDocumentEntity extends BaseEntity {

    private String documentName;
    private String documentType;
    private String filePath;
    private LocalDate uploadDate;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private ClaimEntity claim;

}
