package com.uygunaldim.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REFRESH_TOKEN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class RefreshToken {
    @Id
    @SequenceGenerator(name = "seqProductId", sequenceName = "seq_product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductId")
    private Long id;
    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;
    @Column(name = "EXPIRY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}

