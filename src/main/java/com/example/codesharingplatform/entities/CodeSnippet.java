package com.example.codesharingplatform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonIgnoreProperties({"uuid"})
@Entity
public class CodeSnippet {
    @Id
    @NotNull
    private String uuid;
    @NotNull
    private String code;
    @NotNull
    private String date;
    @NotNull
    private int time;
    @NotNull
    private int views;

    @JsonIgnore
    private boolean timeRestricted;
    @JsonIgnore
    private boolean viewsRestricted;

    public CodeSnippet() {
    }

    public CodeSnippet(String code, int time, int views) {
        this.uuid = UUID.randomUUID().toString();
        this.code = code;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
                .withZone(ZoneId.systemDefault());
        Instant now = Instant.now();
        String date = dateTimeFormatter.format(now);
        this.date = date;
        this.time = time;
        this.views = views;
        this.timeRestricted = time > 0;
        this.viewsRestricted = views > 0;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime() {
        this.time -= 100;
    }

    public int getViews() {
        return views;
    }

    public void setViews() {
        this.views -= 1;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }
}
