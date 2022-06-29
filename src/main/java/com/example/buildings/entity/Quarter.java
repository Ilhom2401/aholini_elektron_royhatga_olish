package com.example.buildings.entity;

import com.example.buildings.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Quarter extends BaseEntity {
    private int code;
    private String name;

    @ManyToOne
    private District district;
}
