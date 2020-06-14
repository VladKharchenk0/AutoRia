package com.gmail.kharchenko55.vlad.model.search;

import com.gmail.kharchenko55.vlad.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "search_history")
@Component
public @Data
class SearchHistory extends BaseEntity {
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "car_body")
    private int carBody;

    @Column(name = "car_brand")
    private int carBrand;

    @Column(name = "car_model")
    private int carModel;
}
