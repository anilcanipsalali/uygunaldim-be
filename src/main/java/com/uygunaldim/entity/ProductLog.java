package com.uygunaldim.entity;

import com.uygunaldim.entity.enums.OperationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "VENDOR")
    private String vendor;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "LOGO")
    private String logo;
    @Column(name = "MARKET")
    private String market;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "OPERATION")
    private OperationEnum operation;
}
