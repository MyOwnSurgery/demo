package com.zalupa.demo.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Scanner;

@XmlRootElement(name = "track")
@Entity
@Table(name = "track")
@SqlResultSetMapping(
        name = "track",
        entities = @EntityResult(entityClass = Track.class)
)

public class Track implements Serializable {
    @Column(name = "track_track_id")
    private int trackId;
    @Id
    @Column(name = "track_track_name")
    private String name;
    @Column(name = "track_track_size")
    private Long size;
    @Column(name = "track_track_duration")
    private Long duration;


    public Track(String name, Long size, Long duration) {
        this.name = name;

        if (size > 0)           // Проверяем корректность значений по заданию
            this.size = size;
        if (duration > 0)
            this.duration = duration;
    }
    public Track(){

    }

    public Track(int id, String name, long size, long duration) {
        this.trackId = id;
        this.name = name;
        this.size = size;
        this.duration = duration;
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

        if (size > 0){
            this.size = size;
        }
        else{
            System.out.println("Invalid size");
            System.exit(0);
        }

    }

    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
        if (duration > 0){
            this.duration = duration;
        }
        else{
            System.out.println("Invalid duration");
            System.exit(0);
        }

    }

    public static void save(Track obj, String name) throws IOException {
        File file = new File(name);
        file.createNewFile();
        FileWriter out = new FileWriter(file);
        out.write(obj.toString());
        out.flush();
        out.close();
    }

    public static Track load(String name) throws IOException{
        Scanner in = new Scanner(name);
        String n = in.next();
        long p = in.nextInt();
        long c = in.nextInt();
        return new Track(n, p, c);
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                '}';
    }


}
