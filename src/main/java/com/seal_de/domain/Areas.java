package com.seal_de.domain;

import javax.persistence.*;

@Entity
@Table(name = "areas")
public class Areas {
    private Integer id;
    private String areaId;
    private String area;
    private String cityId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "area_id", nullable = false, length = 20)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "area", nullable = false, length = 50)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "city_id", nullable = false, length = 20)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Areas areas = (Areas) o;

        if (id != areas.id) return false;
        if (areaId != null ? !areaId.equals(areas.areaId) : areas.areaId != null) return false;
        if (area != null ? !area.equals(areas.area) : areas.area != null) return false;
        if (cityId != null ? !cityId.equals(areas.cityId) : areas.cityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }
}
