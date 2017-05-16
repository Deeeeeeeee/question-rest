package com.seal_de.domain;

import javax.persistence.*;

@Entity
@Table(name = "provinces")
public class Provinces {
    private Integer id;
    private String provinceId;
    private String province;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "province_id", nullable = false, length = 20)
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "province", nullable = false, length = 50)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provinces provinces = (Provinces) o;

        if (id != provinces.id) return false;
        if (provinceId != null ? !provinceId.equals(provinces.provinceId) : provinces.provinceId != null) return false;
        if (province != null ? !province.equals(provinces.province) : provinces.province != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        return result;
    }
}
