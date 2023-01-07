package com.uygunaldim.entity;

import com.uygunaldim.entity.enums.OperationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT_LOG")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductLog {
    @Id
    @SequenceGenerator(name = "seqProductLogId", sequenceName = "seq_product_log_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductLogId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_log_id", referencedColumnName = "id")
    private Product product;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "OPERATION")
    private OperationEnum operation;
}
