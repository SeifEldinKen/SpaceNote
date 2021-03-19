package com.example.spacenotev2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int postId;
    private String Title;
    private String subTitle;
    private String noteBody;
    private String dataTime;
    private String imagePath;
    private String color;
    private String webLink;

    // constructor methods
    public Note(String title, String subTitle, String noteBody, String dataTime, String imagePath, String color, String webLink) {
        Title = title;
        this.subTitle = subTitle;
        this.noteBody = noteBody;
        this.dataTime = dataTime;
        this.imagePath = imagePath;
        this.color = color;
        this.webLink = webLink;
    }

    public Note() {

    }

    // setter methods
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }


    // getter methods
    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getColor() {
        return color;
    }

    public String getWebLink() {
        return webLink;
    }
}
