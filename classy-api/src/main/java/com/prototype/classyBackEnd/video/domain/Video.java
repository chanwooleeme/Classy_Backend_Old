package com.prototype.classyBackEnd.video.domain;

import com.prototype.classyBackEnd.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Entity
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column
    private String title;

    @Column(nullable = false, name = "s3_title")
    private String s3Title;

    @Column(name = "video_url")
    private String videoUrl;

    @Setter
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(nullable = false, name = "upload_date_time")
    private LocalDateTime uploadDateTime;

    @Column(nullable = false, name = "last_modified_time")
    private LocalDateTime lastModifiedTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Long views;

    @Column
    private Long videoDuration;

    protected Video() {}

    public static Video of(String title, String s3Title, String videoUrl, String thumbnailUrl, Member member) {
        Video video = new Video();
        video.title = title;
        video.s3Title = s3Title;
        video.videoUrl = videoUrl;
        video.thumbnailUrl = thumbnailUrl;
        video.member = member;
        video.views = 0L;
        video.uploadDateTime = LocalDateTime.now();
        video.lastModifiedTime = LocalDateTime.now();
        return video;
    }

    public static Video of(String title, String s3Title, String videoUrl, String thumbnailUrl, Long videoDuration, Member member) {
        Video video = new Video();
        video.title = title;
        video.s3Title = s3Title;
        video.videoUrl = videoUrl;
        video.thumbnailUrl = thumbnailUrl;
        video.member = member;
        video.views = 0L;
        video.videoDuration = videoDuration;
        video.uploadDateTime = LocalDateTime.now();
        video.lastModifiedTime = LocalDateTime.now();
        return video;
    }

    public void updateLastUpdateTime() {
        this.lastModifiedTime = LocalDateTime.now();
    }

    public String getUploadedTime() {
        Long yearsDiff = ChronoUnit.YEARS.between(uploadDateTime, LocalDateTime.now());
        Long monthsDiff = ChronoUnit.MONTHS.between(uploadDateTime, LocalDateTime.now());
        Long daysDiff = ChronoUnit.DAYS.between(uploadDateTime, LocalDateTime.now());
        Long hoursDiff = ChronoUnit.HOURS.between(uploadDateTime, LocalDateTime.now());
        Long minutesDiff = ChronoUnit.MINUTES.between(uploadDateTime, LocalDateTime.now());
        StringBuilder diff = new StringBuilder();

        if(yearsDiff != 0)
            diff.append(yearsDiff).append("년");
        else {
            if (monthsDiff != 0)
                diff.append(monthsDiff).append("개월");
            else {
                if (daysDiff != 0)
                    diff.append(daysDiff).append("일");
                else {
                    if (hoursDiff != 0)
                        diff.append(hoursDiff).append("시간");
                    else
                        diff.append(minutesDiff).append("분");
                }
            }
        }
        return diff.append(" 전").toString();
    }

    public void increaseViews() { this.views++; }
}
