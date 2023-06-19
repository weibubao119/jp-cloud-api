package com.jp.configure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : xh
 * @date : Created in 2023/1/16 15:42
 */
@Data
@Entity
public class StorageTbl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String commodityCode;

    private Integer count;
}
