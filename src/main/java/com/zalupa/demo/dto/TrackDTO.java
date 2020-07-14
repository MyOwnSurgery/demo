package com.zalupa.demo.dto;

import javax.validation.constraints.Max;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
@XmlRootElement(name = "track")
public class TrackDTO {
    private int id;
    @XmlTransient
    private int tracklistId;

    private String name;

    private long size;

    private long duration;

    public TrackDTO(int tracklistId, String name, Long valueOf, Long valueOf1) {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
            this.size = size;
    }
    public int getId(){
        return id;
    }
    public int getTracklistId(){
        return tracklistId;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setTracklistId(int tracklistId){
        this.tracklistId = tracklistId;
    }
    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
            this.duration = duration;
    }
    public TrackDTO(String name, Long size, Long duration) {
        this.name = name;

        if (size > 0)           // Проверяем корректность значений по заданию
            this.size = size;
        if (duration > 0)
            this.duration = duration;
    }
    public TrackDTO(){

    }

    public TrackDTO(int id, int tracklistId, String name, long size, long duration) {
        this.tracklistId = tracklistId;
        this.id = id;
        this.name = name;
        this.size = size;
        this.duration = duration;
    }
}
